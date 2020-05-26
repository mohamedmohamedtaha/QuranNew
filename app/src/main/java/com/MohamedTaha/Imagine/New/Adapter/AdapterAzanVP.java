package com.MohamedTaha.Imagine.New.Adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Timings;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.compareTwoTimes;
import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.compareTwoTimess;
import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertDate;
import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertDateToFormatArabic;
import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertTimeToAM;

public class AdapterAzanVP extends PagerAdapter {


    private Context context;
    private List<Timings> azanList = new ArrayList<>();
    private ClickListener checkCity;
    private Timer show_timer_every_second = new Timer();
    private static CountDownTimer show_timer_every_menutes;
    private static CountDownTimer show_timer_for_text_view;
    private static boolean IsFirstTime = false;


    public AdapterAzanVP(Context context, ClickListener checkCity) {
        this.context = context;
        this.checkCity = checkCity;
    }

    @Override
    public int getCount() {
        if (azanList != null && azanList.size() >= 0) {
            return azanList.size();
        } else {
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View row = LayoutInflater.from(context).inflate(R.layout.custom_azan, null);
        RecyclerAzanViewHolder recyclerAzanViewHolder = new RecyclerAzanViewHolder(row);
        Timings azan = azanList.get(position);
        convertDateToFormatArabic(azan.getDate_today());
        setData(recyclerAzanViewHolder, azan);
        checkTimeForChangeColorTextView(recyclerAzanViewHolder, azan);
        showPrayerTimeForward(recyclerAzanViewHolder, azan);
        if (!IsFirstTime) {
            showTimerForTextViewMethod(recyclerAzanViewHolder.TVShowMethod);
        }
        container.addView(row);
        return row;
    }

    private void setData(RecyclerAzanViewHolder recyclerAzanViewHolder, Timings azan) {
        recyclerAzanViewHolder.TVFagr.setText(convertTimeToAM(azan.getFajr().substring(0, 5)));
        recyclerAzanViewHolder.TVSunrise.setText(convertTimeToAM(azan.getSunrise().substring(0, 5)));
        recyclerAzanViewHolder.TVDauhr.setText(convertTimeToAM(azan.getDhuhr().substring(0, 5)));
        recyclerAzanViewHolder.TVAsr.setText(convertTimeToAM(azan.getAsr().substring(0, 5)));
        recyclerAzanViewHolder.TVMagrib.setText(convertTimeToAM(azan.getMaghrib().substring(0, 5)));
        recyclerAzanViewHolder.TVEsha.setText(convertTimeToAM(azan.getIsha().substring(0, 5)));
        recyclerAzanViewHolder.TVDateToday.setText(convertDateToFormatArabic(azan.getDate_today()));
        recyclerAzanViewHolder.TVCity.setText(azan.getCity());
        recyclerAzanViewHolder.IBRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkCity != null) {
                    checkCity.CheckCity();
                }
            }
        });
    }

    private void checkTimeForChangeColorTextView(RecyclerAzanViewHolder recyclerAzanViewHolder, Timings azan) {
        if (convertDate().equals(azan.getDate_today()) && compareTwoTimes(convertTimeToAM(azan.getFajr().substring(0, 5)))) {
            recyclerAzanViewHolder.TVFagr.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
            recyclerAzanViewHolder.TFagr.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
            showTimer(recyclerAzanViewHolder.TVShowTimeFagr, azan.getFajr().substring(0, 5));
        } else if (convertDate().equals(azan.getDate_today()) && compareTwoTimes(convertTimeToAM(azan.getSunrise().substring(0, 5)))) {
            recyclerAzanViewHolder.TVSunrise.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
            recyclerAzanViewHolder.TSunrise.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
            showTimer(recyclerAzanViewHolder.TVShowTimeSunrise, azan.getSunrise().substring(0, 5));
        } else if (convertDate().equals(azan.getDate_today()) && compareTwoTimes(convertTimeToAM(azan.getDhuhr().substring(0, 5)))) {
            recyclerAzanViewHolder.TVDauhr.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
            recyclerAzanViewHolder.TDauhr.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
            showTimer(recyclerAzanViewHolder.TVShowTimeDauhr, azan.getDhuhr().substring(0, 5));
        } else if (convertDate().equals(azan.getDate_today()) && compareTwoTimes(convertTimeToAM(azan.getAsr().substring(0, 5)))) {
            recyclerAzanViewHolder.TVAsr.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
            recyclerAzanViewHolder.TAsr.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
            showTimer(recyclerAzanViewHolder.TVShowTimeAsr, azan.getAsr().substring(0, 5));
        } else if (convertDate().equals(azan.getDate_today()) && compareTwoTimes(convertTimeToAM(azan.getMaghrib().substring(0, 5)))) {
            recyclerAzanViewHolder.TVMagrib.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
            recyclerAzanViewHolder.TMAgrib.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
            showTimer(recyclerAzanViewHolder.TVShowTimeMagrib, azan.getMaghrib().substring(0, 5));
        } else if (convertDate().equals(azan.getDate_today()) && compareTwoTimes(convertTimeToAM(azan.getIsha().substring(0, 5)))) {
            recyclerAzanViewHolder.TVEsha.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
            recyclerAzanViewHolder.TEsha.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
            showTimer(recyclerAzanViewHolder.TVShowTimeEsha, azan.getIsha().substring(0, 5));
        } else {
        }
    }

    private Long getTimeMillisSecond(int hour, int minute) {
        Calendar setTime = Calendar.getInstance();
        setTime.setTimeInMillis(System.currentTimeMillis());
        setTime.set(Calendar.HOUR_OF_DAY, hour);
        setTime.set(Calendar.MINUTE, minute);
        return setTime.getTimeInMillis();
    }

    private void showPrayerTimeForward(RecyclerAzanViewHolder recyclerAzanViewHolder, Timings azan) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        if (convertDate().equals(azan.getDate_today())) {
              if (calendar.getTimeInMillis() > getTimeMillisSecond(Integer.valueOf(azan.getIsha().substring(0, 2)), Integer.valueOf(azan.getIsha().substring(3, 5)))
                    && calendar.getTimeInMillis() < getTimeMillisSecond(Integer.valueOf(azan.getFajr().substring(0, 2)), Integer.valueOf(azan.getFajr().substring(3, 5)))) {
                recyclerAzanViewHolder.TFagr.setTextColor(ContextCompat.getColor(context, R.color.colorOrange));
                recyclerAzanViewHolder.TVFagr.setTextColor(ContextCompat.getColor(context, R.color.colorOrange));
            } else if (calendar.getTimeInMillis() > getTimeMillisSecond(Integer.valueOf(azan.getFajr().substring(0, 2)), Integer.valueOf(azan.getFajr().substring(3, 5)))
                    && calendar.getTimeInMillis() < getTimeMillisSecond(Integer.valueOf(azan.getSunrise().substring(0, 2)), Integer.valueOf(azan.getSunrise().substring(3, 5)))) {
                recyclerAzanViewHolder.TVSunrise.setTextColor(ContextCompat.getColor(context, R.color.colorOrange));
                recyclerAzanViewHolder.TSunrise.setTextColor(ContextCompat.getColor(context, R.color.colorOrange));
            } else if (calendar.getTimeInMillis() > getTimeMillisSecond(Integer.valueOf(azan.getSunrise().substring(0, 2)), Integer.valueOf(azan.getSunrise().substring(3, 5)))
                    && calendar.getTimeInMillis() < getTimeMillisSecond(Integer.valueOf(azan.getDhuhr().substring(0, 2)), Integer.valueOf(azan.getDhuhr().substring(3, 5)))) {
                recyclerAzanViewHolder.TVDauhr.setTextColor(ContextCompat.getColor(context, R.color.colorOrange));
                recyclerAzanViewHolder.TDauhr.setTextColor(ContextCompat.getColor(context, R.color.colorOrange));
            } else if (calendar.getTimeInMillis() > getTimeMillisSecond(Integer.valueOf(azan.getDhuhr().substring(0, 2)), Integer.valueOf(azan.getDhuhr().substring(3, 5)))
                    && calendar.getTimeInMillis() < getTimeMillisSecond(Integer.valueOf(azan.getAsr().substring(0, 2)), Integer.valueOf(azan.getAsr().substring(3, 5)))) {
                recyclerAzanViewHolder.TVAsr.setTextColor(ContextCompat.getColor(context, R.color.colorOrange));
                recyclerAzanViewHolder.TAsr.setTextColor(ContextCompat.getColor(context, R.color.colorOrange));
            } else if (calendar.getTimeInMillis() > getTimeMillisSecond(Integer.valueOf(azan.getAsr().substring(0, 2)), Integer.valueOf(azan.getAsr().substring(3, 5)))
                    && calendar.getTimeInMillis() < getTimeMillisSecond(Integer.valueOf(azan.getMaghrib().substring(0, 2)), Integer.valueOf(azan.getMaghrib().substring(3, 5)))) {
                recyclerAzanViewHolder.TVMagrib.setTextColor(ContextCompat.getColor(context, R.color.colorOrange));
                recyclerAzanViewHolder.TMAgrib.setTextColor(ContextCompat.getColor(context, R.color.colorOrange));
            } else if (calendar.getTimeInMillis() > getTimeMillisSecond(Integer.valueOf(azan.getMaghrib().substring(0, 2)), Integer.valueOf(azan.getMaghrib().substring(3, 5)))
                    && calendar.getTimeInMillis() < getTimeMillisSecond(Integer.valueOf(azan.getIsha().substring(0, 2)), Integer.valueOf(azan.getIsha().substring(3, 5)))) {
                recyclerAzanViewHolder.TVEsha.setTextColor(ContextCompat.getColor(context, R.color.colorOrange));
                recyclerAzanViewHolder.TEsha.setTextColor(ContextCompat.getColor(context, R.color.colorOrange));
            } else {
              }
        }
    }

    private void showTimer(TextView textView, String prayer_times) {
        show_timer_every_menutes = new CountDownTimer(20 * 60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(compareTwoTimess(convertTimeToAM(prayer_times)));
            }

            @Override
            public void onFinish() {
                textView.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

    private void showTimerForTextViewMethod(TextView textView) {
        show_timer_for_text_view = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                textView.setVisibility(View.INVISIBLE);
                IsFirstTime = true;
            }
        }.start();
    }

    public static void cancelTimer() {
        if (show_timer_every_menutes != null) {
            show_timer_every_menutes.cancel();
            show_timer_every_menutes = null;

        }
    }

    public static void cancelTimerForTextView() {
        if (show_timer_for_text_view != null) {
            show_timer_for_text_view.cancel();
            show_timer_for_text_view = null;

        }
    }

    public interface ClickListener {
        void CheckCity();
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
        @BindView(R.id.TV_Magrib)
        TextView TVMagrib;
        @BindView(R.id.TV_Esha)
        TextView TVEsha;
        @BindView(R.id.TV_City)
        TextView TVCity;
        @BindView(R.id.TV_Date_Today)
        TextView TVDateToday;
        @BindView(R.id.IB_Refresh)
        ImageButton IBRefresh;
        @BindView(R.id.TV_Show_Time_Fagr)
        TextView TVShowTimeFagr;
        @BindView(R.id.TV_Show_Time_sunrise)
        TextView TVShowTimeSunrise;
        @BindView(R.id.TV_Show_Time_Dauhr)
        TextView TVShowTimeDauhr;
        @BindView(R.id.TV_Show_Time_Asr)
        TextView TVShowTimeAsr;
        @BindView(R.id.TV_Show_Time_Magrib)
        TextView TVShowTimeMagrib;
        @BindView(R.id.TV_Show_Time_Esha)
        TextView TVShowTimeEsha;
        @BindView(R.id.TV_Show_Method)
        TextView TVShowMethod;
        @BindView(R.id.T_Fagr)
        TextView TFagr;
        @BindView(R.id.T_Sunrise)
        TextView TSunrise;
        @BindView(R.id.T_Dauhr)
        TextView TDauhr;
        @BindView(R.id.T_Asr)
        TextView TAsr;
        @BindView(R.id.T_MAgrib)
        TextView TMAgrib;
        @BindView(R.id.T_Esha)
        TextView TEsha;
        public RecyclerAzanViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
