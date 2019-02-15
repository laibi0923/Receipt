package com.example.lpc.receipt.Record;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.lpc.receipt.R;

public class Record_Remark extends AppCompatActivity {

    private LinearLayout back_btn;

    private EditText remark_edittext;
	
	private String Remark_Text;

    private LinearLayout remark_save_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        setContentView(R.layout.a005_record_remark);
        super.onCreate(savedInstanceState);

		Remark_Text = "";
		
		Bundle mBundle = getIntent().getExtras();
		
		if (!mBundle.isEmpty()){
			
			Remark_Text = mBundle.getString("extraRemark");
		}
		
        Find_View();
    }

    private void Find_View(){

        back_btn = (LinearLayout) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(View_Click_Listener);

        remark_edittext = (EditText) findViewById(R.id.remark_edittext);
		remark_edittext.setText(Remark_Text);

        remark_save_btn = (LinearLayout) findViewById(R.id.remark_save_btn);
        remark_save_btn.setOnClickListener(View_Click_Listener);
    }

    private View.OnClickListener View_Click_Listener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.back_btn:

                    close_keybord();

                    finish();

                    break;


                case R.id.remark_save_btn:

                    if (!remark_edittext.getText().toString().trim().isEmpty()){

                        Intent mIntent = new Intent();

                        mIntent.putExtra("remark_msg", remark_edittext.getText().toString());

                        setResult(3, mIntent);
                    }

                    close_keybord();

                    finish();

                    break;
            }

        }
    };




    private void close_keybord(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

}
