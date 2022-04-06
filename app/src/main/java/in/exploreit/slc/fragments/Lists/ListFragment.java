package in.exploreit.slc.fragments.Lists;

import static in.exploreit.slc.utils.Utils.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.exploreit.slc.MainActivity;
import in.exploreit.slc.R;
import in.exploreit.slc.data.enums.ListSource;
import in.exploreit.slc.data.enums.ResultStatus;
import in.exploreit.slc.data.models.ListItem;
import in.exploreit.slc.data.models.ListResultCallback;
import in.exploreit.slc.databinding.FragmentListBinding;
import in.exploreit.slc.utils.CommonListAdapter;
import in.exploreit.slc.utils.ListItemClickInterface;
import in.exploreit.slc.utils.Utils;

public class ListFragment extends Fragment implements ListItemClickInterface, ListResultCallback {
    TextView title;
    CommonListAdapter commonListAdapter;
    boolean hasDialogFragment = false;
    private FragmentListBinding binding;
    ListsViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater, container, false);
        Toolbar toolbar = binding.toolbarExploreiT;
        toolbar.setNavigationIcon(R.drawable.back_icon);
        toolbar.setNavigationOnClickListener(backButton -> getActivity().onBackPressed());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ListSource listSource = ListFragmentArgs.fromBundle(getArguments()).getSource();
        viewModel = new ViewModelProvider(requireActivity()).get(ListsViewModel.class);
        setupRecyclerView();
        title= binding.toolbarTitle;
        changeVisibilityOfIndicators(ResultStatus.LOADING);
        loadForListSource(listSource);
    }

    private void loadForListSource(ListSource listSource) {
        switch (listSource) {
            case EVENTS: {
                hasDialogFragment = true;
                title.setText("Events");
                viewModel.getAllEvents(this);
                break;
            }
            case OLD_PROJECTS: {
                viewModel.getAllOldProjects(this);
                title.setText("Old Projects");
                break;
            }
            case ON_GOING_PROJECTS: {
                viewModel.getAllOngoingProjects(this);
                title.setText("On-Going Projects");
                break;
            }
            default: changeVisibilityOfIndicators(ResultStatus.ERROR);
        }
    }

    private void changeVisibilityOfIndicators(ResultStatus status) {
        switch (status) {
            case SUCCESS: {
                Log.d(TAG, "changeVisibilityOfIndicators: success");
                binding.recyclerView.setVisibility(View.VISIBLE);
                binding.loadingIcon.setVisibility(View.INVISIBLE);
                binding.tooltipText.setVisibility(View.INVISIBLE);
                binding.tooltipIcon.setVisibility(View.INVISIBLE);
                break;
            }
            case ERROR: {
                Log.d(TAG, "changeVisibilityOfIndicators: error");
                binding.recyclerView.setVisibility(View.INVISIBLE);
                binding.loadingIcon.setVisibility(View.INVISIBLE);
                binding.tooltipText.setVisibility(View.VISIBLE);
                binding.tooltipIcon.setVisibility(View.VISIBLE);
                binding.tooltipIcon.setImageResource(R.drawable.ic_error);
                binding.tooltipText.setText(R.string.error_message);
                break;
            }
            case LOADING: {
                Log.d(TAG, "changeVisibilityOfIndicators: loading");
                binding.recyclerView.setVisibility(View.INVISIBLE);
                binding.loadingIcon.setVisibility(View.VISIBLE);
                binding.tooltipText.setVisibility(View.VISIBLE);
                binding.tooltipIcon.setVisibility(View.INVISIBLE);
                binding.tooltipText.setText(R.string.loading_message);
                break;
            }
            case EMPTY: {
                Log.d(TAG, "changeVisibilityOfIndicators: empty");
                binding.recyclerView.setVisibility(View.INVISIBLE);
                binding.loadingIcon.setVisibility(View.INVISIBLE);
                binding.tooltipText.setVisibility(View.VISIBLE);
                binding.tooltipIcon.setVisibility(View.VISIBLE);
                binding.tooltipIcon.setImageResource(R.drawable.ic_info);
                binding.tooltipText.setText(R.string.empty_message);
                break;
            }
        }
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = binding.recyclerView;
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
            Utils.openWebpage(this.requireContext(), pageUrl, this.getView());
        }
    }

    @Override
    public void responseReturned(List<ListItem> list) {
        if(list == null) {
            changeVisibilityOfIndicators(ResultStatus.ERROR);
            Log.d(Utils.TAG, "responseReturned: something went wrong");
            Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(list.isEmpty()) {
            changeVisibilityOfIndicators(ResultStatus.EMPTY);
            Log.d(Utils.TAG, "responseReturned: no items found!");
            Toast.makeText(requireContext(), "Empty List!", Toast.LENGTH_SHORT).show();
        } else {
            changeVisibilityOfIndicators(ResultStatus.SUCCESS);
            commonListAdapter.submitList(list);
            Log.d(Utils.TAG, "responseReturned:" + list.size() + " items found!");
        }
    }
}