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
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    Button btn_getEmail;
    EditText txt_Email;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        firebaseAuth=FirebaseAuth.getInstance();
        init();
        btn_getEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=txt_Email.getText().toString().trim();
                if (email.isEmpty()){
                    Toast.makeText(ForgotPassword.this, "Enter Email first ", Toast.LENGTH_SHORT).show();
                }else {
                    firebaseAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(ForgotPassword.this, "Check Email", Toast.LENGTH_SHORT).show();
                                        Intent forgotIntent=new Intent(getApplicationContext(),Login.class);
                                        startActivity(forgotIntent);
                                        finish();
                                    }
                                }
                            });
                }
            }
        });
    }
    public  void init(){
        btn_getEmail=findViewById(R.id.getEmail);
        txt_Email=findViewById(R.id.email);

    }
}