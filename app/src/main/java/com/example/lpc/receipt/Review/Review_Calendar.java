package com.example.lpc.receipt.Review;

import android.os.*;
import android.support.annotation.*;
import android.support.v7.app.*;
import android.util.*;
import android.widget.*;
import android.widget.CalendarView.*;
import com.example.lpc.receipt.*;
import java.util.*;
import java.text.*;

public class Review_Calendar extends AppCompatActivity {

	private CalendarView selectdate_calendarview;
	
	private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.b003_review_calendar);
		
		selectdate_calendarview = (CalendarView) findViewById(R.id.selectdate_calendarview);
		selectdate_calendarview.setOnDateChangeListener(new OnDateChangeListener(){

				@Override
				public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfmonth)
				{
					// TODO: Implement this method
					Calendar mCalendar = Calendar.getInstance();
					mCalendar.set(year, month, dayOfmonth);
					String get_cv_date = mSimpleDateFormat.format(mCalendar.getTime());
					Log.e("CalendarView Get", get_cv_date);
					
					finish();
				}
			});
			
			
			
    }
	
}
