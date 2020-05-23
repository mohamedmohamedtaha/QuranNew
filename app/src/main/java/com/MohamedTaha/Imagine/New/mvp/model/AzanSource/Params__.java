
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Params__ {

    @SerializedName("Fajr")
    @Expose
    private Double fajr;
    @SerializedName("Isha")
    @Expose
    private String isha;

    public Double getFajr() {
        return fajr;
    }

    public void setFajr(Double fajr) {
        this.fajr = fajr;
    }

    public String getIsha() {
        return isha;
    }

    public void setIsha(String isha) {
        this.isha = isha;
    }

}
