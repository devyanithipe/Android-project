package com.it45.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class AboutDetails extends AppCompatActivity {
    TextView aboutUs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_details);


        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS

        );
        //toolbar removing
        getSupportActionBar().hide();

      aboutUs=(TextView)findViewById(R.id.about);
      aboutUs.setText("Cooking can be a hobby, a dreaded necessity or just a part of your" +
              "routine, but no matter what your relationship with the kitchen is like," +
              "cooking takes time and effort.\n\n That’s where apps for iOS and Android step in," +
              " offering step-by-step instructions to help you prepare a hearty home-made" +
              "meal or curate a weekly meal plan that goes with your diet.\n");

    }
}