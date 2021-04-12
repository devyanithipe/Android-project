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

public class SignInActivity extends AppCompatActivity {
    EditText txtemail,password2;
    Button btnlogin;
    TextView btnaccount;
    ProgressBar progressBar;
   private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().setTitle("Sign In");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtemail=findViewById(R.id.email2);
        password2=findViewById(R.id.password2);
        btnlogin=findViewById(R.id.btnsignin1);
        progressBar=(ProgressBar)findViewById(R.id.progresslog);
        btnaccount=findViewById(R.id.account2);

        firebaseAuth=FirebaseAuth.getInstance();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=txtemail.getText().toString().trim();
                String password=password2.getText().toString().trim();


                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(SignInActivity.this,"Please Enter Username",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(SignInActivity.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }


                //Password Validation
                if(password.length()<8) {

                    Toast.makeText(SignInActivity.this,"Password too short",Toast.LENGTH_SHORT).show();

                }
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {

                                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                                    Toast.makeText(SignInActivity.this,"LogIn successful...",Toast.LENGTH_LONG).show();


                                } else {

                                    Toast.makeText(SignInActivity.this,"LogIn failed",Toast.LENGTH_LONG).show();

                                }

                                // ...
                            }
                        });






            }
        });


     /*   btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentin=new Intent(SignInActivity.this,HomeActivity.class);
                startActivity(intentin);
            }
        }); */

        btnaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentacc=new Intent(SignInActivity.this,SignUpActivity1.class);
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