package com.example.collaborativetexteditor;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

public class CollabAddTitle extends AppCompatActivity {

    DatabaseReference reff;
    EditText etcollabaddtitle;
    Button btcollabsavetitle;
    FilesAS filesAS;
    ArrayList<String> title = new ArrayList<>();
    String currentDateandTime;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collab_add_title);
        //getting date time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
        currentDateandTime = sdf.format(new Date());

        reff = FirebaseDatabase.getInstance().getReference().child("Files");
        filesAS = new FilesAS();

        etcollabaddtitle = findViewById(R.id.editText_collabAddtitle);
        btcollabsavetitle = findViewById(R.id.button_save_collab_title);
        btcollabsavetitle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                fileadd();
                Log.d("open","open5");
            }
        });
    }

    private void fileadd() {
        if (etcollabaddtitle.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(CollabAddTitle.this, "Please fill the Title", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            Log.d("open","open6");
            aftrcheck();
        }
    }
    public void aftrcheck(){
        String id = reff.push().getKey();
        filesAS.setFileid(id);
        filesAS.setTitle(etcollabaddtitle.getText().toString());
        filesAS.setText("");
        filesAS.setDate(currentDateandTime);

        reff.child(id).setValue(filesAS);

        Toast toast = Toast.makeText(CollabAddTitle.this, "Successfully inserted",Toast.LENGTH_SHORT);
        toast.show();

        etcollabaddtitle.setText("");
        Intent intent = new Intent(CollabAddTitle.this,Home.class);
        startActivity(intent);

    }

}
