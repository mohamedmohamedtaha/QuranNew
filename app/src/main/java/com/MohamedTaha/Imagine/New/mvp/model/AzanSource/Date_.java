
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date_ {

    @SerializedName("readable")
    @Expose
    private String readable;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("gregorian")
    @Expose
    private Gregorian_ gregorian;
    @SerializedName("hijri")
    @Expose
    private Hijri_ hijri;

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

    public Gregorian_ getGregorian() {
        return gregorian;
    }

    public void setGregorian(Gregorian_ gregorian) {
        this.gregorian = gregorian;
    }

    public Hijri_ getHijri() {
        return hijri;
    }

    public void setHijri(Hijri_ hijri) {
        this.hijri = hijri;
    }

}
