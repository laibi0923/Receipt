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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lpc.receipt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;

public class Register_Email extends Fragment {

    private LinearLayout register_email_next;

    private EditText email_edittext;

    private TextView Error_Msg_TextView;

    private FirebaseAuth mFirebaseAuth;

    private LinearLayout email_loading_screen;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mFirebaseAuth = FirebaseAuth.getInstance();

        View v = inflater.inflate(R.layout.r001_register_useremail, container, false);

        email_loading_screen = v.findViewById(R.id.email_loading_screen);

        email_edittext = (EditText) v.findViewById(R.id.email_edittext);

        Error_Msg_TextView = v.findViewById(R.id.errormsg);

        register_email_next = (LinearLayout) v.findViewById(R.id.register_next_btn);
        register_email_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email_edittext.getText().toString().trim().isEmpty()){
                    Error_Msg_TextView.setText("請輸入電郵地址.");
                    return;
                }

                if (!isEmailValid(email_edittext.getText())){

                    Error_Msg_TextView.setText("電郵格式不正確.");

                }else {

                    email_loading_screen.setVisibility(View.VISIBLE);

                    mFirebaseAuth.fetchProvidersForEmail(email_edittext.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<ProviderQueryResult> task) {

                            if (task.getResult().getProviders().size() != 1){

                                ((Register_Main) getActivity()).setEmail(email_edittext.getText().toString());

                                Register_Password mRegister_Password = new Register_Password();

                                ((Register_Main) getActivity()).Load_Fragment(mRegister_Password);

                                Error_Msg_TextView.setText("");

                            }else {
                                Error_Msg_TextView.setText("已註冊電郵, 請嘗試使用其他電郵.");
                            }

                            email_loading_screen.setVisibility(View.GONE);
                        }
                    });


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


    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
