package com.example.alex.fbphotoapp.album;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alex.fbphotoapp.OnClickListener;
import com.example.alex.fbphotoapp.data.model.dao.AlbumEntity;
import com.example.alex.fbphotoapp.databinding.ItemAlbumBinding;

import java.util.LinkedList;
import java.util.List;


public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumHolder> {

    private List<AlbumEntity> data;
    private OnClickListener<AlbumEntity> listener;

    public AlbumsAdapter(OnClickListener<AlbumEntity> listener) {
        data = new LinkedList<>();
        this.listener = listener;
    }

    public void setData(List<AlbumEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public AlbumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemAlbumBinding binding = ItemAlbumBinding.inflate(inflater, parent, false);
        return new AlbumHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumHolder holder, int position) {
        holder.bind(data.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

     class AlbumHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemAlbumBinding binding;
        private OnClickListener<AlbumEntity> listener;

        public AlbumHolder(ItemAlbumBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
            binding.getRoot().setOnClickListener(this);
        }

        public void bind(AlbumEntity album, OnClickListener<AlbumEntity> listener) {
            this.listener = listener;
            binding.setAlbum(album);
        }

         @Override
         public void onClick(View v) {
             listener.onClick(data.get(getAdapterPosition()), getAdapterPosition());
         }
     }
}
