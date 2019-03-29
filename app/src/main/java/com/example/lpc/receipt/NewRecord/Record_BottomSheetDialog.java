package com.example.lpc.receipt.NewRecord;
import android.app.*;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.*;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.*;
import android.view.*;
import com.example.lpc.receipt.*;
import com.example.lpc.receipt.Public.FullScreencall;

import android.widget.*;
import android.graphics.drawable.*;
import android.support.v4.content.*;



public class Record_BottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener{
	
	private LinearLayout bottomSheetLayout;

	private RelativeLayout newrecord_bottomsheet_close;

	private LinearLayout newrecord_type_btn;

	public EditText newrecord_itemname_ed, newrecord_amount_ed;

	private ViewPager mViewPager;

	private RelativeLayout newrecord_keyboard_btn, newrecord_option1_btn, newrecord_option2_btn, newrecord_option3_btn, newrecord_option4_btn,newrecord_save_btn;

	private ImageView newrecord_keyboard_img, newrecord_option1_img, newrecord_option2_img, newrecord_option3_img, newrecord_option4_img;

	private int TopOffset = 0;

	private BottomSheetBehavior<FrameLayout> behavior;

	@Override
	public void onStart() {

		// TODO: Implement this method
		super.onStart();
		
		BottomSheetDialog mBottomSheetDialog = (BottomSheetDialog) getDialog();
		
		
		FrameLayout bottomsheet = (FrameLayout) mBottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
		
		if(mBottomSheetDialog != null){
			
			CoordinatorLayout.LayoutParams parms = (CoordinatorLayout.LayoutParams) bottomsheet.getLayoutParams();
			
			parms.height = getHeight();

			behavior = BottomSheetBehavior.from(bottomsheet);

			behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
		}
	}



	@Override
	public void onDismiss(DialogInterface dialog) {
		super.onDismiss(dialog);

		new FullScreencall().FullScreencall(getActivity());
	}



	private int getHeight(){

		int height = 1920;

		if (getContext() != null){

			WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);

			Point point = new Point();

			if (wm != null){

				wm.getDefaultDisplay().getSize(point);
				height = point.y - getTopOffset();
			}

		}

