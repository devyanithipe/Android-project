package com.it45.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity1 extends AppCompatActivity {
    Button btnsignup;
    TextView btnaccount;
    EditText txtUsername,txtEmail,txtPassword,txtConfrmPassword;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);

        getSupportActionBar().setTitle("Sign Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //binding with xml
        txtUsername=(EditText) findViewById(R.id.username1);
        txtEmail=(EditText) findViewById(R.id.email);
        txtPassword=(EditText) findViewById(R.id.password1);
        txtConfrmPassword=(EditText)findViewById(R.id.repassword1);
        progressBar=(ProgressBar) findViewById(R.id.progress);
        btnsignup=(Button)findViewById(R.id.btnsignup1);
        btnaccount=(TextView) findViewById(R.id.account1);


        firebaseAuth=FirebaseAuth.getInstance();


        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String username=txtUsername.getText().toString().trim();
                String email=txtEmail.getText().toString().trim();
                String password=txtPassword.getText().toString().trim();
                String confirmpassword=txtConfrmPassword.getText().toString().trim();

                if(TextUtils.isEmpty(username))
                {
                    Toast.makeText(SignUpActivity1.this,"Please Enter Username",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email)) //TextUtil class validates
                {
                    Toast.makeText(SignUpActivity1.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(SignUpActivity1.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmpassword))
                {
                    Toast.makeText(SignUpActivity1.this,"Please Enter Confirm Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                //Password Validation
                if(password.length()<8) {

                    Toast.makeText(SignUpActivity1.this,"Password too short",Toast.LENGTH_SHORT).show();

                }

                progressBar.setVisibility(View.VISIBLE);
                if(password.equals(confirmpassword))
                {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpActivity1.this, new OnCompleteListener<AuthResult>()
                            {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if(task.isSuccessful()){

                                        startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                                        Toast.makeText(SignUpActivity1.this,"Registration Successful",Toast.LENGTH_LONG).show();

                                    } else {

                                        Toast.makeText(SignUpActivity1.this,"Authentication Failed",Toast.LENGTH_LONG).show();

                                    }

                                    // ...
                                }
                            });



                }








            }
        });


       /* btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentup=new Intent(SignUpActivity1.this,SignInActivity.class);
                startActivity(intentup);
            }
        }); */




        btnaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentacc=new Intent(SignUpActivity1.this,SignInActivity.class);
                startActivity(intentacc);
            }
        });

        //transparent action bar
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS



        );
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}