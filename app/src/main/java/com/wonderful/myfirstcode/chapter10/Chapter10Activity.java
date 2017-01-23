package com.wonderful.myfirstcode.chapter10;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.chapter10.inquiry_thread.UpdateUITestActivity;
import com.wonderful.myfirstcode.chapter10.practice.DownloadActivity;
import com.wonderful.myfirstcode.chapter10.service.MyServiceActivity;
import com.wonderful.myfirstcode.chapter9.HttpActivity;
import com.wonderful.myfirstcode.chapter9.ParseJSONActivity;
import com.wonderful.myfirstcode.chapter9.ParseXMLActivity;
import com.wonderful.myfirstcode.utils.IntentUtils;

public class Chapter10Activity extends AppCompatActivity implements View.OnClickListener{

    Button btn_thread, btn_service,btn_practice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter10);

        btn_thread = (Button) findViewById(R.id.btn_thread);
        btn_service = (Button) findViewById(R.id.btn_service);
        btn_practice = (Button) findViewById(R.id.btn_practice);


        btn_thread.setOnClickListener(this);
        btn_service.setOnClickListener(this);
        btn_practice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_thread:
                IntentUtils.myIntent(this, UpdateUITestActivity.class);
                break;

            case R.id.btn_service:
                IntentUtils.myIntent(this, MyServiceActivity.class);
                break;

            case R.id.btn_practice:
                IntentUtils.myIntent(this, DownloadActivity.class);
                break;


            default:
                break;
        }
    }
}
