package in.exploreit.slc.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import in.exploreit.slc.data.ListSource;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class ListFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private ListFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private ListFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ListFragmentArgs fromBundle(@NonNull Bundle bundle) {
    ListFragmentArgs __result = new ListFragmentArgs();
    bundle.setClassLoader(ListFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("Source")) {
      ListSource Source;
      if (Parcelable.class.isAssignableFrom(ListSource.class) || Serializable.class.isAssignableFrom(ListSource.class)) {
        Source = (ListSource) bundle.get("Source");
      } else {
        throw new UnsupportedOperationException(ListSource.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
      if (Source == null) {
        throw new IllegalArgumentException("Argument \"Source\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("Source", Source);
    } else {
      throw new IllegalArgumentException("Required argument \"Source\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ListFragmentArgs fromSavedStateHandle(@NonNull SavedStateHandle savedStateHandle) {
    ListFragmentArgs __result = new ListFragmentArgs();
    if (savedStateHandle.contains("Source")) {
      ListSource Source;
      Source = savedStateHandle.get("Source");
      if (Source == null) {
        throw new IllegalArgumentException("Argument \"Source\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("Source", Source);
    } else {
      throw new IllegalArgumentException("Required argument \"Source\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public ListSource getSource() {
    return (ListSource) arguments.get("Source");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
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

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("Source")) {
      ListSource Source = (ListSource) arguments.get("Source");
      if (Parcelable.class.isAssignableFrom(ListSource.class) || Source == null) {
        __result.set("Source", Parcelable.class.cast(Source));
      } else if (Serializable.class.isAssignableFrom(ListSource.class)) {
        __result.set("Source", Serializable.class.cast(Source));
      } else {
        throw new UnsupportedOperationException(ListSource.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    ListFragmentArgs that = (ListFragmentArgs) object;
    if (arguments.containsKey("Source") != that.arguments.containsKey("Source")) {
      return false;
    }
    if (getSource() != null ? !getSource().equals(that.getSource()) : that.getSource() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getSource() != null ? getSource().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ListFragmentArgs{"
        + "Source=" + getSource()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull ListFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull ListSource Source) {
      if (Source == null) {
        throw new IllegalArgumentException("Argument \"Source\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("Source", Source);
    }

    @NonNull
    public ListFragmentArgs build() {
      ListFragmentArgs result = new ListFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setSource(@NonNull ListSource Source) {
      if (Source == null) {
        throw new IllegalArgumentException("Argument \"Source\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("Source", Source);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public ListSource getSource() {
      return (ListSource) arguments.get("Source");
    }
  }
}
