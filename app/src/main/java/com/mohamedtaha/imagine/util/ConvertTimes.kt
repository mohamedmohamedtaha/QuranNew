package com.mohamedtaha.imagine.util

import android.util.Log
import com.mohamedtaha.imagine.util.ConvertTimes
import java.lang.Exception
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object ConvertTimes {
    fun convertDateToFormatArabic(current_date: String?): String? {
        var formattedDate: String? = null
        try {
            val locale = Locale("ar")
            val input_format = SimpleDateFormat("dd-MM-yyyy",locale)
            val output_format = SimpleDateFormat("EEE d MMM yyy", locale)
            //  SimpleDateFormat output_format = new SimpleDateFormat("EEE dd MMM- yyy", locale);
            val currDate = input_format.parse(current_date)
            formattedDate = output_format.format(currDate)
            Log.d("TAG", formattedDate)
        } catch (e: Exception) {
            Log.d("TAG", "Error: " + e.message)
        }
        return formattedDate
    }

    fun convertDateToFormatArabicHegry(current_date: String?): String {
        var formattedDay: String? = null
        var formattedMonth: String? = null
        var formattedYear: String? = null
        try {
            val locale = Locale("ar")
            val input_format = SimpleDateFormat("dd-MM-yyyy",locale)
            val output_day = SimpleDateFormat(" dd", locale)
            val output_month = SimpleDateFormat("M", Locale.getDefault())
            val output_year = SimpleDateFormat(" yyy", locale)
            val currDate = input_format.parse(current_date)
            formattedDay = output_day.format(currDate)
            formattedMonth = output_month.format(currDate)
            formattedYear = output_year.format(currDate)
        } catch (e: Exception) {
            Log.d("TAG", "Error: " + e.message)
        }
        return formattedDay + " " + getNameMonthHegry(formattedMonth) + " " + formattedYear
    }

    private fun getNameMonthHegry(month: String?): String? {
        var name_month: String? = null
        when (month) {
            "1", "١" -> name_month = "محرم"
            "2", "٢" -> name_month = "صفر"
            "3", "٣" -> name_month = "ربيع الأول"
            "4", "٤" -> name_month = "ربيع الآخر"
            "5", "٥" -> name_month = "جمادي الأولى"
            "6", "٦" -> name_month = "جمادي الآخره"
            "7", " ٧" -> name_month = "رجب"
            "8", "٨" -> name_month = "شعبان"
            "9", "٩" -> name_month = "رمصان"
            "10", "١٠" -> name_month = "شوال"
            "11", "١١" -> name_month = "ذو القعدة"
            "12", "١٢" -> name_month = "ذي الحجة"
            else -> {}
        }
        return name_month
    }

    var isTrue = false
        private set

    @JvmStatic
    fun convertDate(): String {
        val calendar = Calendar.getInstance().time
        val simpleDateFormat =
            SimpleDateFormat("dd-MM-yyyy", Locale.US)
        return simpleDateFormat.format(calendar)
    }

    fun convertTimeToAM(old_time: String?): String? {
        var final_time: String? = null
        val _24Hours = SimpleDateFormat("hh:mm")
        val _12Hours = SimpleDateFormat("hh:mm a")
        try {
            val _24HourDt = _24Hours.parse(old_time)
            final_time = _12Hours.format(_24HourDt)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return final_time
    }

    fun compareTwoTimess(time_compare_string: String?): String {
        val format = SimpleDateFormat("hh:mm aa")
        val current_time_date = Calendar.getInstance().time
        var time_compare_date: Date? = null
        var current_time_date_after_editing: Date? = null
        val currentTime_string: String
        try {
            currentTime_string = format.format(current_time_date)
            current_time_date_after_editing = format.parse(currentTime_string)
            time_compare_date = format.parse(time_compare_string)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val mills = current_time_date_after_editing!!.time - time_compare_date!!.time
        val hours = mills.toInt() / (1000 * 60 * 60)
        val mins = (mills / (1000 * 60)) as Int % 60
        val seconds = (mills % (1000 * 60 * 60) % (1000 * 60) / 1000) as Int
        return if (mills < -10 || mills == 0L) {
            String.format("%02d", mins)
        } else {
            String.format("+%02d", mins)
        }
    }

    fun compareTwoTimes(time_compare_string: String?): Boolean {
        val format = SimpleDateFormat("hh:mm aa")
        val current_time_date = Calendar.getInstance().time
        var time_compare_date: Date? = null
        var current_time_date_after_editing: Date? = null
        val currentTime_string: String
        try {
            currentTime_string = format.format(current_time_date)
            current_time_date_after_editing = format.parse(currentTime_string)
            time_compare_date = format.parse(time_compare_string)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val mills = current_time_date_after_editing!!.time - time_compare_date!!.time
        val hours = mills.toInt() / (1000 * 60 * 60)
        val mins = (mills / (1000 * 60)) as Int % 60
        return compare(hours, mins)
    }

    private fun compare(hour: Int, mins: Int): Boolean {
        if (hour == 0 && mins <= 15 && mins >= -15) {
            isTrue = true
        } else {
            isTrue = false
        }
        return isTrue
    }

    @JvmStatic
    fun convertFromMilliSecondsToTime(milliSeconds: Long?): String {
        //Create a DateFormatter object for displaying date in specified format
        val simpleDateFormat = SimpleDateFormat("hh:mm aa")
        //Create a calender object that will convert the date and time value in millisecond to date.
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds!!
        return simpleDateFormat.format(calendar.time)
    }

    fun convertMilliSecondsToTime24Hours(milliSeconds: Long?): String {
        //Create a DateFormatter object for displaying date in specified format
        val simpleDateFormat = SimpleDateFormat("hh:mm")
        //Create a calender object that will convert the date and time value in millisecond to date.
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds!!
        return simpleDateFormat.format(calendar.time)
    }
}