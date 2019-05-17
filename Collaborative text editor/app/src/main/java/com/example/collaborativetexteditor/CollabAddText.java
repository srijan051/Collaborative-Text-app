package com.example.collaborativetexteditor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.collaborativetexteditor.AccountActivity.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class CollabAddText extends AppCompatActivity {

    EditText etcollabtitle, etcollabtext;
    Button btcollabsave;
    FilesAS filesAS;
    String currentDateandTime;
    String exporttextvalue;
    String exporttitlevalue;

    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;


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
    public boolean onCreateOptionsMenu(Menu export) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.export, export);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_export:
                try {
                    exporttotxt();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;

            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void exporttotxt() throws IOException {
        exporttextvalue = etcollabtext.getText().toString().trim();
        exporttitlevalue = etcollabtitle.getText().toString().trim();
        if (exporttitlevalue.isEmpty() || exporttextvalue.isEmpty()){
            Toast.makeText(CollabAddText.this, "Field is empty",Toast.LENGTH_SHORT).show();

        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permissions,WRITE_EXTERNAL_STORAGE_CODE);
                }
                else {
                    Savetotxtfile(exporttextvalue,exporttitlevalue);
                }
            }
            else {
                Savetotxtfile(exporttextvalue,exporttitlevalue);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case WRITE_EXTERNAL_STORAGE_CODE:{
                if (grantResults.length> 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    try {
                        Savetotxtfile(exporttextvalue, exporttitlevalue);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(this,"Storage Permission is required",Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    private void Savetotxtfile(String mText, String mTitle) throws IOException {


        File path = Environment.getExternalStorageDirectory();
        File dir = new File(path+"/CollabTextDocument/");
        dir.mkdirs();
        String filename =  exporttitlevalue+".txt";


        File file = new File(dir, filename);
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(exporttextvalue);
        bw.close();
        Toast.makeText(this,"File exported to "+dir,Toast.LENGTH_SHORT).show();
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
