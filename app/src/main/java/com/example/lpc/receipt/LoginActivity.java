package com.example.lpc.receipt;
import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.support.v7.app.*;
import android.view.*;
import android.view.inputmethod.*;
import android.widget.*;
import com.example.lpc.receipt.Register.*;
import com.google.android.gms.tasks.*;
import com.google.firebase.auth.*;

public class LoginActivity extends AppCompatActivity
{
	private TextView login_errormsg;

	private LinearLayout loginsubmit_btn;

	private TextView lgoin_register_btn;

	private EditText login_email, login_password;

	private RelativeLayout login_loading_view;
	
	private String UserEmail;
	
	FirebaseAuth mFirebaseAuth;
	
	FirebaseUser mFirebaseUser;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO: Implement this method
		super.onActivityResult(requestCode, resultCode, data);
		
		switch(requestCode){
			
			case 644:
				
				if(data != null){
					UserEmail = data.getStringExtra("UserEmail");
					login_email.setText(UserEmail);
					login_password.requestFocus();
				}
				
				break;
		}
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		
		mFirebaseAuth = FirebaseAuth.getInstance();
		
		mFirebaseUser = mFirebaseAuth.getCurrentUser();
		
		setContentView(R.layout.x001_login_main);
		
		if(mFirebaseUser != null){
			Open_MainActivity();
		}
		
		login_loading_view = (RelativeLayout) findViewById(R.id.login_loading_view);

		login_errormsg = (TextView) findViewById(R.id.login_errormsg);

		UserEmail = "";
		login_email = (EditText) findViewById(R.id.login_email);
		//login_email.setText(UserEmail);

		login_password = (EditText) findViewById(R.id.login_password);

		loginsubmit_btn = (LinearLayout) findViewById(R.id.loginsubmit_btn);
		loginsubmit_btn.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method

					login_errormsg.setText("");

					login_loading_view.setVisibility(View.VISIBLE);
					
					close_keybord();

					if (!login_email.getText().toString().isEmpty() && !login_password.getText().toString().isEmpty()){

						mFirebaseAuth.signInWithEmailAndPassword(login_email.getText().toString(), login_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
							@Override
							public void onComplete(@NonNull Task<AuthResult> task) {

								if (task.isSuccessful()){

									login_loading_view.setVisibility(View.GONE);
									Open_MainActivity();

								}else {

									login_loading_view.setVisibility(View.GONE);
									login_errormsg.setText(String.valueOf(task.getException()));

								}

							}
						});

					}else {
						login_errormsg.setText("Email / Password Cannot be empty");
						login_loading_view.setVisibility(View.GONE);
					}


				}
			});


		lgoin_register_btn = (TextView) findViewById(R.id.lgoin_register_btn);
		lgoin_register_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent mIntent = new Intent().setClass(LoginActivity.this, Register_Main.class);
				startActivityForResult(mIntent, 644);

			}
		});

	}
	
	private void close_keybord(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
	

	private void Open_MainActivity(){
		Intent mIntent = new Intent().setClass(LoginActivity.this, MainActivity.class);
		startActivity(mIntent);
		finish();
	}

	
	public void FullScreen(){
		
		if(Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19){
			View v = this.getWindow().getDecorView();
			v.setSystemUiVisibility(View.GONE);
		}else if(Build.VERSION.SDK_INT >= 19){
			View decorView = this.getWindow().getDecorView();
			int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
			decorView.setSystemUiVisibility(uiOptions);
		}
	}
	
}
