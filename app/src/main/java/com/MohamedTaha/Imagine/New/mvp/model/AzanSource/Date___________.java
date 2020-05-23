
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date___________ {

    @SerializedName("readable")
    @Expose
    private String readable;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("gregorian")
    @Expose
    private Gregorian___________ gregorian;
    @SerializedName("hijri")
    @Expose
    private Hijri___________ hijri;

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

    public Gregorian___________ getGregorian() {
        return gregorian;
    }

    public void setGregorian(Gregorian___________ gregorian) {
        this.gregorian = gregorian;
    }

    public Hijri___________ getHijri() {
        return hijri;
    }

    public void setHijri(Hijri___________ hijri) {
        this.hijri = hijri;
    }

}
