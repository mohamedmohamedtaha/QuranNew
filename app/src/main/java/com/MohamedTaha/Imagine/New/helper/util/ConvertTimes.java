package com.MohamedTaha.Imagine.New.helper.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConvertTimes {
    public static boolean getIsTrue() {
        return isTrue;
    }

    private static boolean isTrue  ;
    public static String convertDate(){
        Date calendar = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String DateString = simpleDateFormat.format(calendar);
        return DateString;
    }
    public static String convertTimeToAM(String old_time){
        String final_time = null;
        SimpleDateFormat _24Hours = new SimpleDateFormat("hh:mm");
        SimpleDateFormat _12Hours = new SimpleDateFormat("hh:mm a");
        try {
            Date _24HourDt = _24Hours.parse(old_time);
            final_time= _12Hours.format(_24HourDt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return final_time;
    }
    public static String compareTwoTimess(String time_compare_string){
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
        long mills = current_time_date_after_editing.getTime() - time_compare_date.getTime() ;
        int hours =(int) mills / (1000 * 60 * 60);
        int mins =(int) (mills / (1000 * 60 ))% 60;
        //compare(hours,mins);
        return hours + " : " + mins;
    }

    public static boolean compareTwoTimes(String time_compare_string){
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
        long mills = current_time_date_after_editing.getTime() - time_compare_date.getTime() ;
        int hours =(int) mills / (1000 * 60 * 60);
        int mins =(int) (mills / (1000 * 60 ))% 60;
        //compare(hours,mins);
        //return hours + " : " + mins;
        return compare(hours,mins);

    }
    private static boolean compare(int hour, int mins){
        if (hour == 0 && mins <= 15 && mins >= -15){
             isTrue = true;
        }else {
            isTrue = false;
        }
        return isTrue;
    }
    public static String convertFromMilliSecondsToTime(Long milliSeconds){
        //Create a DateFormatter object for displaying date in specified format
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa");
//Create a calender object that will convert the date and time value in millisecond to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return simpleDateFormat.format(calendar.getTime());

    }

}
