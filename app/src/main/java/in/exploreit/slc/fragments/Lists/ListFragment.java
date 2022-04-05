package in.exploreit.slc.fragments.Lists;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.exploreit.slc.MainActivity;
import in.exploreit.slc.R;
import in.exploreit.slc.data.ListSource;
import in.exploreit.slc.data.models.ListItem;
import in.exploreit.slc.data.models.ListResultCallback;
import in.exploreit.slc.utils.CommonListAdapter;
import in.exploreit.slc.utils.ListItemClickInterface;
import in.exploreit.slc.utils.Utils;

public class ListFragment extends Fragment implements ListItemClickInterface, ListResultCallback {

    CommonListAdapter commonListAdapter;
    boolean hasDialogFragment = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ListSource listSource = ListFragmentArgs.fromBundle(getArguments()).getSource();
        ListsViewModel viewModel = new ViewModelProvider(requireActivity()).get(ListsViewModel.class);
        setupRecyclerView(view);
        switch (listSource) {
            case EVENTS: {
                hasDialogFragment = true;
                viewModel.getAllEvents(this);
                break;
            }
            case OLD_PROJECTS: viewModel.getAllOldProjects(this); break;
            case ON_GOING_PROJECTS:viewModel.getAllOngoingProjects(this); break;
        }
        // TODO replace this with loading icon
        Toast.makeText(requireContext(), "Loading!", Toast.LENGTH_SHORT).show();
    }

    private void setupRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        commonListAdapter = new CommonListAdapter(this);
        recyclerView.setAdapter(commonListAdapter);
    }

    @Override
    public void onListItemClicked(String pageUrl, int position) {
        if(hasDialogFragment) {
            MainActivity activity = (MainActivity) requireActivity();
            NavController navController = activity.getNavController();
            if(navController != null) {
                ListFragmentDirections.ActionListFragmentToEventDialogFragment action =
                        ListFragmentDirections.actionListFragmentToEventDialogFragment(position);
                navController.navigate(action);
            } else {
                Toast.makeText(requireContext(), "Error while opening details for this event!", Toast.LENGTH_SHORT).show();
            }
        } else if(pageUrl == null) {
            Toast.makeText(requireContext(), "Error while opening link!", Toast.LENGTH_SHORT).show();
        } else {
            Utils.openWebpage(this.requireContext(), pageUrl);
        }
    }

    @Override
    public void responseReturned(List<ListItem> list) {
        if(list == null) {
            // TODO show error indicator instead of recycler view
            Log.d(Utils.TAG, "responseReturned: something went wrong");
            Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(list.isEmpty()) {
            // TODO show empty indicator instead of recycler view
            Log.d(Utils.TAG, "responseReturned: no items found!");
            Toast.makeText(requireContext(), "Empty List!", Toast.LENGTH_SHORT).show();
        } else {
            commonListAdapter.submitList(list);
            Log.d(Utils.TAG, "responseReturned:" + list.size() + " items found!");
        }
    }
}