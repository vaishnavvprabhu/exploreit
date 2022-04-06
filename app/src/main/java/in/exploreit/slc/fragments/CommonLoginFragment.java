package in.exploreit.slc.fragments;

import static in.exploreit.slc.utils.Utils.TAG;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import in.exploreit.slc.MainActivity;
import in.exploreit.slc.R;
import in.exploreit.slc.data.enums.AuthStatus;
import in.exploreit.slc.viewmodel.SharedViewModel;

public class CommonLoginFragment extends Fragment {

    private FirebaseAuth mAuth;
    private EditText numberEditText;
    private String number;
    private ProgressDialog progressDialog;
    public PhoneAuthProvider.ForceResendingToken mResendToken;

    private SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide(); //Hide Action Bar
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        cleanBackStack();

        return inflater.inflate(R.layout.fragment_common_login, container, false);
    }

    private void cleanBackStack() {
        MainActivity activity = (MainActivity) requireActivity();
        NavController navController = activity.getNavController();
        if(navController != null) {
            navController.clearBackStack(R.id.commonLoginFragment);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Firebase Related
        mAuth = FirebaseAuth.getInstance();
        //Assignments
        numberEditText = view.findViewById(R.id.mnum);
        view.findViewById(R.id.send_otp).setOnClickListener(
                textView -> {
                    String entered_mobile_number = numberEditText.getText().toString().trim();
                    if(entered_mobile_number.length() != 10){
                        numberEditText.setError("Enter a valid number without the country code!");
                        numberEditText.requestFocus();
                        return;
                    }
                    number = "+91" + entered_mobile_number;
                    sendOTP(number);
                    displayProgressDialog();
                }
        );
    }

    private void sendOTP(String num){
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(num) // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(requireActivity()) // Activity (for callback binding)
                        .setCallbacks(getAuthCallbacks()) // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    public void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        Log.d(TAG, "signInWithPhoneAuthCredential: called");
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");

                        FirebaseUser user = task.getResult().getUser();
                        // Update UI
                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                            Toast.makeText(getActivity(), "The verification code entered is invalid",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void displayProgressDialog() {
        progressDialog = new ProgressDialog(requireContext());
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sit tight we are working on it!");
        progressDialog.show();
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
                mResendToken = token;
                commonLoginFragmentToOTPFragment(verificationId, number);
            }
        };
    }

    public void commonLoginFragmentToOTPFragment(String verificationId, String number) {
        if(progressDialog != null) progressDialog.dismiss();
        Log.d(TAG, "commonLoginFragmentToOTPFragment: going to otp");
        MainActivity activity = (MainActivity) requireActivity();
        NavController navController = activity.getNavController();
        if(navController != null) {
            CommonLoginFragmentDirections.ActionCommonLoginFragmentToOTPFragment action =
                    CommonLoginFragmentDirections.actionCommonLoginFragmentToOTPFragment(number,
                            verificationId, mResendToken);
            navController.navigate(action);
        }
    }
}