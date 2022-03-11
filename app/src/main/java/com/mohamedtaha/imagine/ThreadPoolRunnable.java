package com.mohamedtaha.imagine;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.logging.Handler;

public class ThreadPoolRunnable implements Runnable {
    private static final String TAG = "ThreaPoolRunnable";
    private int startingIndex;
    private int chunkeSize;
    private WeakReference<Handler> mainThreaHandler;

    public ThreadPoolRunnable(int startingIndex, int chunkeSize, Handler mainThreaHandler) {
        this.startingIndex = startingIndex;
        this.chunkeSize = chunkeSize;
        this.mainThreaHandler = new WeakReference<>(mainThreaHandler);
    }

    @Override
    public void run() {
        int numTasks = Runtime.getRuntime().availableProcessors();
        Log.d(TAG," Retrive all images " + Thread.currentThread().getName());

    }
}
