package com.MohamedTaha.Imagine.New.room;

public interface DatabaseCallback {
    void onPrayerTimesAdded();
    void onPrayerTimesDeleted();
    void getDataFromLocationAfterDeleteData();
    void onPrayerTimesError(Throwable e);
}
