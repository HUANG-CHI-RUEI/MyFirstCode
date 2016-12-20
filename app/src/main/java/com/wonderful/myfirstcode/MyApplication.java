package com.wonderful.myfirstcode;

import android.app.Application;

import org.litepal.LitePal;

/**
 * 全局
 * Created by KXwon on 2016/12/9.
 */
public class MyApplication extends Application {

    private static MyApplication mInstance;
 	
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        // 调用 LitePal 的初始化方法
        LitePal.initialize(this);
        
    }

    
    /**
     * Singleton main method. Provides the global static instance of the helper class.
     * @return The MyApplication instance.
     */
    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

}
