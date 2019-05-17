package com.example.collaborativetexteditor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.collaborativetexteditor.AccountActivity.LoginActivity;
import com.example.collaborativetexteditor.AccountActivity.SignupActivity;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);
        relativeLayout = findViewById(R.id.rl_mlayout);
        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                loginactivity_open();
                return true;
            }
        });
    }

    private void loginactivity_open(){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        //finish();
    }
}
