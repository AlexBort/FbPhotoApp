package com.example.alex.fbphotoapp.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.example.alex.fbphotoapp.BaseActivity;
import com.example.alex.fbphotoapp.R;
import com.example.alex.fbphotoapp.data.model.dao.PhotoEntity;
import com.example.alex.fbphotoapp.databinding.ActivityMainBinding;
import com.example.alex.fbphotoapp.gallery.DetailAlbumsViewModel;
import com.example.alex.fbphotoapp.gallery.PhotosAdapter;
import com.example.alex.fbphotoapp.gallery.PreviewPhotoFragment;

import static com.example.alex.fbphotoapp.utils.ExtrasKeys.ALBUM_ID;

public class DetailAlbumsActivity extends BaseActivity implements PhotosAdapter.OnPhotoClickListener {

    private ActivityMainBinding binding;
    private DetailAlbumsViewModel viewModel;
    private PhotosAdapter adapter;
    private Intent intent;

    public static void launch(Context context, String id) {
        Intent intent = new Intent(context, DetailAlbumsActivity.class);
        intent.putExtra(ALBUM_ID, id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        intent = getIntent();
        viewModel = new DetailAlbumsViewModel(intent.getStringExtra(ALBUM_ID));
        getLifecycle().addObserver(viewModel);
        setRecycler();
        observeRefresh();
        observeViewModel();
    }



    private void setRecycler() {
        adapter = new PhotosAdapter(this);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recyclerView.setAdapter(adapter);
    }

    private void observeViewModel() {
        viewModel.getAlbumPhotosRequest(intent.getStringExtra(ALBUM_ID));
        viewModel.getAlbumPhotosCallback().observe(this, photos -> adapter.setData(photos));
    }

    private void observeRefresh() {
        binding.swipe.setOnRefreshListener(() -> {
            binding.swipe.setRefreshing(false);
            viewModel.getAlbumPhotosRequest(intent.getStringExtra(ALBUM_ID));
        });
    }

    @Override
    public void onClick(PhotoEntity item, int position) {
        DetailPhotosActivity.launch(this, item.getAlbumId(), position);
    }

    @Override
    public void onLongClick(View view, String url) {
        PreviewPhotoFragment fragment = PreviewPhotoFragment.newInstance(url);
        fragment.show(getFragmentManager(), fragment.getTag());
    }
}
