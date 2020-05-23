
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AzanNine {

    @SerializedName("timings")
    @Expose
    private Timings________ timings;
    @SerializedName("date")
    @Expose
    private Date________ date;
    @SerializedName("meta")
    @Expose
    private Meta________ meta;

    public Timings________ getTimings() {
        return timings;
    }

    public void setTimings(Timings________ timings) {
        this.timings = timings;
    }

    public Date________ getDate() {
        return date;
    }

    public void setDate(Date________ date) {
        this.date = date;
    }

    public Meta________ getMeta() {
        return meta;
    }

    public void setMeta(Meta________ meta) {
        this.meta = meta;
    }

}
