
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date_________ {

    @SerializedName("readable")
    @Expose
    private String readable;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("gregorian")
    @Expose
    private Gregorian_________ gregorian;
    @SerializedName("hijri")
    @Expose
    private Hijri_________ hijri;

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

    public Gregorian_________ getGregorian() {
        return gregorian;
    }

    public void setGregorian(Gregorian_________ gregorian) {
        this.gregorian = gregorian;
    }

    public Hijri_________ getHijri() {
        return hijri;
    }

    public void setHijri(Hijri_________ hijri) {
        this.hijri = hijri;
    }

}
