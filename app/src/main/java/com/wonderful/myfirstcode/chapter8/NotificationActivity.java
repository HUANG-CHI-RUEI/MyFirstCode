package com.wonderful.myfirstcode.chapter8;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.R;

import java.io.File;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Button btn_send_notice = (Button) findViewById(R.id.btn_send_notice);
        btn_send_notice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send_notice:
                Intent intent = new Intent(this,NotificationTestActivity.class);
                // PendingIntent实例可由getActivity()、getBroadcast()、getServices()等方法获取
                // 接收的参数：第一个是Context，第二个一般用不到传0即可，第三个是Intent对象，第四个用于确定PendingIntent的行为
                PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);

                // 1. 获取 NotificationManager 实例来对通知进行管理
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // 2. 使用 Builder 构造器来创建 Notification 对象
                Notification notification = new NotificationCompat.Builder(this)
                        .setContentTitle("This is content title")  // 标题内容
                        .setContentText("This is content text")   // 正文内容
                        .setWhen(System.currentTimeMillis())     // 通知被创建的时间
                        .setSmallIcon(R.mipmap.ic_launcher)     // 通知的小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))// 通知的大图标
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
                        .setVibrate(new long[]{0,1000,1000,1000})
                        .setLights(Color.GREEN,1000,1000)
                        //.setDefaults(NotificationCompat.DEFAULT_ALL)
                        // 在setStyle方法中创建NotificationCompat.BigTextStyle对象，调用其bigText()方法传入文字即可
                        //.setStyle(new NotificationCompat.BigTextStyle().bigText("红红火火恍恍惚惚，" +
                        //        "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈呵呵，红红火火恍恍惚惚" +
                        //        "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"))
                        //.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.
                        //        decodeResource(getResources(),R.drawable.big_image)))
                        // 一共有5个常量可选：PRIORITY_DEFAULT默认、PRIORITY_MIN最低、PRIORITY_LOW较低、
                        // PRIORITY_HIGH较高、PRIORITY_MAX最高
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .build();
                // 3. 显示通知. 其中notify()的两个参数：第一个是id,要保证为每个通知所指定的id都是不同的;
                // 第二个参数是 Notification 对象
                manager.notify(1,notification);
                break;

            default:
                break;
        }
    }
}
