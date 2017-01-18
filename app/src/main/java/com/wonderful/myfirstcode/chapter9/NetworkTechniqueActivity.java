package com.wonderful.myfirstcode.chapter9;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.utils.IntentUtils;

public class NetworkTechniqueActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_http, btn_xml,btn_json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_technique);

        btn_http = (Button) findViewById(R.id.btn_http);
        btn_xml = (Button) findViewById(R.id.btn_xml);
        btn_json = (Button) findViewById(R.id.btn_json);


        btn_http.setOnClickListener(this);
        btn_xml.setOnClickListener(this);
        btn_json.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_http:
                IntentUtils.myIntent(this, HttpActivity.class);
                break;

            case R.id.btn_xml:
                IntentUtils.myIntent(this, ParseXMLActivity.class);
                break;

            case R.id.btn_json:
                IntentUtils.myIntent(this, ParseJSONActivity.class);
                break;


            default:
                break;
        }
    }
}
