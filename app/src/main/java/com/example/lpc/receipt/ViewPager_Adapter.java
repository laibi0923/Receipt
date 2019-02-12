package com.example.lpc.receipt;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lpc.receipt.Review.Review_Main;
import java.util.List;


public class ViewPager_Adapter extends FragmentStatePagerAdapter {

    private List<String> DateList;

    public ViewPager_Adapter(FragmentManager fm, List<String> DateList) {
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
