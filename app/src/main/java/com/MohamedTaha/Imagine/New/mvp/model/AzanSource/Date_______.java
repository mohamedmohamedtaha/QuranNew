
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date_______ {

    @SerializedName("readable")
    @Expose
    private String readable;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("gregorian")
    @Expose
    private Gregorian_______ gregorian;
    @SerializedName("hijri")
    @Expose
    private Hijri_______ hijri;

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

    public Gregorian_______ getGregorian() {
        return gregorian;
    }

    public void setGregorian(Gregorian_______ gregorian) {
        this.gregorian = gregorian;
    }

    public Hijri_______ getHijri() {
        return hijri;
    }

    public void setHijri(Hijri_______ hijri) {
        this.hijri = hijri;
    }

}
