package com.wonderful.myfirstcode;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.base.BaseActivity;
import com.wonderful.myfirstcode.chapter3.UIActivity;
import com.wonderful.myfirstcode.chapter2.FirstActivity;
import com.wonderful.myfirstcode.chapter4.simple_news.NewsActivity;
import com.wonderful.myfirstcode.chapter5.BroadcastActivity;
import com.wonderful.myfirstcode.chapter6.PersistentActivity;
import com.wonderful.myfirstcode.chapter7.ContentActivity;
import com.wonderful.myfirstcode.utils.IntentUtils;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.btn_inquiry_activity)
    Button btn_inquiry_activity;

    @BindView(R.id.btn_custom_controls)
    Button btn_custom_controls;

    @BindView(R.id.btn_inquiry_fragment)
    Button btn_inquiry_fragment;

    @BindView(R.id.btn_broadcast)
    Button btn_broadcast;

    @BindView(R.id.btn_persistent)
    Button btn_persistent;

    @BindView(R.id.btn_content_provider)
    Button btn_content_provider;

    @BindView(R.id.btn_multimedia)
    Button btn_multimedia;

    @BindView(R.id.btn_network_technique)
    Button btn_network_technique;

    @BindView(R.id.btn_inquiry_service)
    Button btn_inquiry_service;

    @BindView(R.id.btn_lbs_service)
    Button btn_lbs_service;

    @BindView(R.id.btn_material_design)
    Button btn_material_design;

    @BindView(R.id.btn_advance_technique)
    Button btn_advance_technique;

    @BindView(R.id.btn_enter_combat)
    Button btn_enter_combat;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_inquiry_activity.setOnClickListener(this);
        btn_custom_controls.setOnClickListener(this);
        btn_inquiry_fragment.setOnClickListener(this);
        btn_broadcast.setOnClickListener(this);
        btn_persistent.setOnClickListener(this);
        btn_content_provider.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_inquiry_activity:
                IntentUtils.myIntent(this, FirstActivity.class);
                break;

            case R.id.btn_custom_controls:
                IntentUtils.myIntent(this, UIActivity.class);
                break;

            case R.id.btn_inquiry_fragment:
                //IntentUtils.myIntent(this, FragmentActivity.class);
                IntentUtils.myIntent(this, NewsActivity.class);
                break;

            case R.id.btn_broadcast:
                IntentUtils.myIntent(this, BroadcastActivity.class);
                break;

            case R.id.btn_persistent:
                IntentUtils.myIntent(this, PersistentActivity.class);
                break;

            case R.id.btn_content_provider:
                IntentUtils.myIntent(this, ContentActivity.class);
                break;

            default:
                break;
        }
    }
}
