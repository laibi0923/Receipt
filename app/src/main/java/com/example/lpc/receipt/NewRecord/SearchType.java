package com.example.lpc.receipt.NewRecord;
import android.support.v7.app.*;
import android.os.*;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.lpc.receipt.R;

public class SearchType extends AppCompatActivity implements View.OnClickListener {

	private RelativeLayout searchtype_back_btn, searchtype_clear_btn;

	private EditText searchtype_search_ed;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c02_newrecord_searchtype);

		Find_View();
	}
	

	private void Find_View(){

		searchtype_back_btn = findViewById(R.id.searchtype_back_btn);
		searchtype_back_btn.setOnClickListener(this);

		searchtype_clear_btn = findViewById(R.id.searchtype_clear_btn);
		searchtype_clear_btn.setOnClickListener(this);

		searchtype_search_ed = findViewById(R.id.searchtype_search_ed);
	}


	@Override
	public void onClick(View v) {

		switch (v.getId()){

			case R.id.searchtype_back_btn:

				finish();

				break;

			case R.id.searchtype_clear_btn:

				searchtype_search_ed.setText("");

				break;

		}


	}


}
