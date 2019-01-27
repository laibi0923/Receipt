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

import com.example.lpc.receipt.R;

public class Review_Main extends Fragment {

    private LinearLayout calendar_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.b001_review_main, container, false);

        Find_View(v);

        return v;

    }

    private void Find_View(View v){

        calendar_btn = v.findViewById(R.id.calendar_btn);

        calendar_btn.setOnClickListener(ViewClickListener);
    }


    private View.OnClickListener ViewClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.calendar_btn:

                    Intent open_b003_activity = new Intent(getActivity(), Review_Calendar.class);
                    startActivity(open_b003_activity);

                    break;
            }

        }
    };

}
