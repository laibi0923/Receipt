package com.example.lpc.receipt.Record;

public class Record_Item_Model { 

    String product_no, product_name, product_price, product_discount, product_tax, product_final_price;

    public Record_Item_Model(String product_no, String product_name, String product_price, String product_discount, String product_tax, String product_final_price) {
        this.product_no = product_no;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_discount = product_discount;
        this.product_tax = product_tax;
        this.product_final_price = product_final_price;
    } 

    public String getProduct_no() {  
        return product_no;
    }

    public void setProduct_no(String product_no) {
        this.product_no = product_no;
    }

    public String getProduct_noname() {
        return product_name;
    }

    public void setProduct_noname(String product_noname) {
        this.product_name = product_noname;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_discount() {
        return product_discount;
    }

    public void setProduct_discount(String product_discount) {
        this.product_discount = product_discount;
    }

    public String getProduct_tax() {
        return product_tax;
    }

    public void setProduct_tax(String product_tax) {
        this.product_tax = product_tax;
    }

    public String getProduct_final_price() {
        return product_final_price;
    }

    public void setProduct_final_price(String product_final_price) {
        this.product_final_price = product_final_price;
    }
}
