package com.it45.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<FoodData> myFoodList;
    FoodData mFoodData;

 private DatabaseReference databaseReference;
 private ValueEventListener eventListener;
 ProgressDialog progressDialog;
 MyAdopter myAdopter;
 EditText txt_Search;
 Button btnlogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);


        //toolbar removing
        getSupportActionBar().hide();



        mRecyclerView=(RecyclerView)findViewById(R.id.recycleview);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(HomeActivity.this,1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        txt_Search=(EditText)findViewById(R.id.txt_searchtext);
        btnlogout=(Button)findViewById(R.id.logout);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading items...");

        myFoodList=new ArrayList<>();

      //MyAdopter  myAdopter=new MyAdopter(HomeActivity.this,myFoodList);
        myAdopter=new MyAdopter(HomeActivity.this,myFoodList);
        mRecyclerView.setAdapter(myAdopter);
        //Getting instances
        databaseReference= FirebaseDatabase.getInstance().getReference("Recipe");

        progressDialog.show();

        eventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

              myFoodList.clear();
              //getting all child data
              for(DataSnapshot itemSnapshot: snapshot.getChildren()){

                  FoodData foodData= itemSnapshot.getValue(FoodData.class);

                  foodData.setKey(itemSnapshot.getKey());

                  myFoodList.add(foodData);

              }

              myAdopter.notifyDataSetChanged();
              progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();

            }
        });
        txt_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());

            }

            private void filter(String text) {
                ArrayList<FoodData> filterList= new ArrayList<>();

                for(FoodData item:myFoodList){

                    if(item.getItemName().toLowerCase().contains(text.toLowerCase())) {

                        filterList.add(item);

                    }
                }
                myAdopter.filteredList(filterList);
            }
        });

    }

    public void btn_uploadActivity(View view) {
        startActivity(new Intent(this,Upload_Recipe.class));
    }

    public void btn_logout(View view) {
        startActivity(new Intent(HomeActivity.this,MainActivity.class));
    }
}