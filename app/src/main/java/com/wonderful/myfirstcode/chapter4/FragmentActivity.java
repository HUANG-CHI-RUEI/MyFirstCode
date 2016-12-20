package com.wonderful.myfirstcode.chapter4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.R;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        //replaceFragment(new RightFragment());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                //replaceFragment(new AnotherRightFragment());
                break;

            default:
                break;
        }
    }

    private void replaceFragment(Fragment fragment){
        // 获取FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 添加或替换碎片
        transaction.replace(R.id.right_layout,fragment);
        // 用于描述返回栈的状态
        transaction.addToBackStack(null);
        // 提交事务
        transaction.commit();
    }
}
