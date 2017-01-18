package com.wonderful.myfirstcode.chapter9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.http.HttpCallbackListener;
import com.wonderful.myfirstcode.http.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button send_url_request,send_okHttp_request,clear_content;
    private TextView response_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        response_text = (TextView) findViewById(R.id.response_text);

        send_url_request = (Button) findViewById(R.id.send_url_request);
        send_okHttp_request = (Button) findViewById(R.id.send_okHttp_request);
        clear_content = (Button) findViewById(R.id.clear_content);
        send_url_request.setOnClickListener(this);
        send_okHttp_request.setOnClickListener(this);
        clear_content.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_url_request:
                sendRequestWithHttpURLConnection();

            case R.id.send_okHttp_request:
                sendRequestWithOKHttp();

            case R.id.clear_content:
                showResponse(""); //清空数据
        }
    }

    /**
     *  OKHttp 发送请求
     */
    private void sendRequestWithOKHttp() {
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    // 1. 创建 OkHttpClient 实例
                    OkHttpClient client = new OkHttpClient();
                    // 2. 创建 Request 对象
                    Request request = new Request.Builder().url("http://www.baidu.com").build();
                    // 3. 调用 OkHttpClient 的 newCall() 方法来创建 Call 对象
                    Response response = client.newCall(request).execute();
                    // 4. 获取返回的内容
                    String responseData = response.body().string();
                    showResponse(responseData); // 显示请求结果
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();*/
        HttpUtil.sendOKHttpRequest("http://www.baidu.com", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                 // 在这里对异常情况进行处理
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 得到服务器返回的具体内容
                String responseData = response.body().string();
                showResponse(responseData);
            }
        });
    }

    /**
     *  HttpURLConnection 发送请求
     */
    private void sendRequestWithHttpURLConnection() {
        /*// 开启线程来发送网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try{
                    URL url = new URL("http://www.baidu.com");
                    // 1. 获取 HttpURLConnection 实例
                    connection = (HttpURLConnection) url.openConnection();
                    // 2. 设置请求方法
                    connection.setRequestMethod("GET");
                    // 3. 自由定制，如设置连接超时、读取超时等
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    // 4. 获取服务器返回的输入流
                    InputStream in = connection.getInputStream();
                    // 下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine())!= null){
                        response.append(line);
                    }
                    showResponse(response.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (reader != null){
                        try{
                            reader.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if (connection != null){
                        // 5.把 HTTP 连接关掉
                        connection.disconnect();
                    }
                }
            }
        }).start();*/

        HttpUtil.sendHttpRequest("http://www.baidu.com", new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                // 在这里根据返回内容执行具体的逻辑
                showResponse(response);
            }

            @Override
            public void onError(Exception e) {
                // 在这里对异常情况进行处理
            }
        });
    }

    /**
     * 显示请求结果
     * @param response
     */
    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI 操作，将结果显示到界面上
                response_text.setText(response);
            }
        });
    }
}
