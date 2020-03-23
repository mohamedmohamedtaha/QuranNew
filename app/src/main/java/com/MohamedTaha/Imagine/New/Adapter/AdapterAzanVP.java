package com.MohamedTaha.Imagine.New.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Timings;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.compareTwoTimes;
import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertDate;
import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertTimeToAM;

public class AdapterAzanVP extends PagerAdapter {
    private Context context;
    private List<Timings> azanList = new ArrayList<>();
    public AdapterAzanVP(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        if (azanList != null && azanList.size() >=0){
            return azanList.size();
        }
        else {
            return 0;
        }    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View row = LayoutInflater.from(context).inflate(R.layout.custom_azan,null);
        RecyclerAzanViewHolder recyclerAzanViewHolder = new RecyclerAzanViewHolder(row);
        Timings azan = azanList.get(position);

        recyclerAzanViewHolder.TVFagr.setText(convertTimeToAM(azan.getFajr().substring(0, 5)));
        recyclerAzanViewHolder.TVSunrise.setText(convertTimeToAM(azan.getSunrise().substring(0, 5)));
        recyclerAzanViewHolder.TVDauhr.setText(convertTimeToAM(azan.getDhuhr().substring(0, 5)));
        recyclerAzanViewHolder.TVAsr.setText(convertTimeToAM(azan.getAsr().substring(0, 5)));
        recyclerAzanViewHolder.TVMAgrib.setText(convertTimeToAM(azan.getMaghrib().substring(0, 5)));
        recyclerAzanViewHolder.TVEsha.setText(convertTimeToAM(azan.getIsha().substring(0, 5)));
        recyclerAzanViewHolder.TVDateToday.setText(azan.getDate_today());
        if (convertDate().equals(azan.getDate_today()) && compareTwoTimes(convertTimeToAM(azan.getFajr().substring(0, 5)))) {
            recyclerAzanViewHolder.TVFagr.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        } else if (convertDate().equals(azan.getDate_today()) && compareTwoTimes(convertTimeToAM(azan.getSunrise().substring(0, 5)))) {
            recyclerAzanViewHolder.TVSunrise.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else if (convertDate().equals(azan.getDate_today()) && compareTwoTimes(convertTimeToAM(azan.getDhuhr().substring(0, 5)))) {
            recyclerAzanViewHolder.TVDauhr.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else if (convertDate().equals(azan.getDate_today()) && compareTwoTimes(convertTimeToAM(azan.getAsr().substring(0, 5)))) {
            recyclerAzanViewHolder.TVAsr.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else if (convertDate().equals(azan.getDate_today()) && compareTwoTimes(convertTimeToAM(azan.getMaghrib().substring(0, 5)))) {
            recyclerAzanViewHolder.TVMAgrib.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else if (convertDate().equals(azan.getDate_today()) && compareTwoTimes(convertTimeToAM(azan.getIsha().substring(0, 5)))) {
            recyclerAzanViewHolder.TVEsha.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        }else {

        }
        container.addView(row);
    return row;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    public void setAzanList(List<Timings> azanList) {
        this.azanList = azanList;
        notifyDataSetChanged();
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
