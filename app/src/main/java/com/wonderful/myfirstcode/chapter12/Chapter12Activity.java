package com.wonderful.myfirstcode.chapter12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.utils.LogUtils;

public class Chapter12Activity extends AppCompatActivity implements View.OnClickListener{

    Button btn_toolbar, btn_slipping,btn_hover,btn_card,btn_refresh,btn_folding_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter12);

        btn_toolbar = (Button) findViewById(R.id.btn_toolbar);
        btn_slipping = (Button) findViewById(R.id.btn_slipping);
        btn_hover = (Button) findViewById(R.id.btn_hover);
        btn_card = (Button) findViewById(R.id.btn_card);
        btn_refresh = (Button) findViewById(R.id.btn_refresh);
        btn_folding_title = (Button) findViewById(R.id.btn_folding_title);
        btn_toolbar.setOnClickListener(this);
        btn_slipping.setOnClickListener(this);
        btn_hover.setOnClickListener(this);
        btn_card.setOnClickListener(this);
        btn_refresh.setOnClickListener(this);
        btn_folding_title.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_toolbar:
                LogUtils.d("TAG","debug log");
                break;

            case R.id.btn_slipping:
                break;

            case R.id.btn_hover:
                break;

            case R.id.btn_card:
                break;

            case R.id.btn_refresh:
                break;

            case R.id.btn_folding_title:
                break;

            default:
                break;
        }
    }
}
