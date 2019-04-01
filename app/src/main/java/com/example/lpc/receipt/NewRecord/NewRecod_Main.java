package com.example.lpc.receipt.NewRecord;
import android.content.Context;
import android.os.*;
import android.support.annotation.NonNull;
import android.support.v7.app.*;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;
import com.example.lpc.receipt.*;
import com.example.lpc.receipt.Public.Change_Amount;
import com.example.lpc.receipt.Record.Record_Item_Adapter;

import java.util.ArrayList;
import java.util.List;

public class NewRecod_Main extends AppCompatActivity implements Keybord_Fragment.Keyboard_Listener, View.OnClickListener {

	private RelativeLayout newrecord_close_btn;
	
	private RelativeLayout newrecord_addrecord_btn;

	private Change_Amount mChange_Amount;
	
	private BottomSheetDialog mRecord_BottomSheetDialog = new BottomSheetDialog();;

	private LinearLayout newrecord_savereceipt_btn;

	private RecyclerView mRecyclerView;

	private Receipt_Adapter Receipt_Adapter;

	public ArrayList<Receipt_Item_Model> receipt_list;

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




	@Override
	protected void onResume() {
		super.onResume();
		//new FullScreencall().FullScreencall(this);
	}




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO: Implement this method
		super.onCreate(savedInstanceState);

		setContentView(R.layout.c00_record_main);

		mChange_Amount = new Change_Amount();

		Find_View();

		mRecord_BottomSheetDialog.show(getSupportFragmentManager(), "");

	}




	
	private void Find_View() {

		// Main Content
		newrecord_close_btn = (RelativeLayout) findViewById(R.id.newrecord_close_btn);
		newrecord_close_btn.setOnClickListener(this);

		newrecord_addrecord_btn = (RelativeLayout) findViewById(R.id.newrecord_addrecord_btn);
		newrecord_addrecord_btn.setOnClickListener(this);

		newrecord_savereceipt_btn = (LinearLayout) findViewById(R.id.newrecord_savereceipt_btn);
		newrecord_savereceipt_btn.setOnClickListener(this);

		receipt_list = new ArrayList<>();
		Receipt_Adapter = new Receipt_Adapter(this, receipt_list);

		mRecyclerView  = (RecyclerView) findViewById(R.id.newrecord_recycleview);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setNestedScrollingEnabled(false);
		mRecyclerView.setAdapter(Receipt_Adapter);
	}





	@Override
	public void onClick(View v) {

		switch (v.getId()) {

			case R.id.newrecord_close_btn:

				finish();

				break;


			case R.id.newrecord_addrecord_btn:

				mRecord_BottomSheetDialog.show(getSupportFragmentManager(), "");

				break;

			case R.id.newrecord_savereceipt_btn:

				SaveReceipt_toDB();
				finish();

				break;

		}

	}



	public void NewReceipt_Data(String Item_Name, String Item_Type, String Item_Price){

		Receipt_Item_Model modle = new Receipt_Item_Model(Item_Name, Item_Type, Item_Price);

		receipt_list. add(modle);

		Receipt_Adapter.notifyDataSetChanged();


	}



	private void SaveReceipt_toDB() {


	}




}



