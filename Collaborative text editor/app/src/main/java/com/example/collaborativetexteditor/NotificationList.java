package com.example.collaborativetexteditor;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NotificationList extends ArrayAdapter<NotificationPush> {
    Activity context;
    private ArrayList<NotificationPush> filelist;

    public NotificationList(Activity context, ArrayList<NotificationPush> filelist) {
        super(context, R.layout.activity_listlayoutnotification, filelist);
        this.context = context;
        this.filelist = filelist;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_listlayoutnotification,null,true);
        TextView textViewNotification = listViewItem.findViewById(R.id.textView_notification);
        TextView textViewTime = listViewItem.findViewById(R.id.textView_time);
        NotificationPush notificationPush = filelist.get(position);

        textViewNotification.setText(notificationPush.getNotification());
        textViewTime.setText(notificationPush.getDate());
        return listViewItem;
    }
}


