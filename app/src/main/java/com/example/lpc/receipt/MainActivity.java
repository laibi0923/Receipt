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

	/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // TODO: Implement this method
        super.onActivityResult(requestCode, resultCode, data);

        switch(resultCode){

            case 1:
                
                // 暫時用黎示範修改一條紀錄, 會用 Firebase 取代
                                 

                Fragment_Record.remove_data(data.getIntExtra("Position", 0));

                Fragment_Record.my_add_data(
                        data.getStringExtra("Product_No"),
                        data.getStringExtra("Product_Name"),
                        data.getStringExtra("Product_Price"),
                        data.getStringExtra("Product_Discount"),
                        data.getStringExtra("Product_Tax"),
                        data.getStringExtra("Product_FianlPrice"));

                break;

            case 2:
                
                // 暫時用黎示範新增一條紀錄, 會用 Firebase 取代
                                 

                Fragment_Record.my_add_data(
                        data.getStringExtra("Product_No"),
                        data.getStringExtra("Product_Name"),
                        data.getStringExtra("Product_Price"),
                        data.getStringExtra("Product_Discount"),
                        data.getStringExtra("Product_Tax"),
                        data.getStringExtra("Product_FianlPrice"));

                break;

            case 3:

                Fragment_Record.add_remark(data.getStringExtra("remark_msg"));

                break;
        }
    }
***/

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
					Intent open_y001_activity2 = new Intent(MainActivity.this, Record_Main.class);
					startActivity(open_y001_activity2);
					break;
					
				case R.id.setting_btn:
					Intent open_y001_activity = new Intent(MainActivity.this, Setting_Main.class);
					startActivity(open_y001_activity);
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


