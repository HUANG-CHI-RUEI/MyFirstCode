package com.wonderful.myfirstcode.chapter2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.R;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        
        //Intent intent = getIntent();
        // String:getStringExtra(); int:getIntExtra(); 布尔型：getBooleanExtra() ...以此类推
        //String data = intent.getStringExtra("extra_data");
        //Log.d(TAG, data);
        
        Button btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 隐式Intent：打开网页
                //Intent intent = new Intent(Intent.ACTION_VIEW);
                //intent.setData(Uri.parse("http://www.baidu.com"));
                // 隐式Intent：打开系统拨号界面
                //Intent intent = new Intent(Intent.ACTION_DIAL);
                //intent.setData(Uri.parse("tel:10086"));
                //startActivity(intent);

                Intent intent = new Intent();
                intent.putExtra("data_return","hello FirstActivity");
                setResult(RESULT_OK,intent);
                finish();//销毁当前活动

                // 杀掉当前进程
                // killProcess()方法用于杀掉一个进程，它接收一个进程id参数，可通过myPid()方法获得当前程序的进程id
                //android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }
}
