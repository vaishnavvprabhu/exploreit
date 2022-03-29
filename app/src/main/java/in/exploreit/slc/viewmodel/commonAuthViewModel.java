package in.exploreit.slc.viewmodel;/*
package in.exploreit.slc.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;

import in.exploreit.slc.repository.commonAuthRepository;

public class commonAuthViewModel extends AndroidViewModel {

    private commonAuthRepository repository;
    private MutableLiveData<FirebaseUser> userData;
    private MutableLiveData<Boolean> loggedStatus;
    public commonAuthViewModel(@NonNull Application application) {
        super(application);

        //Getting context from commonAuthRepository
        repository = new commonAuthRepository(application);
        userData = repository.getFirebaseUserMutableLiveData();
        loggedStatus = repository.getUserLoggedMutableLiveData();
    }

    public void register(String mnum){
        repository.register(mnum);
    }

    public void signIn(String mnum){
        PhoneAuthCredential credential = null;
        repository.signInWithPhoneAuthCredential(credential);
    }
}
*/
