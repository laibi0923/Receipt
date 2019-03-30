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
import com.example.lpc.receipt.Public.FullScreencall;

import android.view.inputmethod.*;
import android.content.*;

import java.text.DecimalFormat;

public class NewRecod_Main extends AppCompatActivity implements NewRecord_Keybord.Keyboard_Listener
{

	@Override
	public void SendContent(String Info) {

		if (Info.equals("del")){
			int length = mRecord_BottomSheetDialog.newrecord_amount_ed.getText().length();

			if(length > 0){
				mRecord_BottomSheetDialog.newrecord_amount_ed.getText().delete(length - 1, length);
			}

		}else if(Info.equals("del_long")){
			mRecord_BottomSheetDialog.newrecord_amount_ed.setText("");
		}else{
			mRecord_BottomSheetDialog.newrecord_amount_ed.setText(mRecord_BottomSheetDialog.newrecord_amount_ed.getText().toString() +  Info);
//			mChange_Amount.Change_Amount(newrecord_amount_ed.getText().toString() + Info, newrecord_amount_ed);
		}
	}
	


	private RelativeLayout newrecord_close_btn;
	
	private RelativeLayout newrecord_addrecord_btn;

	private Change_Amount mChange_Amount;
	
	private Record_BottomSheetDialog mRecord_BottomSheetDialog;

	@Override
	protected void onResume() {
		super.onResume();
		//new FullScreencall().FullScreencall(this);
	}

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
					
					mRecord_BottomSheetDialog = new Record_BottomSheetDialog();
					
					mRecord_BottomSheetDialog.show(getSupportFragmentManager(), "");
					
					break;
				

			}

		}
		
	};





	}

