package com.example.lpc.receipt.Record;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.lpc.receipt.R;
import com.example.lpc.receipt.Review.Review_Calendar;

import java.text.DecimalFormat;
import java.util.ArrayList;
import android.support.design.widget.*;
import android.support.v4.widget.*;
import android.widget.NumberPicker.*;
import android.support.v7.app.*;

public class Record_Main extends AppCompatActivity {
	
	private NestedScrollView recordmain_scrollview;

    private EditText recordmain_name_edittext;

    private TextView recordmain_date_textview;

    private LinearLayout recordmain_income_btn;

    private LinearLayout recordmain_disbursement_btn;

    private LinearLayout recordmain_newitem_btn;

    private RecyclerView recordmain_item_recyclerview;

    private LinearLayout recordmain_total_amount;

    private TextView recordmain_total_amount_textview;

    private LinearLayout recordmain_payment;

    private LinearLayout recordmain_payment_btn;

    private TextView recordmain_payment_textview;

    private LinearLayout recordmain_remark;

    private LinearLayout recordmain_remark_btn;

    private TextView recordmain_remark_textview;
	
	private FloatingActionButton recordmain_fab;

    private Record_Item_Adapter adapter;

    private ArrayList<Record_Item_Model> xx_list;

    private Double Total_Amount = 0.0;

    DecimalFormat dec = new DecimalFormat("#,##0.00");

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case 1:

                // 全新資料
                if (data != null){

                    Record_Item_Model record = new Record_Item_Model(
                            data.getStringExtra("Product_No"),
                            data.getStringExtra("Product_Name"),
                            data.getStringExtra("Product_Price"),
                            data.getStringExtra("Product_Discount"),
                            data.getStringExtra("Product_Tax"),
                            data.getStringExtra("Product_FianlPrice"));


                    xx_list.add(record);
//                    adapter.addData(data.getIntExtra("Postion", 0), record);
                    adapter.notifyDataSetChanged();

                }

                toggle_view();

                break;


            case 2:

                if (data != null){

                    String Action_Type = data.getStringExtra("Action_Type");

                    if (Action_Type.equals("DEL_APPLICATION")){
						
                        int DEL_POSITION = data.getIntExtra("Del_Postion", 0);
                        xx_list.remove(DEL_POSITION);
                        adapter.notifyItemRemoved(DEL_POSITION);
						
                    }

                    if (Action_Type.equals("UPDATE_APPLICATION")) {

                        int UPDATE_POSITION = data.getIntExtra("Position", 0);
                        xx_list.remove(UPDATE_POSITION);
                        adapter.notifyItemRemoved(UPDATE_POSITION);

                        Record_Item_Model record = new Record_Item_Model(
                                data.getStringExtra("Product_No"),
                                data.getStringExtra("Product_Name"),
                                data.getStringExtra("Product_Price"),
                                data.getStringExtra("Product_Discount"),
                                data.getStringExtra("Product_Tax"),
                                data.getStringExtra("Product_FianlPrice"));

                        xx_list.add(record);
                        adapter.notifyDataSetChanged();

                    }

                }

                toggle_view();


                break;

            case 3:

                if (data != null){
                    add_remark(data.getStringExtra("remark_msg"));
                }

