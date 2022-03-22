// Generated by view binder compiler. Do not edit!
package in.exploreit.slc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import in.exploreit.slc.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialCardView aboutUsParent;

  @NonNull
  public final ImageView aboutus;

  @NonNull
  public final ConstraintLayout constraintLayout;

  @NonNull
  public final TextView contactNo;

  @NonNull
  public final TextView desc;

  @NonNull
  public final ImageView events;

  @NonNull
  public final MaterialCardView eventsParent;

  @NonNull
  public final ImageView exploreitIcon;

  @NonNull
  public final Guideline guideline2;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final LinearLayout linearLayout2;

  @NonNull
  public final LinearLayout linearLayout3;

  @NonNull
  public final LinearLayout linearLayout4;

  @NonNull
  public final Button logout;

  @NonNull
  public final ImageView oldProj;

  @NonNull
  public final MaterialCardView oldProjParent;

  @NonNull
  public final ImageView ongoingProj;

  @NonNull
  public final MaterialCardView ongoingProjParent;

  @NonNull
  public final ImageView rectangle;

  @NonNull
  public final Button slc;

  @NonNull
  public final TextView welcome;

  private FragmentHomeBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialCardView aboutUsParent, @NonNull ImageView aboutus,
      @NonNull ConstraintLayout constraintLayout, @NonNull TextView contactNo,
      @NonNull TextView desc, @NonNull ImageView events, @NonNull MaterialCardView eventsParent,
      @NonNull ImageView exploreitIcon, @NonNull Guideline guideline2,
      @NonNull LinearLayout linearLayout, @NonNull LinearLayout linearLayout2,
      @NonNull LinearLayout linearLayout3, @NonNull LinearLayout linearLayout4,
      @NonNull Button logout, @NonNull ImageView oldProj, @NonNull MaterialCardView oldProjParent,
      @NonNull ImageView ongoingProj, @NonNull MaterialCardView ongoingProjParent,
      @NonNull ImageView rectangle, @NonNull Button slc, @NonNull TextView welcome) {
    this.rootView = rootView;
    this.aboutUsParent = aboutUsParent;
    this.aboutus = aboutus;
    this.constraintLayout = constraintLayout;
    this.contactNo = contactNo;
    this.desc = desc;
    this.events = events;
    this.eventsParent = eventsParent;
    this.exploreitIcon = exploreitIcon;
    this.guideline2 = guideline2;
    this.linearLayout = linearLayout;
    this.linearLayout2 = linearLayout2;
    this.linearLayout3 = linearLayout3;
    this.linearLayout4 = linearLayout4;
    this.logout = logout;
    this.oldProj = oldProj;
    this.oldProjParent = oldProjParent;
    this.ongoingProj = ongoingProj;
    this.ongoingProjParent = ongoingProjParent;
    this.rectangle = rectangle;
    this.slc = slc;
    this.welcome = welcome;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.aboutUsParent;
      MaterialCardView aboutUsParent = ViewBindings.findChildViewById(rootView, id);
      if (aboutUsParent == null) {
        break missingId;
      }

      id = R.id.aboutus;
      ImageView aboutus = ViewBindings.findChildViewById(rootView, id);
      if (aboutus == null) {
        break missingId;
      }

      id = R.id.constraintLayout;
      ConstraintLayout constraintLayout = ViewBindings.findChildViewById(rootView, id);
      if (constraintLayout == null) {
        break missingId;
      }

      id = R.id.contactNo;
      TextView contactNo = ViewBindings.findChildViewById(rootView, id);
      if (contactNo == null) {
        break missingId;
      }

      id = R.id.desc;
      TextView desc = ViewBindings.findChildViewById(rootView, id);
      if (desc == null) {
        break missingId;
      }

      id = R.id.events;
      ImageView events = ViewBindings.findChildViewById(rootView, id);
      if (events == null) {
        break missingId;
      }

      id = R.id.eventsParent;
      MaterialCardView eventsParent = ViewBindings.findChildViewById(rootView, id);
      if (eventsParent == null) {
        break missingId;
      }

      id = R.id.exploreitIcon;
      ImageView exploreitIcon = ViewBindings.findChildViewById(rootView, id);
      if (exploreitIcon == null) {
        break missingId;
      }

      id = R.id.guideline2;
      Guideline guideline2 = ViewBindings.findChildViewById(rootView, id);
      if (guideline2 == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.linearLayout2;
      LinearLayout linearLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout2 == null) {
        break missingId;
      }

      id = R.id.linearLayout3;
      LinearLayout linearLayout3 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout3 == null) {
        break missingId;
      }

      id = R.id.linearLayout4;
      LinearLayout linearLayout4 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout4 == null) {
        break missingId;
      }

      id = R.id.logout;
      Button logout = ViewBindings.findChildViewById(rootView, id);
      if (logout == null) {
        break missingId;
      }

      id = R.id.oldProj;
      ImageView oldProj = ViewBindings.findChildViewById(rootView, id);
      if (oldProj == null) {
        break missingId;
      }

      id = R.id.oldProjParent;
      MaterialCardView oldProjParent = ViewBindings.findChildViewById(rootView, id);
      if (oldProjParent == null) {
        break missingId;
      }

      id = R.id.ongoingProj;
      ImageView ongoingProj = ViewBindings.findChildViewById(rootView, id);
      if (ongoingProj == null) {
        break missingId;
      }

      id = R.id.ongoingProjParent;
      MaterialCardView ongoingProjParent = ViewBindings.findChildViewById(rootView, id);
      if (ongoingProjParent == null) {
        break missingId;
      }

      id = R.id.rectangle;
      ImageView rectangle = ViewBindings.findChildViewById(rootView, id);
      if (rectangle == null) {
        break missingId;
      }

      id = R.id.slc;
      Button slc = ViewBindings.findChildViewById(rootView, id);
      if (slc == null) {
        break missingId;
      }

      id = R.id.welcome;
      TextView welcome = ViewBindings.findChildViewById(rootView, id);
      if (welcome == null) {
        break missingId;
      }

      return new FragmentHomeBinding((ConstraintLayout) rootView, aboutUsParent, aboutus,
          constraintLayout, contactNo, desc, events, eventsParent, exploreitIcon, guideline2,
          linearLayout, linearLayout2, linearLayout3, linearLayout4, logout, oldProj, oldProjParent,
          ongoingProj, ongoingProjParent, rectangle, slc, welcome);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
