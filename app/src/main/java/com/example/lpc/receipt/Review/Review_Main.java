package com.example.lpc.receipt.Review;

import android.os.*;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.example.lpc.receipt.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Calendar;


// 未完成 Fragment 狀態保存
public class Review_Main extends Fragment {

	private TextView reviewmain_totalamount;
	
    private TextView reviewmain_date;
	
	private RecyclerView reviewmain_recyclerView;

	private Long Display_Date_Long;
	
	private String Display_Date_String;

	private Review_Item_Adapter adapter;

	private ArrayList<Review_Item_Model> review_list;
	
	SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("MM月dd日");
	
	
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
		Log.e("setUserVisibleHint", "true");
		onResume();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onActivityCreated(savedInstanceState);

		// 保存 Fragment 收狀態其中一種方式
		setRetainInstance(true);
	}

	
	@Override
	public void onResume()
	{
		// TODO: Implement this method
		super.onResume();
		Calendar mCalendar = Calendar.getInstance();
		
		Display_Date_Long = getArguments().getLong("Display_Date");
		Calendar Get_DateCalendar = Calendar.getInstance();
		Get_DateCalendar.setTimeInMillis(Display_Date_Long);
		
		long one_day_ms = 24 * 60 * 60 * 1000;
		int day_diff = (int) ((mCalendar.getTimeInMillis() - Get_DateCalendar.getTimeInMillis()) / (one_day_ms));
	
		Log.e("day diff", day_diff + "");
		
		Display_Date_String = mSimpleDateFormat.format(Get_DateCalendar.getTime());
		/*
		if(day_diff == 0){
			Display_Date_String = "本日";
		}else{
			Display_Date_String = mSimpleDateFormat.format(Get_DateCalendar.getTime());
		}
		*/
	}
	
	

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.b001_review_main, container, false);

        Find_View(v);

        review_list = new ArrayList<>();

        // For Sample Data
        for (int i = 0; i < 3; i++){
			review_list.add(new Review_Item_Model("11:00", "7-11", "$5,000"));
		}
		// For Sample Data

        reviewmain_recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        adapter = new Review_Item_Adapter(this.getContext(), review_list);

        adapter.setClickListener(ReviewItemClickListener);

        reviewmain_recyclerView.setAdapter(adapter);

        return v;

    }

    private void Find_View(View v){

		reviewmain_totalamount = v.findViewById(R.id.reviewmain_totalamount);
		
        reviewmain_date = v.findViewById(R.id.reviewmain_date);
		reviewmain_date.setText(Display_Date_String);
		reviewmain_date.setOnClickListener(View_ClickListener);
		
		reviewmain_recyclerView = v.findViewById(R.id.reviewmain_recyclerView);

    }

    private Review_Item_Adapter.ItemClickListener ReviewItemClickListener = new Review_Item_Adapter.ItemClickListener() {
		@Override
		public void onItemClick(View view, int position) {
			Toast.makeText(getActivity(), position + " was click" , Toast.LENGTH_SHORT).show();
			Log.e("Click", position + "");
		}
	};

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
}
