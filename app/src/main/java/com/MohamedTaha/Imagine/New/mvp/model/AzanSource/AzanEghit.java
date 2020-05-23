
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AzanEghit {

    @SerializedName("timings")
    @Expose
    private Timings_______ timings;
    @SerializedName("date")
    @Expose
    private Date_______ date;
    @SerializedName("meta")
    @Expose
    private Meta_______ meta;

    public Timings_______ getTimings() {
        return timings;
    }

    public void setTimings(Timings_______ timings) {
        this.timings = timings;
    }

    public Date_______ getDate() {
        return date;
    }

    public void setDate(Date_______ date) {
        this.date = date;
    }

    public Meta_______ getMeta() {
        return meta;
    }

    public void setMeta(Meta_______ meta) {
        this.meta = meta;
    }

}
