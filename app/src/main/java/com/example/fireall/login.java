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

public class login extends AppCompatActivity {

    EditText et_userid, et_passs;
    FirebaseAuth db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_userid=findViewById(R.id.et_userid);
        et_passs=findViewById(R.id.et_passs);

    }

    public void btn_reg_on(View view) {
        Intent intent = new Intent(login.this, register.class);
        startActivity(intent);
        finish();
    }

    public void btn_log_on(View view) {
        String user = et_userid.getText().toString();
        String pass = et_passs.getText().toString();

        if (!user.isEmpty()) {
            et_userid.setError(null);
            if (!pass.isEmpty()) {
                et_passs.setError(null);

                        log_data_fun(user, pass);                  // call the function

            } else {
                et_passs.setError("please enter password");
            }
        } else {
            et_userid.setError("please enter user-id");
        }
    }

    private void log_data_fun(String et_userid, String et_passs)
    {
        db=FirebaseAuth.getInstance();
        db.signInWithEmailAndPassword(et_userid,et_passs).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(login.this, profile.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(login.this, "Invalid email or password"  + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}