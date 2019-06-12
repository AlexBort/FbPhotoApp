package com.example.alex.fbphotoapp.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.tvbarthel.lib.blurdialogfragment.BlurDialogFragment;

import com.example.alex.fbphotoapp.databinding.LayoutImagePreviewerBinding;

public class PreviewPhotoFragment extends BlurDialogFragment {

    private LayoutImagePreviewerBinding binding;
    private static final String URL = "image.url";

    public static PreviewPhotoFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString(URL, url);
        PreviewPhotoFragment fragment = new PreviewPhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LayoutImagePreviewerBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setUrl(getArguments().getString(URL));
    }
}
