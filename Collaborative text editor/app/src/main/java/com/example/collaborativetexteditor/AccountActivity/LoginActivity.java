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
import android.widget.TextView;
import android.widget.Toast;

import com.example.collaborativetexteditor.Home;
import com.example.collaborativetexteditor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText etemail, etpassword;
    Button btlogin;
    TextView tvsignup;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authListener;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        
        pd = new ProgressDialog(this);

        etemail = findViewById(R.id.editText_signin_email);
        etpassword = findViewById(R.id.editText_signin_password);
        btlogin = findViewById(R.id.button_login);
        tvsignup = findViewById(R.id.textview_signup);
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensignup();
            }
        });
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usersignin();
            }
        });
        /*authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()!=null){
                    Intent intent = new Intent(LoginActivity.this, Home.class);
                    startActivity(intent);
                }
            }
        };*/
    }

    /*@Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authListener);
    }*/

    private void usersignin() {
        String email,password;
        email = etemail.getText().toString();
        password = etpassword.getText().toString();
        if (TextUtils.isEmpty(email)|| TextUtils.isEmpty(password)){
            Toast toast = Toast.makeText(this, "Some field are empty", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        pd.setMessage("Trying to Login");
        pd.show();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast toast1 = Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT);
                    toast1.show();

                    Intent intent = new Intent(LoginActivity.this, Home.class);
                    startActivity(intent);
                }

                else {
                    Toast toast1 = Toast.makeText(LoginActivity.this, "Login Fail", Toast.LENGTH_SHORT);
                    toast1.show();
                }
                pd.dismiss();



            }
        });

    }

    private void opensignup() {
        Intent intent1 = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent1);
    }


}
