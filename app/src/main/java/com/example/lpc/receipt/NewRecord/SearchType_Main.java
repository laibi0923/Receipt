package com.example.lpc.receipt.NewRecord;
import android.support.v7.app.*;
import android.os.*;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.lpc.receipt.Public.FullScreencall;
import com.example.lpc.receipt.R;

import java.util.ArrayList;
import android.support.v7.widget.*;
import android.view.inputmethod.*;
import android.widget.Toast;

public class SearchType_Main extends AppCompatActivity implements View.OnClickListener {

	private RelativeLayout searchtype_back_btn, searchtype_clear_btn;

	private EditText searchtype_search_ed;

	private RecyclerView SearchType_RecyclerView;

	private SearchType_Adapter mSearchType_Adapter;

	private ArrayList<SearchType_Item_Model> Type_list;


	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		if (hasFocus) {
			new FullScreencall(this).Hide_SystemUI();
		}

	}



	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c02_newrecord_searchtype);

		Find_View();

		add_Data();
		
		InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
		inputMethodManager.toggleSoftInputFromWindow(searchtype_search_ed.getApplicationWindowToken(),InputMethodManager.SHOW_FORCED, 0);
		
	}
	

	private void Find_View(){

		searchtype_back_btn = (RelativeLayout) findViewById(R.id.searchtype_back_btn);
		searchtype_back_btn.setOnClickListener(this);

		searchtype_clear_btn = (RelativeLayout) findViewById(R.id.searchtype_clear_btn);
		searchtype_clear_btn.setOnClickListener(this);

		searchtype_search_ed = (EditText) findViewById(R.id.searchtype_search_ed);

		Type_list = new ArrayList<>();
		mSearchType_Adapter = new SearchType_Adapter(this, Type_list);
		mSearchType_Adapter.setClickListener(SearchRecyclerview_ItemClickListener);

		SearchType_RecyclerView  = (RecyclerView) findViewById(R.id.searchtype_recyclerview);
		SearchType_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
		SearchType_RecyclerView.addItemDecoration(new DividerItemDecoration(SearchType_RecyclerView.getContext(), DividerItemDecoration.VERTICAL));
		//SearchType_RecyclerView.setNestedScrollingEnabled(false);
		SearchType_RecyclerView.setAdapter(mSearchType_Adapter);
		

	}


	private void add_Data(){

		Type_list.add(new SearchType_Item_Model("未分類" , "未指定類別"));
		Type_list.add(new SearchType_Item_Model("服飾" , "?"));
		Type_list.add(new SearchType_Item_Model("食費" , "早餐 午餐 晚餐 零食"));
		Type_list.add(new SearchType_Item_Model("租金" , "?"));
		Type_list.add(new SearchType_Item_Model("旅遊" , "?"));
		Type_list.add(new SearchType_Item_Model("交通" , "?"));
		Type_list.add(new SearchType_Item_Model("教育" , "?"));
		Type_list.add(new SearchType_Item_Model("帳單" , "?"));
		Type_list.add(new SearchType_Item_Model("薪金" , "?"));
		Type_list.add(new SearchType_Item_Model("日用品" , "?"));
		Type_list.add(new SearchType_Item_Model("保險" , "?"));
		Type_list.add(new SearchType_Item_Model("維修" , "?"));

		mSearchType_Adapter.notifyDataSetChanged();
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




	private SearchType_Adapter.ItemClickListener SearchRecyclerview_ItemClickListener = new SearchType_Adapter.ItemClickListener(){

		@Override
		public void onItemClick(View view, int position) {

			Toast.makeText(view.getContext(), mSearchType_Adapter.get_SearchType_Name(position) + "", Toast.LENGTH_SHORT).show();

		}
	};




}
