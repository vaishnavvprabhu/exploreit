package in.exploreit.slc.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import in.exploreit.slc.MainActivity;
import in.exploreit.slc.R;
import in.exploreit.slc.utils.ListItemClickCallback;

public class ListFragment extends Fragment implements ListItemClickCallback {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.something).setOnClickListener(
            textView -> {
                MainActivity activity = (MainActivity) getActivity();
                NavController navController = activity.getNavController();
                if(navController != null) {
                    navController.navigate(R.id.action_listFragment_to_customWebViewFragment);
                }
            }
        );
    }

    @Override
    public void onListItemClicked(String pageUrl) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this.requireContext(), Uri.parse(pageUrl));
    }
}