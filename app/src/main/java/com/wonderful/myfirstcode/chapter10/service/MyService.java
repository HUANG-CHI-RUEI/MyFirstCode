package com.wonderful.myfirstcode.chapter10.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.wonderful.myfirstcode.R;

public class MyService extends Service {
    public MyService() {
    }

    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder{
        // 模拟开始下载方法
        public void startDownload(){
            Log.d("------MyService------", "startDownload: ");
        }
        // 模拟查看下载进度方法
        public int getProgress(){
            Log.d("------MyService------", "getProgress: ");
            return 0;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }

    /**
     * 在服务创建时调用
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("------MyService------", "onCreate: ");
        Intent intent = new Intent(this,MyServiceActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("这是标题")
                .setContentText("这是内容")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1,notification); //让MyService变成一个前台服务，并在系统状态栏显示出来
    }

    /**
     * 在每次服务启动时调用
     * 若服务一旦启动就立刻执行某个动作，可以将逻辑写在此方法中
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("------MyService------", "onStartCommand: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 处理具体的逻辑
                stopSelf();
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 在服务销毁时调用，回收不再使用的资源
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("------MyService------", "onDestroy: ");
    }
}
