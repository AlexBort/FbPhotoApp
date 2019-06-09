package com.example.alex.fbphotoapp.adapters;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.alex.fbphotoapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private ArrayList<String> list;
    private Context context;
    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_photos, viewGroup, false);

        context = viewGroup.getContext();

        mShortAnimationDuration = context.getResources().getInteger(
                android.R.integer.config_shortAnimTime);

        return new ViewHolder(view, viewGroup.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        String url = list.get(position);
        Picasso.get().load(url).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public final class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private Context context;


        public ViewHolder(View view, Context context) {
            super(view);
            this.context = context;
            imageView = view.findViewById(R.id.image_photo);

        }
    }


}
