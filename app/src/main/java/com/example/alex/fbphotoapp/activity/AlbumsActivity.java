package com.example.alex.fbphotoapp.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.example.alex.fbphotoapp.BaseActivity;
import com.example.alex.fbphotoapp.OnClickListener;
import com.example.alex.fbphotoapp.R;
import com.example.alex.fbphotoapp.album.AlbumsAdapter;
import com.example.alex.fbphotoapp.album.AlbumsViewModel;
import com.example.alex.fbphotoapp.data.model.dao.AlbumEntity;
import com.example.alex.fbphotoapp.databinding.ActivityMainBinding;


public class AlbumsActivity extends BaseActivity implements OnClickListener<AlbumEntity> {

    private ActivityMainBinding binding;
    private AlbumsAdapter adapter;
    private AlbumsViewModel viewModel;

    public static void launch(Context context, boolean isClearTask) {
        Intent intent = new Intent(context, AlbumsActivity.class);
        if (isClearTask)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new AlbumsViewModel();
        getLifecycle().addObserver(viewModel);
        setRecycler();
        observeRefresh();
        viewModel.getAlbumsRequest();
        viewModel.getAllAlbumsCallback().observe(this, albums -> {
            adapter.setData(albums);
        });

    }

    private void observeRefresh() {
        binding.swipe.setOnRefreshListener(() -> {
            binding.swipe.setRefreshing(false);
            viewModel.getAlbumsRequest();
        });
    }

    @Override
    public void onClick(AlbumEntity item, int position) {
        DetailAlbumsActivity.launch(this, item.getId());
    }

    private void setRecycler() {
        adapter = new AlbumsAdapter(this);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recyclerView.setAdapter(adapter);
    }

}
