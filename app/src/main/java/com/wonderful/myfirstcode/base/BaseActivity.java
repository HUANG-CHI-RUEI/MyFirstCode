package com.wonderful.myfirstcode.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wonderful.myfirstcode.chapter2.ActivityCollector;
import com.wonderful.myfirstcode.chapter5.force_offline.LoginActivity;
import com.wonderful.myfirstcode.utils.IntentUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基类
 * Created by KXwon on 2016/12/9.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected Unbinder mBinder;

    private ForceOfflineReceiver receiver;

    /**
     * 初始化布局id
     * @return 布局id
     */
    protected abstract int initLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutId());

        Log.d("BaseActivity", getClass().getSimpleName());// 知晓当前是在哪一个活动
        ActivityCollector.addActivity(this);// 将当前正在创建的活动添加到活动管理期里

        mContext = this;
        mBinder = ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.wonderful.myfirstcode.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null){
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        // 取消绑定
        mBinder.unbind();
        super.onDestroy();
        // 将一个马上要销毁的活动从管理器里移除
        ActivityCollector.removeActivity(this);
    }

    class ForceOfflineReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("警告");
            builder.setMessage("你已被下线，请重新登录");
            // 设置不可取消
            builder.setCancelable(false);
            // 设置点击事件
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // 销毁所有活动
                    ActivityCollector.finishAll();
                    // 重新启动 LoginActivity
                    IntentUtils.myIntent(context, LoginActivity.class);
                }
            });
            builder.show();
        }
    }
}
