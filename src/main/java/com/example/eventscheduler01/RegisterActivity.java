package com.example.eventscheduler01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity{
    EditText etUser, etPwd, etRepwd;
    Button btnRegister, btnGoToLogin;

    DBHelper dbHelper;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUser = findViewById(R.id.login_layout);
        etPwd = findViewById(R.id.password);
        etRepwd = findViewById(R.id.repassword);
        btnRegister = findViewById(R.id.register_btn);
        btnGoToLogin = findViewById(R.id.login_btn);
        dbHelper = new DBHelper(this);
        btnGoToLogin = findViewById(R.id.login_btn);
        btnGoToLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String user, password, repwd;
                user = etUser.getText().toString();
                password = etPwd.getText().toString();
                repwd = etRepwd.getText().toString();
                if(user.isEmpty() || password.isEmpty() || repwd.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(password.equals(repwd)){
                        if(dbHelper.checkUsername(user)){
                            Toast.makeText(RegisterActivity.this, "Username already taken", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //Proceed with registration
                        boolean registeredSuccessfully = dbHelper.insertData(user, password);
                        if(registeredSuccessfully){
                            Toast.makeText(RegisterActivity.this, "Successfully Registered!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Passwords to do not match.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
