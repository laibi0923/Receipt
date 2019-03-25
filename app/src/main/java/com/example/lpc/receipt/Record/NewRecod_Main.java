package com.example.lpc.receipt.Record;
import android.graphics.Color;
import android.os.*;
import android.support.v4.app.*;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import com.example.lpc.receipt.*;
import android.view.View.*;
public class NewRecod_Main extends AppCompatActivity implements NewRecord_Keybord.Keyboard_Listener
{
	private RelativeLayout newrecord_close_btn;

	private EditText newrecord_amount_ed;
	
	private ViewPager mViewPager;

	private RelativeLayout newrecord_keyboard_btn, newrecord_option1_btn, newrecord_option2_btn, newrecord_option3_btn, newrecord_option4_btn,newrecord_save_btn;

	private ImageView newrecord_keyboard_img, newrecord_option1_img, newrecord_option2_img, newrecord_option3_img, newrecord_option4_img,newrecord_save_img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c001_new_record);
		
		Find_View();
	}
	
	private void Find_View(){
		
		newrecord_close_btn = (RelativeLayout) findViewById(R.id.newrecord_close_btn);
		newrecord_close_btn.setOnClickListener(Item_OnclickListener);

		newrecord_amount_ed = findViewById(R.id.newrecord_amount_ed);

		mViewPager = (ViewPager) findViewById(R.id.newrecord_viewpage);
		
		Viewpager_Adapter mViewpager_Adapter = new Viewpager_Adapter(getSupportFragmentManager());
		
		mViewPager.setAdapter(mViewpager_Adapter);

		mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {

				switch (position){

					case 0:
						Clear_Icon_Tint();
						newrecord_keyboard_img.setColorFilter(Color.parseColor("#FFFFFF"));
						break;

					case 1:
						Clear_Icon_Tint();
						newrecord_option1_img.setColorFilter(Color.parseColor("#FFFFFF"));
						break;

					case 2:
						Clear_Icon_Tint();
						newrecord_option2_img.setColorFilter(Color.parseColor("#FFFFFF"));
						break;

					case 3:
						Clear_Icon_Tint();
						newrecord_option3_img.setColorFilter(Color.parseColor("#FFFFFF"));
						break;

					case 4:
						Clear_Icon_Tint();
						newrecord_option4_img.setColorFilter(Color.parseColor("#FFFFFF"));
						break;

				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		newrecord_keyboard_btn = findViewById(R.id.newrecord_keyboard_btn);
		newrecord_keyboard_btn.setOnClickListener(Item_OnclickListener);
		newrecord_keyboard_img = findViewById(R.id.newrecord_keyboard_img);

		newrecord_option1_btn = findViewById(R.id.newrecord_option1_btn);
		newrecord_option1_btn.setOnClickListener(Item_OnclickListener);
		newrecord_option1_img = findViewById(R.id.newrecord_option1_img);

		newrecord_option2_btn = findViewById(R.id.newrecord_option2_btn);
		newrecord_option2_btn.setOnClickListener(Item_OnclickListener);
		newrecord_option2_img = findViewById(R.id.newrecord_option2_img);

		newrecord_option3_btn = findViewById(R.id.newrecord_option3_btn);
		newrecord_option3_btn.setOnClickListener(Item_OnclickListener);
		newrecord_option3_img = findViewById(R.id.newrecord_option3_img);

		newrecord_option4_btn = findViewById(R.id.newrecord_option4_btn);
		newrecord_option4_btn.setOnClickListener(Item_OnclickListener);
		newrecord_option4_img = findViewById(R.id.newrecord_option4_img);

		newrecord_save_btn = findViewById(R.id.newrecord_save_btn);
		newrecord_save_btn.setOnClickListener(Item_OnclickListener);
		newrecord_save_img = findViewById(R.id.newrecord_save_img);
		
	}

	private void Clear_Icon_Tint(){
		newrecord_keyboard_img.setColorFilter(Color.parseColor("#2E2E2E"));
		newrecord_option1_img.setColorFilter(Color.parseColor("#2E2E2E"));
		newrecord_option2_img.setColorFilter(Color.parseColor("#2E2E2E"));
		newrecord_option3_img.setColorFilter(Color.parseColor("#2E2E2E"));
		newrecord_option4_img.setColorFilter(Color.parseColor("#2E2E2E"));
		newrecord_save_img.setColorFilter(Color.parseColor("#2E2E2E"));
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

				case R.id.newrecord_keyboard_btn:

					mViewPager.setCurrentItem(0);
					Clear_Icon_Tint();
					newrecord_keyboard_img.setColorFilter(Color.parseColor("#FFFFFF"));
					break;

				case R.id.newrecord_option1_btn:

					mViewPager.setCurrentItem(1);
					Clear_Icon_Tint();
					newrecord_option1_img.setColorFilter(Color.parseColor("#FFFFFF"));
					break;

				case R.id.newrecord_option2_btn:

					mViewPager.setCurrentItem(2);
					Clear_Icon_Tint();
					newrecord_option2_img.setColorFilter(Color.parseColor("#FFFFFF"));
					break;

				case R.id.newrecord_option3_btn:

					mViewPager.setCurrentItem(3);
					Clear_Icon_Tint();
					newrecord_option3_img.setColorFilter(Color.parseColor("#FFFFFF"));
					break;

				case R.id.newrecord_option4_btn:

					mViewPager.setCurrentItem(4);
					Clear_Icon_Tint();
					newrecord_option4_img.setColorFilter(Color.parseColor("#FFFFFF"));
					break;

				case R.id.newrecord_save_btn:
					break;
			}

		}
		
	};

	@Override
	public void SendContent(String Info) {
		if (Info.equals("del")){
			Toast.makeText(this, "del", Toast.LENGTH_SHORT).show();
		}else {
			newrecord_amount_ed.append(Info);
		}
	}

	// ViewPager Adapter
	public class Viewpager_Adapter extends FragmentPagerAdapter {

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
