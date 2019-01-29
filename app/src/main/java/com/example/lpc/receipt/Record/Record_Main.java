package com.example.lpc.receipt.Record;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class Record_Main extends Fragment {
	
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


    public void my_add_data(String Product_No, String Product_Name, String Price, String Discount, String Tax, String Final_Price){

        Record_Item_Model record = new Record_Item_Model(
                Product_No,
                Product_Name,
                Price,
                Discount,
                Tax,
                Final_Price);

        xx_list.add(record);

        Total_Amount = Total_Amount + Double.parseDouble(Final_Price.replaceAll("[$,]", ""));

        recordmain_total_amount_textview.setText("$" + dec.format(Total_Amount));

        adapter.notifyDataSetChanged();

        if (xx_list.isEmpty()){

            recordmain_total_amount.setVisibility(View.GONE);

            recordmain_payment_btn.setVisibility(View.GONE);

            recordmain_remark.setVisibility(View.GONE);

        }else {

            recordmain_total_amount.setVisibility(View.VISIBLE);

            recordmain_payment_btn.setVisibility(View.VISIBLE);

            recordmain_remark.setVisibility(View.VISIBLE);
        }

    }

    public void add_remark(String remark){

        recordmain_remark_textview.setText(remark);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.a001_record_main, container, false);

        Find_View(v);

        xx_list = new ArrayList<>();

        // set up the RecyclerView
        recordmain_item_recyclerview.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new Record_Item_Adapter(this.getActivity(), xx_list);
//        adapter.setClickListener(this.getActivity());
        recordmain_item_recyclerview.setAdapter(adapter);

        return v;
    }




    private void Find_View(View v){
		
		recordmain_scrollview = v.findViewById(R.id.recordmain_scrollview);
		recordmain_scrollview.setOnScrollChangeListener(NestedScrollView_OnScrollChangeListener);

        recordmain_name_edittext = v.findViewById(R.id.recordmain_name_edittext);
        recordmain_name_edittext.setText("");

        recordmain_date_textview = v.findViewById(R.id.recordmain_date_textview);
        recordmain_date_textview.setOnClickListener(View_Click_Listener);

        recordmain_income_btn = v.findViewById(R.id.recordmain_income_btn);
        recordmain_income_btn.setOnClickListener(View_Click_Listener);

        recordmain_disbursement_btn = v.findViewById(R.id.recordmain_disbursement_btn);
        recordmain_disbursement_btn.setOnClickListener(View_Click_Listener);

        recordmain_newitem_btn = v.findViewById(R.id.recordmain_newitem_btn);
        recordmain_newitem_btn.setOnClickListener(View_Click_Listener);

        recordmain_item_recyclerview = v.findViewById(R.id.recordmain_item_recyclerview);
        recordmain_item_recyclerview.setHasFixedSize(true);
        recordmain_item_recyclerview.setNestedScrollingEnabled(false);

        recordmain_total_amount = v.findViewById(R.id.recordmain_total_amount);

        recordmain_total_amount_textview = v.findViewById(R.id.recordmain_total_amount_textview);

        recordmain_payment = v.findViewById(R.id.recordmain_payment);

        recordmain_payment_btn = v.findViewById(R.id.recordmain_payment_btn);
        recordmain_payment_btn.setOnClickListener(View_Click_Listener);

        recordmain_payment_textview = v.findViewById(R.id.recordmain_payment_textview);

        recordmain_remark = v.findViewById(R.id.recordmain_remark);

        recordmain_remark_btn = v.findViewById(R.id.recordmain_remark_btn);
        recordmain_remark_btn.setOnClickListener(View_Click_Listener);

        recordmain_remark_textview = v.findViewById(R.id.recordmain_remark_textview);
		
		recordmain_fab = v.findViewById(R.id.recordmain_fab);
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

                    Intent open_b003_activity = new Intent(getActivity(), Review_Calendar.class);
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

                    Intent open_a005_activity = new Intent(getActivity(), Record_Remark.class);
                    startActivityForResult(open_a005_activity, 3);

                    break;


                case R.id.recordmain_payment_btn:

                    Intent open_a007_activity = new Intent(getActivity(), Record_Payment.class);
					
					open_a007_activity.putExtra("Record_Size", xx_list.size());
					
					open_a007_activity.putExtra("Total_Amount", recordmain_total_amount_textview.getText().toString());
					
                    startActivity(open_a007_activity);

                    break;
					
					
				case R.id.recordmain_fab:
					
					Intent open_a002_activity = new Intent(getActivity(), Record_Item.class);
                    startActivityForResult(open_a002_activity, 2);
					
					break;

            }

        }
    };


}
