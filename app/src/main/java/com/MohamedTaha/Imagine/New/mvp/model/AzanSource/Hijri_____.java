
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hijri_____ {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("weekday")
    @Expose
    private Weekday___________ weekday;
    @SerializedName("month")
    @Expose
    private Month___________ month;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("designation")
    @Expose
    private Designation___________ designation;
    @SerializedName("holidays")
    @Expose
    private List<Object> holidays = null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Weekday___________ getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday___________ weekday) {
        this.weekday = weekday;
    }

    public Month___________ getMonth() {
        return month;
    }

    public void setMonth(Month___________ month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Designation___________ getDesignation() {
        return designation;
    }

    public void setDesignation(Designation___________ designation) {
        this.designation = designation;
    }

    public List<Object> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<Object> holidays) {
        this.holidays = holidays;
    }

}
