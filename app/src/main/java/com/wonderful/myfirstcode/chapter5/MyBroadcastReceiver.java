package com.wonderful.myfirstcode.chapter5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.wonderful.myfirstcode.utils.ToastUtils;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ToastUtils.showShort("收到自定义广播");
        // 将这条广播拦截
        abortBroadcast();
    }
}
