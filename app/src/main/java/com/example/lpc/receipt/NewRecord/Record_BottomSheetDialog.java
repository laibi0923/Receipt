package com.example.lpc.receipt.NewRecord;
import android.app.*;
import android.support.design.widget.*;
import android.util.*;
import android.view.*;
import com.example.lpc.receipt.*;
import android.widget.*;

public class Record_BottomSheetDialog extends BottomSheetDialogFragment
{

	@Override
	public void onStart()
	{
		// TODO: Implement this method
		super.onStart();
		
		BottomSheetDialog mBottomSheetDialog = (BottomSheetDialog) getDialog();
		
		FrameLayout bottomsheet = (FrameLayout) mBottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
		
		if(mBottomSheetDialog != null){
			
			CoordinatorLayout.LayoutParams parms = (CoordinatorLayout.LayoutParams) bottomsheet.getLayoutParams();
			
			parms.height = getHeight();
		}
		
		
	}
	
	
	private int getHeight(){

		int height = 0;
		
		return height;
	}
	
}
