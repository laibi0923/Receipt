package com.example.lpc.receipt.Record;

import android.content.Context;
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

import com.example.lpc.receipt.R;

import java.text.DecimalFormat;

public class Record_Payment extends AppCompatActivity {

    private  LinearLayout back_btn;

    private TextView total_payment_textview;

    private  LinearLayout payment_method_btn;

    private TextView payment_method_textview;

    private LinearLayout payment_method_list;

    private EditText payment_edittext;

    private EditText exchange_amount_edittext;

    private EditText remark_edittext;

    private LinearLayout save_btn;

    private LinearLayout payment_method_cash, payment_method_card, payment_method_octopus, payment_method_epayment, payment_method_other;

    DecimalFormat dec = new DecimalFormat("#,##0.00");

    private Double Total_Amount = 0.0;

    private Double Payment_Amount = 0.0;

    private Double Exchange_Amount = 0.0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.a007_record_payment);

        Total_Amount = 500.0;

        Find_View();
    }

    private void Find_View(){

        back_btn = (LinearLayout) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(View_OnclickListener);

        total_payment_textview = (TextView) findViewById(R.id.total_payment_textview);
        total_payment_textview.setText(Total_Amount.toString());

        payment_method_btn = (LinearLayout) findViewById(R.id.payment_method_btn);
        payment_method_btn.setOnClickListener(View_OnclickListener);

        payment_method_list = (LinearLayout) findViewById(R.id.payment_method_list);

        payment_method_textview = (TextView) findViewById(R.id.payment_method_textview);
        payment_method_textview.setOnClickListener(View_OnclickListener);

        payment_method_cash = (LinearLayout) findViewById(R.id.payment_method_cash);
        payment_method_cash.setOnClickListener(Payment_MethodClick_Listener);

        payment_method_card = (LinearLayout) findViewById(R.id.payment_method_card);
        payment_method_card.setOnClickListener(Payment_MethodClick_Listener);

        payment_method_octopus = (LinearLayout) findViewById(R.id.payment_method_octopus);
        payment_method_octopus.setOnClickListener(Payment_MethodClick_Listener);

        payment_method_epayment = (LinearLayout) findViewById(R.id.payment_method_epayment);
        payment_method_epayment.setOnClickListener(Payment_MethodClick_Listener);

        payment_method_other = (LinearLayout) findViewById(R.id.payment_method_other);
        payment_method_other.setOnClickListener(Payment_MethodClick_Listener);

        payment_edittext = (EditText) findViewById(R.id.payment_edittext);
        payment_edittext.setOnClickListener(View_OnclickListener);
        payment_edittext.addTextChangedListener(Payment_Textwatch);

        exchange_amount_edittext = (EditText) findViewById(R.id.exchange_amount_edittext);
        exchange_amount_edittext.setOnClickListener(View_OnclickListener);

        remark_edittext = (EditText) findViewById(R.id.remark_edittext);

        save_btn = (LinearLayout) findViewById(R.id.save_btn);
        save_btn.setOnClickListener(View_OnclickListener);



    }

    private TextWatcher Payment_Textwatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            change_Amount(s, payment_edittext);

            String Price_ReplaceText = payment_edittext.getText().toString().replaceAll("[^0-9.]","");

            Payment_Amount = Double.parseDouble(Price_ReplaceText);

            Sum_Value();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private View.OnClickListener Payment_MethodClick_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.payment_method_cash:

                    payment_method_textview.setText("現金");

                    break;

                case R.id.payment_method_card:

                    payment_method_textview.setText("信用卡");

                    break;

                case R.id.payment_method_octopus:

                    payment_method_textview.setText("八達通");

                    break;

                case R.id.payment_method_epayment:

                    payment_method_textview.setText("電子支付");

                    break;

                case R.id.payment_method_other:

                    payment_method_textview.setText("其他");

                    break;

            }

            payment_method_list.setVisibility(View.GONE);

        }
    };

    private View.OnClickListener View_OnclickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.back_btn:

                    close_keybord();
                    finish();

                    break;

                case R.id.payment_method_btn:

                    payment_method_list.setVisibility(View.VISIBLE);

                    break;

                case R.id.payment_edittext:

                    payment_edittext.setText("");

                    break;

                case R.id.exchange_amount_edittext:

                    exchange_amount_edittext.setText("");

                    break;

                case R.id.save_btn:

                    close_keybord();
                    finish();

                    break;

            }

        }
    };

    private void change_Amount(CharSequence s, EditText mEditText){

        if(!s.toString().matches("^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$"))
        {
            String userInput= "" + s.toString().replaceAll("[^\\d]", "");

            if (userInput.length() > 0) {

                Double in = Double.parseDouble(userInput);

                double percen = in / 100;

                mEditText.setText("$" + dec.format(percen));

                mEditText.setSelection(mEditText.getText().length());

            }
        }
    }

    private void Sum_Value(){

        Exchange_Amount = Payment_Amount - Total_Amount;

        DecimalFormat mDecimalFormat = new DecimalFormat("#,##0.00");

        exchange_amount_edittext.setText("$" + mDecimalFormat.format(Exchange_Amount));

    }

    private void close_keybord(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

}
