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

public class MainActivity extends AppCompatActivity {
    EditText txt_email,txt_password,txt_Cpassword;
    Button btn_signup;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        init();
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=txt_email.getText().toString().trim();
                String password=txt_password.getText().toString();
                String CPassword=txt_Cpassword.getText().toString();

                if (email.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                if (password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                if (password.length()>6){
                    Toast.makeText(MainActivity.this, "Password Too Short ", Toast.LENGTH_SHORT).show();
                }
                if (CPassword.equals(password)){
                    firebaseAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()){
                               Toast.makeText(MainActivity.this, "Signup Comp", Toast.LENGTH_SHORT).show();
                               Intent signupIntent=new Intent(getApplicationContext(),Home.class);
                               startActivity(signupIntent);
                               finish();
                           }else {
                               Toast.makeText(MainActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                           }
                        }
                    });

                }
            }
        });
    }
    public void init(){
        txt_email=findViewById(R.id.email);
        txt_password=findViewById(R.id.password);
        txt_Cpassword=findViewById(R.id.Cpassword);
        btn_signup=findViewById(R.id.signup);
    }
}