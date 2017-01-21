package com.wonderful.myfirstcode.chapter10.inquiry_thread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wonderful.myfirstcode.R;

public class ThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        // 启动线程
        new MyThread().run();

        // 启动线程
        MyThread2 myThread2 = new MyThread2();
        new Thread(myThread2).start();

        // 匿名类方式实现
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 处理具体的逻辑
            }
        }).start();
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            // 处理具体的逻辑
        }
    }

    class MyThread2 implements Runnable{
        @Override
        public void run() {
            // 处理具体的逻辑
        }
    }

}
