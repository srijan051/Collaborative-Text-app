package com.example.collaborativetexteditor.AccountActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.collaborativetexteditor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    EditText etemail, etpassword, etconfpassword;
    Button btsignup;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_signup);

        pd = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        etemail = findViewById(R.id.editText_signup_email);
        etpassword = findViewById(R.id.editText_signup_password);
        etconfpassword = findViewById(R.id.editText_signup_confpassword);
        btsignup = findViewById(R.id.button_signup);
        btsignup.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                usersignup();
            }
        });
    }


    private void usersignup() {
        final String email,password,confpassword;
        email = etemail.getText().toString();
        password = etpassword.getText().toString();
        confpassword = etconfpassword.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confpassword)){

            Toast toast= Toast.makeText(this, "Some fields are empty", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        pd.setMessage("Registering");
        pd.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast toast= Toast.makeText(SignupActivity.this, "Signup successfull", Toast.LENGTH_SHORT);
                    toast.show();

                    Intent loginintent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(loginintent);

                }
            }
        });

    }

}
