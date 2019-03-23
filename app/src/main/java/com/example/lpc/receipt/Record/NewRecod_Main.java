package com.example.lpc.receipt.Record;
import android.os.*;
import android.support.v4.app.*;
import android.support.v4.view.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import com.example.lpc.receipt.*;
import android.view.View.*;
public class NewRecod_Main extends AppCompatActivity
{
	private LinearLayout newrecord_close_btn;
	
	private ViewPager mViewPager;
	
	//private TabLayout mTabLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c001_new_record);
		
		Find_View();
	}
	
	private void Find_View(){
		
		newrecord_close_btn = (LinearLayout) findViewById(R.id.newrecord_close_btn);
		newrecord_close_btn.setOnClickListener(Item_OnclickListener);
		
		mViewPager = (ViewPager) findViewById(R.id.newrecord_viewpage);
		
		Viewpager_Adapter mViewpager_Adapter = new Viewpager_Adapter(getSupportFragmentManager());
		
		mViewPager.setAdapter(mViewpager_Adapter);
		
	}
	
	private View.OnClickListener Item_OnclickListener = new View.OnClickListener(){

		@Override
		public void onClick(View view)
		{
			// TODO: Implement this method
			
			switch(view.getId()){
				
				case R.id.newrecord_close_btn:
					finish();
					break;
			}
			
		}
		
	};
	
	public class Viewpager_Adapter extends FragmentPagerAdapter
	{
		public Viewpager_Adapter(FragmentManager mFragmentManager){
			super(mFragmentManager);
		}

		@Override
		public int getCount()
		{
			// TODO: Implement this method
			return 5;
		}

		@Override
		public Fragment getItem(int position)
		{
			// TODO: Implement this method
			switch (position){
				
				default:
					return null;
					
				case 0:
					return new NewRecord_Keybord();
					
				case 1:
					return new NreRecord_FastPage();

				case 2:
					return new NreRecord_FastPage();

				case 3:
					return new NreRecord_FastPage();

				case 4:
					return new NreRecord_FastPage();

			}
			
		}
		
	}
}
