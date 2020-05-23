
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date____ {

    @SerializedName("readable")
    @Expose
    private String readable;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("gregorian")
    @Expose
    private Gregorian____ gregorian;
    @SerializedName("hijri")
    @Expose
    private Hijri____ hijri;

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

    public Gregorian____ getGregorian() {
        return gregorian;
    }

    public void setGregorian(Gregorian____ gregorian) {
        this.gregorian = gregorian;
    }

    public Hijri____ getHijri() {
        return hijri;
    }

    public void setHijri(Hijri____ hijri) {
        this.hijri = hijri;
    }

}
