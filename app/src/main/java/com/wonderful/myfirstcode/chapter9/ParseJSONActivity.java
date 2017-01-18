package com.wonderful.myfirstcode.chapter9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.http.HttpUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ParseJSONActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_object,btn_gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_json);

        btn_object = (Button) findViewById(R.id.btn_object);
        btn_gson = (Button) findViewById(R.id.btn_gson);

        btn_object.setOnClickListener(this);
        btn_gson.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_object:
                HttpUtil.sendOKHttpRequest("http://10.0.2.2/get_data.json", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                          String responseData = response.body().string();
                        parseJSONWithJSONObject(responseData);   // 用 JSONObject 解析
                    }
                });
                break;

            case R.id.btn_gson:
                HttpUtil.sendOKHttpRequest("http://10.0.2.2/get_data.json", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        parseJSONWithGSON(responseData);  // 用 GSON 解析
                    }
                });
                break;
        }

    }

    /**
     * 用 JSONObject 解析
     * @param jsonData 需要解析的数据
     */
    private void parseJSONWithJSONObject(String jsonData) {
        try {
            // 把需要解析的数据传入到 JSONArray 对象中
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String sex = jsonObject.getString("sex");
                Log.d("JSONObject解析", "id is "+id);
                Log.d("JSONObject解析", "name is "+name);
                Log.d("JSONObject解析", "sex is "+sex);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *  用 GSON 解析
     * @param jsonData
     */
    private void parseJSONWithGSON(String jsonData){
        Gson gson = new Gson();
        List<Student>studentList = gson.fromJson(jsonData,new TypeToken<List<Student>>(){}.getType());
        for (Student student:studentList){
            Log.d("GSON解析", "id is "+student.getId());
            Log.d("GSON解析", "name is "+student.getName());
            Log.d("GSON解析", "sex is "+student.getSex());
        }
    }
}
