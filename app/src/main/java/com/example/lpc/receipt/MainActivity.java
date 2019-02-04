package com.example.lpc.receipt;

import android.annotation.*;
import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.support.v4.view.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import com.example.lpc.receipt.Review.*;
import com.example.lpc.receipt.Setting.*;
import java.util.*;
import com.example.lpc.receipt.Record.*;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    //private Record_Main Fragment_Record = new Record_Main();
    private Review_Main Fragment_Review = new Review_Main();
    //private Setting_Main Fragment_Setting = new Setting_Main();
	
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

        //fragmentList.add(Fragment_Review);

        fragmentList.add(Fragment_Review);

        //fragmentList.add(Fragment_Review);

//        Setup Adapter
        final ViewPager_Adapter mViewPager_Adapter = new ViewPager_Adapter(this.getSupportFragmentManager(), fragmentList);

        mViewPager.setAdapter(mViewPager_Adapter);

        mViewPager.setCurrentItem(1);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mViewPager.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

       @Override
       public int getItemPosition(@NonNull Object object) {
           return POSITION_NONE;
       }
   }

}


