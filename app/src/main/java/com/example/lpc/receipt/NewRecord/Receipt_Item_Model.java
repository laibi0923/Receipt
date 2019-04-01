package com.example.lpc.receipt.NewRecord;

public class Receipt_Item_Model {

    public String ItemName, ItemType, ItemPrice, ItemRemarks;

    public Receipt_Item_Model(String itemName, String itemType, String itemPrice, String itemRemarks) {
        ItemName = itemName;
        ItemType = itemType;
        ItemPrice = itemPrice;
        ItemRemarks = itemRemarks;
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

    public String getItemRemarks() {
        return ItemRemarks;
    }

    public void setItemRemarks(String itemRemarks) {
        ItemRemarks = itemRemarks;
    }
}
