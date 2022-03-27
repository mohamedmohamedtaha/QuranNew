package com.mohamedtaha.imagine.room

interface DatabaseCallback {
    fun onPrayerTimesAdded()
    fun onPrayerTimesDeleted()
    val dataFromLocationAfterDeleteData: Unit
    fun onPrayerTimesError(e: Throwable)
}