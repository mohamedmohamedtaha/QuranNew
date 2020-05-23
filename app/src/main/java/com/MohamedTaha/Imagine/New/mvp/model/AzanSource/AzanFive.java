
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AzanFive {

    @SerializedName("timings")
    @Expose
    private Timings____ timings;
    @SerializedName("date")
    @Expose
    private Date____ date;
    @SerializedName("meta")
    @Expose
    private Meta____ meta;

    public Timings____ getTimings() {
        return timings;
    }

    public void setTimings(Timings____ timings) {
        this.timings = timings;
    }

    public Date____ getDate() {
        return date;
    }

    public void setDate(Date____ date) {
        this.date = date;
    }

    public Meta____ getMeta() {
        return meta;
    }

    public void setMeta(Meta____ meta) {
        this.meta = meta;
    }

}
