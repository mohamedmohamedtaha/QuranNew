
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AzanSeven {

    @SerializedName("timings")
    @Expose
    private Timings______ timings;
    @SerializedName("date")
    @Expose
    private Date______ date;
    @SerializedName("meta")
    @Expose
    private Meta______ meta;

    public Timings______ getTimings() {
        return timings;
    }

    public void setTimings(Timings______ timings) {
        this.timings = timings;
    }

    public Date______ getDate() {
        return date;
    }

    public void setDate(Date______ date) {
        this.date = date;
    }

    public Meta______ getMeta() {
        return meta;
    }

    public void setMeta(Meta______ meta) {
        this.meta = meta;
    }

}
