package in.exploreit.slc.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import in.exploreit.slc.data.WebViewSource;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class CustomWebViewFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private CustomWebViewFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private CustomWebViewFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static CustomWebViewFragmentArgs fromBundle(@NonNull Bundle bundle) {
    CustomWebViewFragmentArgs __result = new CustomWebViewFragmentArgs();
    bundle.setClassLoader(CustomWebViewFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("WebViewSource")) {
      WebViewSource WebViewSource;
      if (Parcelable.class.isAssignableFrom(WebViewSource.class) || Serializable.class.isAssignableFrom(WebViewSource.class)) {
        WebViewSource = (WebViewSource) bundle.get("WebViewSource");
      } else {
        throw new UnsupportedOperationException(WebViewSource.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
      if (WebViewSource == null) {
        throw new IllegalArgumentException("Argument \"WebViewSource\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("WebViewSource", WebViewSource);
    } else {
      throw new IllegalArgumentException("Required argument \"WebViewSource\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static CustomWebViewFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    CustomWebViewFragmentArgs __result = new CustomWebViewFragmentArgs();
    if (savedStateHandle.contains("WebViewSource")) {
      WebViewSource WebViewSource;
      WebViewSource = savedStateHandle.get("WebViewSource");
      if (WebViewSource == null) {
        throw new IllegalArgumentException("Argument \"WebViewSource\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("WebViewSource", WebViewSource);
    } else {
      throw new IllegalArgumentException("Required argument \"WebViewSource\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public WebViewSource getWebViewSource() {
    return (WebViewSource) arguments.get("WebViewSource");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("WebViewSource")) {
      WebViewSource WebViewSource = (WebViewSource) arguments.get("WebViewSource");
      if (Parcelable.class.isAssignableFrom(WebViewSource.class) || WebViewSource == null) {
        __result.putParcelable("WebViewSource", Parcelable.class.cast(WebViewSource));
      } else if (Serializable.class.isAssignableFrom(WebViewSource.class)) {
        __result.putSerializable("WebViewSource", Serializable.class.cast(WebViewSource));
      } else {
        throw new UnsupportedOperationException(WebViewSource.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("WebViewSource")) {
      WebViewSource WebViewSource = (WebViewSource) arguments.get("WebViewSource");
      if (Parcelable.class.isAssignableFrom(WebViewSource.class) || WebViewSource == null) {
        __result.set("WebViewSource", Parcelable.class.cast(WebViewSource));
      } else if (Serializable.class.isAssignableFrom(WebViewSource.class)) {
        __result.set("WebViewSource", Serializable.class.cast(WebViewSource));
      } else {
        throw new UnsupportedOperationException(WebViewSource.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
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
    CustomWebViewFragmentArgs that = (CustomWebViewFragmentArgs) object;
    if (arguments.containsKey("WebViewSource") != that.arguments.containsKey("WebViewSource")) {
      return false;
    }
    if (getWebViewSource() != null ? !getWebViewSource().equals(that.getWebViewSource()) : that.getWebViewSource() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getWebViewSource() != null ? getWebViewSource().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "CustomWebViewFragmentArgs{"
        + "WebViewSource=" + getWebViewSource()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull CustomWebViewFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull WebViewSource WebViewSource) {
      if (WebViewSource == null) {
        throw new IllegalArgumentException("Argument \"WebViewSource\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("WebViewSource", WebViewSource);
    }

    @NonNull
    public CustomWebViewFragmentArgs build() {
      CustomWebViewFragmentArgs result = new CustomWebViewFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setWebViewSource(@NonNull WebViewSource WebViewSource) {
      if (WebViewSource == null) {
        throw new IllegalArgumentException("Argument \"WebViewSource\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("WebViewSource", WebViewSource);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public WebViewSource getWebViewSource() {
      return (WebViewSource) arguments.get("WebViewSource");
    }
  }
}
