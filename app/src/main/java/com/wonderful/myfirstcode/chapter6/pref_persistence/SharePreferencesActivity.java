package com.wonderful.myfirstcode.chapter6.pref_persistence;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wonderful.myfirstcode.R;

public class SharePreferencesActivity extends AppCompatActivity {

    private Button save_data,restore_data;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preferences);

        save_data = (Button) findViewById(R.id.save_data);
        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1.指定文件名为 wonderful，并得到 SharedPreferences.Editor 对象
                SharedPreferences.Editor editor = getSharedPreferences("wonderful",MODE_PRIVATE).edit();
                // 2.添加不同类型的数据
                editor.putString("name","开心wonderful");
                editor.putInt("age",20);
                editor.putBoolean("married",false);
                // 3.数据提交
                editor.apply();
            }
        });

        textView = (TextView) findViewById(R.id.show_data);
        restore_data = (Button) findViewById(R.id.restore_data);
        restore_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获得 SharedPreferences 对象
                SharedPreferences pref = getSharedPreferences("wonderful",MODE_PRIVATE);
                // 获取相应的值
                String name = pref.getString("name","");
                int age = pref.getInt("age",0);
                boolean married = pref.getBoolean("married",false);
                // 将获取到的值显示
                textView.setText("name is " + name + ",age is "+ age + ",married is "+ married);
            }
        });
    }
}
