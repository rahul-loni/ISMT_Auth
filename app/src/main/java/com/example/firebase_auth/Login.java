package com.example.firebase_auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText txt_email,txt_password;
    Button btn_login;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();
        init();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=txt_email.getText().toString().trim();
                String password=txt_password.getText().toString().trim();
                if (email.isEmpty()){
                    Toast.makeText(Login.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                if (password.isEmpty()){
                    Toast.makeText(Login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(Login.this, "Login Comp", Toast.LENGTH_SHORT).show();
                                    Intent loginIntent=new Intent(getApplicationContext(),Home.class);
                                    startActivity(loginIntent);
                                    finish();
                                }else {
                                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
    public void init(){
        txt_email=findViewById(R.id.email);
        txt_password=findViewById(R.id.password);
        btn_login=findViewById(R.id.login);
    }
}