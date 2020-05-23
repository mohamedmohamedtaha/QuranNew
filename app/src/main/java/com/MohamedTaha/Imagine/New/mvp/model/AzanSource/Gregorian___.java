
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gregorian___ {

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
    private Weekday______ weekday;
    @SerializedName("month")
    @Expose
    private Month______ month;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("designation")
    @Expose
    private Designation______ designation;

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

    public Weekday______ getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday______ weekday) {
        this.weekday = weekday;
    }

    public Month______ getMonth() {
        return month;
    }

    public void setMonth(Month______ month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Designation______ getDesignation() {
        return designation;
    }

    public void setDesignation(Designation______ designation) {
        this.designation = designation;
    }

}
