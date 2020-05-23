
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Azaneleven {

    @SerializedName("timings")
    @Expose
    private Timings__________ timings;
    @SerializedName("date")
    @Expose
    private Date__________ date;
    @SerializedName("meta")
    @Expose
    private Meta__________ meta;

    public Timings__________ getTimings() {
        return timings;
    }

    public void setTimings(Timings__________ timings) {
        this.timings = timings;
    }

    public Date__________ getDate() {
        return date;
    }

    public void setDate(Date__________ date) {
        this.date = date;
    }

    public Meta__________ getMeta() {
        return meta;
    }

    public void setMeta(Meta__________ meta) {
        this.meta = meta;
    }

}
