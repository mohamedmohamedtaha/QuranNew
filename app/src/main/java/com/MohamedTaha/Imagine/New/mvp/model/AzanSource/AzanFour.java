
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AzanFour {

    @SerializedName("timings")
    @Expose
    private Timings___ timings;
    @SerializedName("date")
    @Expose
    private Date___ date;
    @SerializedName("meta")
    @Expose
    private Meta___ meta;

    public Timings___ getTimings() {
        return timings;
    }

    public void setTimings(Timings___ timings) {
        this.timings = timings;
    }

    public Date___ getDate() {
        return date;
    }

    public void setDate(Date___ date) {
        this.date = date;
    }

    public Meta___ getMeta() {
        return meta;
    }

    public void setMeta(Meta___ meta) {
        this.meta = meta;
    }

}
