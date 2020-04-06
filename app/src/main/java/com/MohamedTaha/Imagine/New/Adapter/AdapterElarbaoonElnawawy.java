package com.MohamedTaha.Imagine.New.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.databinding.CustomElarbaoonElnawawyBinding;
import com.MohamedTaha.Imagine.New.mvp.model.ElarbaoonElnawawyModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterElarbaoonElnawawy extends RecyclerView.Adapter<AdapterElarbaoonElnawawy.ElarbaoonViewHolder> {

    private CustomElarbaoonElnawawyBinding customElarbaoonElnawawyBinding;
    private List<ElarbaoonElnawawyModel> elnawawyModelsList;
    private Context context;

    private ClickListener clickListener;

    public interface ClickListener {
        void onClick(int position);
    }

    public AdapterElarbaoonElnawawy(List<ElarbaoonElnawawyModel> elnawawyModelsList, ClickListener clickListener) {
        this.elnawawyModelsList = elnawawyModelsList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ElarbaoonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //customElarbaoonElnawawyBinding = CustomElarbaoonElnawawyBinding.inflate();
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_elarbaoon_elnawawy, null);
        ElarbaoonViewHolder elarbaoonViewHolder = new ElarbaoonViewHolder(row);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int positionRow = elarbaoonViewHolder.getAdapterPosition();
                if (clickListener != null) clickListener.onClick(positionRow);
            }
        });

        return elarbaoonViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ElarbaoonViewHolder holder, int position) {
        ElarbaoonElnawawyModel elnawawyModel = elnawawyModelsList.get(position);
        holder.TVNameElhadeth.setText(elnawawyModel.getName_elhadeth());
        holder.TVNumberElhadeth.setText(elnawawyModel.getNumber_elhadeth());

    }

    @Override
    public int getItemCount() {
        return elnawawyModelsList.size();
    }

    static class ElarbaoonViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.TV_Name_Elhadeth)
        TextView TVNameElhadeth;
        @BindView(R.id.TV_Number_Elhadeth)
        TextView TVNumberElhadeth;
        public ElarbaoonViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
