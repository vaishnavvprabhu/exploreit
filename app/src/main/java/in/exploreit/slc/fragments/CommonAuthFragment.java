package in.exploreit.slc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import in.exploreit.slc.MainActivity;
import in.exploreit.slc.R;

public class CommonAuthFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_common_auth, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.something).setOnClickListener(
            textView -> {
                MainActivity activity = (MainActivity) getActivity();
                NavController navController = activity.getNavController();
                if(navController != null) {
                    navController.navigate(R.id.action_commonAuthFragment_to_homeFragment);
                }
            }
        );
    }
}