                break;

        }


    }


    public void toggle_view(){

        if (xx_list.isEmpty()){

            recordmain_total_amount.setVisibility(View.GONE);
            recordmain_payment_btn.setVisibility(View.GONE);
            recordmain_remark.setVisibility(View.GONE);

        }else {

            recordmain_total_amount.setVisibility(View.VISIBLE);
            recordmain_payment_btn.setVisibility(View.VISIBLE);
            recordmain_remark.setVisibility(View.VISIBLE);

            Total_Amount = 0.0;
            for (int i = 0; i < xx_list.size(); i++){
                Total_Amount = Total_Amount + Double.parseDouble(xx_list.get(i).getProduct_final_price().replaceAll("[$,]", ""));
            }
            recordmain_total_amount_textview.setText("$" + dec.format(Total_Amount));
        }

    }



    public void add_remark(String remark){

        recordmain_remark_textview.setText(remark);
    }

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.a001_record_main);
		
		Find_View();
		
		xx_list = new ArrayList<>();

        // set up the RecyclerView
        recordmain_item_recyclerview.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Record_Item_Adapter(this, xx_list);
        adapter.setClickListener(new Record_Item_Adapter.ItemClickListener(){

				@Override
				public void onItemClick(View view, int position) {

				    Log.e("Adapter Action", "You click Position : " + position);

					Intent open_a002_activity = new Intent(Record_Main.this, Record_Item.class);

					open_a002_activity.putExtra("extras_position", position);
					open_a002_activity.putExtra("extras_product_no", adapter.get_Product_No(position));
					open_a002_activity.putExtra("extras_product_name", adapter.get_Product_Name(position));
					open_a002_activity.putExtra("extras_product_price", adapter.get_Product_Price(position));
					open_a002_activity.putExtra("extras_product_discount", adapter.get_Product_Discount(position));
					open_a002_activity.putExtra("extras_product_tax", adapter.get_Product_Tax(position));
					open_a002_activity.putExtra("extras_product_finalprice", adapter.get_Product_FinalPrice(position));

					startActivityForResult(open_a002_activity, 2);

				}
			});

        recordmain_item_recyclerview.setAdapter(adapter);
	}


    private void Find_View(){
		
		recordmain_scrollview = (NestedScrollView) findViewById(R.id.recordmain_scrollview);
		recordmain_scrollview.setOnScrollChangeListener(NestedScrollView_OnScrollChangeListener);

        recordmain_name_edittext = (EditText) findViewById(R.id.recordmain_name_edittext);
        recordmain_name_edittext.setText("");

        recordmain_date_textview = (TextView) findViewById(R.id.recordmain_date_textview);
        recordmain_date_textview.setOnClickListener(View_Click_Listener);

        recordmain_income_btn = (LinearLayout) findViewById(R.id.recordmain_income_btn);
        recordmain_income_btn.setOnClickListener(View_Click_Listener);

        recordmain_disbursement_btn = (LinearLayout) findViewById(R.id.recordmain_disbursement_btn);
        recordmain_disbursement_btn.setOnClickListener(View_Click_Listener);

        recordmain_newitem_btn = (LinearLayout) findViewById(R.id.recordmain_newitem_btn);
        recordmain_newitem_btn.setOnClickListener(View_Click_Listener);

        recordmain_item_recyclerview = (RecyclerView) findViewById(R.id.recordmain_item_recyclerview);
        recordmain_item_recyclerview.setNestedScrollingEnabled(false);

        recordmain_total_amount = (LinearLayout) findViewById(R.id.recordmain_total_amount);

        recordmain_total_amount_textview = (TextView) findViewById(R.id.recordmain_total_amount_textview);

        recordmain_payment = (LinearLayout) findViewById(R.id.recordmain_payment);

        recordmain_payment_btn = (LinearLayout) findViewById(R.id.recordmain_payment_btn);
        recordmain_payment_btn.setOnClickListener(View_Click_Listener);

        recordmain_payment_textview = (TextView) findViewById(R.id.recordmain_payment_textview);

        recordmain_remark = (LinearLayout) findViewById(R.id.recordmain_remark);

        recordmain_remark_btn = (LinearLayout) findViewById(R.id.recordmain_remark_btn);
        recordmain_remark_btn.setOnClickListener(View_Click_Listener);

        recordmain_remark_textview = (TextView) findViewById(R.id.recordmain_remark_textview);
		
		recordmain_fab = (FloatingActionButton) findViewById(R.id.recordmain_fab);
		recordmain_fab.setOnClickListener(View_Click_Listener);

    }

	private NestedScrollView.OnScrollChangeListener NestedScrollView_OnScrollChangeListener = new NestedScrollView.OnScrollChangeListener(){

		@Override
		public void onScrollChange(NestedScrollView v, int scrollx, int scrolly, int oldx, int oldy)
		{
			// TODO: Implement this method
			if(scrolly > oldy){
				recordmain_fab.hide();
			}else{
				recordmain_fab.show();
			}
		}
		
		
	};


    private View.OnClickListener View_Click_Listener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.recordmain_date_textview:

                    Intent open_b003_activity = new Intent(Record_Main.this, Review_Calendar.class);
                    startActivity(open_b003_activity);

                    break;

                case R.id.recordmain_income_btn:

                    recordmain_income_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.a001_left_border_select));
                    recordmain_disbursement_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.a001_right_border_normal));

                    break;

                case R.id.recordmain_disbursement_btn:

                    recordmain_income_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.a001_left_border_normal));
                    recordmain_disbursement_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.a001_right_border_select));

                    break;

                case R.id.recordmain_newitem_btn:

//                    Intent open_a002_activity = new Intent(getActivity(), Record_Item.class);
//                    startActivityForResult(open_a002_activity, 2);

                    break;

                case R.id.recordmain_remark_btn:

                    Intent open_a005_activity = new Intent(Record_Main.this, Record_Remark.class);
                    startActivityForResult(open_a005_activity, 3);

                    break;


                case R.id.recordmain_payment_btn:

                    Intent open_a007_activity = new Intent(Record_Main.this, Record_Payment.class);
					
					open_a007_activity.putExtra("Record_Size", xx_list.size());
					open_a007_activity.putExtra("Total_Amount", recordmain_total_amount_textview.getText().toString());
					
                    startActivity(open_a007_activity);

                    break;
					
					
				case R.id.recordmain_fab:
					
					Intent open_a002_activity = new Intent(Record_Main.this, Record_Item.class);
                    startActivityForResult(open_a002_activity, 1);
					
					break;

            }

        }
    };


}
