
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date_____ {

    @SerializedName("readable")
    @Expose
    private String readable;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("gregorian")
    @Expose
    private Gregorian_____ gregorian;
    @SerializedName("hijri")
    @Expose
    private Hijri_____ hijri;

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

    public Gregorian_____ getGregorian() {
        return gregorian;
    }

    public void setGregorian(Gregorian_____ gregorian) {
        this.gregorian = gregorian;
    }

    public Hijri_____ getHijri() {
        return hijri;
    }

    public void setHijri(Hijri_____ hijri) {
        this.hijri = hijri;
    }

}
