package com.example.lpc.receipt;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.lpc.receipt.NewRecord.NewRecod_Main;
import com.example.lpc.receipt.Public.Calendar_Selecter;
import com.example.lpc.receipt.Public.Change_Date;
import com.example.lpc.receipt.Public.FullScreencall;
import com.example.lpc.receipt.Review.Review_Main;
import com.example.lpc.receipt.Setting.Setting_Main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
	
	private ViewPager_Adapter mViewPager_Adapter;
	
	private int Index_Position;

    private int ViewrPager_Position;

	private LinearLayout New_btn, Setting_btn;

	private FloatingActionButton activity_main_fab;

	private List<Long> DateList;

	// 本日 (不可修改)
	private static Calendar mCalendar = Calendar.getInstance();

	public static Calendar StartDate_Calendar, EndDate_Calendar, SelectDate_Calendar;
	
	public long Select_Date_parseLong;
	
	private Change_Date mChange_Date;

    @Override
    protected void onResume() {
        super.onResume();
        new FullScreencall().FullScreencall(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case 8:

                // 接收用戶係 Calendar 揀左邊日, 然後跳轉到果日顯示
                if (data != null){

                    Calendar New_Start_Date = Calendar.getInstance();
                    New_Start_Date.set(1970, 0, 1);
                    
					SelectDate_Calendar = (Calendar) data.getSerializableExtra("Calendar_Selecter_Date");

                    int Select_Date_Position = mChange_Date.getDate_Diff(SelectDate_Calendar.getTimeInMillis(), New_Start_Date.getTimeInMillis());
					
                    mViewPager.setCurrentItem(Select_Date_Position + 1);

                }
				
                break;
				
			case 773:
				
				if(data != null){
					Jump_ToPage(data.getIntExtra("Page_Position", 0));
					Select_Date_parseLong = data.getLongExtra("RecordMain_SelectDate", 0);
				}
				
				break;
				
			case 688:
				Open_LoginScreen();
				break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.a000_activity_main);
		
		mChange_Date = new Change_Date();

		// 用戶所選的日期, 預設為今天
        SelectDate_Calendar = Calendar.getInstance();
		//Select_Date_parseLong = SelectDate_Calendar.getTimeInMillis();

        // 設始初始日期 1970-1-1
        StartDate_Calendar = Calendar.getInstance();
        StartDate_Calendar.set(1970, 0, 1);

        // 最後日期為初始日期 +300 年
        EndDate_Calendar = (Calendar) StartDate_Calendar.clone();
        EndDate_Calendar.add(Calendar.YEAR, 300);

        Find_View();

    }


    @SuppressLint("WrongViewCast")
    private void Find_View(){

        activity_main_fab = (FloatingActionButton) findViewById(R.id.activity_main_fab);
        activity_main_fab.setOnClickListener(View_OnClickListeren);

        mViewPager = (ViewPager) findViewById(R.id.main_activity_viewpager);

        Setup_ViewPager();

		New_btn = (LinearLayout) findViewById(R.id.new_btn);
		New_btn.setOnClickListener(View_OnClickListeren);

		Setting_btn = (LinearLayout) findViewById(R.id.setting_btn);
		Setting_btn.setOnClickListener(View_OnClickListeren);

    }


	private View.OnClickListener View_OnClickListeren = new View.OnClickListener(){

		@Override
		public void onClick(View v)
		{
			// TODO: Implement this method
			switch(v.getId()){

                case R.id.activity_main_fab:
					
                    Jump_ToPage(ViewrPager_Position);
					
                    break;

				case R.id.new_btn:
					
					Intent open_a001_activity = new Intent(MainActivity.this, NewRecod_Main.class);

					// 向當前 Fragment (Review_Main) 取 Calendar
                    FragmentStatePagerAdapter f = (FragmentStatePagerAdapter) mViewPager.getAdapter();

                    Review_Main mReview_Main = (Review_Main) f.instantiateItem(mViewPager, Index_Position);

                    SelectDate_Calendar = mReview_Main.getFragmentCalendar();

					open_a001_activity.putExtra("Testing_Select_Date", SelectDate_Calendar);
					startActivityForResult(open_a001_activity, 773);
					
					break;

				case R.id.setting_btn:
					
					Intent open_z001_activity = new Intent(MainActivity.this, Setting_Main.class);
					startActivityForResult(open_z001_activity, 688);
					
					break;
			}
		}
	};

    
    private void Setup_ViewPager(){


		ViewrPager_Position = mChange_Date.getDate_Diff(mCalendar.getTimeInMillis(), StartDate_Calendar.getTimeInMillis()) + 1;
		
		Index_Position = ViewrPager_Position;
		
		// 由 Start Date 開始循環放入日期, 直至到 End Date
        DateList = new ArrayList<>();
		
		while(StartDate_Calendar.compareTo(EndDate_Calendar) <= 0){

			DateList.add(StartDate_Calendar.getTimeInMillis());
			
			StartDate_Calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
        mViewPager_Adapter = new ViewPager_Adapter(getSupportFragmentManager(), DateList);

        mViewPager.setAdapter(mViewPager_Adapter);
		
		mViewPager.setOffscreenPageLimit(1);

        mViewPager.setCurrentItem(ViewrPager_Position);


        // 過場動畫
        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                position = (float) Math.sqrt(1 - Math.abs(position));
                page.setAlpha(position);
            }
        });

        // 過場監聽
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position == ViewrPager_Position){
                    activity_main_fab.hide();
                }else {
                    activity_main_fab.show();
                }
			
            }

            @Override
            public void onPageSelected(int position) {

				Index_Position = position;
			
            }

            @Override
            public void onPageScrollStateChanged(int state) {

				
            }
        });

    }

    // 返回指定頁
    public void Jump_ToPage(int position){
        mViewPager.setCurrentItem(position);
    }



	public void Open_Calendar(){
        Intent open_b003_activity = new Intent(MainActivity.this, Calendar_Selecter.class);
        startActivityForResult(open_b003_activity, 8);
    }



	public void Open_LoginScreen(){

		Intent mIntent = new Intent();

		mIntent.setClass(MainActivity.this, LoginActivity.class);

		startActivity(mIntent);

		finish();
	}

}


