package com.example.lpc.receipt.Record;
import android.content.Context;
import android.support.v4.app.*;
import android.view.*;
import android.os.*;
import android.widget.RelativeLayout;

import com.example.lpc.receipt.R;
public class NewRecord_Keybord extends Fragment {

	public Keyboard_Listener mKeyboard_Listener;

	private RelativeLayout numberpad_del, numberpad_1, numberpad_2, numberpad_3, numberpad_4, numberpad_5, numberpad_6, numberpad_7,
							numberpad_8, numberpad_9, numberpad_0, numberpad_00, numberpad_dec;

	public interface Keyboard_Listener{
		public void SendContent(String Info);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mKeyboard_Listener = (Keyboard_Listener) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		View v = inflater.inflate(R.layout.c001_newrecord_keybord, container, false);
		Find_View(v);
		return v;
	}

	private void Find_View(View v){

		numberpad_del = v.findViewById(R.id.numberpad_del);
		numberpad_del.setOnClickListener(NumberPad_OnclickListener);

		numberpad_1 = v.findViewById(R.id.numberpad_1);
		numberpad_1.setOnClickListener(NumberPad_OnclickListener);

		numberpad_2 = v.findViewById(R.id.numberpad_2);
		numberpad_2.setOnClickListener(NumberPad_OnclickListener);

		numberpad_3 = v.findViewById(R.id.numberpad_3);
		numberpad_3.setOnClickListener(NumberPad_OnclickListener);

		numberpad_4 = v.findViewById(R.id.numberpad_4);
		numberpad_4.setOnClickListener(NumberPad_OnclickListener);

		numberpad_5 = v.findViewById(R.id.numberpad_5);
		numberpad_5.setOnClickListener(NumberPad_OnclickListener);

		numberpad_6 = v.findViewById(R.id.numberpad_6);
		numberpad_6.setOnClickListener(NumberPad_OnclickListener);

		numberpad_7 = v.findViewById(R.id.numberpad_7);
		numberpad_7.setOnClickListener(NumberPad_OnclickListener);

		numberpad_8 = v.findViewById(R.id.numberpad_8);
		numberpad_8.setOnClickListener(NumberPad_OnclickListener);

		numberpad_9 = v.findViewById(R.id.numberpad_9);
		numberpad_9.setOnClickListener(NumberPad_OnclickListener);

		numberpad_0 = v.findViewById(R.id.numberpad_0);
		numberpad_0.setOnClickListener(NumberPad_OnclickListener);

		numberpad_00 = v.findViewById(R.id.numberpad_00);
		numberpad_00.setOnClickListener(NumberPad_OnclickListener);

		numberpad_dec = v.findViewById(R.id.numberpad_dec);
		numberpad_dec.setOnClickListener(NumberPad_OnclickListener);

	}

	private View.OnClickListener NumberPad_OnclickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {

			switch (v.getId()){

				case R.id.numberpad_del:
					mKeyboard_Listener.SendContent("del");
					break;

				case R.id.numberpad_1:
					mKeyboard_Listener.SendContent("1");
					break;

				case R.id.numberpad_2:
					mKeyboard_Listener.SendContent("2");
					break;

				case R.id.numberpad_3:
					mKeyboard_Listener.SendContent("3");
					break;

				case R.id.numberpad_4:
					mKeyboard_Listener.SendContent("4");
					break;

				case R.id.numberpad_5:
					mKeyboard_Listener.SendContent("5");
					break;

				case R.id.numberpad_6:
					mKeyboard_Listener.SendContent("6");
					break;

				case R.id.numberpad_7:
					mKeyboard_Listener.SendContent("7");
					break;

				case R.id.numberpad_8:
					mKeyboard_Listener.SendContent("8");
					break;

				case R.id.numberpad_9:
					mKeyboard_Listener.SendContent("9");
					break;

				case R.id.numberpad_0:
					mKeyboard_Listener.SendContent("0");
					break;

				case R.id.numberpad_00:
					mKeyboard_Listener.SendContent("00");
					break;

				case R.id.numberpad_dec:
					mKeyboard_Listener.SendContent(".");
					break;


			}

		}
	};
	
	
}
