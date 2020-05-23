
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AzanTen {

    @SerializedName("timings")
    @Expose
    private Timings_________ timings;
    @SerializedName("date")
    @Expose
    private Date_________ date;
    @SerializedName("meta")
    @Expose
    private Meta_________ meta;

    public Timings_________ getTimings() {
        return timings;
    }

    public void setTimings(Timings_________ timings) {
        this.timings = timings;
    }

    public Date_________ getDate() {
        return date;
    }

    public void setDate(Date_________ date) {
        this.date = date;
    }

    public Meta_________ getMeta() {
        return meta;
    }

    public void setMeta(Meta_________ meta) {
        this.meta = meta;
    }

}
