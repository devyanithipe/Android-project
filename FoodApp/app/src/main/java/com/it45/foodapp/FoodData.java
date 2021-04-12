package com.it45.foodapp;

import android.widget.TextView;

public class FoodData {
    private String itemName;
    private String itemDescription;
    private  String itemImage;
    private String key;


    public FoodData(){

    }

    public FoodData(String itemName, String itemDescription, String itemImage) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemImage = itemImage;

    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemImage() {
        return itemImage;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
