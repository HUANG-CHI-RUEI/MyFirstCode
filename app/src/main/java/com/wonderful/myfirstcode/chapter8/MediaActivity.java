package com.wonderful.myfirstcode.chapter8;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.utils.IntentUtils;

public class MediaActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_notification, btn_take_photo,btn_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        btn_notification = (Button) findViewById(R.id.btn_notification);
        btn_take_photo = (Button) findViewById(R.id.btn_take_photo);
        btn_play = (Button) findViewById(R.id.btn_play);


        btn_notification.setOnClickListener(this);
        btn_take_photo.setOnClickListener(this);
        btn_play.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_notification:
                IntentUtils.myIntent(this, NotificationActivity.class);
                break;

            case R.id.btn_take_photo:
                IntentUtils.myIntent(this, TakePhotoActivity.class);
                break;

            case R.id.btn_play:
                IntentUtils.myIntent(this, MediaPlayActivity.class);
                break;


            default:
                break;
        }
    }
}
