package com.example.lpc.receipt.Review;

import android.content.Intent;
import android.os.Bundle; 
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lpc.receipt.Public.Change_Date;
import com.example.lpc.receipt.R;
import com.example.lpc.receipt.Record.Record_Item_Adapter;
import com.example.lpc.receipt.Record.Record_Item_Model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Review_Item_Details extends AppCompatActivity {

    private NestedScrollView recordmain_scrollview;

    private LinearLayout back_btn;

    private LinearLayout del_btn;

    private EditText recordmain_name_edittext;

    private TextView recordmain_year_textview;

    private TextView recordmain_date_textview;

    private LinearLayout recordmain_income_btn;

    private LinearLayout recordmain_disbursement_btn;

    private LinearLayout recordmain_newitem_btn;

    private RecyclerView recordmain_item_recyclerview;

    private LinearLayout recordmain_total_amount;

    private TextView recordmain_total_amount_textview;

    private LinearLayout recordmain_payment;

    private TextView recordmain_payment_textview;

    private LinearLayout recordmain_payment_btn;

    private LinearLayout recordmain_exchange;

    private TextView recordmain_exchange_textview;

    private LinearLayout recordmain_paymethod;

    private TextView recordmain_paymethod_textview;

    private LinearLayout recordmain_remark;

    private LinearLayout recordmain_remark_btn;

    private TextView recordmain_remark_textview;

    private FloatingActionButton recordmain_fab;

    private Record_Item_Adapter adapter;

    private ArrayList<Record_Item_Model> xx_list;

    private Double Total_Amount = 0.0;

    private String get_Select_Date, get_Select_Year;

    DecimalFormat dec = new DecimalFormat("#,##0.00");

    private String Type = "Income";

    //private long getDate_long;

    private Change_Date mChange_Date;

    private Calendar RecordMain_Calendar = Calendar.getInstance();


    private String Details_Name, Details_Type, Details_TotalPrice, Details_Exchange, Details_PayMethod, Details_Remarks;

    private ArrayList<Record_Item_Model> Details_Zitem;

    private long Details_CreateDate;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(resultCode){

            case 998:

                if (data != null){

                }

                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a001_record_main);

        Bundle extra = getIntent().getExtras();

        if (extra != null){

            Details_Name = extra.getString("For_ItemDetails_Name");
            Details_CreateDate = extra.getLong("For_ItemDetails_CreateDate");
            Details_Type = extra.getString("For_ItemDetails_Type");
            Details_Zitem = (ArrayList<Record_Item_Model>) extra.getSerializable("For_ItemDetails_Zitem");
            Details_TotalPrice = extra.getString("For_ItemDetails_TotalPrice");
            Details_Exchange = extra.getString("For_ItemDetails_Exchange");
            Details_PayMethod = extra.getString("For_ItemDetails_PayMethod");
            Details_Remarks = extra.getString("For_ItemDetails_Remarks");
        }

        Find_View();

        xx_list = new ArrayList<>();

        for (int i = 0; i < Details_Zitem.size(); i++){

            Record_Item_Model record = new Record_Item_Model(
                    Details_Zitem.get(i).getProduct_no(),
                    Details_Zitem.get(i).getProduct_name(),
                    Details_Zitem.get(i).getProduct_price(),
                    Details_Zitem.get(i).getProduct_discount(),
                    Details_Zitem.get(i).getProduct_tax(),
                    Details_Zitem.get(i).getProduct_final_price());

            xx_list.add(record);

        }

        // set up the RecyclerView
        recordmain_item_recyclerview.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Record_Item_Adapter(this, xx_list);
//        adapter.setClickListener(RecycleviewItemClickListener);
        recordmain_item_recyclerview.setAdapter(adapter);

    }


    private void Find_View(){

        recordmain_scrollview = (NestedScrollView) findViewById(R.id.recordmain_scrollview);
//        recordmain_scrollview.setOnScrollChangeListener(NestedScrollView_OnScrollChangeListener);

        back_btn = (LinearLayout) findViewById(R.id.back_btn);
//        back_btn.setOnClickListener(View_Click_Listener);

        del_btn = (LinearLayout) findViewById(R.id.del_btn);

        recordmain_name_edittext = (EditText) findViewById(R.id.recordmain_name_edittext);
        recordmain_name_edittext.setEnabled(false);
        recordmain_name_edittext.setText(Details_Name);

        recordmain_year_textview = (TextView) findViewById(R.id.recordmain_year_textview);
        recordmain_year_textview.setText(new Change_Date().parseToDateString(Details_CreateDate, "yyyy"));

        recordmain_date_textview = (TextView) findViewById(R.id.recordmain_date_textview);
        recordmain_date_textview.setText(new Change_Date().parseToDateString(Details_CreateDate, "MM月dd日"));
//        recordmain_date_textview.setOnClickListener(View_Click_Listener);

        recordmain_income_btn = (LinearLayout) findViewById(R.id.recordmain_income_btn);
//        recordmain_income_btn.setOnClickListener(View_Click_Listener);

        recordmain_disbursement_btn = (LinearLayout) findViewById(R.id.recordmain_disbursement_btn);
//        recordmain_disbursement_btn.setOnClickListener(View_Click_Listener);

//        recordmain_newitem_btn = (LinearLayout) findViewById(R.id.recordmain_newitem_btn);
//        recordmain_newitem_btn.setOnClickListener(View_Click_Listener);

        recordmain_item_recyclerview = (RecyclerView) findViewById(R.id.recordmain_item_recyclerview);
        recordmain_item_recyclerview.setNestedScrollingEnabled(false);

        recordmain_total_amount = (LinearLayout) findViewById(R.id.recordmain_total_amount);
        recordmain_total_amount.setVisibility(View.VISIBLE);

        recordmain_total_amount_textview = (TextView) findViewById(R.id.recordmain_total_amount_textview);
        recordmain_total_amount_textview.setText(Details_TotalPrice);

        recordmain_payment = (LinearLayout) findViewById(R.id.recordmain_payment);
        recordmain_payment.setVisibility(View.VISIBLE);

        recordmain_payment_textview = (TextView) findViewById(R.id.recordmain_payment_textview);

        recordmain_payment_btn = (LinearLayout) findViewById(R.id.recordmain_payment_btn);
//        recordmain_payment_btn.setOnClickListener(View_Click_Listener);

        recordmain_exchange = (LinearLayout) findViewById(R.id.recordmain_exchange);
        recordmain_exchange.setVisibility(View.VISIBLE);

        recordmain_exchange_textview = (TextView) findViewById(R.id.recordmain_exchange_textview);
        recordmain_exchange_textview.setText(Details_Exchange);

        recordmain_paymethod = (LinearLayout) findViewById(R.id.recordmain_paymethod);
        recordmain_paymethod.setVisibility(View.VISIBLE);

        recordmain_paymethod_textview = (TextView) findViewById(R.id.recordmain_paymethod_textview);
        recordmain_paymethod_textview.setText(Details_PayMethod);

        recordmain_remark = (LinearLayout) findViewById(R.id.recordmain_remark);

        recordmain_remark_btn = (LinearLayout) findViewById(R.id.recordmain_remark_btn);
//        recordmain_remark_btn.setOnClickListener(View_Click_Listener);

        recordmain_remark_textview = (TextView) findViewById(R.id.recordmain_remark_textview);
        recordmain_remark_textview.setText(Details_Remarks);

        recordmain_fab = (FloatingActionButton) findViewById(R.id.recordmain_fab);
        recordmain_fab.hide();
//        recordmain_fab.setOnClickListener(View_Click_Listener);

    }


}
