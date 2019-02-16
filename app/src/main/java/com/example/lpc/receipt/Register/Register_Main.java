package com.example.lpc.receipt.Register;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.lpc.receipt.R;

public class Register_Main extends AppCompatActivity {

    private LinearLayout register_close_btn;

    private Register_Email mRegister_Email;

    private Register_Password mRegister_Password;

    private Register_UserName mRegister_UserName;

    private Register_Gender_Age mRegister_Gender_Age;

    private String Email, Password, UserName, Gender, Age_of_Year;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r001_register_main);

        register_close_btn = findViewById(R.id.register_close_btn);
        register_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Load_Init_Fragment();

    }

    private void Load_Init_Fragment(){

        Fragment Register_Email = new Register_Email();
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.register_framlayout, Register_Email);
        mFragmentTransaction.commit();

    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setAge_of_Year(String age_of_Year) {
        Age_of_Year = age_of_Year;
    }
}
