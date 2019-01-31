package com.example.lpc.receipt.Setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lpc.receipt.R;

public class Setting_Main extends Fragment {

    private LinearLayout setting_statistics_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.z001_setting_main, container, false);

        Find_View(v);

        return v;
    }

    private void Find_View(View v){

        setting_statistics_btn = v.findViewById(R.id.setting_statistics_btn);
        setting_statistics_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){

                    case R.id.setting_statistics_btn:

                        Intent open_y001_activity = new Intent(getActivity(), Statustics_Main.class);
                        startActivity(open_y001_activity);

                        break;
                }

            }

        });
    }


}
