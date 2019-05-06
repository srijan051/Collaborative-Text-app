package com.example.collaborativetexteditor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class CollabAddText extends AppCompatActivity {

    EditText etcollabtitle, etcollabtext;
    Button btcollabsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collab_add_text);

        etcollabtitle = findViewById(R.id.editText_collabtitle);
        etcollabtext = findViewById(R.id.editText_collabtext);
        btcollabsave = findViewById(R.id.button_save_collab_add_text);


        Intent intent = getIntent();
        if (intent==null){
            Toast toast = Toast.makeText(CollabAddText.this, "intent not null", Toast.LENGTH_SHORT);
            toast.show();
            Log.d("getintentnotnull","getintentnot null");
        }
        else {

            Bundle bundle = intent.getBundleExtra(Home.EXTRA_MESSAGE);
            FilesAS item = (FilesAS) bundle.getSerializable("OBJECT");
            etcollabtitle.setText(item.getTitle());
            etcollabtext.setText(item.getText());
        }
    }


}
