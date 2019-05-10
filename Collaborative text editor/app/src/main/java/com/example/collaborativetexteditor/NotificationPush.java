package com.example.collaborativetexteditor;

import java.io.Serializable;

public class NotificationPush implements Serializable {
    private String Notification;
    private String Date;

    public NotificationPush() {
    }

    public String getNotification() {
        return Notification;
    }

    public void setNotification(String notification) {
        Notification = notification;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
