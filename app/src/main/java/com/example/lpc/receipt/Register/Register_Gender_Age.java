package com.example.lpc.receipt.Register;

import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.support.v4.view.*;
import android.view.*;
import android.widget.*;
import com.example.lpc.receipt.*;
import java.util.*;

public class Register_Gender_Age extends Fragment {

    private LinearLayout register_next_btn, register_back_btn;

    private EditText birthyear_edittext;

    private TextView Error_Msg_TextView;
	
	private ViewPager gender_viewpager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.r004_register_gendernage, container, false);

        birthyear_edittext  = v.findViewById(R.id.register_birthyear_edittext);

        Error_Msg_TextView = v.findViewById(R.id.errormsg);
		
		List<String> mListr = new ArrayList();
		
		gender_viewpager = v.findViewById(R.id.gender_viewpager);
		Gender_ViewPager_Adapter mAdapter = new Gender_ViewPager_Adapter(getContext(), mListr);
		gender_viewpager.setAdapter(mAdapter);
		gender_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

				@Override
				public void onPageScrolled(int p1, float p2, int p3)
				{
					// TODO: Implement this method
				}

				@Override
				public void onPageSelected(int p1)
				{
					// TODO: Implement this method
				}

				@Override
				public void onPageScrollStateChanged(int p1)
				{
					// TODO: Implement this method
				}
			});

        register_next_btn = v.findViewById(R.id.register_next_btn);
        register_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!birthyear_edittext.getText().toString().isEmpty()){

                    ((Register_Main) getActivity()).setAge_of_Year(birthyear_edittext.getText().toString());

                    Register_Finish mRegister_Finish = new Register_Finish();

                    ((Register_Main) getActivity()).Load_Fragment(mRegister_Finish);

                    Error_Msg_TextView.setText("");

                }else {
                    Error_Msg_TextView.setText("BOY Cannot Be Empty");
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
	
	
	
	
	
	public static class Gender_ViewPager_Adapter extends PagerAdapter
	{

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
			View v = mLayoutInflater.inflate(R.layout.r006_gender_viewpager, container, false);
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
			super.destroyItem(container, position, object);
			container.removeView((View)object);
		}
		
		
		
	}
	
	
}
