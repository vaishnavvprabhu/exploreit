package in.exploreit.slc.fragments;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import in.exploreit.slc.R;

public class OTPFragmentDirections {
  private OTPFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionOTPFragmentToHomeFragment() {
    return new ActionOnlyNavDirections(R.id.action_OTPFragment_to_homeFragment);
  }
}
