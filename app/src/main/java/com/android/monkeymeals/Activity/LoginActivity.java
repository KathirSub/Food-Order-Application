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
public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button buttonLogin;
    DataBaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.Loginusername);
        password = (EditText) findViewById(R.id.Loginpass);
        buttonLogin = (Button) findViewById(R.id.loginButton);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login");

        DB = new DataBaseHelper(this);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);

                    if(checkuserpass==true){

                        Toast.makeText(LoginActivity.this, "Sign in was Successfull ! ", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(LoginActivity.this, AfterOrderActivity.class);
                        startActivity(intent);
                        finish();

                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}