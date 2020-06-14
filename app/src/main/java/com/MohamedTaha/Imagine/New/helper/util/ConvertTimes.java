package com.MohamedTaha.Imagine.New.helper.util;

import android.os.Build;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ConvertTimes {
    public static boolean getIsTrue() {
        return isTrue;
    }

    public static String convertDateToFormatArabic(String current_date) {
        String formattedDate = null;
        try {
            Locale locale = new Locale("ar");
            SimpleDateFormat input_format = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat output_format = new SimpleDateFormat("EEE d MMM yyy", locale);
            //  SimpleDateFormat output_format = new SimpleDateFormat("EEE dd MMM- yyy", locale);
            Date currDate = input_format.parse(current_date);
            formattedDate = output_format.format(currDate);
            Log.d("TAG", formattedDate);
        } catch (Exception e) {
            Log.d("TAG", "Error: " + e.getMessage());
        }
        return formattedDate;
    }

    public static String convertDateToFormatArabicHegry(String current_date) {
        String formattedDay = null;
        String formattedMonth = null;
        String formattedYear = null;
        try {
            Locale locale = new Locale("ar");
            SimpleDateFormat input_format = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat output_day = new SimpleDateFormat(" dd", locale);
            SimpleDateFormat output_month = new SimpleDateFormat("M", Locale.getDefault());
            SimpleDateFormat output_year = new SimpleDateFormat(" yyy", locale);

            Date currDate = input_format.parse(current_date);
             formattedDay = output_day.format(currDate);
             formattedMonth = output_month.format(currDate);
             formattedYear = output_year.format(currDate);
        } catch (Exception e) {
            Log.d("TAG", "Error: " + e.getMessage());
        }
        return formattedDay + " " + getNameMonthHegry(formattedMonth) + " " + formattedYear;
    }

    private static String getNameMonthHegry(String month) {
        String name_month = null;
        switch (month) {
            case "1":
            case "١":
                name_month = "محرم";
                break;
            case "2":
            case "٢":
                name_month = "صفر";
                break;
            case "3":
            case "٣":
                name_month = "ربيع الأول";
                break;
            case "4":
            case "٤":
                name_month = "ربيع الآخر";
                break;
            case "5":
            case "٥":
                name_month = "جمادي الأولى";
                break;
            case "6":
            case "٦" :
                name_month = "جمادي الآخره";
                break;
            case "7":
            case " ٧":
                name_month = "رجب";
                break;
            case "8":
            case "٨":
                name_month = "شعبان";
                break;
            case "9":
            case "٩":
                name_month = "رمصان";
                break;
            case "10":
            case "١٠":
                name_month = "شوال";
                break;
            case "11":
            case "١١":
                name_month = "ذو القعدة";
                break;
            case "12":
            case "١٢":
                name_month = "ذي الحجة";
                break;
            default:
                break;
        }
        return name_month;

    }

    private static boolean isTrue;

    public static String convertDate() {
        Date calendar = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy",Locale.US);
        String DateString = simpleDateFormat.format(calendar);
        return DateString;
    }

    public static String convertTimeToAM(String old_time) {
        String final_time = null;
        SimpleDateFormat _24Hours = new SimpleDateFormat("hh:mm");
        SimpleDateFormat _12Hours = new SimpleDateFormat("hh:mm a");
        try {
            Date _24HourDt = _24Hours.parse(old_time);
            final_time = _12Hours.format(_24HourDt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return final_time;
    }

    public static String compareTwoTimess(String time_compare_string) {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm aa");
        Date current_time_date = Calendar.getInstance().getTime();
        Date time_compare_date = null;
        Date current_time_date_after_editing = null;

        String currentTime_string;
        try {
            currentTime_string = format.format(current_time_date);
            current_time_date_after_editing = format.parse(currentTime_string);
            time_compare_date = format.parse(time_compare_string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long mills = current_time_date_after_editing.getTime() - time_compare_date.getTime();
        int hours = (int) mills / (1000 * 60 * 60);
        int mins = (int) (mills / (1000 * 60)) % 60;
        int seconds = (int) ((mills % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        if (mills < -10 || mills == 0) {
            return String.format("%02d", mins);
        } else {
            return String.format("+%02d", mins);
        }
    }

    public static boolean compareTwoTimes(String time_compare_string) {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm aa");
        Date current_time_date = Calendar.getInstance().getTime();
        Date time_compare_date = null;
        Date current_time_date_after_editing = null;

        String currentTime_string;
        try {
            currentTime_string = format.format(current_time_date);
            current_time_date_after_editing = format.parse(currentTime_string);
            time_compare_date = format.parse(time_compare_string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long mills = current_time_date_after_editing.getTime() - time_compare_date.getTime();
        int hours = (int) mills / (1000 * 60 * 60);
        int mins = (int) (mills / (1000 * 60)) % 60;
        return compare(hours, mins);

    }

    private static boolean compare(int hour, int mins) {
        if (hour == 0 && mins <= 15 && mins >= -15) {
            isTrue = true;
        } else {
            isTrue = false;
        }
        return isTrue;
    }

    public static String convertFromMilliSecondsToTime(Long milliSeconds) {
        //Create a DateFormatter object for displaying date in specified format
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa");
//Create a calender object that will convert the date and time value in millisecond to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return simpleDateFormat.format(calendar.getTime());

    }


    public static String convertMilliSecondsToTime24Hours(Long milliSeconds) {
        //Create a DateFormatter object for displaying date in specified format
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
//Create a calender object that will convert the date and time value in millisecond to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return simpleDateFormat.format(calendar.getTime());

    }

}
