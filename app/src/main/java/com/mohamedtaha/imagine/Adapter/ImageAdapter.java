package com.mohamedtaha.imagine.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.ui.activities.ListSoundReader;
import com.mohamedtaha.imagine.mvp.model.ImageModel;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MANASATT on 20/08/17.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{
    public static final String SHEKH_ID = "shekh_id";
    public static final String SHEKH_NAME = "shekh_name";
    private List<ImageModel>imageModels;

    private Activity activity;

    public ImageAdapter( List<ImageModel> imageModels,Activity activity) {
        //  super(context, 0,imageModels);
        this.activity = activity;
        this.imageModels = imageModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View convertView = inflater.inflate(R.layout.custom_name_reader, null);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageModel imageModel = imageModels.get(position);
        holder.textViewShow.setText(imageModel.getName_shekh());
        holder.textTypeTlawa.setText(imageModel.getType_sora());
        Glide.with(holder.textViewShow.getContext())
                .load(imageModel.getUrl_image())
                .override(holder.imageView.getWidth(),holder.imageView.getHeight())
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listSound = new Intent(holder.textViewShow.getContext(), ListSoundReader.class);
                Bundle bundle = new Bundle();
                bundle.putString(SHEKH_ID, new Gson().toJson(imageModel.getPosition()));
                bundle.putString(SHEKH_NAME,new Gson().toJson(imageModel.getName_shekh()));
                listSound.putExtras(bundle);
                holder.textViewShow.getContext().startActivity(listSound);
                activity.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.textViewShow)
        TextView textViewShow;
        @BindView(R.id.textTypeTlawa)
        TextView textTypeTlawa;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
