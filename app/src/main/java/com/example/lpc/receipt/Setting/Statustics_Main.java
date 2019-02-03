package com.example.lpc.receipt.Setting;

import android.graphics.Color;
import android.os.*;
import android.support.annotation.*;
import android.support.v7.app.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.example.lpc.receipt.*;

public class Statustics_Main extends AppCompatActivity {

    // 標題文字
	private String[] mths_text = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private String[] mths_text_cht = new String[]{"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};


    private DisplayMetrics displayMetrics;


	private RelativeLayout statustics_view;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.y002_statistics_parts);

        displayMetrics = new DisplayMetrics();

        Find_View();

    }


	private void Find_View(){

        statustics_view = (RelativeLayout) findViewById(R.id.statustics_view);

		Circle_Statustics();
	}

	private int  PX_convert_DP(int width_px){
        return Math.round(width_px / displayMetrics.density);
    }


    private int  DP_convert_PX(int width_dp){
        return Math.round(width_dp * displayMetrics.density);
    }


    // 得知手機寬度
    private int Get_Window_Width(){
        WindowManager mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.getDefaultDisplay().getMetrics(displayMetrics);

        return displayMetrics.widthPixels;
    }


	private void Circle_Statustics(){
		
		// 螢幕長度
		int phone_width = Get_Window_Width();
		// 螢幕長度 - 80dp (marginLeft, marginRight)
		int setup_height_width = phone_width - DP_convert_PX(80);
		// 中心部份圓的大小
		int center_circle_size = 280;
		// 統計項目數 (1 - 12月)
		int line_count = 12;
		// 最大高度
		int statustics_bar_maxheight = 350;
		// 底線寬度
		int base_line_width = 1;
		// 底線高度
		int base_line_height = DP_convert_PX(100);
		// 統計條寬 (白)
		int statustics_bar1_width = 30;
		// 統計條寬 (綠)
		int statustics_bar2_width = 30;

		statustics_view.getLayoutParams().height = setup_height_width;
		statustics_view.getLayoutParams().width = setup_height_width;

        Log.e("Phone_Width", phone_width + "px, " + PX_convert_DP(phone_width) + "dp");
        Log.e("setup_height_width", setup_height_width + "px, " + PX_convert_DP(setup_height_width) + "dp");

		
		// LinearyLayout
		// 	- TextView
		// 	- RelativeLayout
		// 		- View (baseline)
		// 		- View (Bar 1)
		//		- View (Bar 2)


		for (int i = 0; i < line_count; i++){
			
			LinearLayout mLinearLayout = new LinearLayout(this);
			mLinearLayout.setOrientation(LinearLayout.VERTICAL);
			RelativeLayout.LayoutParams pp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, statustics_bar_maxheight);
			pp1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			mLinearLayout.setLayoutParams(pp1);
			statustics_view.addView(mLinearLayout);
			
			// 標題
			TextView mTextView = new TextView(this);
			mTextView.setText(mths_text_cht[i]);
			mTextView.setTextColor(getResources().getColor(R.color.text_color_2));
			RelativeLayout.LayoutParams Textview_LayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			Textview_LayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			Textview_LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
			Textview_LayoutParams.setMargins(0, 20, 0, 0);
			mTextView.setLayoutParams(Textview_LayoutParams);
			mLinearLayout.addView(mTextView);

			
			RelativeLayout mRelativeLayout = new RelativeLayout(this);
			RelativeLayout.LayoutParams mRelativeLayout_LayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
			mRelativeLayout_LayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			mRelativeLayout.setLayoutParams(mRelativeLayout_LayoutParams);
			mLinearLayout.addView(mRelativeLayout);
			

			// Statustics Line
			View statustics_line = new View(this);
			statustics_line.setBackgroundColor(getResources().getColor(R.color.text_color_2));
			RelativeLayout.LayoutParams line_LayoutParams = new RelativeLayout.LayoutParams(base_line_width, base_line_height);
			line_LayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			line_LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
			statustics_line.setLayoutParams(line_LayoutParams);
			mRelativeLayout.addView(statustics_line);


			// 統計條 (白)
			View statustics_bar1 = new View(this);
			statustics_bar1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
			int Random_Num1 = (int) (Math.random() * 100 +100);
//			Log.e("RandomNum", Random_Num1 + "");
			RelativeLayout.LayoutParams statustics_bar1_LayoutPams = new RelativeLayout.LayoutParams(statustics_bar1_width, Random_Num1);
			statustics_bar1_LayoutPams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			statustics_bar1_LayoutPams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
			statustics_bar1.setLayoutParams(statustics_bar1_LayoutPams);
			mRelativeLayout.addView(statustics_bar1);


			// 統計條 (綠)
			View statustics_bar2 = new View(this);
			statustics_bar2.setBackgroundColor(getResources().getColor(R.color.text_color_1));
			int Random_Num2 = (int) (Math.random() * 100  );
//			Log.e("RandomNum", Random_Num2 + "");
			RelativeLayout.LayoutParams statustics_bar2_LayoutPams = new RelativeLayout.LayoutParams(statustics_bar2_width, Random_Num2);
			statustics_bar2_LayoutPams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			statustics_bar2_LayoutPams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
			statustics_bar2.setLayoutParams(statustics_bar2_LayoutPams);
			mRelativeLayout.addView(statustics_bar2);





			// Calculate the angle of the current view. Adjust by 90 degrees to
			// get View 0 at the top. We need the angle in degrees and radians.
			float angleDeg = i * 250.0f / line_count - 90.0f;
			float angleRad = (float)(angleDeg * Math.PI / 180.0f);

			// Calculate the position of the view, offset from center (300 px from
			// center). Again, this should be done in a display size independent way.
			mLinearLayout.setTranslationX(center_circle_size * (float)Math.cos(angleRad));

			mLinearLayout.setTranslationY(center_circle_size * (float)Math.sin(angleRad));

			// Set the rotation of the view.
			mLinearLayout.setRotation(angleDeg + 90.0f);

			// 將文字旋轉到岩位
			mTextView.setRotation(-angleDeg + 270.0f);

            //statustics_view.addView(mLinearLayout);

		}

	}
	
	
	

}

