package com.wonderful.myfirstcode.chapter6.sqlite_persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库帮助类
 * Created by KXwon on 2016/12/16.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    // Book表的建表语句
    private static final String CREATE_BOOK = "create table book("
            +"id integer primary key autoincrement,"
            +"author text,"
            +"price real,"
            +"pages integer,"
            +"name text)";

    // Category表的建表语句
    private static final String CREATE_CATEGORY = "create table category("
            +"id integer primary key autoincrement,"
            +"category_name text,"
            +"category_code integer)";

    private Context mContext;

    /**
     * 构造方法
     * @param context
     * @param name 数据库名
     * @param factory 允许我们在查询数据的时候返回一个自定义的 Cursor，一般都是传入 null
     * @param version 当前数据库的版本号，可用于对数据库进行升级操作
     */
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    /**
     * 创建数据库
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 执行建表语句
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        //ToastUtils.showShort("创建成功");
    }

    /**
     * 升级数据库
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 若发现数据库中已存在 book 表或 category 表，将这两张表删除掉
        db.execSQL("drop table if exists book");
        db.execSQL("drop table if exists category");
        // 重新创建表
        onCreate(db);
    }
}
