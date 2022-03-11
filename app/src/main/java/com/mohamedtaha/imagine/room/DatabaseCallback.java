package com.mohamedtaha.imagine.room;

public interface DatabaseCallback {
    void onPrayerTimesAdded();
    void onPrayerTimesDeleted();
    void getDataFromLocationAfterDeleteData();
    void onPrayerTimesError(Throwable e);
}
