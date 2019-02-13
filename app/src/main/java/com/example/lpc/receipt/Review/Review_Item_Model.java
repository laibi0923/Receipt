package com.example.lpc.receipt.Review;

public class Review_Item_Model {
    String Create_Time, Item_Name, Price;

    public String getCreate_Time() {
        return Create_Time;
    }

    public void setCreate_Time(String create_Time) {
        Create_Time = create_Time;
    }

    public String getItem_Name() {
        return Item_Name;
    }

    public void setItem_Name(String item_Name) {
        Item_Name = item_Name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public Review_Item_Model(String create_Time, String item_Name, String price) {

        Create_Time = create_Time;
        Item_Name = item_Name;
        Price = price;
    }
}
