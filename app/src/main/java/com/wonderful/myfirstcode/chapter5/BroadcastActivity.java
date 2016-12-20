package com.wonderful.myfirstcode.chapter5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.chapter5.force_offline.LoginActivity;
import com.wonderful.myfirstcode.utils.IntentUtils;
import com.wonderful.myfirstcode.utils.ToastUtils;

/**
 * 广播，动态监听网络变化
 */
public class BroadcastActivity extends AppCompatActivity {

    private IntentFilter intentFilter;

    private LocalReceiver localReceiver;

    private LocalBroadcastManager localBroadcastManager;

    //private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);


        Button btn_offline = (Button) findViewById(R.id.btn_offline);
        btn_offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.myIntent(BroadcastActivity.this, LoginActivity.class);
            }
        });


        // 获取实例
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        Button btn_send_broadcast = (Button) findViewById(R.id.btn_send_broadcast);
        btn_send_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 构建 Intent 对象
                //Intent intent = new Intent("com.wonderful.myfirstcode.MY_BROADCAST");
                // 发送标准广播
                //sendBroadcast(intent);
                // 发送有序广播
                //sendOrderedBroadcast(intent, null);
                Intent intent = new Intent("com.wonderful.myfirstcode.LOCAL_BROADCAST");
                // 发送有序广播
                localBroadcastManager.sendBroadcast(intent);

            }
        });

        // 创建 IntentFilter 实例
        intentFilter = new IntentFilter();
        // 添加广播值
        //intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("com.wonderful.myfirstcode.LOCAL_BROADCAST");
        // 创建 LocalReceiver 实例
        localReceiver = new LocalReceiver();
        // 注册本地广播监听器
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);

        // 创建 NetworkChangeReceiver 实例
        //networkChangeReceiver = new NetworkChangeReceiver();
        // 注册广播
        //registerReceiver(networkChangeReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消注册
        //unregisterReceiver(networkChangeReceiver);
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            // 获取管理网络连接的系统服务类的实例
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            // 判断网络是否可用
            if (networkInfo != null && networkInfo.isAvailable()){
                //ToastUtils.showShort("网络可用");
            }else {
                //ToastUtils.showShort("网络不可用");
            }

        }
    }

    /**
     * 本地广播接收器
     */
    class LocalReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ToastUtils.showShort("收到本地广播");
        }
    }
}
