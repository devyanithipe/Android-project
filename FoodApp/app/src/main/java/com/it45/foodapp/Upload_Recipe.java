package com.it45.foodapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class Upload_Recipe extends AppCompatActivity {

    ImageView recipeImage;
     Uri uri;
     EditText txt_name,txt_description;
     String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__recipe);

        //toolbar removing
        getSupportActionBar().hide();

        recipeImage=(ImageView)findViewById(R.id.foodImage);
        txt_name=(EditText)findViewById(R.id.txt_recipe_name);
        txt_description=(EditText)findViewById(R.id.text_description);
    }

    public void btnSelectImage(View view) {

        Intent photoPicker=new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker,1);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){

            uri= data.getData();
            recipeImage.setImageURI(uri);

        }
        else Toast.makeText(this,"You haven't Select Image",Toast.LENGTH_SHORT).show();
    }

    public void uploadImage()
    {
        StorageReference storageReference= FirebaseStorage.getInstance().getReference().child("RecipeImages").child(uri.getLastPathSegment());

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Recipe uploading....");
        progressDialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlimage =uriTask.getResult();
                imageUrl=urlimage.toString();
                uploadRecipe();
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
            }
        });
    }

    public void btnUploadRecipe(View view) {
        uploadImage();
    }
    public void uploadRecipe()
    {

        FoodData foodData=new FoodData(
                txt_name.getText().toString(),
                txt_description.getText().toString(),
                imageUrl
        );
        String myCurrentDateTime= DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("Recipe").child(myCurrentDateTime).setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(Upload_Recipe.this,"Recipe Uploaded",Toast.LENGTH_SHORT).show();

                    finish();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_Recipe.this,e.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }

}