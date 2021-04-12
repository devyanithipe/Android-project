package com.it45.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button login, signup,about,contactus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //transparent actionbar
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS

        );
        //toolbar removing
        getSupportActionBar().hide();
        //main code
        login=findViewById(R.id.btnlogin);
        signup=findViewById(R.id.btnsignUp);
        about=findViewById(R.id.aboutUs);


        //login button code
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Login has been clicked", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });
        //Registration button code
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Registration has been clicked", Toast.LENGTH_LONG).show();
                Intent intentup=new Intent(MainActivity.this,SignUpActivity1.class);
                startActivity(intentup);
            }
        });

        //aboutUs
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "About Us has been Clicked", Toast.LENGTH_LONG).show();
                Intent intentab=new Intent(MainActivity.this,AboutDetails.class);
                startActivity(intentab);
            }
        });

    }

}