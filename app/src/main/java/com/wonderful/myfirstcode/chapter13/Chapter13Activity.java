package com.wonderful.myfirstcode.chapter13;

import android.app.AlarmManager;
import android.content.Context;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wonderful.myfirstcode.R;

public class Chapter13Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter13);

        // 获取 AlarmManager 的实例
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // 设置触发时间
        // SystemClock.elapsedRealtime() 获取系统开机至今所经历时间的毫秒数
        // SystemClock.currentTimeMillis() 获取1970年1月1日0点至今所经历时间的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime() + 10 * 1000;

        // 3个参数：指定 AlarmManager 的工作类型、定时任务的触发时间、PendingIntent
        // 其中AlarmManager 的工作类型有四种：
        // ELAPSED_REALTIME 定时任务的触发时间从系统开机开始时算起，不会唤醒 CPU
        // ELAPSED_REALTIME_WAKEUP 系统开机开始时算起，会唤醒 CPU
        // RTC 从1970年1月1日0点开始算起，不会唤醒 CPU
        // RTC_WAKEUP 从1970年1月1日0点开始算起，会唤醒 CPU
        //manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pandingIntent);
    }
}
