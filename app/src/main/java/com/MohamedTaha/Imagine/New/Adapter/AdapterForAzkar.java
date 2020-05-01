package com.MohamedTaha.Imagine.New.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.mvp.model.ModelAzkar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterForAzkar extends RecyclerView.Adapter<AdapterForAzkar.RecyclerAzkarViewHolder> {
    private List<ModelAzkar> modelAzkars = new ArrayList<>();
    private ClickListener clickListener;

    public AdapterForAzkar(List<ModelAzkar> modelAzkars, ClickListener clickListener) {
        this.modelAzkars = modelAzkars;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerAzkarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_azkar, parent, false);
        RecyclerAzkarViewHolder holder = new RecyclerAzkarViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (clickListener != null) clickListener.onClick(view, position);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAzkarViewHolder holder, int position) {
        ModelAzkar azkar = modelAzkars.get(position);
        holder.TVNameAzkar.setText(azkar.getName_azkar());

    }

    @Override
    public int getItemCount() {
        return modelAzkars.size();
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }

    public static class RecyclerAzkarViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.TV_Name_Azkar)
        TextView TVNameAzkar;
        private View view;

        public RecyclerAzkarViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
