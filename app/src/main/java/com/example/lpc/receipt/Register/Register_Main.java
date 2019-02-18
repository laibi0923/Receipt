package com.example.lpc.receipt.Register;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.lpc.receipt.R;

import android.content.*;

public class Register_Main extends AppCompatActivity {

    private LinearLayout register_close_btn;

    private String Email, Password, UserName, Gender, Age_of_Year;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.r001_register_main);

        Fragment Register_Email = new Register_Email();

        Load_Fragment(Register_Email);

        register_close_btn = (LinearLayout) findViewById(R.id.register_close_btn);
        register_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void Load_Fragment(Fragment fragment){

        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.register_framlayout, fragment);
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();

    }

    public void PopbackFragment(){
        getSupportFragmentManager().popBackStack();
    }


    public void Finish_Register(){
        Intent mIntent = new Intent();
        mIntent.putExtra("UserEmail", Email);
        setResult(644, mIntent);
        this.finish();
    }



    public void show_keybord(EditText mEditText){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText ,0);
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

    public String getEmail() {
        return Email;
    }

    public String getUserName() {
        return UserName;
    }

    public String getGender() {
        return Gender;
    }

    public String getAge_of_Year() {
        return Age_of_Year;
    }

    public String getPassword() {
        return Password;
    }
}
