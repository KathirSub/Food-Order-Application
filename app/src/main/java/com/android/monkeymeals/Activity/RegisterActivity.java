package com.android.monkeymeals.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.monkeymeals.DataBase.DataBaseHelper;
import com.android.monkeymeals.R;

/* REFERENCE : https://www.youtube.com/watch?v=8obgNNlj3Eo&t=445s */

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText username, password, repassword;
        Button registerButton, loginInButton;
        DataBaseHelper DB;

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Register");

            username = (EditText) findViewById(R.id.usernameText);
            password = (EditText) findViewById(R.id.passWordText);
            repassword = (EditText) findViewById(R.id.repassword);

            registerButton = (Button) findViewById(R.id.registerButton);
            loginInButton = (Button) findViewById(R.id.loginButton);

            DB = new DataBaseHelper(this);

        loginInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String user = username.getText().toString();
                    String pass = password.getText().toString();
                    String repass = repassword.getText().toString();

                    if (user.equals("") || pass.equals("") || repass.equals(""))
                        Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    else {
                        if (pass.equals(repass)) {
                            Boolean checkuser = DB.checkusername(user);
                            if (checkuser == false) {
                                Boolean insert = DB.insertData(user, pass);
                                if (insert == true) {
                                    Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), AfterOrderActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(RegisterActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        }
    }

