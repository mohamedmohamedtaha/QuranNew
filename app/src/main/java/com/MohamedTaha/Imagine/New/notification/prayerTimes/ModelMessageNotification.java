package com.MohamedTaha.Imagine.New.notification.prayerTimes;
public class ModelMessageNotification {
    Long time_payer;
    String text_notification;
    int number_azkar;

    public int getNumber_azkar() {
        return number_azkar;
    }

    public void setNumber_azkar(int number_azkar) {
        this.number_azkar = number_azkar;
    }

    public ModelMessageNotification(Long time_payer, String text_notification,int number_azkar) {
        this.time_payer = time_payer;
        this.text_notification = text_notification;
        this.number_azkar = number_azkar;
    }
    public ModelMessageNotification(Long time_payer, String text_notification) {
        this.time_payer = time_payer;
        this.text_notification = text_notification;
    }

    public ModelMessageNotification() {
    }

    public Long getTime_payer() {
        return time_payer;
    }

    public void setTime_payer(Long time_payer) {
        this.time_payer = time_payer;
    }

    public String getText_notification() {
        return text_notification;
    }

    public void setText_notification(String text_notification) {
        this.text_notification = text_notification;
    }
}
