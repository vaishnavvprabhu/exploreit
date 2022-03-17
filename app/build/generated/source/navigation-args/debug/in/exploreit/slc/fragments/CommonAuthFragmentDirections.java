package in.exploreit.slc.fragments;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import in.exploreit.slc.R;

public class CommonAuthFragmentDirections {
  private CommonAuthFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionCommonAuthFragmentToHomeFragment() {
    return new ActionOnlyNavDirections(R.id.action_commonAuthFragment_to_homeFragment);
  }
}
