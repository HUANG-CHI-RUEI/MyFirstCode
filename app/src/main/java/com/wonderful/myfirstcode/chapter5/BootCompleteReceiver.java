package com.wonderful.myfirstcode.chapter5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.wonderful.myfirstcode.utils.ToastUtils;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // 执行相应的逻辑
        //ToastUtils.showShort("开机完成");
        Toast.makeText(context,"开机完成",Toast.LENGTH_SHORT).show();
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
