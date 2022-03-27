package in.exploreit.slc.fragments;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import in.exploreit.slc.R;

public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_about, container, false);
        View v = inflater.inflate(R.layout.fragment_about, container, false);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbarAboutus);

        toolbar.setNavigationIcon(R.drawable.back_icon);
        toolbar.setNavigationOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();

            }

                                                  // do something when click navigation
        });
        return v;





    }


}