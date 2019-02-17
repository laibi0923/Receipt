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

public class Register_Email extends Fragment {

    private LinearLayout register_email_next;

    private EditText email_edittext;

    private TextView Error_Msg_TextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.r001_register_useremail, container, false);

        email_edittext = (EditText) v.findViewById(R.id.email_edittext);

        Error_Msg_TextView = v.findViewById(R.id.errormsg);

        register_email_next = (LinearLayout) v.findViewById(R.id.register_next_btn);
        register_email_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!email_edittext.getText().toString().isEmpty()){

                    ((Register_Main) getActivity()).setEmail(email_edittext.getText().toString());

                    Register_Password mRegister_Password = new Register_Password();

                    ((Register_Main) getActivity()).Load_Fragment(mRegister_Password);

                    Error_Msg_TextView.setText("");

                }else {
                    Error_Msg_TextView.setText("Email cannot notbe empty");
                }

            }
        });

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((Register_Main) getActivity()).show_keybord(email_edittext);
    }
}
