
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AzanTwo {

    @SerializedName("timings")
    @Expose
    private Timings_ timings;
    @SerializedName("date")
    @Expose
    private Date_ date;
    @SerializedName("meta")
    @Expose
    private Meta_ meta;

    public Timings_ getTimings() {
        return timings;
    }

    public void setTimings(Timings_ timings) {
        this.timings = timings;
    }

    public Date_ getDate() {
        return date;
    }

    public void setDate(Date_ date) {
        this.date = date;
    }

    public Meta_ getMeta() {
        return meta;
    }

    public void setMeta(Meta_ meta) {
        this.meta = meta;
    }

}
