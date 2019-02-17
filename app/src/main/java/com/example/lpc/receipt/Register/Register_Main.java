package com.example.lpc.receipt.Register;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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

        Fragment Register_Email = new Register_Email();

        Load_Fragment(Register_Email);

        register_close_btn = findViewById(R.id.register_close_btn);
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

    public void UserInput_Value(){

        Log.e("", Email);
        Log.e("", Password);
        Log.e("", UserName);
//        Log.e("", Gender);
        Log.e("", Age_of_Year);

    }

    public void Finish_Register(){
        this.finish();
    }

    private void New_Register(){

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
}
