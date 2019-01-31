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

    // 統計項目數
    private int line_count = 12;
    // 底線高度
    private int base_line_height = 400;
    // 底線長度
    private int base_line_width = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.y002_statistics_parts);

        final RelativeLayout main = findViewById(R.id.main);

        for (int i = 0; i < line_count; i++){

            RelativeLayout mRelativeLayout = new RelativeLayout(this);

//            mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));

            RelativeLayout.LayoutParams mRelativeLayout_LayoutParams = new RelativeLayout.LayoutParams(30, 400);

            mRelativeLayout_LayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

            mRelativeLayout.setLayoutParams(mRelativeLayout_LayoutParams);


            // TextView
            TextView mTextView = new TextView(this);

            int mths = i + 1;

            mTextView.setText(String.valueOf(mths));

            mTextView.setTextColor(getResources().getColor(R.color.text_color_1));

            RelativeLayout.LayoutParams lp0 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            lp0.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

            lp0.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);

            mTextView.setLayoutParams(lp0);

            mRelativeLayout.addView(mTextView);



            // Line 1
            View base_line = new View(this);
            base_line.setBackgroundColor(getResources().getColor(R.color.text_color_1));

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(1, 300);
            lp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

            base_line.setLayoutParams(lp);

            mRelativeLayout.addView(base_line);


            // Line 2
            View base_line2 = new View(this);
            base_line2.setBackgroundColor(getResources().getColor(R.color.colorAccent));

            int R1 = (int) (Math.random() * 99 +200);
            Log.e("RandomNum", R1 + "");

            RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(30, R1);
            lp2.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            lp2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);

            base_line2.setLayoutParams(lp2);

            mRelativeLayout.addView(base_line2);


            // Line 3
            View base_line3 = new View(this);
            base_line3.setBackgroundColor(getResources().getColor(R.color.text_color_1));

            int R2 = (int) (Math.random() * 99 + 100);
            Log.e("RandomNum", R2 + "");

            RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(30, R2);
            lp3.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            lp3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);

            base_line3.setLayoutParams(lp3);

            mRelativeLayout.addView(base_line3);





            // Calculate the angle of the current view. Adjust by 90 degrees to
            // get View 0 at the top. We need the angle in degrees and radians.
            float angleDeg = i * 250.0f / line_count - 90.0f;
            float angleRad = (float)(angleDeg * Math.PI / 180.0f);

            // Calculate the position of the view, offset from center (300 px from
            // center). Again, this should be done in a display size independent way.
            mRelativeLayout.setTranslationX(300 * (float)Math.cos(angleRad));
            mRelativeLayout.setTranslationY(300 * (float)Math.sin(angleRad));

            // Set the rotation of the view.
            mRelativeLayout.setRotation(angleDeg + 90.0f);

            main.addView(mRelativeLayout);


        }


    }



    //
//            // Create some quick TextViews that can be placed.
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
