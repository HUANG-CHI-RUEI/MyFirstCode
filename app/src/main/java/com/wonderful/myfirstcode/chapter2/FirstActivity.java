package com.wonderful.myfirstcode.chapter2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.utils.IntentUtils;

public class FirstActivity extends AppCompatActivity {

    private static final String TAG = "FirstActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 显示Intent
                //Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                // 隐式Intent
                //Intent intent = new Intent("com.wonderful.ACTION_START");
                //intent.addCategory("com.wonderful.MY_CATEGORY");
                //String data = "hello SecondActivity";
                //Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                //intent.putExtra("extra_data",data);
                //startActivity(intent);

                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                startActivityForResult(intent,1);//请求码只要是唯一值就行了，这里传入1
            }
        });

        Button btn_test = (Button) findViewById(R.id.btn_test);
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.myIntent(FirstActivity.this,TestActivity.class);
            }
        });
    }

    /**
     * 处理得到的返回数据
     * @param requestCode  启动活动时传入的请求码
     * @param resultCode  返回数据时传入的处理结果
     * @param data  携带返回数据的Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    String returnedData = data.getStringExtra("data_return");
                    Toast.makeText(FirstActivity.this,returnedData,Toast.LENGTH_SHORT).show();
                    Log.d(TAG, returnedData);
                }
                break;
            
            default:
                break;
        }
    }
}
