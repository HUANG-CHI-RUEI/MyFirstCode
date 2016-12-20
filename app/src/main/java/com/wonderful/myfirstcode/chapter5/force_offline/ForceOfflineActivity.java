package com.wonderful.myfirstcode.chapter5.force_offline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.base.BaseActivity;
import com.wonderful.myfirstcode.utils.ToastUtils;

public class ForceOfflineActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button btn_force_offline = (Button) findViewById(R.id.btn_force_offline);
        btn_force_offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.wonderful.myfirstcode.FORCE_OFFLINE");
                // 发送强制下线广播
                sendBroadcast(intent);
            }
        });

    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_force_offline;
    }
}
