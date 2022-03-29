package in.exploreit.slc.fragments;

import static android.content.ContentValues.TAG;

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
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import in.exploreit.slc.MainActivity;
import in.exploreit.slc.R;


public class CommonLoginFragment extends Fragment {

    private FirebaseAuth mAuth;
    private EditText mnum;
    private EditText mnum_temp;
    private String pnum;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    public PhoneAuthProvider.ForceResendingToken mResendToken;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Hide Action Bar
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_common_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Firebase Related
        mAuth = FirebaseAuth.getInstance();
        //Assignments
        mnum_temp = view.findViewById(R.id.mnum);
        view.findViewById(R.id.send_otp).setOnClickListener(
                textView -> {
                    String mobile = mnum_temp.getText().toString().trim();

                    if(mobile.isEmpty() || mobile.length() < 10){
                        mnum_temp.setError("Enter a valid mobile");
                        mnum_temp.requestFocus();
                        return;
                    }
                    String ernum = String.valueOf(mnum_temp.getText());
                    String pnum = "+91 "+ernum;
                    sendOTP(pnum);
                    Toast.makeText(getActivity(), "You shall be redirected to a webpage for re-CAPTCHA verification. Kindly Do Not Exit.", Toast.LENGTH_LONG).show();
                }
        );

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:" + credential);

                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    //TODO: Change the error toasts when deploying
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }
                // Show a message and update the UI
                Toast.makeText(getActivity(), "error:"+ e,
                        Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                String mVerificationId = verificationId;

                mResendToken = token;

                commonLoginFragmentToOTPFragment(verificationId);
            }
        };
    }



    private void sendOTP(String pnum){
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(pnum)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(getActivity())                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        /*Toast.makeText(getActivity(), "OTP" , Toast.LENGTH_LONG).show();*/


    }

    public void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
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
                    }
                });


    }

    public void commonLoginFragmentToOTPFragment(String mVerificationId){
        Bundle bundle = new Bundle();
        bundle.putString("number", pnum);
        bundle.putString("verifid", mVerificationId);

        MainActivity activity = (MainActivity) getActivity();
        NavController navController = activity.getNavController();
        if(navController != null) {
            navController.navigate(R.id.action_commonLoginFragment_to_OTPFragment, bundle);
        }
    }
}