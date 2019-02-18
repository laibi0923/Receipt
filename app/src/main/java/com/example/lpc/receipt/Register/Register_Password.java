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

import com.example.lpc.receipt.MainActivity;
import com.example.lpc.receipt.R;

public class Register_Password extends Fragment {

    private LinearLayout register_next_btn, register_back_btn;

    private EditText password_edittext;

    private TextView Error_Msg_TextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.r002_register_password, container, false);

        password_edittext  = v.findViewById(R.id.register_password_edittext);

        Error_Msg_TextView = v.findViewById(R.id.errormsg);

        register_next_btn = v.findViewById(R.id.register_next_btn);
        register_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (password_edittext.getText().length() <= 6) {
                    Error_Msg_TextView.setText("請輸入密碼長度大於六位.");
                    return;
                }

                if (password_edittext.getText().toString().isEmpty()){
                    Error_Msg_TextView.setText("請輸入密碼.");
                    return;
                }

                if (!password_edittext.getText().toString().trim().isEmpty()) {

                    ((Register_Main) getActivity()).setPassword(password_edittext.getText().toString());

                    Register_UserName mRegister_UserName = new Register_UserName();

                    ((Register_Main) getActivity()).Load_Fragment(mRegister_UserName);

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

        ((Register_Main) getActivity()).show_keybord(password_edittext);
    }

}
