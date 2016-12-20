package com.wonderful.myfirstcode.chapter3;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wonderful.myfirstcode.R;

public class CustomTitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_title);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide(); // 隐藏系统自带的标题栏
        }
    }
}
