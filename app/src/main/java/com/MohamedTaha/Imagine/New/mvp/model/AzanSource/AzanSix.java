
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AzanSix {

    @SerializedName("timings")
    @Expose
    private Timings_____ timings;
    @SerializedName("date")
    @Expose
    private Date_____ date;
    @SerializedName("meta")
    @Expose
    private Meta_____ meta;

    public Timings_____ getTimings() {
        return timings;
    }

    public void setTimings(Timings_____ timings) {
        this.timings = timings;
    }

    public Date_____ getDate() {
        return date;
    }

    public void setDate(Date_____ date) {
        this.date = date;
    }

    public Meta_____ getMeta() {
        return meta;
    }

    public void setMeta(Meta_____ meta) {
        this.meta = meta;
    }

}
