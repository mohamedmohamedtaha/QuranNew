package com.mohamedtaha.imagine.adapter

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.databinding.CustomAzanBinding
import com.mohamedtaha.imagine.util.ConvertTimes
import com.mohamedtaha.imagine.mvp.model.azan.Timings
import com.mohamedtaha.imagine.util.ClickListener
import java.util.*

class AdapterAzanVP(private val checkCity: ClickListener<Int>) :
    androidx.recyclerview.widget.ListAdapter<Timings, AdapterAzanVP.RecyclerAzanViewHolder>(
        DiffCallback()
    ) {
    private val show_timer_every_second = Timer()

//    private fun setData(recyclerAzanViewHolder: RecyclerAzanViewHolder, azan: Timings) {
//        recyclerAzanViewHolder.TVFagr!!.text =
//            ConvertTimes.convertTimeToAM(azan.fajr.substring(0, 5))
//        recyclerAzanViewHolder.TVSunrise!!.text =
//            ConvertTimes.convertTimeToAM(azan.sunrise.substring(0, 5))
//        recyclerAzanViewHolder.TVDauhr!!.text =
//            ConvertTimes.convertTimeToAM(azan.dhuhr.substring(0, 5))
//        recyclerAzanViewHolder.TVAsr!!.text = ConvertTimes.convertTimeToAM(azan.asr.substring(0, 5))
//        recyclerAzanViewHolder.TVMagrib!!.text =
//            ConvertTimes.convertTimeToAM(azan.maghrib.substring(0, 5))
//        recyclerAzanViewHolder.TVEsha!!.text =
//            ConvertTimes.convertTimeToAM(azan.isha.substring(0, 5))
//        recyclerAzanViewHolder.TVDateToday!!.text =
//            ConvertTimes.convertDateToFormatArabic(azan.date_today)
//        recyclerAzanViewHolder.TVCity!!.text = azan.city
//        recyclerAzanViewHolder.TVDateTodayHegry!!.text =
//            ConvertTimes.convertDateToFormatArabicHegry(azan.day_today_hegry)
//    }

    private fun checkTimeForChangeColorTextView(
        recyclerAzanViewHolder: RecyclerAzanViewHolder,
        azan: Timings
    ) {
        val context = recyclerAzanViewHolder.binding.root.context
        if (ConvertTimes.convertDate() == azan.date_today && ConvertTimes.compareTwoTimes(
                ConvertTimes.convertTimeToAM(azan.fajr?.substring(0, 5))
            )
        ) {
            recyclerAzanViewHolder.binding.TVFagr.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorAccent
                )
            )
            recyclerAzanViewHolder.binding.TFagr.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorAccent
                )
            )
            showTimer(recyclerAzanViewHolder.binding.TVShowTimeFagr, azan.fajr?.substring(0, 5) ?: "")
        } else if (ConvertTimes.convertDate() == azan.date_today && ConvertTimes.compareTwoTimes(
                ConvertTimes.convertTimeToAM(azan.sunrise?.substring(0, 5))
            )
        ) {
            recyclerAzanViewHolder.binding.TVSunrise.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorAccent
                )
            )
            recyclerAzanViewHolder.binding.TSunrise.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorAccent
                )
            )
            showTimer(recyclerAzanViewHolder.binding.TVShowTimeSunrise, azan.sunrise?.substring(0, 5) ?: "")
        } else if (ConvertTimes.convertDate() == azan.date_today && ConvertTimes.compareTwoTimes(
                ConvertTimes.convertTimeToAM(azan.dhuhr?.substring(0, 5))
            )
        ) {
            recyclerAzanViewHolder.binding.TVDauhr.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorAccent
                )
            )
            recyclerAzanViewHolder.binding.TDauhr.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorAccent
                )
            )
            showTimer(recyclerAzanViewHolder.binding.TVShowTimeDauhr, azan.dhuhr?.substring(0, 5) ?: "")
        } else if (ConvertTimes.convertDate() == azan.date_today && ConvertTimes.compareTwoTimes(
                ConvertTimes.convertTimeToAM(azan.asr?.substring(0, 5))
            )
        ) {
            recyclerAzanViewHolder.binding.TVAsr.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorAccent
                )
            )
            recyclerAzanViewHolder.binding.TAsr.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorAccent
                )
            )
            showTimer(recyclerAzanViewHolder.binding.TVShowTimeAsr, azan.asr?.substring(0, 5) ?: "")
        } else if (ConvertTimes.convertDate() == azan.date_today && ConvertTimes.compareTwoTimes(
                ConvertTimes.convertTimeToAM(azan.maghrib?.substring(0, 5))
            )
        ) {
            recyclerAzanViewHolder.binding.TVMagrib.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorAccent
                )
            )
            recyclerAzanViewHolder.binding.TMAgrib.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorAccent
                )
            )
            showTimer(recyclerAzanViewHolder.binding.TVShowTimeMagrib, azan.maghrib?.substring(0, 5) ?: "")
        } else if (ConvertTimes.convertDate() == azan.date_today && ConvertTimes.compareTwoTimes(
                ConvertTimes.convertTimeToAM(azan.isha?.substring(0, 5))
            )
        ) {
            recyclerAzanViewHolder.binding.TVEsha.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorAccent
                )
            )
            recyclerAzanViewHolder.binding.TEsha.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorAccent
                )
            )
            showTimer(recyclerAzanViewHolder.binding.TVShowTimeEsha, azan.isha?.substring(0, 5) ?: "")
        } else {
        }
    }

    private fun getTimeMillisSecond(hour: Int, minute: Int): Long {
        val setTime = Calendar.getInstance()
        setTime.timeInMillis = System.currentTimeMillis()
        setTime[Calendar.HOUR_OF_DAY] = hour
        setTime[Calendar.MINUTE] = minute
        return setTime.timeInMillis
    }

    private fun showPrayerTimeForward(
        recyclerAzanViewHolder: RecyclerAzanViewHolder,
        azan: Timings
    ) {

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        if (ConvertTimes.convertDate() == azan.date_today) {
            if (calendar.timeInMillis > getTimeMillisSecond(
                    Integer.valueOf(
                        azan.isha!!.substring(
                            0,
                            2
                        )
                    ), Integer.valueOf(azan.isha?.substring(3, 5) ?: "")
                )
                && calendar.timeInMillis < getTimeMillisSecond(
                    Integer.valueOf(
                        azan.fajr!!.substring(
                            0,
                            2
                        )
                    ), Integer.valueOf(azan.fajr?.substring(3, 5) ?: "")
                )
            ) {
                recyclerAzanViewHolder.binding.TVFagr.setTextColor(
                    ContextCompat.getColor(
                        recyclerAzanViewHolder.binding.root.context,
                        R.color.colorOrange
                    )
                )
                recyclerAzanViewHolder.binding.TFagr.setTextColor(
                    ContextCompat.getColor(
                        recyclerAzanViewHolder.binding.root.context,
                        R.color.colorOrange
                    )
                )
            } else if (calendar.timeInMillis > getTimeMillisSecond(
                    Integer.valueOf(
                        azan.fajr?.substring(
                            0,
                            2
                        ) ?: ""
                    ), Integer.valueOf(azan.fajr?.substring(3, 5) ?: "")
                )
                && calendar.timeInMillis < getTimeMillisSecond(
                    Integer.valueOf(
                        azan.sunrise?.substring(
                            0,
                            2
                        ) ?: ""
                    ), Integer.valueOf(azan.sunrise?.substring(3, 5) ?: "")
                )
            ) {
                recyclerAzanViewHolder.binding.TVSunrise.setTextColor(
                    ContextCompat.getColor(
                        recyclerAzanViewHolder.binding.root.context,
                        R.color.colorOrange
                    )
                )
                recyclerAzanViewHolder.binding.TSunrise.setTextColor(
                    ContextCompat.getColor(
                        recyclerAzanViewHolder.binding.root.context,
                        R.color.colorOrange
                    )
                )
            } else if (calendar.timeInMillis > getTimeMillisSecond(
                    Integer.valueOf(
                        azan.sunrise?.substring(
                            0,
                            2
                        ) ?: ""
                    ), Integer.valueOf(azan.sunrise?.substring(3, 5) ?: "")
                )
                && calendar.timeInMillis < getTimeMillisSecond(
                    Integer.valueOf(
                        azan.dhuhr?.substring(
                            0,
                            2
                        ) ?: ""
                    ), Integer.valueOf(azan.dhuhr?.substring(3, 5) ?: "")
                )
            ) {
                recyclerAzanViewHolder.binding.TVDauhr.setTextColor(
                    ContextCompat.getColor(
                        recyclerAzanViewHolder.binding.root.context,
                        R.color.colorOrange
                    )
                )
                recyclerAzanViewHolder.binding.TDauhr.setTextColor(
                    ContextCompat.getColor(
                        recyclerAzanViewHolder.binding.root.context,
                        R.color.colorOrange
                    )
                )
            } else if (calendar.timeInMillis > getTimeMillisSecond(
                    Integer.valueOf(
                        azan.dhuhr?.substring(
                            0,
                            2
                        ) ?: ""
                    ), Integer.valueOf(azan.dhuhr?.substring(3, 5) ?: "")
                )
                && calendar.timeInMillis < getTimeMillisSecond(
                    Integer.valueOf(
                        azan.asr?.substring(
                            0,
                            2
                        ) ?: ""
                    ), Integer.valueOf(azan.asr?.substring(3, 5) ?: "")
                )
            ) {
                recyclerAzanViewHolder.binding.TVAsr.setTextColor(
                    ContextCompat.getColor(
                        recyclerAzanViewHolder.binding.root.context,
                        R.color.colorOrange
                    )
                )
                recyclerAzanViewHolder.binding.TAsr.setTextColor(
                    ContextCompat.getColor(
                        recyclerAzanViewHolder.binding.root.context,
                        R.color.colorOrange
                    )
                )
            } else if (calendar.timeInMillis > getTimeMillisSecond(
                    Integer.valueOf(
                        azan.asr?.substring(
                            0,
                            2
                        ) ?: ""
                    ), Integer.valueOf(azan.asr?.substring(3, 5) ?: "")
                )
                && calendar.timeInMillis < getTimeMillisSecond(
                    Integer.valueOf(
                        azan.maghrib?.substring(
                            0,
                            2
                        ) ?: ""
                    ), Integer.valueOf(azan.maghrib?.substring(3, 5) ?: "")
                )
            ) {
                recyclerAzanViewHolder.binding.TVMagrib.setTextColor(
                    ContextCompat.getColor(
                        recyclerAzanViewHolder.binding.root.context,
                        R.color.colorOrange
                    )
                )
                recyclerAzanViewHolder.binding.TMAgrib.setTextColor(
                    ContextCompat.getColor(
                        recyclerAzanViewHolder.binding.root.context,
                        R.color.colorOrange
                    )
                )
            } else if (calendar.timeInMillis > getTimeMillisSecond(
                    Integer.valueOf(
                        azan.maghrib?.substring(
                            0,
                            2
                        ) ?: ""
                    ), Integer.valueOf(azan.maghrib?.substring(3, 5) ?: "")
                )
                && calendar.timeInMillis < getTimeMillisSecond(
                    Integer.valueOf(
                        azan.isha?.substring(
                            0,
                            2
                        ) ?: ""
                    ), Integer.valueOf(azan.isha?.substring(3, 5) ?: "")
                )
            ) {
                recyclerAzanViewHolder.binding.TVEsha.setTextColor(
                    ContextCompat.getColor(
                        recyclerAzanViewHolder.binding.root.context,
                        R.color.colorOrange
                    )
                )
                recyclerAzanViewHolder.binding.TEsha.setTextColor(
                    ContextCompat.getColor(
                        recyclerAzanViewHolder.binding.root.context,
                        R.color.colorOrange
                    )
                )
            } else {
            }
        }
    }

    private fun showTimer(textView: TextView?, prayer_times: String) {
        show_timer_every_menutes = object : CountDownTimer(20 * 60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                textView!!.visibility = View.VISIBLE
                textView.text =
                    ConvertTimes.compareTwoTimess(
                        ConvertTimes.convertTimeToAM(prayer_times))
            }

            override fun onFinish() {
                textView!!.visibility = View.INVISIBLE
            }
        }.start()
    }

    private fun showTimerForTextViewMethod(textView: TextView) {
        show_timer_for_text_view = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                textView.visibility = View.VISIBLE
            }

            override fun onFinish() {
                textView.visibility = View.INVISIBLE
                IsFirstTime = true
            }
        }.start()
    }
    class RecyclerAzanViewHolder(val binding: CustomAzanBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(timings: Timings,checkCity: ClickListener<Int>) {
            binding.azanModel = timings
            binding.IBRefresh.setOnClickListener { checkCity.onClick(it, position = timings.id) }

        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Timings>() {
        override fun areItemsTheSame(oldItem: Timings, newItem: Timings): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Timings, newItem: Timings): Boolean {
            return oldItem == newItem
        }

    }

    companion object {
        private var show_timer_every_menutes: CountDownTimer? = null
        private var show_timer_for_text_view: CountDownTimer? = null
        private var IsFirstTime = false
        fun cancelTimer() {
            if (show_timer_every_menutes != null) {
                show_timer_every_menutes!!.cancel()
                show_timer_every_menutes = null
            }
        }

        fun cancelTimerForTextView() {
            if (show_timer_for_text_view != null) {
                show_timer_for_text_view!!.cancel()
                show_timer_for_text_view = null
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAzanViewHolder {

        return RecyclerAzanViewHolder(
            CustomAzanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerAzanViewHolder, position: Int) {
        val azan = getItem(position)
        holder.bind(azan,checkCity)
        ConvertTimes.convertDateToFormatArabic(azan.date_today)
        checkTimeForChangeColorTextView(holder, azan)
        showPrayerTimeForward(holder, azan)

//        if (!IsFirstTime) {
//            if (convertDate().equals(azan.getDate_today())) {
//                showTimerForTextViewMethod(recyclerAzanViewHolder.TVShowMethod);
//            }
//        }
    }
}