package com.wonderful.myfirstcode.chapter10.inquiry_thread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wonderful.myfirstcode.R;

public class UpdateUITestActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_change_text;
    private TextView tv_text;

    // 定义一个整型常量用于表示更新TextView这个动作
    public static final int UPDATE_TEXT = 1;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case  UPDATE_TEXT:
                    // 在这里可以进行 UI 操作
                    tv_text.setText("你好世界");
                    break;

                 default:
                     break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_uitest);

        tv_text = (TextView) findViewById(R.id.tv_text);
        btn_change_text = (Button) findViewById(R.id.btn_change_text);
        btn_change_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.btn_change_text:
                 new Thread(new Runnable() {
                     @Override
                     public void run() {
                         // 把显示内容“Hello world”改成“你好世界”
                         //tv_text.setText("你好世界");
                         // 创建Message对象，并将它的what字段指定为UPDATE_TEXT
                         Message message = new Message();
                         message.what = UPDATE_TEXT;
                         handler.sendMessage(message);//将Message对象发送出去
                     }
                 }).start();
                 break;

             default:
                 break;
         }
    }
}
