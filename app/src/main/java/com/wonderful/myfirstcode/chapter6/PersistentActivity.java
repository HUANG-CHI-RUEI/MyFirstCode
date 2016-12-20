package com.wonderful.myfirstcode.chapter6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.chapter6.file_persistence.FilePersistenceActivity;
import com.wonderful.myfirstcode.chapter6.litepal_persistence.LitePalActivity;
import com.wonderful.myfirstcode.chapter6.pref_persistence.SharePreferencesActivity;
import com.wonderful.myfirstcode.chapter6.sqlite_persistence.SQLiteActivity;
import com.wonderful.myfirstcode.utils.IntentUtils;

public class PersistentActivity extends AppCompatActivity implements View.OnClickListener {


    Button btn_file, btn_preference, btn_sq_lite, btn_lite_pal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persistent);

        btn_file = (Button) findViewById(R.id.btn_file);
        btn_preference = (Button) findViewById(R.id.btn_preference);
        btn_sq_lite = (Button) findViewById(R.id.btn_sq_lite);
        btn_lite_pal = (Button) findViewById(R.id.btn_lite_pal);

        btn_file.setOnClickListener(this);
        btn_preference.setOnClickListener(this);
        btn_sq_lite.setOnClickListener(this);
        btn_lite_pal.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_file:
                IntentUtils.myIntent(this, FilePersistenceActivity.class);
                break;

            case R.id.btn_preference:
                IntentUtils.myIntent(this, SharePreferencesActivity.class);
                break;

            case R.id.btn_sq_lite:
                IntentUtils.myIntent(this, SQLiteActivity.class);
                break;

            case R.id.btn_lite_pal:
                IntentUtils.myIntent(this, LitePalActivity.class);
                break;

            default:
                break;
        }
    }
}
