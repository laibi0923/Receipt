package com.example.lpc.receipt;
import android.os.*;
import android.support.annotation.NonNull;
import android.support.v7.app.*;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.content.*;

import com.example.lpc.receipt.Register.Register_Main;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity
{
	private TextView login_errormsg;

	private LinearLayout loginsubmit_btn;

	private TextView lgoin_register_btn;

	private EditText login_email, login_password;

	private ProgressBar login_loading_view;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.x001_login_main);

		login_loading_view = findViewById(R.id.login_loading_view);

		login_errormsg = findViewById(R.id.login_errormsg);

		login_email = findViewById(R.id.login_email);

		login_password = findViewById(R.id.login_password);

		loginsubmit_btn = (LinearLayout) findViewById(R.id.loginsubmit_btn);
		loginsubmit_btn.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method

					login_errormsg.setText("");

					login_loading_view.setVisibility(View.VISIBLE);

					if (!login_email.getText().toString().isEmpty() && !login_password.getText().toString().isEmpty()){

						FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();

						mFirebaseAuth.signInWithEmailAndPassword(login_email.getText().toString(), login_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
							@Override
							public void onComplete(@NonNull Task<AuthResult> task) {

								if (task.isSuccessful()){

									login_loading_view.setVisibility(View.GONE);
									Intent mIntent = new Intent().setClass(LoginActivity.this, MainActivity.class);
									startActivity(mIntent);
									finish();

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


		lgoin_register_btn = findViewById(R.id.lgoin_register_btn);
		lgoin_register_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent mIntent = new Intent().setClass(LoginActivity.this, Register_Main.class);
				startActivity(mIntent);

			}
		});

	}


}
