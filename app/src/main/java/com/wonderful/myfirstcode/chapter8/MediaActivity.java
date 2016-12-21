package com.wonderful.myfirstcode.chapter8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.chapter7.permission.CallPhoneActivity;
import com.wonderful.myfirstcode.chapter7.provider.ReadContactActivity;
import com.wonderful.myfirstcode.utils.IntentUtils;

public class MediaActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_notification, btn_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        btn_notification = (Button) findViewById(R.id.btn_notification);
        btn_content = (Button) findViewById(R.id.btn_content);

        btn_notification.setOnClickListener(this);
        btn_content.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_permission:
                IntentUtils.myIntent(this, CallPhoneActivity.class);
                break;

            case R.id.btn_content:
                IntentUtils.myIntent(this, ReadContactActivity.class);
                break;


            default:
                break;
        }
    }
}
