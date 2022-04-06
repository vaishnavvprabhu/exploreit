package in.exploreit.slc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import in.exploreit.slc.MainActivity;
import in.exploreit.slc.R;
import in.exploreit.slc.data.enums.ListSource;
import in.exploreit.slc.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Hide Action Bar
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        
        binding = FragmentHomeBinding.inflate(inflater, container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null) {
            binding.phoneNumber.setText(user.getPhoneNumber());
        }

        binding.aboutUsParent.setOnClickListener(
            textView -> {
                MainActivity activity = (MainActivity) requireActivity();
                NavController navController = activity.getNavController();
                if(navController != null) {
                    navController.navigate(R.id.action_homeFragment_to_aboutFragment);
                }
            }
        );
        binding.eventsParent.setOnClickListener(button -> { navigateToList(ListSource.EVENTS); });
        binding.oldProjParent.setOnClickListener(button -> { navigateToList(ListSource.OLD_PROJECTS); });
        binding.ongoingProjParent.setOnClickListener(button -> { navigateToList(ListSource.ON_GOING_PROJECTS); });
        binding.logout.setOnClickListener(textView -> { logout(); });
    }

    void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setMessage("Are you sure you want to logout?").setTitle("We will miss you!");

        builder.setCancelable(true)
                .setPositiveButton("Yes", (dialog, id) -> {
                    FirebaseAuth.getInstance().signOut();
                    MainActivity activity = (MainActivity) requireActivity();
                    NavController navController = activity.getNavController();
                    if(navController != null) {
                        navController.navigate(R.id.action_homeFragment_to_commonLoginFragment);
                    }
                })
                .setNegativeButton("No", (dialog, id) -> {
                    dialog.dismiss();
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    void navigateToList(ListSource listSource) {
        MainActivity activity = (MainActivity) requireActivity();
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