		return height;
	}



	public int getTopOffset(){
		return TopOffset;
	}



	public void setTopOffset(int topOffset){
		this.TopOffset = topOffset;
	}



	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		// TODO: Implement this method
		super.onActivityCreated(savedInstanceState);
		
		((View) getView().getParent()).setBackgroundColor(Color.TRANSPARENT);
	}


	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// TODO: Implement this method
		View v = LayoutInflater.from(getContext()).inflate(R.layout.c001_newrecord_bottomsheet, null);

		//((View) v.getParent()).setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));

		setTopOffset(150);

		Find_View(v);
		
		return v;
	}

	

	private void Find_View(View v){
		
		newrecord_bottomsheet_close = v.findViewById(R.id.newrecord_bottomsheet_close);
		newrecord_bottomsheet_close.setOnClickListener(this);

		newrecord_itemname_ed = v.findViewById(R.id.newrecord_itemname_ed);

		newrecord_type_btn = v.findViewById(R.id.newrecord_type_btn);
		newrecord_type_btn.setOnClickListener(this);

		newrecord_amount_ed = v.findViewById(R.id.newrecord_amount_ed);
//		newrecord_amount_ed.addTextChangedListener(Amount_TextWatcher);

		mViewPager = v.findViewById(R.id.newrecord_viewpage);

		Viewpager_Adapter mViewpager_Adapter = new Viewpager_Adapter(getChildFragmentManager());

		mViewPager.setAdapter(mViewpager_Adapter);

		mViewPager.addOnPageChangeListener(ViewPager_OnPageChangeListener);

		newrecord_keyboard_btn = v.findViewById(R.id.newrecord_keyboard_btn);
		newrecord_keyboard_btn.setOnClickListener(this);
		newrecord_keyboard_img = v.findViewById(R.id.newrecord_keyboard_img);

		newrecord_option1_btn = v.findViewById(R.id.newrecord_option1_btn);
		newrecord_option1_btn.setOnClickListener(this);
		newrecord_option1_img = v.findViewById(R.id.newrecord_option1_img);

		newrecord_option2_btn = v. findViewById(R.id.newrecord_option2_btn);
		newrecord_option2_btn.setOnClickListener(this);
		newrecord_option2_img = v.findViewById(R.id.newrecord_option2_img);

		newrecord_option3_btn = v.findViewById(R.id.newrecord_option3_btn);
		newrecord_option3_btn.setOnClickListener(this);
		newrecord_option3_img = v.findViewById(R.id.newrecord_option3_img);

		newrecord_option4_btn = v.findViewById(R.id.newrecord_option4_btn);
		newrecord_option4_btn.setOnClickListener(this);
		newrecord_option4_img = v.findViewById(R.id.newrecord_option4_img);

		newrecord_save_btn = v.findViewById(R.id.newrecord_save_btn);
		newrecord_save_btn.setOnClickListener(this);

	}
	


	@Override
	public void onClick(View v) {

		switch (v.getId()){

			case R.id.newrecord_bottomsheet_close:

				dismiss();

				break;


			case R.id.newrecord_type_btn:

				Intent open_c002_activity = new Intent(getActivity(), NewRecord_SearchType.class);
				startActivityForResult(open_c002_activity, 733);

				break;

			case R.id.newrecord_keyboard_btn:

				mViewPager.setCurrentItem(0);
				Clear_Icon_Tint();
				newrecord_keyboard_img.setColorFilter(Color.parseColor("#FFFFFF"));
				break;

			case R.id.newrecord_option1_btn:

				mViewPager.setCurrentItem(1);
				Clear_Icon_Tint();
				newrecord_option1_img.setColorFilter(Color.parseColor("#FFFFFF"));
				break;

			case R.id.newrecord_option2_btn:

				mViewPager.setCurrentItem(2);
				Clear_Icon_Tint();
				newrecord_option2_img.setColorFilter(Color.parseColor("#FFFFFF"));
				break;

			case R.id.newrecord_option3_btn:

				mViewPager.setCurrentItem(3);
				Clear_Icon_Tint();
				newrecord_option3_img.setColorFilter(Color.parseColor("#FFFFFF"));
				break;

			case R.id.newrecord_option4_btn:

				mViewPager.setCurrentItem(4);
				Clear_Icon_Tint();
				newrecord_option4_img.setColorFilter(Color.parseColor("#FFFFFF"));
				break;

			case R.id.newrecord_save_btn:

//					if(newrecord_itemname_ed.getText().length() == 0){
//
//						InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//						inputMethodManager.toggleSoftInputFromWindow(newrecord_itemname_ed.getApplicationWindowToken(),InputMethodManager.SHOW_FORCED, 0);
//
//						newrecord_itemname_ed.requestFocus();
//
//						Toast.makeText(getApplicationContext(), "Please Enter Item Name...", Toast.LENGTH_SHORT).show();

//					}else{

//						InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//						imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//
//						newrecord_itemname_ed.setText("");
//						newrecord_amount_ed.setText("");
//
//						mBottomSheetBehavior.setState(mBottomSheetBehavior.STATE_COLLAPSED);
//					}

				break;

		}
		
	}




	// ViewPager Adapter
	public class Viewpager_Adapter extends FragmentPagerAdapter {

		public Viewpager_Adapter(android.support.v4.app.FragmentManager mFragmentManager){
			super(mFragmentManager);
		}

		@Override
		public int getCount()
		{
			// TODO: Implement this method
			return 5;
		}

		@Override
		public Fragment getItem(int position)
		{
			// TODO: Implement this method
			switch (position){

				default:
					return null;

				case 0:
					return new NewRecord_Keybord();

				case 1:
					return new NewRecord_FastPage();

				case 2:
					return new NewRecord_FastPage();

				case 3:
					return new NewRecord_FastPage();

				case 4:
					return new NewRecord_FastPage();

			}

		}

	}



	// ViewPager PagerChangeListener
	private ViewPager.OnPageChangeListener ViewPager_OnPageChangeListener = new ViewPager.OnPageChangeListener() {
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

		}

		@Override
		public void onPageSelected(int position) {

			switch (position) {

				case 0:
					Clear_Icon_Tint();
					newrecord_keyboard_img.setColorFilter(Color.parseColor("#FFFFFF"));
					break;

				case 1:
					Clear_Icon_Tint();
					newrecord_option1_img.setColorFilter(Color.parseColor("#FFFFFF"));
					break;

				case 2:
					Clear_Icon_Tint();
					newrecord_option2_img.setColorFilter(Color.parseColor("#FFFFFF"));
					break;

				case 3:
					Clear_Icon_Tint();
					newrecord_option3_img.setColorFilter(Color.parseColor("#FFFFFF"));
					break;

				case 4:
					Clear_Icon_Tint();
					newrecord_option4_img.setColorFilter(Color.parseColor("#FFFFFF"));
					break;

			}

		}

		@Override
		public void onPageScrollStateChanged(int state) {

		}
	};



	// 當按下該輸入方式時使用
	private void Clear_Icon_Tint(){
		newrecord_keyboard_img.setColorFilter(Color.parseColor("#3f4447"));
		newrecord_option1_img.setColorFilter(Color.parseColor("#3f4447"));
		newrecord_option2_img.setColorFilter(Color.parseColor("#3f4447"));
		newrecord_option3_img.setColorFilter(Color.parseColor("#3f4447"));
		newrecord_option4_img.setColorFilter(Color.parseColor("#3f4447"));
	}



}
