package in.exploreit.slc.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import in.exploreit.slc.data.enums.AuthStatus;

public class SharedViewModel extends ViewModel {
    public MutableLiveData<AuthStatus> hasAuthSucceeded = new MutableLiveData<>(AuthStatus.LOADING);
}
