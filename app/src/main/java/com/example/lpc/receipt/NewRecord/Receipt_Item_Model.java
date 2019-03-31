package com.example.lpc.receipt.NewRecord;

public class Receipt_Item_Model {

    public String ItemName, ItemType, ItemPrice;

    public Receipt_Item_Model(String itemName, String itemType, String itemPrice) {
        ItemName = itemName;
        ItemType = itemType;
        ItemPrice = itemPrice;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemType() {
        return ItemType;
    }

    public void setItemType(String itemType) {
        ItemType = itemType;
    }

    public String getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        ItemPrice = itemPrice;
    }
}
