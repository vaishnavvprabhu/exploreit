package in.exploreit.slc.fragments;

import static in.exploreit.slc.utils.Utils.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import in.exploreit.slc.MainActivity;
import in.exploreit.slc.R;
import in.exploreit.slc.data.enums.AuthStatus;
import in.exploreit.slc.utils.Utils;
import in.exploreit.slc.viewmodel.SharedViewModel;

public class OTPFragment extends Fragment {

    private FirebaseAuth mAuth;
    private String verifyId;
    private String number;
    private PhoneAuthProvider.ForceResendingToken resendToken;

    private SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide(); //Hide Action Bar

        verifyId = OTPFragmentArgs.fromBundle(getArguments()).getVerifyId();
        number = OTPFragmentArgs.fromBundle(getArguments()).getNumber();
        resendToken = OTPFragmentArgs.fromBundle(getArguments()).getResendToken();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        return inflater.inflate(R.layout.fragment_otp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((TextView) view.findViewById(R.id.number)).setText(number);

        mAuth = FirebaseAuth.getInstance();
        sharedViewModel.hasAuthSucceeded.observe(getViewLifecycleOwner(), authStatus -> {
            switch (authStatus) {
                case SUCCESS: {
                    navigateToHome();
                    Log.d(TAG, "live data changed to success");
                    break;
                }
                case FAILURE: showErrorMessage();
            }
        });
        view.findViewById(R.id.login_btn).setOnClickListener(
                textView -> {
                    EditText OTPEditText = view.findViewById(R.id.otp_field);
                    String otp = OTPEditText.getText().toString();
                    if(otp.length() != 6){
                        OTPEditText.setError("Enter a valid mobile");
                        OTPEditText.requestFocus();
                        return;
                    }
                    int code = Integer.parseInt(otp);

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifyId, String.valueOf(code));
                    signInWithPhoneAuthCredential(credential);
                });
        view.findViewById(R.id.resend_otp).setOnClickListener(textView -> {
            resendVerificationCode(number, resendToken);
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "going to home from success listener");
                        navigateToHome();
                        Log.d(TAG, "signInWithCredential:success");
//                        FirebaseUser user = task.getResult().getUser();
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    });
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.verifyPhoneNumber(
                PhoneAuthOptions.newBuilder(mAuth)
                        .setActivity(requireActivity())
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setCallbacks(getAuthCallbacks())
                        .setForceResendingToken(token)
                        .build());
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks getAuthCallbacks() {
        return new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:" + credential);
                sharedViewModel.hasAuthSucceeded.postValue(AuthStatus.SUCCESS);
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e);
                sharedViewModel.hasAuthSucceeded.postValue(AuthStatus.FAILURE);
                if(e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    //TODO: Change the error toasts when deploying
                    Toast.makeText(getActivity(), "Invalid request!", Toast.LENGTH_LONG).show();
                } else if(e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    Toast.makeText(getActivity(), "Too Many requests! Please try again in a few hours!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);
                resendToken = token;
            }
        };
    }

    private void showErrorMessage() {
        Utils.snackBar(this.getView(), "Something went wrong! Try again later.");
    }

    void navigateToHome() {
        MainActivity activity = (MainActivity) requireActivity();
        NavController navController = activity.getNavController();
        if (navController != null) {
            navController.navigate(R.id.action_OTPFragment_to_homeFragment);
        }
    }
}