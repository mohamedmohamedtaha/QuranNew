
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gregorian_______ {

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
    private Weekday______________ weekday;
    @SerializedName("month")
    @Expose
    private Month______________ month;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("designation")
    @Expose
    private Designation______________ designation;

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

    public Weekday______________ getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday______________ weekday) {
        this.weekday = weekday;
    }

    public Month______________ getMonth() {
        return month;
    }

    public void setMonth(Month______________ month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Designation______________ getDesignation() {
        return designation;
    }

    public void setDesignation(Designation______________ designation) {
        this.designation = designation;
    }

}
