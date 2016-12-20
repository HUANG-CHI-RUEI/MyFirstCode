package com.wonderful.myfirstcode.chapter7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.chapter6.file_persistence.FilePersistenceActivity;
import com.wonderful.myfirstcode.chapter6.pref_persistence.SharePreferencesActivity;
import com.wonderful.myfirstcode.chapter7.permission.CallPhoneActivity;
import com.wonderful.myfirstcode.chapter7.provider.ReadContactActivity;
import com.wonderful.myfirstcode.utils.IntentUtils;

public class ContentActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_permission, btn_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        btn_permission = (Button) findViewById(R.id.btn_permission);
        btn_content = (Button) findViewById(R.id.btn_content);

        btn_permission.setOnClickListener(this);
        btn_content.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_permission:
                IntentUtils.myIntent(this, CallPhoneActivity.class);
                break;

            case R.id.btn_content:
                IntentUtils.myIntent(this, ReadContactActivity.class);
                break;


            default:
                break;
        }
    }
}
