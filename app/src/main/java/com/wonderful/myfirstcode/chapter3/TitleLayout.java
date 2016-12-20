package com.wonderful.myfirstcode.chapter3;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wonderful.myfirstcode.R;

/**
 * 自定义标题栏
 * Created by KXwon on 2016/12/9.
 */

public class TitleLayout extends LinearLayout {

    public TitleLayout(Context context, AttributeSet attrs) {

        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);

        // 初始化两个按钮
        Button titleBack = (Button) findViewById(R.id.title_back);
        Button titleMessage = (Button) findViewById(R.id.title_message);
        // 设置点击事件
        titleBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击返回按钮销毁当前活动
                ((Activity) getContext()).finish();
            }
        });

        titleMessage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You clicked Message button", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
