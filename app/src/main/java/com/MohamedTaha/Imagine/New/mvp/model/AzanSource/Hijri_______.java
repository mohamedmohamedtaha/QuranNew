
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hijri_______ {

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
    private Weekday_______________ weekday;
    @SerializedName("month")
    @Expose
    private Month_______________ month;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("designation")
    @Expose
    private Designation_______________ designation;
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

    public Weekday_______________ getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday_______________ weekday) {
        this.weekday = weekday;
    }

    public Month_______________ getMonth() {
        return month;
    }

    public void setMonth(Month_______________ month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Designation_______________ getDesignation() {
        return designation;
    }

    public void setDesignation(Designation_______________ designation) {
        this.designation = designation;
    }

    public List<Object> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<Object> holidays) {
        this.holidays = holidays;
    }

}
