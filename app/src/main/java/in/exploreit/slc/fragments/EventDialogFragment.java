package in.exploreit.slc.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import in.exploreit.slc.R;
import in.exploreit.slc.data.models.Event;
import in.exploreit.slc.databinding.FragmentDialogBinding;
import in.exploreit.slc.fragments.Lists.ListsViewModel;
import in.exploreit.slc.utils.Constants;

public class EventDialogFragment extends DialogFragment {

    private FragmentDialogBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(EventDialogFragment.STYLE_NORMAL, R.style.DialogFullScreen);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d!=null){
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDialogBinding.inflate(inflater, container, false);
        requireDialog().getWindow().setWindowAnimations(
                R.style.DialogAnimation
        );
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = EventDialogFragmentArgs.fromBundle(getArguments()).getEventPosition();
        ListsViewModel viewModel = new ViewModelProvider(requireActivity()).get(ListsViewModel.class);
        try {
            Event event = viewModel.latestEvents.get(position);
            binding.eventName.setText(event.getName());
            binding.contentPage.setText(event.getDescription());
            Glide.with(this).load(event.getImageUrl()).centerCrop().into(binding.imgEvent);
            binding.contentDate.setText(event.getDate_time().toString());
            binding.contentVenue.setText(event.getVenue());
            binding.contentDate.setText(event.getDate_time().toString());
            binding.contentPrice.setText(event.getPrice());
        } catch (Exception e) {
            Log.d(Constants.TAG, "onViewCreated: " + e);
            Toast.makeText(requireActivity(), "Could not find Event!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
