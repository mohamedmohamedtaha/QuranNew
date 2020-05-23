
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AzanThree {

    @SerializedName("timings")
    @Expose
    private Timings__ timings;
    @SerializedName("date")
    @Expose
    private Date__ date;
    @SerializedName("meta")
    @Expose
    private Meta__ meta;

    public Timings__ getTimings() {
        return timings;
    }

    public void setTimings(Timings__ timings) {
        this.timings = timings;
    }

    public Date__ getDate() {
        return date;
    }

    public void setDate(Date__ date) {
        this.date = date;
    }

    public Meta__ getMeta() {
        return meta;
    }

    public void setMeta(Meta__ meta) {
        this.meta = meta;
    }

}
