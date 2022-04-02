package in.exploreit.slc.fragments;

import static in.exploreit.slc.utils.Constants.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import in.exploreit.slc.MainActivity;
import in.exploreit.slc.R;
import in.exploreit.slc.viewmodel.SharedViewModel;

public class OTPFragment extends Fragment {

    private FirebaseAuth mAuth;
    private String verifyId;
    private String number;

    private SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide(); //Hide Action Bar

        verifyId = OTPFragmentArgs.fromBundle(getArguments()).getVerifyId();
        number = OTPFragmentArgs.fromBundle(getArguments()).getNumber();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        return inflater.inflate(R.layout.fragment_otp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((TextView) view.findViewById(R.id.number)).setText(number);

        mAuth = FirebaseAuth.getInstance();
        sharedViewModel.hasAuthSucceeded.observe(getViewLifecycleOwner(), authStatus -> {
            switch (authStatus) {
                case SUCCESS: navigateToHome(); break;
                case FAILURE: showErrorMessage();
            }
        });
        view.findViewById(R.id.login_btn).setOnClickListener(
                textView -> {
                    EditText OTPEditText = ((EditText) view.findViewById(R.id.otp_field));
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
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
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

    private void showErrorMessage() {
        // TODO show snackbar
    }

    void navigateToHome() {
        MainActivity activity = (MainActivity) getActivity();
        NavController navController = activity.getNavController();
        if (navController != null) {
            navController.navigate(R.id.action_OTPFragment_to_homeFragment);
        }
    }
}