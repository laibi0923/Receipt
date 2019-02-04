package com.example.lpc.receipt.Setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lpc.receipt.R;
import android.support.v7.app.*;

public class Setting_Main extends AppCompatActivity {

    private LinearLayout setting_statistics_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.z001_setting_main);
		
		Find_View();
	}
	

    private void Find_View(){

        setting_statistics_btn = (LinearLayout) findViewById(R.id.setting_statistics_btn);
		
        setting_statistics_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){

                    case R.id.setting_statistics_btn:

                        Intent open_y001_activity = new Intent(Setting_Main.this, Statustics_Main.class);
                        startActivity(open_y001_activity);

                        break;
                }

            }

        });
    }


}
