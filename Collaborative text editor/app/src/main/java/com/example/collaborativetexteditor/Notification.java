package com.example.collaborativetexteditor;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {

    DatabaseReference reff;
    public ArrayList<NotificationPush> notificationASlist;
    NotificationPush notificationPush;
    private ListView notificationListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        reff = FirebaseDatabase.getInstance().getReference().child("Notification");
        notificationASlist = new ArrayList<>();
        notificationPush = new NotificationPush();
        notificationListView = findViewById(R.id.lv_notification);

    }

    @Override
    protected void onStart() {
        super.onStart();
        reff.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notificationASlist.clear();
                for (final DataSnapshot ds: dataSnapshot.getChildren()){
                    notificationPush = ds.getValue(NotificationPush.class);
                    notificationASlist.add(notificationPush);
                }
                NotificationList adapter = new NotificationList(Notification.this, notificationASlist);
                notificationListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
