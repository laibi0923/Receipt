package com.example.lpc.receipt;

import android.annotation.*;
import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.support.v4.view.*;
import android.support.v7.app.*;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.example.lpc.receipt.Review.*;
import com.example.lpc.receipt.Setting.*;
import java.util.*;
import com.example.lpc.receipt.Record.*;

import static android.support.v4.view.ViewPager.SCROLL_STATE_SETTLING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE;
import java.text.*;
import java.time.*;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

	private LinearLayout New_btn, Setting_btn;

	private List<String> DateList;

	// 本日 (不可修改)
	private static Calendar mCalendar = Calendar.getInstance();

	private static Calendar Start_Date, End_Date, Select_Date;

    // 計算開始日期與本日之間日差 定義為當前顯示頁面 Current_Position
    // one_day_ms 為計算一日毫秒，用作計算日差, 必須定義為 long
    private long one_day_ms = 24 * 60 * 60 * 1000;

	private int Current_Position;

    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("MM月dd日");

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case 8:

                // 接收用戶係 Calendar 揀左邊日, 然後跳轉到果日顯示
                if (data != null){

                    Calendar New_Statr_Date = Calendar.getInstance();
                    New_Statr_Date.set(1970, 0, 1);

                    long getselect_Date = data.getLongExtra("getselect_Date", 0);

                    Select_Date.setTimeInMillis(getselect_Date);

                    int day_diff = (int) ((Select_Date.getTimeInMillis() - New_Statr_Date.getTimeInMillis()) / (one_day_ms));

                    Current_Position = day_diff + 1;
                    mViewPager.setCurrentItem(Current_Position);

                }
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.a000_activity_main);

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

				case R.id.new_btn:
					Intent open_a001_activity = new Intent(MainActivity.this, Record_Main.class);
					long Select_Date_parseLong = Select_Date.getTimeInMillis();
					open_a001_activity.putExtra("Select_Date", Select_Date_parseLong);
					startActivity(open_a001_activity);
					break;

				case R.id.setting_btn:
					Intent open_z001_activity = new Intent(MainActivity.this, Setting_Main.class);
					startActivity(open_z001_activity);
					break;
			}
		}
	};

    public void Open_Calendar(){

        Intent open_b003_activity = new Intent(MainActivity.this, Review_Calendar.class);
        startActivityForResult(open_b003_activity, 8);
    }

    private void Setup_ViewPager(){

		int day_diff = (int) ((mCalendar.getTimeInMillis() - Start_Date.getTimeInMillis()) / (one_day_ms));
		Current_Position = day_diff + 1;
		
		// 由 Start Date 開始循環放入日期, 直至到 End Date
        DateList = new ArrayList<>();
		
		while(Start_Date.compareTo(End_Date) <= 0){
			
			DateList.add(mSimpleDateFormat.format(Start_Date.getTime()));
			
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
            public void onPageScrolled(int i, float v, int i1) {

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
    private void Jump_Today(){
        mViewPager.setCurrentItem(Current_Position);
    }



}


