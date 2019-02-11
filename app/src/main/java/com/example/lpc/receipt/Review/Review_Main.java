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

import com.example.lpc.receipt.R;

public class Review_Main extends Fragment {

    private TextView reviewmain_date;
	private int mDate;

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

        reviewmain_date = (TextView) v.findViewById(R.id.reviewmain_date);
		reviewmain_date.setText(Display_Date);

    }

    public void ChangeSearch_Date(String datetext){

        reviewmain_date.setText(datetext);

    }
    

}
