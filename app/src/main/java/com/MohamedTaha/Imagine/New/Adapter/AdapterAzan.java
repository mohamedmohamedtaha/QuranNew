package com.MohamedTaha.Imagine.New.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Datum;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Timings;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.compareTwoTimes;
import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertTimeToAM;

public class AdapterAzan extends RecyclerView.Adapter<AdapterAzan.RecyclerAzanViewHolder> {
    private Context context;
    private List<Timings> azanList = new ArrayList<>();

    public AdapterAzan(Context context) {
        this.context = context;
    }

    public void setAzanList(List<Timings> azanList) {
        this.azanList = azanList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerAzanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_azan, parent, false);
        RecyclerAzanViewHolder recyclerAzanViewHolder = new RecyclerAzanViewHolder(row);

        return recyclerAzanViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAzanViewHolder holder, int position) {
        Timings azan = azanList.get(position);

        holder.TVFagr.setText(convertTimeToAM(azan.getFajr().substring(0, 5)));
        holder.TVSunrise.setText(convertTimeToAM(azan.getSunrise().substring(0, 5)));
        holder.TVDauhr.setText(convertTimeToAM(azan.getDhuhr().substring(0, 5)));
        holder.TVAsr.setText(convertTimeToAM(azan.getAsr().substring(0, 5)));
        holder.TVMAgrib.setText(convertTimeToAM(azan.getMaghrib().substring(0, 5)));
        holder.TVEsha.setText(convertTimeToAM(azan.getIsha().substring(0, 5)));
        holder.TVDateToday.setText(azan.getDate_today());
        if (compareTwoTimes(convertTimeToAM(azan.getFajr().substring(0, 5)))) {
            holder.TVFagr.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        } else if (compareTwoTimes(convertTimeToAM(azan.getSunrise().substring(0, 5)))) {
            holder.TVSunrise.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else if (compareTwoTimes(convertTimeToAM(azan.getDhuhr().substring(0, 5)))) {
            holder.TVDauhr.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else if (compareTwoTimes(convertTimeToAM(azan.getAsr().substring(0, 5)))) {
            holder.TVAsr.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else if (compareTwoTimes(convertTimeToAM(azan.getMaghrib().substring(0, 5)))) {
            holder.TVMAgrib.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else if (compareTwoTimes(convertTimeToAM(azan.getIsha().substring(0, 5)))) {
            holder.TVEsha.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        }else {

        }

    }

    @Override
    public int getItemCount() {
        return azanList.size();
    }

    public static class RecyclerAzanViewHolder extends RecyclerView.ViewHolder {
        private View view;
        @BindView(R.id.TV_Fagr)
        TextView TVFagr;
        @BindView(R.id.TV_Sunrise)
        TextView TVSunrise;
        @BindView(R.id.TV_Dauhr)
        TextView TVDauhr;
        @BindView(R.id.TV_Asr)
        TextView TVAsr;
        @BindView(R.id.TV_Magrib)
        TextView TVMAgrib;
        @BindView(R.id.TV_Esha)
        TextView TVEsha;
        @BindView(R.id.TV_City)
        TextView TVCity;
        @BindView(R.id.TV_Date_Today)
        TextView TVDateToday;

        public RecyclerAzanViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }
    }

}
