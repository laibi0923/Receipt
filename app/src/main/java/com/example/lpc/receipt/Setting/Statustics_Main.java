package com.example.lpc.receipt.Setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lpc.receipt.R;

public class Statustics_Main extends AppCompatActivity {

    // 中心部份圓的大小
    private int center_circle_size = 250;
    // 統計項目數 (1 - 12月)
    private int line_count = 12;
	// 最大高度
	private int statustics_bar_maxheight = 350;
	// 底線寬度
    private int base_line_width = 1;
    // 底線高度
    private int base_line_height = 270;
    // 統計條寬 (白)
    private int statustics_bar1_width = 30;
    // 統計條寬 (綠)
    private int statustics_bar2_width = 30;

	// 標題文字
	private String[] mths_text = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private String[] mths_text_cht = new String[]{"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.y002_statistics_parts);

        final RelativeLayout main = (RelativeLayout) findViewById(R.id.main);

        for (int i = 0; i < line_count; i++){

            // 主要容器
            RelativeLayout mRelativeLayout = new RelativeLayout(this);
            // 設定長度 寬度
            RelativeLayout.LayoutParams mRelativeLayout_LayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, statustics_bar_maxheight);
            // 置中
            mRelativeLayout_LayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

            mRelativeLayout.setLayoutParams(mRelativeLayout_LayoutParams);


            // 標題
            TextView mTextView = new TextView(this);

            mTextView.setText(mths_text_cht[i]);

            mTextView.setTextColor(getResources().getColor(R.color.text_color_2));

            RelativeLayout.LayoutParams Textview_LayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            Textview_LayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

            Textview_LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
			
			Textview_LayoutParams.setMargins(0, 20, 0, 0);

            mTextView.setLayoutParams(Textview_LayoutParams);

            mRelativeLayout.addView(mTextView);



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

            int Random_Num1 = (int) (Math.random() * 100 * 2.5);
			
            Log.e("RandomNum", Random_Num1 + "");

            RelativeLayout.LayoutParams statustics_bar1_LayoutPams = new RelativeLayout.LayoutParams(statustics_bar1_width, Random_Num1);
			
            statustics_bar1_LayoutPams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			
            statustics_bar1_LayoutPams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);

            statustics_bar1.setLayoutParams(statustics_bar1_LayoutPams);

            mRelativeLayout.addView(statustics_bar1);


            // 統計條 (綠)
            View statustics_bar2 = new View(this);
			
            statustics_bar2.setBackgroundColor(getResources().getColor(R.color.text_color_1));

            int Random_Num2 = (int) (Math.random() * 100  );
			
            Log.e("RandomNum", Random_Num2 + "");

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
            mRelativeLayout.setTranslationX(center_circle_size * (float)Math.cos(angleRad));
			
            mRelativeLayout.setTranslationY(center_circle_size * (float)Math.sin(angleRad));

            // Set the rotation of the view.
            mRelativeLayout.setRotation(angleDeg + 90.0f);

            // 將文字旋轉到岩位
            mTextView.setRotation(-angleDeg + 270.0f);

            main.addView(mRelativeLayout);


        }

		
		
		
		
		

    }



//            // Crea>te some quick TextViews that can be placed.
//            TextView v = new TextView(this);
//            // Set a text and center it in each view.
//            v.setText("View " + i);
//            v.setGravity(Gravity.CENTER);
//            v.setBackgroundColor(0xffff0000);
//            // Force the views to a nice size (150x100 px) that fits my display.
//            // This should of course be done in a display size independent way.
//            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(150, 100);
//            // Place all views in the center of the layout. We'll transform them
//            // away from there in the code below.
//            lp.gravity = Gravity.CENTER;
//            // Set layout params on view.
//            v.setLayoutParams(lp);
//
//            // Calculate the angle of the current view. Adjust by 90 degrees to
//            // get View 0 at the top. We need the angle in degrees and radians.
//            float angleDeg = i * 360.0f / numViews - 90.0f;
//            float angleRad = (float)(angleDeg * Math.PI / 180.0f);
//            // Calculate the position of the view, offset from center (300 px from
//            // center). Again, this should be done in a display size independent way.
//            v.setTranslationX(300 * (float)Math.cos(angleRad));
//            v.setTranslationY(300 * (float)Math.sin(angleRad));
//            // Set the rotation of the view.
//            v.setRotation(angleDeg + 90.0f);
//            main.addView(v);


}

