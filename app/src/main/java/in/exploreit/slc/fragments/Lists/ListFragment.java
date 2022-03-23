package in.exploreit.slc.fragments.Lists;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import in.exploreit.slc.R;
import in.exploreit.slc.utils.CommonListAdapter;
import in.exploreit.slc.utils.ListItemClickInterface;

public class ListFragment extends Fragment implements ListItemClickInterface {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        CommonListAdapter commonListAdapter = new CommonListAdapter(this);
        recyclerView.setAdapter(commonListAdapter);
        ListsViewModel viewModel = new ViewModelProvider(requireActivity()).get(ListsViewModel.class);
        commonListAdapter.submitList(viewModel.getListItems());
    }

    @Override
    public void onListItemClicked(String pageUrl) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this.requireContext(), Uri.parse(pageUrl));
    }
}