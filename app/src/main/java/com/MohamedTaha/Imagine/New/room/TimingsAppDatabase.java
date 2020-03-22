package com.MohamedTaha.Imagine.New.room;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.MohamedTaha.Imagine.New.mvp.model.azan.Timings;

@Database(entities = {Timings.class},version = 1,exportSchema = false)
public abstract class TimingsAppDatabase extends RoomDatabase {
    private static final String TAG = TimingsAppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "timingsdb";
    private static TimingsAppDatabase mInstance;
    public abstract TimingsDao timingsDao();
    public static TimingsAppDatabase getInstance(Context context){
        if (mInstance == null){
           // synchronized (LOCK){
            synchronized (TimingsAppDatabase.class){
                if (mInstance == null){
                Log.d(TAG, "getInstance: Creating a new database instance");
                mInstance = Room.databaseBuilder(context.getApplicationContext(),
                        TimingsAppDatabase.class,
                        TimingsAppDatabase.DATABASE_NAME).build();
            }}
            Log.d(TAG,"getInstance :  Getting the database instance, no need to recreated it.");
        }
        return mInstance;
    }
    /**
     * Populate Database Section
     */
    private static RoomDatabase.Callback mRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}
