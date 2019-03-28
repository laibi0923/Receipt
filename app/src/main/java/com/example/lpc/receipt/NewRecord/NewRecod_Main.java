package com.example.lpc.receipt.NewRecord;
import android.graphics.Color;
import android.os.*;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.*;
import android.support.v4.view.*;
import android.support.v7.app.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.*;
import com.example.lpc.receipt.*;
import com.example.lpc.receipt.Public.Change_Amount;

import android.view.inputmethod.*;
import android.content.*;

import java.text.DecimalFormat;

public class NewRecod_Main extends AppCompatActivity implements NewRecord_Keybord.Keyboard_Listener
{

	@Override
	public void SendContent(String Info)
	{
		// TODO: Implement this method
	}
	

	private RelativeLayout newrecord_close_btn;
	
	private RelativeLayout newrecord_addrecord_btn;

	private Change_Amount mChange_Amount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO: Implement this method
		super.onCreate(savedInstanceState);

		setContentView(R.layout.c001_new_record);

		mChange_Amount = new Change_Amount();

		Find_View();

	}
	
	private void Find_View() {

		// Main Content
		newrecord_close_btn = (RelativeLayout) findViewById(R.id.newrecord_close_btn);
		newrecord_close_btn.setOnClickListener(Item_OnclickListener);

		newrecord_addrecord_btn = (RelativeLayout) findViewById(R.id.newrecord_addrecord_btn);
		newrecord_addrecord_btn.setOnClickListener(Item_OnclickListener);

	}


	
	private View.OnClickListener Item_OnclickListener = new View.OnClickListener(){

		@Override
		public void onClick(View view)
		{
			// TODO: Implement this method
			
			switch(view.getId()){

				case R.id.newrecord_close_btn:

					finish();

					break;
					
					
				case R.id.newrecord_addrecord_btn:
					
					new Record_BottomSheetDialog().show(getSupportFragmentManager(), "");
					break;
				

			}

		}
		
	};





	}

