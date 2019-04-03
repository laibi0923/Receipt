package com.example.lpc.receipt;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import com.example.lpc.receipt.*;
import com.example.lpc.receipt.Public.FullScreencall;

import java.util.*;

public class SplashScreen extends AppCompatActivity{
	
	private Handler mHandler;

    private long delay = 1000;
    private int i = 0;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            new FullScreencall(this).Hide_SystemUI();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.a000_welcome_page);

        Timer timer = new Timer();
        timer.schedule(task, delay);
    }


    TimerTask task = new TimerTask() {
        @Override
        public void run() {

			Intent in = new Intent().setClass(SplashScreen.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(in);
			finish();

        }
    };
	
	
}
