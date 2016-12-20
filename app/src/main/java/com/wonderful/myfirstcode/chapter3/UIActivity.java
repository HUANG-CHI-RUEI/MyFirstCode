package com.wonderful.myfirstcode.chapter3;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.chapter3.recycler_view.RecyclerVewActivity;
import com.wonderful.myfirstcode.chapter3.ui_best_practice.UIBestPracticeActivity;
import com.wonderful.myfirstcode.utils.IntentUtils;

public class UIActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_progress_dialog,btn_percent_layout,btn_custom_title,btn_recycler_view,btn_ui_best_practice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);

        initView();
    }

    private void initView() {
        btn_progress_dialog = (Button) findViewById(R.id.btn_progress_dialog);
        btn_percent_layout = (Button) findViewById(R.id.btn_percent_layout);
        btn_custom_title = (Button) findViewById(R.id.btn_custom_title);
        btn_recycler_view = (Button) findViewById(R.id.btn_recycler_view);
        btn_ui_best_practice = (Button) findViewById(R.id.btn_ui_best_practice);
        btn_ui_best_practice.setOnClickListener(this);
        btn_recycler_view.setOnClickListener(this);
        btn_custom_title.setOnClickListener(this);
        btn_progress_dialog.setOnClickListener(this);
        btn_percent_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_progress_dialog:
                ProgressDialog progressDialog = new ProgressDialog(UIActivity.this);
                progressDialog.setTitle("this is ProgressDialog");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);// 设置false则不能通过Back键取消掉
                progressDialog.show();
                break;

            case R.id.btn_percent_layout:
                IntentUtils.myIntent(UIActivity.this,PercentActivity.class);
                break;

            case R.id.btn_custom_title:
                IntentUtils.myIntent(UIActivity.this,CustomTitleActivity.class);
                break;

            case R.id.btn_recycler_view:
                IntentUtils.myIntent(UIActivity.this,RecyclerVewActivity.class);
                break;

            case R.id.btn_ui_best_practice:
                IntentUtils.myIntent(UIActivity.this,UIBestPracticeActivity.class);
                break;
        }
    }
}
