package com.example.lpc.receipt.Public;


import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.text.DecimalFormat;

import javax.security.auth.login.LoginException;

public class Amount_TextWatcher implements TextWatcher {

    DecimalFormat dec = new DecimalFormat("#,##0.00");

    private EditText mEditText;

    public Amount_TextWatcher(EditText mEditText){

        this.mEditText = mEditText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {


        if(!s.toString().matches("^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$"))
        {
            String userInput= "" + s.toString().replaceAll("[^\\d]", "");

            if (userInput.length() > 0) {

                Float in = Float.parseFloat(userInput);

                float percen = in/100;

                mEditText.setText("$"+dec.format(percen));

                mEditText.setSelection(mEditText.getText().length());

            }
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
