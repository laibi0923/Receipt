package com.example.lpc.receipt.Register;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lpc.receipt.R;

public class Register_Gender_Age extends Fragment {

    private LinearLayout register_next_btn, register_back_btn;

    private EditText birthyear_edittext;

    private TextView Error_Msg_TextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.r004_register_gendernage, container, false);

        birthyear_edittext  = v.findViewById(R.id.register_birthyear_edittext);

        Error_Msg_TextView = v.findViewById(R.id.errormsg);

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
}
