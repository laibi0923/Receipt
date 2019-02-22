package com.example.lpc.receipt;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lpc.receipt.Review.Review_Main;
import java.util.List;
import android.view.*;


public class ViewPager_Adapter extends FragmentStatePagerAdapter {

    private List<Long> DateList;


    public ViewPager_Adapter(FragmentManager fm, List<Long> DateList) {
        super(fm);
        this.DateList = DateList;
    }

    @Override
    public Fragment getItem(int position) {

        return Review_Main.newInstance(DateList.get(position));

    }

    @Override
    public int getCount() {
        return DateList.size();
    }


	
	
	


}
