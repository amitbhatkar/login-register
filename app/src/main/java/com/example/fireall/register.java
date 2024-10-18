package com.example.fireall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class register extends AppCompatActivity {
    EditText et_name,et_email,et_password,et_contact;
    FirebaseAuth db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_contact = findViewById(R.id.et_contact);

        db=FirebaseAuth.getInstance();

    }
    public void btn_login_go(View view) {
        Intent intent = new Intent(register.this, login.class);
        startActivity(intent);
        finish();
    }

    public void btn_register_go(View view) {
        String name = et_name.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        String contact = et_contact.getText().toString();

        if (!name.isEmpty()) {
            et_name.setError(null);
            if (!email.isEmpty()) {
                et_email.setError(null);
                if (!password.isEmpty()) {
                    et_password.setError(null);
                    if (!contact.isEmpty()) {
                        et_contact.setError(null);
                        if(email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
                            if (password.length() >=6){
                                et_password.setError(null);

                                register_data(email,password);

                            }else {
                                et_password.setError("enter strong password");
                            }
                        }else{
                            et_email.setError("Invalid Email");
                        }
                    } else {
                        et_contact.setError("please enter contact");
                    }
                } else {
                    et_password.setError("please enter password");
                }
            } else {
                et_email.setError("please enter email");
            }
        } else {
            et_name.setError("please enter full-name");
        }

    }

    public void register_data(String et_email, String et_password)
    {

        db= FirebaseAuth.getInstance();
        db.createUserWithEmailAndPassword(et_email,et_password).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(register.this, "Register successful", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(register.this, profile.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(register.this, "please try again"+ task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /*user_model model = new user_model(name, email, password, contact);
        db.collection("user").document().set(model).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {

            if(task.isSuccessful()){
                Toast.makeText(register.this, "Register successful", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(register.this, profile.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(register.this, "please try again", Toast.LENGTH_SHORT).show();
            }
        }
    });     */

}