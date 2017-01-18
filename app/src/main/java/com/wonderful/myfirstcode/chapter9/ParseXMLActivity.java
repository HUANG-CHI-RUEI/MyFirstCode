package com.wonderful.myfirstcode.chapter9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.http.HttpUtil;
import com.wonderful.myfirstcode.utils.ToastUtils;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ParseXMLActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_pull,btn_sax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_xml);

        btn_pull = (Button) findViewById(R.id.btn_pull);
        btn_sax = (Button) findViewById(R.id.btn_sax);

        btn_pull.setOnClickListener(this);
        btn_sax.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_pull:
                HttpUtil.sendOKHttpRequest("http://10.0.2.2/get_data.xml", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //ToastUtils.showShort("请求失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        parseXMLWithPull(responseData);  // pull 解析
                    }
                });
                break;

            case R.id.btn_sax:
                HttpUtil.sendOKHttpRequest("http://10.0.2.2/get_data.xml", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //ToastUtils.showShort("请求失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        parseXMLWithSAX(responseData);  // sax 解析
                    }
                });
                break;

        }

    }

    /**
     * pull 解析
     * @param xmlData 要解析的xml数据
     */
    private void parseXMLWithPull(String xmlData) {
        try {
            // 1. 获取 XmlPullParserFactory 实例
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            // 2. 借助 XmlPullParserFactory 实例得到 XmlPullParser 对象
            XmlPullParser xmlPullParser = factory.newPullParser();
            // 3. 调用 setInput() 方法设置xml数据
            xmlPullParser.setInput(new StringReader(xmlData));
            // 4. 获取当前的解析事件
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String sex = "";
            // 5. 通过 while 循环不断地进行解析
            while (eventType != XmlPullParser.END_DOCUMENT){
                String nodeName = xmlPullParser.getName();
                switch (eventType){
                    // 开始解析某个节点
                    case XmlPullParser.START_TAG:
                        if ("id".equals(nodeName)){
                            id = xmlPullParser.nextText();
                        }else if ("name".equals(nodeName)){
                            name = xmlPullParser.nextText();
                        }else if ("sex".equals(nodeName)){
                            sex = xmlPullParser.nextText();
                        }
                        break;

                    // 完成解析某个节点
                    case  XmlPullParser.END_TAG:
                        if ("student".equals(nodeName)){
                            Log.d("pull解析：", "id is" + id);
                            Log.d("pull解析：", "name is" + name);
                            Log.d("pull解析：", "sex is" + sex);
                        }
                        break;

                    default:
                        break;
                }
                // 获取下一个解析事件
                eventType = xmlPullParser.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * sax 解析
     * @param xmlData
     */
    private void parseXMLWithSAX(String xmlData){
        try {
            // 创建 SAXParserFactory 对象
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // 获取 XMLReader 对象
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler handler = new ContentHandler();
            // 将 ContentHandler 的实例设置到 XMLReader 中
            xmlReader.setContentHandler(handler);
            // 开始执行解析
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
