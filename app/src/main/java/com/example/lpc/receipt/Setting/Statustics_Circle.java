package com.example.lpc.receipt.Setting;
import android.content.Context;
import android.util.*;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lpc.receipt.R;

public class Statustics_Circle
{
	private Context context;
	private DisplayMetrics displayMetrics;

	// 標題文字
	private String[] mths_text = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	private String[] mths_text_cht = new String[]{"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};

	public Statustics_Circle(){

	}

	public void Draw_Statustics_Circle(RelativeLayout Target_View, Context context){

		this.context = context;

		displayMetrics = new DisplayMetrics();

		// 螢幕長度
		int phone_width = Get_Window_Width();
		// 螢幕長度 - 80dp (marginLeft, marginRight)
		int setup_height_width = phone_width - DP_convert_PX(80);
		Log.e("Height", setup_height_width + "");
		// 中心部份圓的大小
		int center_circle_size = 250;
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

		Target_View.getLayoutParams().height = setup_height_width;
		Target_View.getLayoutParams().width = setup_height_width;

		Log.e("Phone_Width", phone_width + "px, " + PX_convert_DP(phone_width) + "dp");
		Log.e("setup_height_width", setup_height_width + "px, " + PX_convert_DP(setup_height_width) + "dp");


		// LinearyLayout
		// 	- TextView
		// 	- RelativeLayout
		// 		- View (baseline)
		// 		- View (Bar 1)
		//		- View (Bar 2)


		for (int i = 0; i < line_count; i++){

			// 主容器
			LinearLayout mLinearLayout = new LinearLayout(context);
			mLinearLayout.setOrientation(LinearLayout.VERTICAL);
			RelativeLayout.LayoutParams pp1 = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, statustics_bar_maxheight);
			pp1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			mLinearLayout.setLayoutParams(pp1);
			Target_View.addView(mLinearLayout);

			// 標題
			TextView mTextView = new TextView(context);
			mTextView.setText(mths_text_cht[i]);
			mTextView.setPadding(20, 20,20,20);
			mTextView.setTextColor(context.getResources().getColor(R.color.text_color_2));
			LinearLayout.LayoutParams mTextView_LayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			mTextView.setLayoutParams(mTextView_LayoutParams);
			mLinearLayout.addView(mTextView);

			//
			RelativeLayout mRelativeLayout = new RelativeLayout(context);
			RelativeLayout.LayoutParams mRelativeLayout_LayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
			mRelativeLayout_LayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			mRelativeLayout_LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
			mRelativeLayout.setLayoutParams(mRelativeLayout_LayoutParams);
			mLinearLayout.addView(mRelativeLayout);


			// 統計線 (Heigt 100dp)
			View statustics_line = new View(context);
			statustics_line.setBackgroundColor(context.getResources().getColor(R.color.text_color_2));
			RelativeLayout.LayoutParams line_LayoutParams = new RelativeLayout.LayoutParams(base_line_width, base_line_height);
			line_LayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			line_LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
			statustics_line.setLayoutParams(line_LayoutParams);
			mRelativeLayout.addView(statustics_line);


			// 統計條 (白)
			View statustics_bar1 = new View(context);
			statustics_bar1.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
			int Random_Num1 = (int) (Math.random() * 100 +100);
			RelativeLayout.LayoutParams statustics_bar1_LayoutPams = new RelativeLayout.LayoutParams(statustics_bar1_width, Random_Num1);
			statustics_bar1_LayoutPams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			statustics_bar1_LayoutPams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
			statustics_bar1.setLayoutParams(statustics_bar1_LayoutPams);
			mRelativeLayout.addView(statustics_bar1);


			// 統計條 (綠)
			View statustics_bar2 = new View(context);
			statustics_bar2.setBackgroundColor(context.getResources().getColor(R.color.text_color_1));
			int Random_Num2 = (int) (Math.random() * 100  );
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

	private int  PX_convert_DP(int width_px){
		return Math.round(width_px / displayMetrics.density);
	}


	private int  DP_convert_PX(int width_dp){
		return Math.round(width_dp * displayMetrics.density);
	}


	// 得知手機寬度
	private int Get_Window_Width(){
		WindowManager mWindowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
		mWindowManager.getDefaultDisplay().getMetrics(displayMetrics);

		return displayMetrics.widthPixels;
	}
	
}
