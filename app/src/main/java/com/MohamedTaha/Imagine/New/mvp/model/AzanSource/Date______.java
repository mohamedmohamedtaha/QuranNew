
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date______ {

    @SerializedName("readable")
    @Expose
    private String readable;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("gregorian")
    @Expose
    private Gregorian______ gregorian;
    @SerializedName("hijri")
    @Expose
    private Hijri______ hijri;

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

    public Gregorian______ getGregorian() {
        return gregorian;
    }

    public void setGregorian(Gregorian______ gregorian) {
        this.gregorian = gregorian;
    }

    public Hijri______ getHijri() {
        return hijri;
    }

    public void setHijri(Hijri______ hijri) {
        this.hijri = hijri;
    }

}
