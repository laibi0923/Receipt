package com.example.lpc.receipt.Register;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lpc.receipt.R;

public class Register_Finish extends Fragment {

    private LinearLayout register_getvalue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.r006_register_finish, container, false);

        register_getvalue = v.findViewById(R.id.register_getvalue);
        register_getvalue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((Register_Main) getActivity()).Finish_Register();

            }
        });

        return v;
    }
}
