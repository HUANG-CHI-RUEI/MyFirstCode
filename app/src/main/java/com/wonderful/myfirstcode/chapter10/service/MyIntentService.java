package com.wonderful.myfirstcode.chapter10.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");//调用父类的有参构造函数
    }

    /**
     * 此方法在子线程中运行，可以处理一些具体的逻辑，且不用担心 ANR 问题
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        // 打印当前线程的 id
        Log.d("MyIntentService", "onHandleIntent: 线程id是 "+ Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService", "onDestroy: 服务停止");
    }
}
