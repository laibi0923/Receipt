package com.example.lpc.receipt.Review;

import android.os.*;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.example.lpc.receipt.*;
import com.google.firebase.auth.*;
import java.text.*;
import java.util.*;

import com.example.lpc.receipt.R;
import com.example.lpc.receipt.Public.*;
import com.google.firebase.database.*;
import com.example.lpc.receipt.Record.*;

public class Review_Main extends Fragment {

	private TextView reviewmain_totalamount;
	
    private TextView reviewmain_date;
	
	private RecyclerView reviewmain_recyclerView;

	private Long Display_Date_Long;
	
	private String Display_Date_String;

	private Review_Item_Adapter adapter;

	private ArrayList<Record_Model> review_list;

//	SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("MM月dd日");
	
	public Calendar Get_DateCalendar;

	private FirebaseAuth mFirebaseAuth;

	private FirebaseUser mFirebaseUser;

	private FirebaseDatabase mFirebaseDatabase;

	String UserId, GetDate_Year, GetDate_Mth, GetDate_Day;
	
	private int TextColor_Red, TextColor_Green, TextColor_Normal;
	
	
	public static Review_Main newInstance(Long Display_Date){
		
		Review_Main fragment = new Review_Main();

		Bundle args = new Bundle();

		args.putLong("Display_Date", Display_Date);

		fragment.setArguments(args);
		
		return fragment;
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser)
	{
		// TODO: Implement this method
		super.setUserVisibleHint(isVisibleToUser);

		onResume();
	}
	

	

	
	@Override
	public void onResume() {

		// TODO: Implement this method
		super.onResume();
		
		Display_Date_Long = getArguments().getLong("Display_Date");
		Get_DateCalendar = Calendar.getInstance();
		Get_DateCalendar.setTimeInMillis(Display_Date_Long);
		
		((MainActivity) getActivity()).SelectDate_Calendar = Get_DateCalendar;
		
		Display_Date_String = new Change_Date().parseToDateString(Get_DateCalendar.getTimeInMillis(),  "MM月dd日");
		
		
		Display_Date_Long = getArguments().getLong("Display_Date");
		Get_DateCalendar = Calendar.getInstance();
		Get_DateCalendar.setTimeInMillis(Display_Date_Long);


		mFirebaseAuth = FirebaseAuth.getInstance();
		mFirebaseUser = mFirebaseAuth.getCurrentUser();
		mFirebaseDatabase = FirebaseDatabase.getInstance();
		UserId = mFirebaseUser.getUid();
		GetDate_Year = new Change_Date().parseToDateString(Get_DateCalendar.getTimeInMillis(), "yyyy");
		GetDate_Mth = new Change_Date().parseToDateString(Get_DateCalendar.getTimeInMillis(), "MM");
		GetDate_Day = new Change_Date().parseToDateString(Get_DateCalendar.getTimeInMillis(), "dd");

		// Firebase Patch
		String Patch_Root = UserId + "/Record/" + GetDate_Year + "/" +  GetDate_Mth + "/" + GetDate_Day + "/A_Receipt/";
		
		
		
		DatabaseReference Ref_ReviewItem = mFirebaseDatabase.getReference(Patch_Root);

		Ref_ReviewItem.addValueEventListener(new ValueEventListener(){

				@Override
				public void onDataChange(DataSnapshot snapshot)
				{
					// 唔加呢句新增數據會重覆顯示舊數據
					review_list.clear();

					// TODO: Implement this method
					for(DataSnapshot mDataSnapshot : snapshot.getChildren()){

						//Log.e("check", mDataSnapshot.getValue() + "");

						Record_Model mRecord_Model = mDataSnapshot.getValue(Record_Model.class);

						review_list.add(mRecord_Model);

					}

					adapter.get_list(review_list);

					reviewmain_recyclerView.setAdapter(adapter);

					adapter.notifyDataSetChanged();

					Get_DailyAmount();
				}

				@Override
				public void onCancelled(DatabaseError p1)
				{
					// TODO: Implement this method
				}


			});
			
			
			
	}
	
	
	
	

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		
        View v = inflater.inflate(R.layout.b001_review_main, container, false);

        Find_View(v);

		reviewmain_recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

		adapter = new Review_Item_Adapter(this.getContext());

		adapter.setClickListener(ReviewItemClickListener);

		review_list = new ArrayList<>();
		
		TextColor_Red = getContext().getResources().getColor(R.color.text_color_red);
		
		TextColor_Green = getContext().getResources().getColor(R.color.text_color_green);
		
		TextColor_Normal = getContext().getResources().getColor(R.color.text_color_1);

        return v;

    }


	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onActivityCreated(savedInstanceState);

		// 保存 Fragment 收狀態其中一種方式
		setRetainInstance(true);
	}
	

	private void Find_View(View v){

		reviewmain_totalamount = v.findViewById(R.id.reviewmain_totalamount);

		reviewmain_date = v.findViewById(R.id.reviewmain_date);
		reviewmain_date.setText(Display_Date_String);
		reviewmain_date.setOnClickListener(View_ClickListener);

		reviewmain_recyclerView = v.findViewById(R.id.reviewmain_recyclerView);

	}


	/*
	 * 睇返每日用左 / 用淨情況, 需同讀取 Firebase 數據時一齊用, 因為新增紀錄後會重新計算.
	 */



    private void Get_DailyAmount(){

		// Get Daily Amount
		String Patch_DailyAmount = UserId + "/Record/" + GetDate_Year + "/" +  GetDate_Mth + "/" + GetDate_Day;

		DatabaseReference Ref_DailyAmount = mFirebaseDatabase.getReference(Patch_DailyAmount);

		Ref_DailyAmount.child("Daily_Amount").addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


				if (dataSnapshot.exists()){

					DecimalFormat dec = new DecimalFormat("#,##0.00");

					Double get_daily_amount = Double.parseDouble(String.valueOf(dataSnapshot.getValue()));

					new Change_Amount().Change_Amount(dec.format(get_daily_amount), reviewmain_totalamount);

					
					// 設定每日銀碼字體顏色
					if (get_daily_amount < 0){
						reviewmain_totalamount.setTextColor(TextColor_Red);
					}else if (get_daily_amount > 0){
						reviewmain_totalamount.setTextColor(TextColor_Green);
					}else {
						reviewmain_totalamount.setTextColor(TextColor_Normal);
					}
					
				}

			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		});
	}



	private View.OnClickListener View_ClickListener = new View.OnClickListener(){

		@Override
		public void onClick(View view)
		{
			// TODO: Implement this method

			switch(view.getId()){

				case R.id.reviewmain_date:

					// 試過直按 呼叫 startActivityForResult, 但 MainActiviy onActivityResult 接收唔到
					// 所以改成係 MainActivity 入面寫個 Method 打開 Calendar
					((MainActivity) getActivity()).Open_Calendar();

					break;

			}
		}


	};



	/*
	 * 每個 Item 點擊後用, 用黎睇果條紀錄入邊詳細, 如有幾多個貨品, 收款方式等等...
	 */



    private Review_Item_Adapter.ItemClickListener ReviewItemClickListener = new Review_Item_Adapter.ItemClickListener() {
		@Override
		public void onItemClick(View view, int position) {
			Toast.makeText(getActivity(), position + " was click" , Toast.LENGTH_SHORT).show();
			Log.e("Click", position + "");
		}
	};




}
