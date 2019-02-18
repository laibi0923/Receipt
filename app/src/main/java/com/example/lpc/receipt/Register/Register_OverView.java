package com.example.lpc.receipt.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lpc.receipt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register_OverView extends Fragment {

    private LinearLayout register_submit;

    private TextView overview_email, overview_username, overview_gender, overview_year;

    private FirebaseAuth mFirebaseAuth;

    private LinearLayout overview_loading_screen;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mFirebaseAuth = FirebaseAuth.getInstance();

        View v = inflater.inflate(R.layout.r005_register_overview, container, false);

        overview_loading_screen = v.findViewById(R.id.overview_loading_screen);

        overview_email = v.findViewById(R.id.overview_email);
        overview_email.setText(((Register_Main) getActivity()).getEmail());

        overview_username = v.findViewById(R.id.overview_username);
        overview_username.setText(((Register_Main) getActivity()).getUserName());

        overview_gender = v.findViewById(R.id.overview_gender);

        String Gender_String = "";

        if (((Register_Main) getActivity()).getGender() == "M"){
            Gender_String = "男";
        }else {
            Gender_String = "女";
        }

        overview_gender.setText(Gender_String);

        overview_year = v.findViewById(R.id.overview_year);
        overview_year.setText(((Register_Main) getActivity()).getAge_of_Year());


        register_submit = v.findViewById(R.id.register_submit);
        register_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                overview_loading_screen.setVisibility(View.VISIBLE);

                // Send Data to Firebase to create new user
                mFirebaseAuth.createUserWithEmailAndPassword(((Register_Main) getActivity()).getEmail(), ((Register_Main) getActivity()).getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            mFirebaseAuth.signOut();
                            Register_Finish mRegister_Finish = new Register_Finish();
                            ((Register_Main) getActivity()).Load_Fragment(mRegister_Finish);

                        }else {
                            Log.e("Failed Create New User", task.getException().getMessage());
                        }

                        overview_loading_screen.setVisibility(View.GONE);
                    }

                });


            }
        });

        return v;
    }
}
