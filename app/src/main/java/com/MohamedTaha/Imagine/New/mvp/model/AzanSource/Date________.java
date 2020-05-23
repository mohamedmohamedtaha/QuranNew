
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date________ {

    @SerializedName("readable")
    @Expose
    private String readable;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("gregorian")
    @Expose
    private Gregorian________ gregorian;
    @SerializedName("hijri")
    @Expose
    private Hijri________ hijri;

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

    public Gregorian________ getGregorian() {
        return gregorian;
    }

    public void setGregorian(Gregorian________ gregorian) {
        this.gregorian = gregorian;
    }

    public Hijri________ getHijri() {
        return hijri;
    }

    public void setHijri(Hijri________ hijri) {
        this.hijri = hijri;
    }

}
