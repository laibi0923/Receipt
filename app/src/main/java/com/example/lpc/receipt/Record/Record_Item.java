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

    private LinearLayout del_btn;

    private EditText productno_edittext;

    private EditText name_edittext;

    private EditText price_edittext;

    private EditText discount_edittext;

    private EditText tax_edittext;

    private TextView total_amount_textview;

    private LinearLayout next_record_btn;

    private String Product_No_Text, Product_Name_Text, Product_Price_Text, Product_Discount_Text, Product_Tax_Text, Product_FinalPrice_Text;

    private Double Price, Discount, Tax;

    private Bundle extras;

    private int Item_Position;

    Change_Amount mChange_Amount;

    DecimalFormat mDecimalFormat = new DecimalFormat("#,##0.00");

    private String Action_Type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.a002_record_item);

        Init_Value();

        Find_View();

        toggle_delbtn();

        mChange_Amount = new Change_Amount();
    }


    // 初始化資料
    private void Init_Value(){

        extras = getIntent().getExtras();

        if (extras == null){

            Product_No_Text = "";
            Product_Name_Text = "";
            Product_Price_Text = "$0.00";
            Product_Discount_Text = "0";
            Product_Tax_Text = "$0.00";
            Product_FinalPrice_Text = "$0.00";

            Price = 0.00;
            Discount = 1.00;
            Tax = 0.00;

        }else {

            Item_Position = extras.getInt("extras_position", 0);
			
			Log.e("recorditeam", Item_Position +"");
            Product_No_Text = extras.getString("extras_product_no");
            Product_Name_Text = extras.getString("extras_product_name");
            Product_Price_Text = extras.getString("extras_product_price");
            Product_Discount_Text = extras.getString("extras_product_discount");
            Product_Tax_Text = extras.getString("extras_product_tax");
            Product_FinalPrice_Text = extras.getString("extras_product_finalprice");

            Price = Double.parseDouble(Product_Price_Text.replaceAll("[^0-9.]", ""));
            Discount = Double.parseDouble(Product_Discount_Text.replaceAll("[^0-9.]", "")) / 100;
            Tax = Double.parseDouble(Product_Tax_Text.replaceAll("[^0-9.]", ""));

        }

    }

    private void toggle_delbtn(){

        if (extras == null){
            del_btn.setVisibility(View.GONE);
        }else {
            del_btn.setVisibility(View.VISIBLE);
        }

    }

    private void Find_View(){

        back_btn = (LinearLayout) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(View_Click_Listener);

        del_btn = (LinearLayout) findViewById(R.id.del_btn);
        del_btn.setOnClickListener(View_Click_Listener);

        productno_edittext = (EditText) findViewById(R.id.productno_edittext);
        productno_edittext.setText(Product_No_Text);

        name_edittext = (EditText) findViewById(R.id.name_edittext);
        name_edittext.setText(Product_Name_Text);

        price_edittext = (EditText) findViewById(R.id.price_edittext);
        price_edittext.setText(Product_Price_Text);
        price_edittext.setOnClickListener(View_Click_Listener);
        price_edittext.addTextChangedListener(Price_TextWatcher);

        discount_edittext = (EditText) findViewById(R.id.discount_edittext);
        discount_edittext.setText(Product_Discount_Text);
        discount_edittext.setOnClickListener(View_Click_Listener);
        discount_edittext.addTextChangedListener(Discount_TextWatcher);

        tax_edittext = (EditText) findViewById(R.id.tax_edittext);
        tax_edittext.setText(Product_Tax_Text);
        tax_edittext.setOnClickListener(View_Click_Listener);
        tax_edittext.addTextChangedListener(Tax_TextWatcher);

        total_amount_textview = (TextView) findViewById(R.id.total_amount_textview);
        total_amount_textview.setText(Product_FinalPrice_Text);

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

                case R.id.del_btn:

                    Intent del_item_Intent = new Intent();

                    del_item_Intent.putExtra("Action_Type", "DEL_APPLICATION");
                    del_item_Intent.putExtra("Del_Postion", Item_Position);

                    setResult(2, del_item_Intent);
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
                            discount_edittext.setText("0");
                        }

                        if (tax_edittext.getText().toString().trim().isEmpty()){
                            mChange_Amount.Change_Amount("0.0", tax_edittext);
                        }

						Sum_Value();
						
                        Intent mIntent = new Intent();

                        mIntent.putExtra("Position", Item_Position);
                        mIntent.putExtra("Product_No",productno_edittext.getText().toString());
                        mIntent.putExtra("Product_Name", name_edittext.getText().toString());
                        mIntent.putExtra("Product_Price", price_edittext.getText().toString());
                        mIntent.putExtra("Product_Discount", discount_edittext.getText().toString());
                        mIntent.putExtra("Product_Tax", tax_edittext.getText().toString());
                        mIntent.putExtra("Product_FianlPrice", total_amount_textview.getText().toString());

                        if (extras == null){
                            mIntent.putExtra("Action_Type", "NEW_APPLICATION");
                            setResult(1, mIntent);
                        }else {
                            mIntent.putExtra("Action_Type", "UPDATE_APPLICATION");
                            setResult(2, mIntent);
                        }

                        close_keybord();
                        finish();

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

                if (Discount_ReplaceText.isEmpty() || Discount_ReplaceText == null || Double.parseDouble(Discount_ReplaceText) > 100){
					
                    discount_edittext.setText("0");
					
                }
				
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
		
			
		if(!price_edittext.getText().toString().isEmpty() && !discount_edittext.getText().toString().isEmpty() && !tax_edittext.getText().toString().isEmpty()){
			
			Price = Double.parseDouble(price_edittext.getText().toString().replaceAll("[^0-9.]", ""));

			Discount = 1 - Double.parseDouble(discount_edittext.getText().toString()) / 100;

			Tax = Double.parseDouble(tax_edittext.getText().toString().replaceAll("[^0-9.]",""));

			Double Result = Price * Discount + Tax;

			total_amount_textview.setText("$" + mDecimalFormat.format(Result));
			
		}
    }

    //    關閉鍵盤
    private void close_keybord(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

}
