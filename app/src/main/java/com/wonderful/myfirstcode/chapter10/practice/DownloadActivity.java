package com.wonderful.myfirstcode.chapter10.practice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.utils.ToastUtils;

import java.util.jar.Manifest;

public class DownloadActivity extends AppCompatActivity implements View.OnClickListener {

    private DownloadService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        Button start_download = (Button) findViewById(R.id.start_download);
        Button pause_download = (Button) findViewById(R.id.pause_download);
        Button cancel_download = (Button) findViewById(R.id.cancel_download);
        start_download.setOnClickListener(this);
        pause_download.setOnClickListener(this);
        cancel_download.setOnClickListener(this);

        Intent intent = new Intent(this,DownloadService.class);
        startService(intent);//启动服务
        bindService(intent,connection,BIND_AUTO_CREATE);//绑定服务
        if (ContextCompat.checkSelfPermission(DownloadActivity.this, android.Manifest.
                permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(DownloadActivity.this,new
                    String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    @Override
    public void onClick(View v) {
        if (downloadBinder == null){
            return;
        }
        switch (v.getId()){
            // 开始下载
            case R.id.start_download:
                String url = "https://raw.githubusercontent.com/guolindev/eclipse/" +
                        "master/eclipse-inst-win64.exe";
                downloadBinder.startDownload(url);
                break;

            // 暂停下载
            case R.id.pause_download:
                downloadBinder.pauseDownload();
                break;

            // 取消下载
            case R.id.cancel_download:
                downloadBinder.cancelDownload();
                break;

            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    ToastUtils.showShort("拒绝权限将无法使用程序");
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);//解绑服务
    }
}
