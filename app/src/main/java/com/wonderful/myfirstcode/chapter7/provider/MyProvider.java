package com.wonderful.myfirstcode.chapter7.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * 自己的内容提供器
 * Created by KXwon on 2016/12/18.
 */

public class MyProvider extends ContentProvider{

    public static final int TABLE1_DIR = 0; //访问 table1 表中的所有数据
    public static final int TABLE1_ITEM = 1;//访问 table1 表中的单条数据
    public static final int TABLE2_DIR = 3; //访问 table2 表中的所有数据
    public static final int TABLE2_ITEM = 4;//访问 table2 表中的单条数据

    private static UriMatcher uriMatcher;

    static {
        // 创建 UriMatcher 实例
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 调用 addURI() 方法，此方法接收3个参数：authority、path、自定义代码
        uriMatcher.addURI("com.example.app.provider","table1",TABLE1_DIR);
        uriMatcher.addURI("com.example.app.provider","table1/#",TABLE1_ITEM);
        uriMatcher.addURI("com.example.app.provider","table2",TABLE2_DIR);
        uriMatcher.addURI("com.example.app.provider","table2/#",TABLE2_ITEM);
    }

    // 初始化内容提供器的时候调用，返回true表示成功，false失败
    @Override
    public boolean onCreate() {
        return false;
    }

    // 从内容提供器中查询数据
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                // 查询 table1 表中的所有数据
                break;
            case TABLE1_ITEM:
                // 查询 table1 表中的单条数据
                break;
            case TABLE2_DIR:
                // 查询 table2 表中的所有数据
                break;
            case TABLE2_ITEM:
                // 查询 table2 表中的单条数据
                break;
            default:
                break;
        }
        return null;
    }

   // 向内容提供器中添加一条数据
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    // 从内容提供器中删除数据
    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    // 更新内容提供器中已有的数据
    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }

    // 根据传入的内容 URI 来返回 MIME 类型
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.table1";
            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table1";
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.table2";
            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table2";
            default:
                break;
        }
        return null;
    }
}
