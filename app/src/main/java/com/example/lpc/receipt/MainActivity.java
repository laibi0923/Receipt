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

public class MainActivity extends AppCompatActivity {

    private int mViewpager_Position;

    private ViewPager mViewPager;

	private LinearLayout New_btn, Setting_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.a000_activity_main);

        Find_View();
    }





    @SuppressLint("WrongViewCast")
    private void Find_View(){

        mViewPager = (ViewPager) findViewById(R.id.main_activity_viewpager);

        mViewPager.setOffscreenPageLimit(3);

        Setup_Viewpager();

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

    private void Setup_Viewpager(){

        List<Fragment> fragmentList = new ArrayList<Fragment>();
        Review_Main Fragment_Review = new Review_Main();


        for (int i = 0; i < 3; i++){
            fragmentList.add(Fragment_Review);
        }

        final ViewPager_Adapter mViewPager_Adapter = new ViewPager_Adapter(this.getSupportFragmentManager(), fragmentList);

        mViewPager.setAdapter(mViewPager_Adapter);

        mViewPager.setCurrentItem(1);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

                Log.e("PageScroll", "Position : " + i );
            }

            @Override
            public void onPageSelected(int position) {

                mViewpager_Position = position;
                Log.e("onPageSelected", "Position : " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                switch(state){

                    // 停止滑動
                    case SCROLL_STATE_IDLE:

                        // 無限循環設定
                        // http://www.voidcn.com/article/p-tyexajzi-bhh.html
                        if(mViewpager_Position == 0){

                            mViewPager.setCurrentItem(mViewPager_Adapter.getCount() - 2, false);

                        }else if(mViewpager_Position == mViewPager_Adapter.getCount() - 1) {

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




   public class ViewPager_Adapter extends FragmentPagerAdapter{

        private List<Fragment> fragmentList;

        public ViewPager_Adapter(FragmentManager fm, List<Fragment> fragmentList){
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return new Review_Main();
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

       @Override
       public int getItemPosition(@NonNull Object object) {
           return POSITION_NONE;
       }

       @Override
       public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
           
       }
   }

}


