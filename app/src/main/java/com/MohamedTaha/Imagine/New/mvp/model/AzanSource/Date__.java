
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date__ {

    @SerializedName("readable")
    @Expose
    private String readable;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("gregorian")
    @Expose
    private Gregorian__ gregorian;
    @SerializedName("hijri")
    @Expose
    private Hijri__ hijri;

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Gregorian__ getGregorian() {
        return gregorian;
    }

    public void setGregorian(Gregorian__ gregorian) {
        this.gregorian = gregorian;
    }

    public Hijri__ getHijri() {
        return hijri;
    }

    public void setHijri(Hijri__ hijri) {
        this.hijri = hijri;
    }

}
