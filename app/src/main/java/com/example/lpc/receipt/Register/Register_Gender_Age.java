package com.example.lpc.receipt.Register;

import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.support.v4.view.*;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.example.lpc.receipt.*;
import java.util.*;

public class Register_Gender_Age extends Fragment {

    private LinearLayout register_next_btn, register_back_btn;

    private EditText birthyear_edittext;

    private TextView Error_Msg_TextView;
	
	private ViewPager gender_viewpager;

	private int viewpager_position = 1;

	private String Gender_String = "M";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.r004_register_gendernage, container, false);

        birthyear_edittext  = v.findViewById(R.id.register_birthyear_edittext);

        Error_Msg_TextView = v.findViewById(R.id.errormsg);

		List<String> Gender_List = new ArrayList();
		Gender_List.add("女");
		Gender_List.add("男");
		Gender_List.add("女");
		Gender_List.add("男");
		
		gender_viewpager = v.findViewById(R.id.gender_viewpager);
		Gender_ViewPager_Adapter mAdapter = new Gender_ViewPager_Adapter(getContext(), Gender_List);
		gender_viewpager.setAdapter(mAdapter);
		gender_viewpager.setCurrentItem(1);
		gender_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

				@Override
				public void onPageScrolled(int position, float offset, int offsetPixels)
				{
					// TODO: Implement this method
				}

				@Override
				public void onPageSelected(int position)
				{
					// TODO: Implement this method
                    viewpager_position = position;
                    Log.e("Get Viewpager Position", viewpager_position + "");
				}

				@Override
				public void onPageScrollStateChanged(int p1)
				{
					// TODO: Implement this method
					if (viewpager_position == 0){
						gender_viewpager.setCurrentItem(2, false);
						Gender_String = "F";
					}else if (viewpager_position == 3){
						gender_viewpager.setCurrentItem(1, false);
						Gender_String = "M";
					}
				}
			});

        register_next_btn = v.findViewById(R.id.register_next_btn);
        register_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

				int AgeOfYear = Integer.parseInt(birthyear_edittext.getText().toString());

				if (AgeOfYear < 1919 || AgeOfYear > 2119){
					Error_Msg_TextView.setText("請檢查你所輸入資料是否正確");
					return;
				}

				if (birthyear_edittext.getText().toString().trim().isEmpty()){
					Error_Msg_TextView.setText("請輸入出生年份");
					return;
				}

                if (!birthyear_edittext.getText().toString().isEmpty()){

                    ((Register_Main) getActivity()).setAge_of_Year(birthyear_edittext.getText().toString());

					((Register_Main) getActivity()).setGender(Gender_String);

                    Register_OverView mRegister_OverView = new Register_OverView();

                    ((Register_Main) getActivity()).Load_Fragment(mRegister_OverView);

                    Error_Msg_TextView.setText("");

                }
            }
        });

        register_back_btn = v.findViewById(R.id.register_back_btn);
        register_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Register_Main) getActivity()).PopbackFragment();
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((Register_Main) getActivity()).show_keybord(birthyear_edittext);
    }
	
	
	
	
	
	public static class Gender_ViewPager_Adapter extends PagerAdapter {

		private Integer[] Gender_Resource= {R.mipmap.ic_female, R.mipmap.ic_male, R.mipmap.ic_female, R.mipmap.ic_male};
		private LayoutInflater mLayoutInflater;
		private List<String> mList;
		
		public Gender_ViewPager_Adapter(Context context, List<String> mList){
			this.mList = mList;
			mLayoutInflater = LayoutInflater.from(context);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position)
		{
			// TODO: Implement this method
			View v = mLayoutInflater.inflate(R.layout.r007_gender_viewpager, container, false);

			ImageView Gender_Image = v.findViewById(R.id.viewpager_img);
			Gender_Image.setImageResource(Gender_Resource[+position]);
			// Set Color Tint???
			// Gender_Image.setColorFilter(???);

			TextView Gender_Titel = v.findViewById(R.id.gender_title);
			Gender_Titel.setText(mList.get(position));

			container.addView(v);

			return v;
		}

			
		@Override
		public int getCount()
		{
			// TODO: Implement this method
			return 4;
		}

		@Override
		public boolean isViewFromObject(View view, Object object)
		{
			// TODO: Implement this method
			return view == object;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			// TODO: Implement this method
//			super.destroyItem(container, position, object);
			container.removeView((View)object);
		}
		
		
		
	}
	
	
}
