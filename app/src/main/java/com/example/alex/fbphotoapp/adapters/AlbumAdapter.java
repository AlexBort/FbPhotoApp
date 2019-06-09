package com.example.alex.fbphotoapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.fbphotoapp.R;
import com.example.alex.fbphotoapp.model.Album;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private ArrayList<Album> list;

    public AlbumAdapter(ArrayList<Album> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_album, viewGroup, false);
//        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.ViewHolder viewHolder, int position) {
        Album album = list.get(position);

        Picasso.get().load(album.getUrl()).into(viewHolder.imageView);
        viewHolder.textAlbum.setText(album.getName());
        viewHolder.textDateCreate.setText(album.getCreated_time());

    }

    public void addToAdapter(Album album) {
        list.add(album);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public final class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textAlbum;
        private TextView textDateCreate;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_album);
            textAlbum = itemView.findViewById(R.id.text_album);
            textDateCreate = itemView.findViewById(R.id.text_date_create);
        }


    }


}
