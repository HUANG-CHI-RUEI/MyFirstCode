package com.wonderful.myfirstcode.chapter11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.chapter10.inquiry_thread.UpdateUITestActivity;
import com.wonderful.myfirstcode.chapter10.practice.DownloadActivity;
import com.wonderful.myfirstcode.chapter10.service.MyServiceActivity;
import com.wonderful.myfirstcode.utils.IntentUtils;

public class Chapter11Activity extends AppCompatActivity implements View.OnClickListener{

    Button btn_location, btn_map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter11);

        btn_location = (Button) findViewById(R.id.btn_location);
        btn_map = (Button) findViewById(R.id.btn_map);

        btn_location.setOnClickListener(this);
        btn_map.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_location:
                IntentUtils.myIntent(this, LocationActivity.class);
                break;

            case R.id.btn_map:
                IntentUtils.myIntent(this, MapActivity.class);
                break;

            default:
                break;
        }
    }
}
