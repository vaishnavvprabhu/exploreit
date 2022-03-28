package in.exploreit.slc.fragments;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import in.exploreit.slc.R;

public class CommonLoginFragmentDirections {
  private CommonLoginFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionCommonLoginFragmentToOTPFragment() {
    return new ActionOnlyNavDirections(R.id.action_commonLoginFragment_to_OTPFragment);
  }
}
