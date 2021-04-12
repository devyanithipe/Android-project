package com.it45.foodapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdopter extends RecyclerView.Adapter<Foodviewholder>{
    private Context mContext;
    private List<FoodData> myFoodList;
    private int lastPosition=-1;

    public MyAdopter(Context mContext, List<FoodData> myFoodList) {
        this.mContext = mContext;
        this.myFoodList = myFoodList;
    }

    @Override
    public Foodviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView=LayoutInflater.from(parent.getContext()).inflate(R.layout.recucler_item,parent,false);
        return new Foodviewholder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final Foodviewholder holder, int position) {

        Glide.with(mContext).load(myFoodList.get(position).getItemImage()).into(holder.imageView);

       // holder.imageView.setImageResource(myFoodList.get(position).getItemImage());
        holder.mTitle.setText(myFoodList.get(position).getItemName());
        holder.mDescription.setText(myFoodList.get(position).getItemDescription());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,DetailActivity.class);
                intent.putExtra("Image",myFoodList.get(holder.getAdapterPosition()).getItemImage());
                intent.putExtra("Description",myFoodList.get(holder.getAdapterPosition()).getItemDescription());
               intent.putExtra("keyValue",myFoodList.get(holder.getAdapterPosition()).getKey());
                mContext.startActivity(intent);
            }
        });

        setAnimation(holder.itemView,position); //here position of item will be in position

    }

    //Animation function
    public  void  setAnimation(View viewAnimation,int positn) {

        if(positn>lastPosition)
        {
            ScaleAnimation scaleAnimation=new ScaleAnimation(0.0f,1.0f,0.0f,1.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            scaleAnimation.setDuration(1500); //1.5sec Duration
            viewAnimation.startAnimation(scaleAnimation);
            lastPosition=positn; //itemposition
        }

    }


    @Override
    public int getItemCount() {
        return myFoodList.size();
    }

    public void filteredList(ArrayList<FoodData> filterList) {

        myFoodList=filterList;
        notifyDataSetChanged();
    }
}

 class Foodviewholder extends RecyclerView.ViewHolder {


    ImageView imageView;
    TextView mTitle,mDescription;
    CardView mCardView;

    public Foodviewholder(View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.ivImage);
        mTitle=itemView.findViewById(R.id.tvTitle);
        mDescription=itemView.findViewById(R.id.tvDescription);
        mCardView=itemView.findViewById(R.id.myCardView);
    }
}
