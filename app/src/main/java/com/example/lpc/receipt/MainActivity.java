package com.example.lpc.receipt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lpc.receipt.Record.Record_Main;
import com.example.lpc.receipt.Review.Review_Main;
import com.example.lpc.receipt.Setting.Setting_Main;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private Record_Main Fragment_Record = new Record_Main();
    private Review_Main Fragment_Review = new Review_Main();
    private Setting_Main Fragment_Setting = new Setting_Main();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
        setContentView(R.layout.a000_activity_main);

        Find_View();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // TODO: Implement this method
        super.onActivityResult(requestCode, resultCode, data);

        switch(resultCode){

            case 2:

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


    @SuppressLint("WrongViewCast")
    private void Find_View(){

        mViewPager = (ViewPager) findViewById(R.id.main_activity_viewpager);

        mViewPager.setOffscreenPageLimit(3);

        Setup_Viewpager();

    }

    private void Setup_Viewpager(){

        List<Fragment> fragmentList = new ArrayList<Fragment>();

        fragmentList.add(Fragment_Record);

        fragmentList.add(Fragment_Review);

        fragmentList.add(Fragment_Setting);

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


