
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date__________ {

    @SerializedName("readable")
    @Expose
    private String readable;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("gregorian")
    @Expose
    private Gregorian__________ gregorian;
    @SerializedName("hijri")
    @Expose
    private Hijri__________ hijri;

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

    public Gregorian__________ getGregorian() {
        return gregorian;
    }

    public void setGregorian(Gregorian__________ gregorian) {
        this.gregorian = gregorian;
    }

    public Hijri__________ getHijri() {
        return hijri;
    }

    public void setHijri(Hijri__________ hijri) {
        this.hijri = hijri;
    }

}
