// Generated by view binder compiler. Do not edit!
package in.exploreit.slc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import in.exploreit.slc.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentDialogBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button attendeventBtn;

  @NonNull
  public final TextView contentDate;

  @NonNull
  public final TextView contentPage;

  @NonNull
  public final TextView contentPrice;

  @NonNull
  public final TextView contentTime;

  @NonNull
  public final TextView contentVenue;

  @NonNull
  public final ImageView imageView10;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final TextView loginTextView2;

  private FragmentDialogBinding(@NonNull ConstraintLayout rootView, @NonNull Button attendeventBtn,
      @NonNull TextView contentDate, @NonNull TextView contentPage, @NonNull TextView contentPrice,
      @NonNull TextView contentTime, @NonNull TextView contentVenue, @NonNull ImageView imageView10,
      @NonNull ImageView imageView2, @NonNull TextView loginTextView2) {
    this.rootView = rootView;
    this.attendeventBtn = attendeventBtn;
    this.contentDate = contentDate;
    this.contentPage = contentPage;
    this.contentPrice = contentPrice;
    this.contentTime = contentTime;
    this.contentVenue = contentVenue;
    this.imageView10 = imageView10;
    this.imageView2 = imageView2;
    this.loginTextView2 = loginTextView2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentDialogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_dialog, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentDialogBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.attendevent_btn;
      Button attendeventBtn = ViewBindings.findChildViewById(rootView, id);
      if (attendeventBtn == null) {
        break missingId;
      }

      id = R.id.content_date;
      TextView contentDate = ViewBindings.findChildViewById(rootView, id);
      if (contentDate == null) {
        break missingId;
      }

      id = R.id.content_page;
      TextView contentPage = ViewBindings.findChildViewById(rootView, id);
      if (contentPage == null) {
        break missingId;
      }

      id = R.id.content_price;
      TextView contentPrice = ViewBindings.findChildViewById(rootView, id);
      if (contentPrice == null) {
        break missingId;
      }

      id = R.id.content_time;
      TextView contentTime = ViewBindings.findChildViewById(rootView, id);
      if (contentTime == null) {
        break missingId;
      }

      id = R.id.content_venue;
      TextView contentVenue = ViewBindings.findChildViewById(rootView, id);
      if (contentVenue == null) {
        break missingId;
      }

      id = R.id.imageView10;
      ImageView imageView10 = ViewBindings.findChildViewById(rootView, id);
      if (imageView10 == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.login_textView2;
      TextView loginTextView2 = ViewBindings.findChildViewById(rootView, id);
      if (loginTextView2 == null) {
        break missingId;
      }

      return new FragmentDialogBinding((ConstraintLayout) rootView, attendeventBtn, contentDate,
          contentPage, contentPrice, contentTime, contentVenue, imageView10, imageView2,
          loginTextView2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
