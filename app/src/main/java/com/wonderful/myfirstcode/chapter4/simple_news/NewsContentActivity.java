package com.wonderful.myfirstcode.chapter4.simple_news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wonderful.myfirstcode.R;

public class NewsContentActivity extends AppCompatActivity {

    /**
     * 构建Intent，传递所需数据
     * @param context
     * @param newsTitle
     * @param newsContent
     */
    public static void actionStart(Context context, String newsTitle, String newsContent) {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", newsTitle);
        intent.putExtra("news_content", newsContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        // 获取传入的新闻标题、新闻内容
        String newsTitle = getIntent().getStringExtra("news_title");
        String newsContent = getIntent().getStringExtra("news_content");
        // 获取 NewsContentFragment 实例
        NewsContentFragment newsContentFragment = (NewsContentFragment) getSupportFragmentManager()
                .findFragmentById(R.id.news_content_fragment);
        // 刷新 NewsContentFragment 界面
        newsContentFragment.refresh(newsTitle, newsContent);
    }

}
