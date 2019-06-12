package com.example.alex.fbphotoapp.gallery;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alex.fbphotoapp.OnClickListener;
import com.example.alex.fbphotoapp.data.model.dao.PhotoEntity;
import com.example.alex.fbphotoapp.databinding.ItemPhotoBinding;

import java.util.LinkedList;
import java.util.List;


public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoHolder> {

    private List<PhotoEntity> data;
    private OnPhotoClickListener listener;

    public PhotosAdapter(OnPhotoClickListener listener) {
        data = new LinkedList<>();
        this.listener = listener;
    }

    public void setData(List<PhotoEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemPhotoBinding binding = ItemPhotoBinding.inflate(inflater, parent, false);
        return new PhotoHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        holder.bind(data.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private ItemPhotoBinding binding;
        private OnPhotoClickListener listener;

        public PhotoHolder(ItemPhotoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.ivAlbumCover.setOnClickListener(this);
            binding.ivAlbumCover.setOnLongClickListener(this);
        }

        public void bind(PhotoEntity photo, OnPhotoClickListener listener) {
            this.listener = listener;
            binding.setPhoto(photo);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(data.get(getAdapterPosition()), getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            listener.onLongClick(v, data.get(getAdapterPosition()).getUrl());
            return false;
        }
    }

    public interface OnPhotoClickListener extends OnClickListener<PhotoEntity> {
        void onLongClick(View view, String url);
    }
}
