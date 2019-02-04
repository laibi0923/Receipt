package com.example.lpc.receipt.Record;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.lpc.receipt.Public.Change_Amount;
import com.example.lpc.receipt.R;

import java.text.DecimalFormat;

public class Record_Item extends AppCompatActivity {


    private LinearLayout back_btn;

    private EditText productno_edittext;

    private EditText name_edittext;

    private EditText price_edittext;

    private EditText discount_edittext;

    private EditText tax_edittext;

    private TextView total_amount_textview;

    private LinearLayout next_record_btn;

    private String Product_No, Product_Name, Product_Price, Product_Discount, Product_Tax, Product_FinalPrice;

    private Double Price, Discount, Tax;

    private Bundle extras;

    private int Position;

    Change_Amount mChange_Amount;

    DecimalFormat mDecimalFormat = new DecimalFormat("#,##0.00");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.a002_record_item);

        extras = getIntent().getExtras();

        if (extras == null){

            Product_No = "";
            Product_Name = "";
            Product_Price = "";
            Product_Discount = "";
            Product_Tax = "";
            Product_FinalPrice = "$0.00";

            Price = 0.00;
            Discount = 1.00;
            Tax = 0.00;

        }else {

            Position = extras.getInt("extras_position", 0);
            Product_No = extras.getString("extras_product_no");
            Product_Name = extras.getString("extras_product_name");
            Product_Price = extras.getString("extras_product_price");
            Product_Discount = extras.getString("extras_product_discount");
            Product_Tax = extras.getString("extras_product_tax");
            Product_FinalPrice = extras.getString("extras_product_finalprice");

            Price = Double.parseDouble(Product_Price.replaceAll("[^0-9.]", ""));
            Discount = Double.parseDouble(Product_Discount.replaceAll("[^0-9.]", ""));;
            Tax = Double.parseDouble(Product_Tax.replaceAll("[^0-9.]", ""));;

        }

        Find_View();

        mChange_Amount = new Change_Amount();
    }

    private void Find_View(){

        back_btn = (LinearLayout) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(View_Click_Listener);

        productno_edittext = (EditText) findViewById(R.id.productno_edittext);
        productno_edittext.setText(Product_No);

        name_edittext = (EditText) findViewById(R.id.name_edittext);
        name_edittext.setText(Product_Name);

        price_edittext = (EditText) findViewById(R.id.price_edittext);
        price_edittext.setText(Product_Price);
        price_edittext.setOnClickListener(View_Click_Listener);
        price_edittext.addTextChangedListener(Price_TextWatcher);

        discount_edittext = (EditText) findViewById(R.id.discount_edittext);
        discount_edittext.setText(Product_Discount);
        discount_edittext.setOnClickListener(View_Click_Listener);
        discount_edittext.addTextChangedListener(Discount_TextWatcher);

        tax_edittext = (EditText) findViewById(R.id.tax_edittext);
        tax_edittext.setText(Product_Tax);
        tax_edittext.setOnClickListener(View_Click_Listener);
        tax_edittext.addTextChangedListener(Tax_TextWatcher);

        total_amount_textview = (TextView) findViewById(R.id.total_amount_textview);
        total_amount_textview.setText(Product_FinalPrice);

        next_record_btn = (LinearLayout) findViewById(R.id.next_record_btn);
        next_record_btn.setOnClickListener(View_Click_Listener);
    }


    private View.OnClickListener View_Click_Listener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.back_btn:

                    close_keybord();
                    finish();

                    break;


                case R.id.price_edittext:

                    price_edittext.setText("");
                    Price = 0.0;
                    Sum_Value();

                    break;

                case R.id.discount_edittext:

                    discount_edittext.setText("");
                    Discount = 1.0;
                    Sum_Value();

                    break;

                case R.id.tax_edittext:

                    tax_edittext.setText("");
                    Tax = 0.0;
                    Sum_Value();

                    break;

                case R.id.next_record_btn:

                    if (!name_edittext.getText().toString().trim().isEmpty() && !price_edittext.getText().toString().trim().isEmpty()){

                        if (discount_edittext.getText().toString().trim().isEmpty()){
                            discount_edittext.setText("-");
                        }

                        if (tax_edittext.getText().toString().trim().isEmpty()){
                            mChange_Amount.Change_Amount("0.0", tax_edittext);
                        }

                        Intent mIntent = new Intent();

                        mIntent.putExtra("Product_No",productno_edittext.getText().toString());
                        mIntent.putExtra("Product_Name", name_edittext.getText().toString());
                        mIntent.putExtra("Product_Price", price_edittext.getText().toString());
                        mIntent.putExtra("Product_Discount", discount_edittext.getText().toString());
                        mIntent.putExtra("Product_Tax", tax_edittext.getText().toString());
                        mIntent.putExtra("Product_FianlPrice", total_amount_textview.getText().toString());

                        if (extras == null){

                            setResult(2, mIntent);
                            close_keybord();
                            finish();

                        }else {

                            mIntent.putExtra("Position", Position);
                            setResult(1, mIntent);
                            close_keybord();
                            finish();

                        }

                    }

                    break;

            }

        }
    };

    private TextWatcher Price_TextWatcher = new TextWatcher(){

        @Override
        public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
        {
            // TODO: Implement this method
        }

        @Override
        public void onTextChanged(CharSequence s, int p2, int p3, int p4)
        {
            // TODO: Implement this method
            if(s.length() > 0){

                mChange_Amount.Change_Amount(s.toString(), price_edittext);

                String Price_ReplaceText = price_edittext.getText().toString().replaceAll("[^0-9.]","");

                Price = Double.parseDouble(Price_ReplaceText);

                Sum_Value();

            }

        }

        @Override
        public void afterTextChanged(Editable p1)
        {
            // TODO: Implement this method

        }

    };


    private TextWatcher Discount_TextWatcher = new TextWatcher(){

        @Override
        public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
        {
            // TODO: Implement this method
        }

        @Override
        public void onTextChanged(CharSequence s, int p2, int p3, int p4)
        {
            // TODO: Implement this method
            if(s.length() > 0){

                String Discount_ReplaceText = discount_edittext.getText().toString().replaceAll("[^0-9.]","");

                Discount = Double.parseDouble(Discount_ReplaceText) / 100;

                Sum_Value();
            }
        }

        @Override
        public void afterTextChanged(Editable p1)
        {
            // TODO: Implement this method

        }

    };


    private TextWatcher Tax_TextWatcher = new TextWatcher(){

        @Override
        public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
        {
            // TODO: Implement this method
        }

        @Override
        public void onTextChanged(CharSequence s, int p2, int p3, int p4)
        {
            // TODO: Implement this method
            if(s.length() > 0){

                mChange_Amount.Change_Amount(s.toString(),tax_edittext);

                String Tax_ReplaceText = tax_edittext.getText().toString().replaceAll("[^0-9.]","");

                Tax = Double.parseDouble(Tax_ReplaceText);

                Sum_Value();

            }
        }

        @Override
        public void afterTextChanged(Editable p1)
        {
            // TODO: Implement this method

        }

    };

    //    計算總銀碼
    private void Sum_Value(){

        Double Result = Price * Discount + Tax;

        total_amount_textview.setText("$" + mDecimalFormat.format(Result));
    }

    //    關閉鍵盤
    private void close_keybord(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

}
