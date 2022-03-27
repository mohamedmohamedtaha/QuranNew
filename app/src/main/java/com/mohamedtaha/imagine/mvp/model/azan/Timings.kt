package com.mohamedtaha.imagine.mvp.model.azan

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "prayer_time")
class Timings {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_prayer_time")
    var id = 0
    var date_today: String? = null
    var city: String? = null
    var note: String? = null

    @SerializedName("Fajr")
    @Expose
    var fajr: String? = null

    @SerializedName("Sunrise")
    @Expose
    var sunrise: String? = null

    @SerializedName("Dhuhr")
    @Expose
    var dhuhr: String? = null

    @SerializedName("Asr")
    @Expose
    var asr: String? = null

    @SerializedName("Sunset")
    @Expose
    var sunset: String? = null

    @SerializedName("Maghrib")
    @Expose
    var maghrib: String? = null

    @SerializedName("Isha")
    @Expose
    var isha: String? = null

    @SerializedName("Imsak")
    @Expose
    var imsak: String? = null

    @SerializedName("Midnight")
    @Expose
    var midnight: String? = null
    var id_seq = 0
    var day_today_hegry: String? = null

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}