package in.exploreit.slc.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import in.exploreit.slc.R;
import in.exploreit.slc.data.ListSource;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class HomeFragmentDirections {
  private HomeFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionHomeFragmentToAboutFragment() {
    return new ActionOnlyNavDirections(R.id.action_homeFragment_to_aboutFragment);
  }

  @NonNull
  public static ActionHomeFragmentToListFragment actionHomeFragmentToListFragment(
      @NonNull ListSource Source) {
    return new ActionHomeFragmentToListFragment(Source);
  }

  public static class ActionHomeFragmentToListFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionHomeFragmentToListFragment(@NonNull ListSource Source) {
      if (Source == null) {
        throw new IllegalArgumentException("Argument \"Source\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("Source", Source);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionHomeFragmentToListFragment setSource(@NonNull ListSource Source) {
      if (Source == null) {
        throw new IllegalArgumentException("Argument \"Source\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("Source", Source);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("Source")) {
        ListSource Source = (ListSource) arguments.get("Source");
        if (Parcelable.class.isAssignableFrom(ListSource.class) || Source == null) {
          __result.putParcelable("Source", Parcelable.class.cast(Source));
        } else if (Serializable.class.isAssignableFrom(ListSource.class)) {
          __result.putSerializable("Source", Serializable.class.cast(Source));
        } else {
          throw new UnsupportedOperationException(ListSource.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
        }
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_homeFragment_to_listFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public ListSource getSource() {
      return (ListSource) arguments.get("Source");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionHomeFragmentToListFragment that = (ActionHomeFragmentToListFragment) object;
      if (arguments.containsKey("Source") != that.arguments.containsKey("Source")) {
        return false;
      }
      if (getSource() != null ? !getSource().equals(that.getSource()) : that.getSource() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getSource() != null ? getSource().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionHomeFragmentToListFragment(actionId=" + getActionId() + "){"
          + "Source=" + getSource()
          + "}";
    }
  }
}
