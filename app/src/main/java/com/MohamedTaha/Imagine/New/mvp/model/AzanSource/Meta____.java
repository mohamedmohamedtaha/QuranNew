
package com.MohamedTaha.Imagine.New.mvp.model.AzanSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta____ {

    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("method")
    @Expose
    private Method____ method;
    @SerializedName("latitudeAdjustmentMethod")
    @Expose
    private String latitudeAdjustmentMethod;
    @SerializedName("midnightMode")
    @Expose
    private String midnightMode;
    @SerializedName("school")
    @Expose
    private String school;
    @SerializedName("offset")
    @Expose
    private Offset____ offset;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Method____ getMethod() {
        return method;
    }

    public void setMethod(Method____ method) {
        this.method = method;
    }

    public String getLatitudeAdjustmentMethod() {
        return latitudeAdjustmentMethod;
    }

    public void setLatitudeAdjustmentMethod(String latitudeAdjustmentMethod) {
        this.latitudeAdjustmentMethod = latitudeAdjustmentMethod;
    }

    public String getMidnightMode() {
        return midnightMode;
    }

    public void setMidnightMode(String midnightMode) {
        this.midnightMode = midnightMode;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Offset____ getOffset() {
        return offset;
    }

    public void setOffset(Offset____ offset) {
        this.offset = offset;
    }

}
