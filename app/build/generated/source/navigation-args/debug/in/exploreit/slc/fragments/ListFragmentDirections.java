package in.exploreit.slc.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import in.exploreit.slc.R;
import in.exploreit.slc.data.WebViewSource;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class ListFragmentDirections {
  private ListFragmentDirections() {
  }

  @NonNull
  public static ActionListFragmentToCustomWebViewFragment actionListFragmentToCustomWebViewFragment(
      @NonNull WebViewSource WebViewSource) {
    return new ActionListFragmentToCustomWebViewFragment(WebViewSource);
  }

  public static class ActionListFragmentToCustomWebViewFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionListFragmentToCustomWebViewFragment(@NonNull WebViewSource WebViewSource) {
      if (WebViewSource == null) {
        throw new IllegalArgumentException("Argument \"WebViewSource\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("WebViewSource", WebViewSource);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionListFragmentToCustomWebViewFragment setWebViewSource(
        @NonNull WebViewSource WebViewSource) {
      if (WebViewSource == null) {
        throw new IllegalArgumentException("Argument \"WebViewSource\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("WebViewSource", WebViewSource);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
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

    @Override
    public int getActionId() {
      return R.id.action_listFragment_to_customWebViewFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public WebViewSource getWebViewSource() {
      return (WebViewSource) arguments.get("WebViewSource");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionListFragmentToCustomWebViewFragment that = (ActionListFragmentToCustomWebViewFragment) object;
      if (arguments.containsKey("WebViewSource") != that.arguments.containsKey("WebViewSource")) {
        return false;
      }
      if (getWebViewSource() != null ? !getWebViewSource().equals(that.getWebViewSource()) : that.getWebViewSource() != null) {
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
      result = 31 * result + (getWebViewSource() != null ? getWebViewSource().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionListFragmentToCustomWebViewFragment(actionId=" + getActionId() + "){"
          + "WebViewSource=" + getWebViewSource()
          + "}";
    }
  }
}
