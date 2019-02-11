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


	private static Calendar mCalendar = Calendar.getInstance();

	private int Current_Position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.a000_activity_main);

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
					startActivity(open_a001_activity);
					break;

				case R.id.setting_btn:
					Intent open_z001_activity = new Intent(MainActivity.this, Setting_Main.class);
					startActivity(open_z001_activity);
					break;
			}
		}
	};


// https://github.com/codepath/android_guides/wiki/ViewPager-with-FragmentPagerAdapter
// https://stackoverflow.com/questions/19731320/android-viewpager-working-with-date
    private void Setup_ViewPager(){

        DateList = new ArrayList<>();

        mCalendar.setTime(new Date());

        mCalendar.add(Calendar.DAY_OF_MONTH, -1);

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("MM月dd日");

        for (int i = 0; i < 3; i++){

            DateList.add(mSimpleDateFormat.format(mCalendar.getTime()));

            mCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        final ViewPager_Adapter mViewPager_Adapter = new ViewPager_Adapter(getSupportFragmentManager(), DateList);

        mViewPager.setAdapter(mViewPager_Adapter);

        mViewPager.setCurrentItem(1);

//        Current_Position = Integer.MAX_VALUE / 2;

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

                if (position < Current_Position){
                    // 左滑時當前日子減少一日
                    mCalendar.add(Calendar.DAY_OF_MONTH, -1);
                }else {
                    // 右滑時當前日子增加一日
                    mCalendar.add(Calendar.DAY_OF_MONTH, 1);
                }

                Current_Position = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

				switch(state){

                    // 停止滑動
                    case SCROLL_STATE_IDLE:

                        // 無限循環設定
                        // http://www.voidcn.com/article/p-tyexajzi-bhh.html
                        if(Current_Position == 0){
                            mViewPager.setCurrentItem(mViewPager_Adapter.getCount() - 2, false);
                        }else if(Current_Position == mViewPager_Adapter.getCount() - 1) {
                            mViewPager.setCurrentItem(1, false);
                        }
                        break;

                    // 滑動進行中
                    case SCROLL_STATE_DRAGGING:
                        break;

                    // 滑動放手, 自動歸位過程
                    case SCROLL_STATE_SETTLING:
                        break;


                }

            }
        });

    }


    private void renderView(){

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("MM月dd日");

    }



    // 返回本日
    private void Jump_Today(){
        mViewPager.setCurrentItem(0);
    }






//    private void Setup_Viewpager(){
//
//        Current_Position = Integer.MAX_VALUE / 2;
//
//        List<Fragment> fragmentList = new ArrayList<Fragment>();
//
//        SimpleDateFormat mDateFormat = new SimpleDateFormat("MM" + "月" + "dd" + "日");
//
//		mCalendar = Calendar.getInstance();
//
//		mCalendar.setTime(new Date());
//
//		mCalendar.add(mCalendar.DATE, -1);
//
//        for (int i = 0; i < 3; i++){
//
//			String formatDate = mDateFormat.format(mCalendar.getTime());
//
//			Review_Main Fragment_Review = new Review_Main();
//
//			Bundle args = new Bundle();
//
//			args.putString("date", formatDate);
//
//			Fragment_Review.setArguments(args);
//
//			fragmentList.add(Fragment_Review);
//
//			mCalendar.add(Calendar.DATE, 1);
//        }
//
//
//
//
//        final ViewPager_Adapter mViewPager_Adapter = new ViewPager_Adapter(this.getSupportFragmentManager(), fragmentList);
//
//        mViewPager.setAdapter(mViewPager_Adapter);
//
//        mViewPager.setCurrentItem(Current_Position);
//
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//                Log.e("PageScroll", "Position : " + i );
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//                if (position < Current_Position){
//                    mCalendar.add(mCalendar.DAY_OF_MONTH, -1);
//                }else {
//                    mCalendar.add(mCalendar.DAY_OF_MONTH, 1);
//                }
//
//                Current_Position = position;
//
//
//
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//                switch(state){
//
//                    // 停止滑動
//                    case SCROLL_STATE_IDLE:
//
//                        // 無限循環設定
//                        // http://www.voidcn.com/article/p-tyexajzi-bhh.html
//                        if(mViewpager_Position == 0){
//
//                            mViewPager.setCurrentItem(mViewPager_Adapter.getCount() - 2, false);
//
//                        }else if(mViewpager_Position == mViewPager_Adapter.getCount() - 1) {
//
//                            mViewPager.setCurrentItem(1, false);
//
//                        }
//
//
//
//                        break;
//
//                    // 滑動進行中
//                    case SCROLL_STATE_DRAGGING:
//                        break;
//
//                    // 滑動放手, 自動歸位過程
//                    case SCROLL_STATE_SETTLING:
//                        break;
//
//
//                }
//
//            }
//        });
//
//
//    }




}


