
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gregorian________ {

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
    private Weekday________________ weekday;
    @SerializedName("month")
    @Expose
    private Month________________ month;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("designation")
    @Expose
    private Designation________________ designation;

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

    public Weekday________________ getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday________________ weekday) {
        this.weekday = weekday;
    }

    public Month________________ getMonth() {
        return month;
    }

    public void setMonth(Month________________ month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Designation________________ getDesignation() {
        return designation;
    }

    public void setDesignation(Designation________________ designation) {
        this.designation = designation;
    }

}
