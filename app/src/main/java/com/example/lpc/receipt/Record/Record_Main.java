package com.example.lpc.receipt.Record;

import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.support.design.widget.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.example.lpc.receipt.R;
import com.example.lpc.receipt.Public.*;
import java.text.*;
import java.util.*;
import com.google.firebase.database.*;
import com.google.firebase.auth.*;
import java.time.*;

public class Record_Main extends AppCompatActivity {
	
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
	
	private Calendar Fake_Calendar = Calendar.getInstance();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case 1:

                // 新增物品內容
                if (data != null){

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

                toggle_view();

                break;


            case 2:

				// 修改 / 刪除物品內容
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

				// 備註按鍵功能
                if (data != null){
                    recordmain_remark_textview.setText(data.getStringExtra("remark_msg"));
                }

                break;
				
				
			case 488:
				
				// 日曆按鍵功能
				if (data != null){
					
					//getDate_long = data.getLongExtra("Calendar_Selecter_Date", 0);
					
					//Fake_Calendar.setTimeInMillis(getDate_long);
					
					Fake_Calendar = (Calendar) data.getSerializableExtra("Calendar_Selecter_Date");
					
					recordmain_year_textview.setText(new Change_Date().parseToDateString(Fake_Calendar.getTimeInMillis(), "yyyy"));
					recordmain_date_textview.setText(new Change_Date().parseToDateString(Fake_Calendar.getTimeInMillis(), "MM月dd日"));
//					recordmain_year_textview.setText(mChange_Date.parseToDateString(data.getLongExtra("getselect_Date", 0), "yyyy"));
//					recordmain_date_textview.setText(mChange_Date.parseToDateString(data.getLongExtra("getselect_Date", 0), "MM月dd日"));
				}
				
				break;
				
				
			case 137:
				
				// 支付按鍵功能
				if (data != null){
					
					if(!recordmain_name_edittext.getText().toString().isEmpty()){
						
						recordmain_payment.setVisibility(View.VISIBLE);
						recordmain_payment_textview.setText(data.getStringExtra("extraPaymentPrice"));

						recordmain_exchange.setVisibility(View.VISIBLE);
						recordmain_exchange_textview.setText(data.getStringExtra("extraPaymentExchange"));

						recordmain_paymethod.setVisibility(View.VISIBLE);
						recordmain_paymethod_textview.setText(data.getStringExtra("extraPaymentMethod"));


						Calendar New_Start_Date = Calendar.getInstance();
						New_Start_Date.set(1970, 0, 1);

						
						int Select_Date_Position = new Change_Date().getDate_Diff(Fake_Calendar.getTimeInMillis(), New_Start_Date.getTimeInMillis()) + 1;

						Intent mIntent = new Intent();
						mIntent.putExtra("Page_Position", Select_Date_Position);
						mIntent.putExtra("RecordMain_SelectDate", Fake_Calendar.getTimeInMillis());
						setResult(773, mIntent);

						Insert_RecordValue();

						finish();
						
					}else{
						
						Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show();
						
						recordmain_name_edittext.setFocusable(true);
					}
					
				}
				
				break;
				
        }


    }

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.a001_record_main);
	
		// 接收係 MainActivity ViewPager 所顯示日子
        Bundle mBundle = getIntent().getExtras();
		
        if(mBundle != null){
			
			Fake_Calendar = Calendar.getInstance();
			
			Log.e("bubdle", "not bull");
			
			Fake_Calendar = (Calendar) mBundle.get("Testing_Select_Date");
			
			get_Select_Year = new Change_Date().parseToDateString(Fake_Calendar.getTimeInMillis(), "yyyy");
            get_Select_Date = new Change_Date().parseToDateString(Fake_Calendar.getTimeInMillis(), "MM月dd日");
			
        }
		
		Find_View();
		
		xx_list = new ArrayList<>();

        // set up the RecyclerView
        recordmain_item_recyclerview.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Record_Item_Adapter(this, xx_list);
        adapter.setClickListener(RecycleviewItemClickListener);
        recordmain_item_recyclerview.setAdapter(adapter);
	}


    private void Find_View(){
		
		recordmain_scrollview = (NestedScrollView) findViewById(R.id.recordmain_scrollview);
		recordmain_scrollview.setOnScrollChangeListener(NestedScrollView_OnScrollChangeListener);
		
		back_btn = (LinearLayout) findViewById(R.id.back_btn);
		back_btn.setOnClickListener(View_Click_Listener);

        del_btn = (LinearLayout) findViewById(R.id.del_btn);
        del_btn.setVisibility(View.GONE);

        recordmain_name_edittext = (EditText) findViewById(R.id.recordmain_name_edittext);
        recordmain_name_edittext.setText("");
		
		recordmain_year_textview = (TextView) findViewById(R.id.recordmain_year_textview);
		recordmain_year_textview.setText(get_Select_Year);

        recordmain_date_textview = (TextView) findViewById(R.id.recordmain_date_textview);
        recordmain_date_textview.setText(get_Select_Date);
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

        recordmain_payment_textview = (TextView) findViewById(R.id.recordmain_payment_textview);

		recordmain_payment_btn = (LinearLayout) findViewById(R.id.recordmain_payment_btn);
        recordmain_payment_btn.setOnClickListener(View_Click_Listener);
		
		recordmain_exchange = (LinearLayout) findViewById(R.id.recordmain_exchange);
		
		recordmain_exchange_textview = (TextView) findViewById(R.id.recordmain_exchange_textview);
		
		recordmain_paymethod = (LinearLayout) findViewById(R.id.recordmain_paymethod);
		
		recordmain_paymethod_textview = (TextView) findViewById(R.id.recordmain_paymethod_textview);
		
        recordmain_remark = (LinearLayout) findViewById(R.id.recordmain_remark);

        recordmain_remark_btn = (LinearLayout) findViewById(R.id.recordmain_remark_btn);
        recordmain_remark_btn.setOnClickListener(View_Click_Listener);

        recordmain_remark_textview = (TextView) findViewById(R.id.recordmain_remark_textview);
		
		recordmain_fab = (FloatingActionButton) findViewById(R.id.recordmain_fab);
		recordmain_fab.setOnClickListener(View_Click_Listener);

    }


    // Float Action Button 顥示
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




    /*
     *  打開及修改物品內容 (如: 名稱, 銀碼, 折扣, 稅, 總銀碼)
     */

    private Record_Item_Adapter.ItemClickListener RecycleviewItemClickListener = new Record_Item_Adapter.ItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {

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
    };


    private View.OnClickListener View_Click_Listener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            switch (v.getId()){
				
				case R.id.back_btn:
					finish();
					break;

                case R.id.recordmain_date_textview:

                    Intent open_b003_activity = new Intent(Record_Main.this, Calendar_Selecter.class);
					open_b003_activity.putExtra("index", "448");
                    startActivityForResult(open_b003_activity, 488);

                    break;

                case R.id.recordmain_income_btn:

                    recordmain_income_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.a001_left_border_select));
                    recordmain_disbursement_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.a001_right_border_normal));
					Type = "Income";
					
                    break;

                case R.id.recordmain_disbursement_btn:

                    recordmain_income_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.a001_left_border_normal));
                    recordmain_disbursement_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.a001_right_border_select));
					Type = "Disbursement";

                    break;


                case R.id.recordmain_remark_btn:

                    Intent open_a005_activity = new Intent(Record_Main.this, Record_Remark.class);
					open_a005_activity.putExtra("extraRemark", recordmain_remark_textview.getText().toString());
                    startActivityForResult(open_a005_activity, 3);

                    break;


                case R.id.recordmain_payment_btn:

                    Intent open_a007_activity = new Intent(Record_Main.this, Record_Payment.class);
					
					open_a007_activity.putExtra("Record_Size", xx_list.size());
					open_a007_activity.putExtra("Total_Amount", recordmain_total_amount_textview.getText().toString());
					
                    startActivityForResult(open_a007_activity, 137);

                    break;
					
					
				case R.id.recordmain_fab:
					
					Intent open_a002_activity = new Intent(Record_Main.this, Record_Item.class);
                    startActivityForResult(open_a002_activity, 1);
					
					break;

            }

        }
    };


    public void toggle_view(){

        if (xx_list.isEmpty()){

            recordmain_total_amount.setVisibility(View.GONE);
            recordmain_payment_btn.setVisibility(View.GONE);
            recordmain_remark.setVisibility(View.GONE);
			recordmain_payment.setVisibility(View.GONE);
            recordmain_exchange.setVisibility(View.GONE);
            recordmain_paymethod.setVisibility(View.GONE);

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
	
	
	private void Insert_RecordValue(){
		
		
		// Create local new time in long when user click a new button
		// Fake Calendar use for get the Firebase patch value only
		Calendar Real_Local_Calendar = Calendar.getInstance();

        // Patch
		// Patch_RecordDetails  : UserId/Record/Year/Months/dd/TimInMillis
		// Patch_RecordItems    : UserId/Record/Year/Months/dd/z_Item/Position
		// Patch_Monthly_Amount : UserId/Record/Year/Months/MonthlyAmount
		// Patch_Year_Amount    : UserId/Record/Year/YearAmount

		
		//long CreateTimeInMillis = CreateTime_Calendar.getTimeInMillis();
		String CreateYear = new Change_Date().parseToDateString(Fake_Calendar.getTimeInMillis(), "yyyy");
		String CreateMonth = new Change_Date().parseToDateString(Fake_Calendar.getTimeInMillis(), "MM");
        String CreateDay = new Change_Date().parseToDateString(Fake_Calendar.getTimeInMillis(), "dd");
		
		FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
		FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
		String UserUid = mFirebaseUser.getUid();
		
		
		// Firebase
		FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
		String Root_Ref = UserUid + "/" + "Record";

		// Firebase Patch
		String Patch_RecordDetails = Root_Ref + "/" + CreateYear + "/" + CreateMonth + "/" + CreateDay + "/A_Receipt/" + Real_Local_Calendar.getTimeInMillis();
		DatabaseReference Ref_RecordDetails = mFirebaseDatabase.getReference(Patch_RecordDetails);

		ArrayList<Record_Item_Model> mklist = new ArrayList();
		
		
		// ZItem
		for(int i = 0; i < xx_list.size(); i++){

			mklist.add(new Record_Item_Model(
				xx_list.get(i).getProduct_no(),
				xx_list.get(i).getProduct_name(),
				xx_list.get(i).getProduct_price(),
				xx_list.get(i).getProduct_discount(),
				xx_list.get(i).getProduct_tax(),
				xx_list.get(i).getProduct_final_price()
			    ));


		}
		
		
        Ref_RecordDetails.setValue(new Record_Model(
						 recordmain_name_edittext.getText().toString(),
						 Fake_Calendar.getTimeInMillis(),
						 Type,
						 recordmain_total_amount_textview.getText().toString(),
						 recordmain_exchange_textview.getText().toString(),
						 recordmain_paymethod_textview.getText().toString(),
						 recordmain_remark_textview.getText().toString(),
						 mklist
						 ));
						 
		
		
		
		
		// Monthly Amount
		String Patch_Monthly_Amount = Root_Ref + "/" + CreateYear + "/" + CreateMonth;
		final DatabaseReference Ref_Monthly_Amount = mFirebaseDatabase.getReference(Patch_Monthly_Amount);
		Query Query_MonthlyAmouunt = Ref_Monthly_Amount.child("Monthly_Amount");
        Query_MonthlyAmouunt.addListenerForSingleValueEvent(new ValueEventListener(){

				@Override
				public void onDataChange(DataSnapshot snapshot)
				{
					Double Monthly_Amount = 0.0;
					
					// TODO: Implement this method
					if(snapshot.exists()){
						Monthly_Amount = Double.parseDouble(snapshot.getValue().toString());
					}else{
						Monthly_Amount = 0.0;
					}
					
					if(Type.equals("Income")){
						Monthly_Amount = Monthly_Amount + Total_Amount;
					}else{
						Monthly_Amount = Monthly_Amount - Total_Amount;
					}
					
					Ref_Monthly_Amount.child("Monthly_Amount").setValue(Monthly_Amount);
				}

				@Override
				public void onCancelled(DatabaseError p1)
				{
					// TODO: Implement this method
				}
			
		});


		// Year Amount
        String Patch_Year_Amount = Root_Ref + "/" + CreateYear;
        final DatabaseReference Ref_Year_Amount = mFirebaseDatabase.getReference(Patch_Year_Amount);
        Query Query_YearAmount = Ref_Year_Amount.child("Year_Amount");
        Query_YearAmount.addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot snapshot)
            {
                Double Year_Amount = 0.0;

                // TODO: Implement this method
                if(snapshot.exists()){
                    Year_Amount = Double.parseDouble(snapshot.getValue().toString());
                }else{
                    Year_Amount = 0.0;
                }

                if(Type.equals("Income")){
                    Year_Amount = Year_Amount + Total_Amount;
                }else{
                    Year_Amount = Year_Amount - Total_Amount;
                }

                Ref_Year_Amount.child("Year_Amount").setValue(Year_Amount);
            }

            @Override
            public void onCancelled(DatabaseError p1)
            {
                // TODO: Implement this method
            }

        });


        // Daily Amount
        String Patch_Daily_Amount = Root_Ref + "/" + CreateYear + "/" + CreateMonth + "/" + CreateDay;
        final DatabaseReference Ref_Daily_Amount = mFirebaseDatabase.getReference(Patch_Daily_Amount);
        Query Query_DailyAmount = Ref_Daily_Amount.child("Daily_Amount");
        Query_DailyAmount.addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot snapshot)
            {
                Double Daily_Amount = 0.0;

                // TODO: Implement this method
                if(snapshot.exists()){
                    Daily_Amount = Double.parseDouble(snapshot.getValue().toString());
                }else{
                    Daily_Amount = 0.0;
                }

                if(Type.equals("Income")){
                    Daily_Amount = Daily_Amount + Total_Amount;
                }else{
                    Daily_Amount = Daily_Amount - Total_Amount;
                }

                Ref_Daily_Amount.child("Daily_Amount").setValue(Daily_Amount);
            }

            @Override
            public void onCancelled(DatabaseError p1)
            {
                // TODO: Implement this method
            }

        });
		

	}



}
