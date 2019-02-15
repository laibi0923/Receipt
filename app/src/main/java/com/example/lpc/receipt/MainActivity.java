package com.example.lpc.receipt;

import android.annotation.*;
import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;

import com.example.lpc.receipt.Public.Calendar_Selecter;
import com.example.lpc.receipt.Setting.*;
import java.util.*;
import com.example.lpc.receipt.Record.*;

import java.text.*;
import com.example.lpc.receipt.Public.*;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

	private LinearLayout New_btn, Setting_btn;

	private FloatingActionButton activity_main_fab;

	private List<Long> DateList;

	// 本日 (不可修改)
	private static Calendar mCalendar = Calendar.getInstance();

	private static Calendar Start_Date, End_Date, Select_Date;

    // 計算開始日期與本日之間日差 定義為當前顯示頁面 Current_Position
    // one_day_ms 為計算一日毫秒，用作計算日差, 必須定義為 long
    // private long one_day_ms = 24 * 60 * 60 * 1000;

	private int Current_Position;

    //SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("MM月dd日");
	
	private Change_Date mChange_Date;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case 8:

                // 接收用戶係 Calendar 揀左邊日, 然後跳轉到果日顯示
                if (data != null){

                    Calendar New_Start_Date = Calendar.getInstance();
                    New_Start_Date.set(1970, 0, 1);

                    long getselect_Date = data.getLongExtra("getselect_Date", 0);
                    Select_Date.setTimeInMillis(getselect_Date);

                    //int day_diff = (int) ((Select_Date.getTimeInMillis() - New_Start_Date.getTimeInMillis()) / (one_day_ms));

                    int Select_Date_Position = mChange_Date.getDate_Diff(Select_Date.getTimeInMillis(), New_Start_Date.getTimeInMillis() + 1);
                    mViewPager.setCurrentItem(Select_Date_Position);

                }
                break;
				
			case 773:
				
				if(data != null){
					Jump_ToPage(data.getIntExtra("Page_Position", 0));
				}
				
				break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.a000_activity_main);
		
		mChange_Date = new Change_Date();

        Select_Date = Calendar.getInstance();

        // 設始初始日期 1970-1-1
        Start_Date = Calendar.getInstance();
        Start_Date.set(1970, 0, 1);

        // 最後日期為初始日期 +100,000 日
        End_Date = (Calendar) Start_Date.clone();
        End_Date.add(Calendar.DAY_OF_MONTH, 100000);

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
                    Jump_ToPage(Current_Position);
                    break;

				case R.id.new_btn:
					Intent open_a001_activity = new Intent(MainActivity.this, Record_Main.class);
					long Select_Date_parseLong = Select_Date.getTimeInMillis();
					open_a001_activity.putExtra("Select_Date", Select_Date_parseLong);
					startActivityForResult(open_a001_activity, 773);
					break;

				case R.id.setting_btn:
					Intent open_z001_activity = new Intent(MainActivity.this, Setting_Main.class);
					startActivity(open_z001_activity);
					break;
			}
		}
	};

    
    private void Setup_ViewPager(){

		//int day_diff = (int) ((mCalendar.getTimeInMillis() - Start_Date.getTimeInMillis()) / (one_day_ms));
		Current_Position = mChange_Date.getDate_Diff(mCalendar.getTimeInMillis(), Start_Date.getTimeInMillis()) + 1;
		
		// 由 Start Date 開始循環放入日期, 直至到 End Date
        DateList = new ArrayList<>();
		
		while(Start_Date.compareTo(End_Date) <= 0){
			
			//DateList.add(mSimpleDateFormat.format(Start_Date.getTime()));
			DateList.add(Start_Date.getTimeInMillis());
			
			Start_Date.add(Calendar.DAY_OF_MONTH, 1);
		}
		
        final ViewPager_Adapter mViewPager_Adapter = new ViewPager_Adapter(getSupportFragmentManager(), DateList);

        mViewPager.setAdapter(mViewPager_Adapter);
		
		mViewPager.setOffscreenPageLimit(0);

        mViewPager.setCurrentItem(Current_Position);


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

                if (position == Current_Position){
                    activity_main_fab.hide();
                }else {
                    activity_main_fab.show();
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

				
            }
        });

    }

    // 返回本日
    public void Jump_ToPage(int position){
        mViewPager.setCurrentItem(position);
    }


	public void Open_Calendar(){
        Intent open_b003_activity = new Intent(MainActivity.this, Calendar_Selecter.class);
        startActivityForResult(open_b003_activity, 8);
    }

}


