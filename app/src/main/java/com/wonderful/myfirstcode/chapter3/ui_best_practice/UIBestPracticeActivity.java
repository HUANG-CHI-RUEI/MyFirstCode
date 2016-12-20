package com.wonderful.myfirstcode.chapter3.ui_best_practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wonderful.myfirstcode.R;

import java.util.ArrayList;
import java.util.List;

public class UIBestPracticeActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();

    private EditText et_input_text;
    private Button btn_send;

    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_best_practice);

        initMsg(); // 初始化消息数据

        et_input_text = (EditText) findViewById(R.id.et_input_text);
        btn_send = (Button) findViewById(R.id.btn_send);

        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = et_input_text.getText().toString();
                if (!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    // 当有新消息时，刷新RecyclerView中的显示
                    adapter.notifyItemInserted(msgList.size() - 1);
                    // 将RecyclerView定位到最后一行
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
                    // 清空输入框中的内容
                    et_input_text.setText("");
                }
            }
        });
    }

    private void initMsg() {
        Msg msg1 = new Msg("Hello world!",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello. Who is that?",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("。。。",Msg.TYPE_SENT);
        msgList.add(msg3);
        Msg msg4 = new Msg("This is 逗逼. Nice talking to you",Msg.TYPE_RECEIVED);
        msgList.add(msg4);
    }
}
