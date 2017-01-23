package com.wonderful.myfirstcode.chapter10.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.R;

public class MyServiceActivity extends AppCompatActivity implements View.OnClickListener{

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 服务绑定成功后调用
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 服务解除绑定后调用
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);

        Button start_service = (Button) findViewById(R.id.start_service);
        Button stop_service = (Button) findViewById(R.id.stop_service);
        Button bind_service = (Button) findViewById(R.id.bind_service);
        Button unbind_service = (Button) findViewById(R.id.unbind_service);
        Button start_intent_service = (Button) findViewById(R.id.start_intent_service);
        start_service.setOnClickListener(this);
        stop_service.setOnClickListener(this);
        bind_service.setOnClickListener(this);
        unbind_service.setOnClickListener(this);
        start_intent_service.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:
                Intent startIntent = new Intent(this,MyService.class);
                startService(startIntent);// 启动服务
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this,MyService.class);
                stopService(stopIntent);// 停止服务
                break;

            case R.id.bind_service:
                Intent bindIntent = new Intent(this,MyService.class);
                // 绑定服务，三个参数：Intent对象、ServiceConnection实例、标志位
                // (BIND_AUTO_CREATE 表示活动和服务绑定后自动创建服务)
                bindService(bindIntent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                // 解绑服务
                unbindService(connection);
                break;
            case R.id.start_intent_service:
                // 打印主线程的 id
                Log.d("MyServiceActivity", "onClick: 主线程id："+ Thread.currentThread().getId());
                Intent intentService = new Intent(this,MyIntentService.class);
                startService(intentService);
                break;
            default:
                break;
        }
    }
}
