package com.example.alex.fbphotoapp.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.alex.fbphotoapp.databinding.ActivityPhotoDetailBinding;
import com.example.alex.fbphotoapp.databinding.LayoutFullscreenPhotoBinding;
import com.example.alex.fbphotoapp.BaseActivity;
import com.example.alex.fbphotoapp.R;
import com.example.alex.fbphotoapp.data.model.dao.PhotoEntity;
import com.example.alex.fbphotoapp.detail.DetailsViewModel;
import com.example.alex.fbphotoapp.utils.ExtrasKeys;

import java.util.ArrayList;
import java.util.List;

public class DetailPhotosActivity extends BaseActivity {

    private PhotosPagerAdapter pagerAdapter;
    private ActivityPhotoDetailBinding binding;
    private Intent intent;

    public static void launch(Context context, String id, int position) {
        Intent intent = new Intent(context, DetailPhotosActivity.class);
        intent.putExtra(ExtrasKeys.ALBUM_ID, id);
        intent.putExtra(ExtrasKeys.ITEM_POSITION, position);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_detail);
        intent = getIntent();
        DetailsViewModel viewModel = new DetailsViewModel(intent.getStringExtra(ExtrasKeys.ALBUM_ID));
        observePager();
        viewModel.getAllPhotos().observe(this, photoEntities -> {
            pagerAdapter.setData(photoEntities);
            binding.pagerPhotos.setCurrentItem(intent.getIntExtra(ExtrasKeys.ITEM_POSITION, 0));
        });
    }

    private void observePager() {
        pagerAdapter = new PhotosPagerAdapter();
        binding.pagerPhotos.setAdapter(pagerAdapter);
    }

    private class PhotosPagerAdapter extends PagerAdapter {
        private List<PhotoEntity> data;

        public PhotosPagerAdapter() {
            data = new ArrayList<>();
        }

        public void setData(List<PhotoEntity> data) {
            this.data = data;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutFullscreenPhotoBinding binding = LayoutFullscreenPhotoBinding.inflate(getLayoutInflater());
            binding.setUrl(data.get(position).getUrl());
            container.addView(binding.getRoot());
            return binding.getRoot();
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
