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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.compareTwoTimes;
import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertTimeToAM;

public class AdapterAzan extends RecyclerView.Adapter<AdapterAzan.RecyclerAzanViewHolder> {
    private Context context;
    private List<Datum> azanList = new ArrayList<>();

    public AdapterAzan(Context context) {
        this.context = context;
    }

    public void setAzanList(List<Datum> azanList) {
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
        Datum azan = azanList.get(position);

        holder.TVFagr.setText(convertTimeToAM(azan.getTimings().getFajr().substring(0, 5)));
        holder.TVSunrise.setText(convertTimeToAM(azan.getTimings().getSunrise().substring(0, 5)));
        holder.TVDauhr.setText(convertTimeToAM(azan.getTimings().getDhuhr().substring(0, 5)));
        holder.TVAsr.setText(convertTimeToAM(azan.getTimings().getAsr().substring(0, 5)));
        holder.TVMAgrib.setText(convertTimeToAM(azan.getTimings().getMaghrib().substring(0, 5)));
        holder.TVEsha.setText(convertTimeToAM(azan.getTimings().getIsha().substring(0, 5)));
        holder.TVDateToday.setText(azan.getDate().getGregorian().getDate());
        if (compareTwoTimes(convertTimeToAM(azan.getTimings().getFajr().substring(0, 5)))) {
            holder.TVFagr.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        } else if (compareTwoTimes(convertTimeToAM(azan.getTimings().getSunrise().substring(0, 5)))) {
            holder.TVSunrise.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else if (compareTwoTimes(convertTimeToAM(azan.getTimings().getDhuhr().substring(0, 5)))) {
            holder.TVDauhr.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else if (compareTwoTimes(convertTimeToAM(azan.getTimings().getAsr().substring(0, 5)))) {
            holder.TVAsr.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else if (compareTwoTimes(convertTimeToAM(azan.getTimings().getMaghrib().substring(0, 5)))) {
            holder.TVMAgrib.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else if (compareTwoTimes(convertTimeToAM(azan.getTimings().getIsha().substring(0, 5)))) {
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
        @BindView(R.id.TV_MAgrib)
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
