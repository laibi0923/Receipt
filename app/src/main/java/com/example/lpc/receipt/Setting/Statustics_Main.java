package com.example.lpc.receipt.Setting;

import android.os.*;
import android.support.annotation.*;
import android.support.v7.app.*;
import android.util.*;
import android.widget.*;
import com.example.lpc.receipt.*;

public class Statustics_Main extends AppCompatActivity {


	private RelativeLayout statustics_view;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.y002_statistics_parts);

        Find_View();

    }


	private void Find_View(){

        statustics_view = (RelativeLayout) findViewById(R.id.statustics_view);

		Statustics_Circle mDraw_Statustics_Circle = new Statustics_Circle();
		mDraw_Statustics_Circle.Draw_Statustics_Circle(statustics_view, this);

	}

	
	

}

