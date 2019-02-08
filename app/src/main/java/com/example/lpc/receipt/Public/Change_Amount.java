package com.example.lpc.receipt.Public;

import java.text.*;
import android.widget.*; 

public class Change_Amount
{
	
	DecimalFormat dec = new DecimalFormat("#,##0.00");
	
	public void Change_Amount(){}
	
	
	public String Change_Amount(String Input_Amount, EditText mEditText){
		
		String Result = "";

        if(!Input_Amount.matches("^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$"))
        {
            String userInput= "" + Input_Amount.replaceAll("[^\\d-]", "");

            if (userInput.length() > 0) {

                Double in = Double.parseDouble(userInput);

                double percen = in / 100;

				Result = "$" + dec.format(percen);
				
				mEditText.setText(Result);
				
				mEditText.setSelection(mEditText.length());

            }
        }

		return Result;
	}
	
	
	
	public String Change_Amount(String Input_Amount, TextView mTextView){

		String Result = "";

        if(!Input_Amount.matches("^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$"))
        {
            String userInput= "" + Input_Amount.replaceAll("[^\\d-]", "");

            if (userInput.length() > 0) {

                Double in = Double.parseDouble(userInput);

                double percen = in / 100;

				Result = "$" + dec.format(percen);

				mTextView.setText(Result);

            }
        }

		return Result;
	}
	
	
	
	
	
	
}
