package com.example.lpc.receipt.Public;

import android.content.Intent;
import android.os.*;
import android.support.annotation.*;
import android.support.v7.app.*;
import android.util.*;
import android.view.View;
import android.widget.*;
import android.widget.CalendarView.*;
import com.example.lpc.receipt.*;
import java.util.*;
import java.text.*;
 
public class Calendar_Selecter extends AppCompatActivity {

	private LinearLayout back_btn; 

	private CalendarView selectdate_calendarview;
	
	private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private Bundle mBundle;
	
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
		
		mBundle = getIntent().getExtras();

        setContentView(R.layout.b003_review_calendar);
		
		selectdate_calendarview = (CalendarView) findViewById(R.id.selectdate_calendarview);
		selectdate_calendarview.setOnDateChangeListener(new OnDateChangeListener(){

				@Override
				public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfmonth)
				{
					// TODO: Implement this method
					Calendar mCalendar = Calendar.getInstance();
					mCalendar.set(year, month, dayOfmonth);
//					String get_cv_date = mSimpleDateFormat.format(mCalendar.getTime());
//					Log.e("CalendarView Get", get_cv_date);

					//Log.e("getdate", mCalendar.getTimeInMillis() + "");

					Intent mIntent = new Intent();
					//mIntent.putExtra("Calendar_Selecter_Date", mCalendar.getTimeInMillis());
					
					mIntent.putExtra("Calendar_Selecter_Date", mCalendar);
					
					if(mBundle == null){
						setResult(8, mIntent);
					}else{
						setResult(488, mIntent);
					}
					
					

					finish();
				}
			});


		back_btn = (LinearLayout) findViewById(R.id.back_btn);
		back_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				finish();

			}
		});
			
    }
	
	
	
	
	
	
}
