package com.mohamedtaha.imagine;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

public final class RxBusData {
    private RxBusData() {
        // hidden constructor
    }
    private static PublishSubject<Object> behaviorSubject = PublishSubject.create();

    public static Disposable subscribe(Consumer<Object> action) {
        return behaviorSubject.subscribe(action);
    }

    //use this method to send data
    public static void sendData(Object send_data) {
        behaviorSubject.onNext(send_data);
    }
}
