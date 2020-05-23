
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hijri_________ {

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
    private Weekday___________________ weekday;
    @SerializedName("month")
    @Expose
    private Month___________________ month;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("designation")
    @Expose
    private Designation___________________ designation;
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

    public Weekday___________________ getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday___________________ weekday) {
        this.weekday = weekday;
    }

    public Month___________________ getMonth() {
        return month;
    }

    public void setMonth(Month___________________ month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Designation___________________ getDesignation() {
        return designation;
    }

    public void setDesignation(Designation___________________ designation) {
        this.designation = designation;
    }

    public List<Object> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<Object> holidays) {
        this.holidays = holidays;
    }

}
