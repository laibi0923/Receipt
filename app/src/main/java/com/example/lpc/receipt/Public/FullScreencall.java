package com.example.lpc.receipt.Public;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;

public class FullScreencall {

    private Activity mActivity;

    public FullScreencall(Activity mActivity) {
        this.mActivity = mActivity;
    }


    public void Hide_SystemUI(){

        if (Build.VERSION.SDK_INT >= 19) {

            View decorView = mActivity.getWindow().getDecorView();

            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        }

    }


}
