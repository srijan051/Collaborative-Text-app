package com.example.collaborativetexteditor;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class CollabAddText extends AppCompatActivity {

    EditText etcollabtitle, etcollabtext;
    Button btcollabsave;
    FilesAS filesAS;
    String currentDateandTime;

    //inseerting notification
    DatabaseReference reff;
    NotificationPush notificationPush;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collab_add_text);

        //inserting notification
        reff = FirebaseDatabase.getInstance().getReference().child("Notification");
        notificationPush = new NotificationPush();

        etcollabtitle = findViewById(R.id.editText_collabtitle);
        etcollabtext = findViewById(R.id.editText_collabtext);
        btcollabsave = findViewById(R.id.button_save_collab_add_text);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Home.EXTRA_MESSAGE);
        FilesAS item = (FilesAS) bundle.getSerializable("OBJECT");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
        currentDateandTime = sdf.format(new Date());

        etcollabtitle.setText(item.getTitle());
        etcollabtext.setText(item.getText());
        final String fid = item.getFileid();

        filesAS = new FilesAS();
        btcollabsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etcollabtitle.getText().toString().trim();
                String text = etcollabtext.getText().toString().trim();
                String notid = reff.push().getKey();
                notificationPush.setNotification("File "+title + " has been updated");
                notificationPush.setDate(currentDateandTime);
                reff.child(notid).setValue(notificationPush);

                if (TextUtils.isEmpty(title)){
                    etcollabtitle.setError("Title Required");
                    return;
                }
                updatecollab(fid,title,text,currentDateandTime);

            }
        });
    }

    private boolean updatecollab(String id, String title, String text, String date){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Files").child(id);
        filesAS.setTitle(title);
        filesAS.setText(text);
        filesAS.setFileid(id);
        filesAS.setDate(date);
        databaseReference.setValue(filesAS);
        Toast.makeText(this, "File Updated",Toast.LENGTH_SHORT).show();
        return true;

    }


}
