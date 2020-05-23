
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AzanTwelve {

    @SerializedName("timings")
    @Expose
    private Timings___________ timings;
    @SerializedName("date")
    @Expose
    private Date___________ date;
    @SerializedName("meta")
    @Expose
    private Meta___________ meta;

    public Timings___________ getTimings() {
        return timings;
    }

    public void setTimings(Timings___________ timings) {
        this.timings = timings;
    }

    public Date___________ getDate() {
        return date;
    }

    public void setDate(Date___________ date) {
        this.date = date;
    }

    public Meta___________ getMeta() {
        return meta;
    }

    public void setMeta(Meta___________ meta) {
        this.meta = meta;
    }

}
