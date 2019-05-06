package com.example.collaborativetexteditor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homedemo extends AppCompatActivity {

    Button btaddTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homedemo);
        btaddTitle = findViewById(R.id.button_add);
        btaddTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openadd();
            }
        });
    }

    private void openadd() {
        Intent intent = new Intent(this, CollabAddTitle.class);
        startActivity(intent);
    }
}
