package com.example.lpc.receipt;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import android.content.*;

public class LoginActivity extends AppCompatActivity
{
	private LinearLayout loginsubmit_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.x001_login_main);
		
		loginsubmit_btn = (LinearLayout) findViewById(R.id.loginsubmit_btn);
		loginsubmit_btn.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					Intent mIntent = new Intent().setClass(LoginActivity.this, MainActivity.class);
					startActivity(mIntent);
					finish();
				}
			});
	}
	
	
}