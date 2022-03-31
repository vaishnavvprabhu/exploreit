package in.exploreit.slc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.google.firebase.auth.FirebaseAuth;

import in.exploreit.slc.MainActivity;
import in.exploreit.slc.R;
import in.exploreit.slc.data.ListSource;

public class HomeFragment extends Fragment {
    private FirebaseAuth mAuth;
    private TextView number;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Hide Action Bar
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Firebase Related
        mAuth = FirebaseAuth.getInstance();
        number = view.findViewById(R.id.num);
        mAuth.getCurrentUser();
        //number.setText(mAuth.getCurrentUser().getPhoneNumber());
        view.findViewById(R.id.aboutUsParent).setOnClickListener(
                textView -> {
                    MainActivity activity = (MainActivity) getActivity();
                    NavController navController = activity.getNavController();
                    if(navController != null) {
                        navController.navigate(R.id.action_homeFragment_to_aboutFragment);
                    }
                }
        );

        view.findViewById(R.id.eventsParent).setOnClickListener(button -> {
            navigateToListFragment(ListSource.EVENTS);
        });
        view.findViewById(R.id.oldProjParent).setOnClickListener(button -> {
            navigateToListFragment(ListSource.OLD_PROJECTS);
        });
        view.findViewById(R.id.ongoingProjParent).setOnClickListener(button -> {
            navigateToListFragment(ListSource.ON_GOING_PROJECTS);
        });
    }

    void navigateToListFragment(ListSource listSource) {
        MainActivity activity = (MainActivity) getActivity();
        NavController navController = activity.getNavController();
        if(navController != null) {
            HomeFragmentDirections.ActionHomeFragmentToListFragment action =
                    HomeFragmentDirections.actionHomeFragmentToListFragment(listSource);
            navController.navigate(action);
        } else {
            Toast.makeText(requireContext(), "Something went wrong while navigating!", Toast.LENGTH_LONG).show();
        }
    }
}