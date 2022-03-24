package in.exploreit.slc.fragments;

import static android.content.ContentValues.TAG;

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
import androidx.navigation.NavController;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import in.exploreit.slc.MainActivity;
import in.exploreit.slc.R;


public class OTPFragment extends Fragment {
    private FirebaseAuth mAuth;
    EditText pcode;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Hide Action Bar
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        //Get Value from previous Fragment
        String value = getArguments().getString("number");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView tv = view.findViewById(R.id.number);
        pcode = view.findViewById(R.id.otp_field);
        tv.setText(getArguments().getString("number"));
        //Firebase Related
        mAuth = FirebaseAuth.getInstance();
        view.findViewById(R.id.home_btn).setOnClickListener(
                textView -> {
                    MainActivity activity = (MainActivity) getActivity();
                    NavController navController = activity.getNavController();
                    if(navController != null) {
                        navController.navigate(R.id.action_OTPFragment_to_homeFragment);
                    }
                }
        );
        view.findViewById(R.id.login_btn).setOnClickListener(
                textView -> {
                    String verificationId = getArguments().getString("verifid");
                    int code =  Integer.parseInt(String.valueOf(pcode.getText()));
                   PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, String.valueOf(code));
/*                   System.out.println("OTPFragment:verificationid: "  + verificationId);
                   System.out.println("OTPFragment:code: "  + code);*/
                   signInWithPhoneAuthCredential(credential);
                });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Update UI
                            MainActivity activity = (MainActivity) getActivity();
                            NavController navController = activity.getNavController();
                            if (navController != null) {
                                navController.navigate(R.id.action_OTPFragment_to_homeFragment);}
                                Log.d(TAG, "signInWithCredential:success");
                                FirebaseUser user = task.getResult().getUser();
                            } else {
                                // Sign in failed, display a message and update the UI
                                Log.w(TAG, "signInWithCredential:failure", task.getException());
                                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                    // The verification code entered was invalid
                                }
                            }
                        }
                    });
    }
}