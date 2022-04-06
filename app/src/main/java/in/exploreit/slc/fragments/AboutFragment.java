package in.exploreit.slc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import in.exploreit.slc.R;
import in.exploreit.slc.utils.Utils;

public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbarExploreiT);

        toolbar.setNavigationIcon(R.drawable.back_icon);
        toolbar.setNavigationOnClickListener(backButton -> getActivity().onBackPressed());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.contribute_btn).setOnClickListener(button -> {
            Utils.openWebpage(this.requireContext(), "https://exploreit.in/", this.getView());
        });
    }
}