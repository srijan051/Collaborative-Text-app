package com.example.collaborativetexteditor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CollabAddText extends AppCompatActivity {

    EditText etcollabtitle, etcollabtext;
    Button btcollabsave;

    DatabaseReference reff;
    FilesAS filesAS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collab_add_text);
        etcollabtitle = findViewById(R.id.editText_collabtitle);
        etcollabtext = findViewById(R.id.editText_collabtext);
        btcollabsave = findViewById(R.id.button_save_collab_add_text);

        filesAS = new FilesAS();
        reff= FirebaseDatabase.getInstance().getReference().child("Files");

        btcollabsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filesAS.setTitle(etcollabtitle.getText().toString());
                filesAS.setText(etcollabtext.getText().toString());
                reff.push().setValue(filesAS);

                Toast toast = Toast.makeText(CollabAddText.this, "Successfully inserted",Toast.LENGTH_SHORT);
                toast.show();
                etcollabtitle.setText("");
                etcollabtext.setText("");
                Intent intent = new Intent(CollabAddText.this,Home.class);
                startActivity(intent);
            }
        });

    }
}
