package com.example.lpc.receipt.Review;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lpc.receipt.MainActivity;
import com.example.lpc.receipt.R;
import android.support.v7.widget.*;
import android.view.View.*;

public class Review_Main extends Fragment {

	private TextView reviewmain_totalamount;
	
    private TextView reviewmain_date;
	
	private RecyclerView reviewmain_recyclerView;

	private String Display_Date;
	
	
	public static Review_Main newInstance(String Display_Date){
		
		Review_Main fragment = new Review_Main();

		Bundle args = new Bundle();

		args.putString("Display_Date", Display_Date);

		fragment.setArguments(args);
		
		return fragment;
	}
	

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.b001_review_main, container, false);

        Display_Date = getArguments().getString("Display_Date");

        Find_View(v);

        return v;

    }

    private void Find_View(View v){

		reviewmain_totalamount = v.findViewById(R.id.reviewmain_totalamount);
		
        reviewmain_date = v.findViewById(R.id.reviewmain_date);
		reviewmain_date.setText(Display_Date);
		reviewmain_date.setOnClickListener(View_ClickListener);
		
		reviewmain_recyclerView = v.findViewById(R.id.reviewmain_recyclerView);

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
}
