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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.b001_review_main, container, false);

        Find_View(v);

        return v;

    }

    private void Find_View(View v){

        reviewmain_date = (TextView) v.findViewById(R.id.reviewmain_date);

    }

    public void ChangeSearch_Date(String datetext){

        reviewmain_date.setText(datetext);

    }
    

}
