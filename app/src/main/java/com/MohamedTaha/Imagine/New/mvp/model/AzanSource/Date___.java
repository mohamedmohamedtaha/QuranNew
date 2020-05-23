
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date___ {

    @SerializedName("readable")
    @Expose
    private String readable;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("gregorian")
    @Expose
    private Gregorian___ gregorian;
    @SerializedName("hijri")
    @Expose
    private Hijri___ hijri;

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

    public Gregorian___ getGregorian() {
        return gregorian;
    }

    public void setGregorian(Gregorian___ gregorian) {
        this.gregorian = gregorian;
    }

    public Hijri___ getHijri() {
        return hijri;
    }

    public void setHijri(Hijri___ hijri) {
        this.hijri = hijri;
    }

}
