package com.wonderful.myfirstcode.chapter6.sqlite_persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.R;

public class SQLiteActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        // 构建MyDatabaseHelper对象，指定数据库名为"BookStore.db、版本号为1
        //dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
        // 将数据库版本号指定为2
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);

        Button btn_create_database = (Button) findViewById(R.id.btn_create_database);
        btn_create_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建或打开一个现有的数据库（已存在则打开，否则创建一个新的）
                dbHelper.getWritableDatabase();
            }
        });

        // 添加数据
        Button btn_add_data = (Button) findViewById(R.id.btn_add_data);
        btn_add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                // 开始组装第一条数据
                values.put("name","The Da Vinci Code");
                values.put("author","Dan Brown");
                values.put("pages",454);
                values.put("price",16.96);
                db.insert("book",null,values); //插入第一条数据
                values.clear();
                // 开始组装第二条数据
                values.put("name","The Lost Symbol");
                values.put("author","Dan Brown");
                values.put("pages",510);
                values.put("price",19.95);
                db.insert("book",null,values); //插入第二条数据
            }
        });

        // 更新数据
        Button btn_update_data = (Button) findViewById(R.id.btn_update_data);
        btn_update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                // 把名字为 The Da Vinci Code 的这本书的更新成666.66
                values.put("price",666.66);
                db.update("book",values, "name = ?", new String[]{"The Da Vinci Code"});
            }
        });

        // 删除数据
        Button btn_delete_data = (Button) findViewById(R.id.btn_delete_data);
        btn_delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                // 删除页数超过500页的书
                db.delete("book", "pages > ?", new String[]{"500"});
            }
        });

        // 查询数据
        Button btn_query_data = (Button) findViewById(R.id.btn_query_data);
        btn_query_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                // 查询 book 表中所有数据
                Cursor cursor = db.query("book", null,null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do {
                        // 遍历 Cursor 对象，取出数据并打印
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));

                        Log.d("SQLiteActivity_query", "book name is: " + name);
                        Log.d("SQLiteActivity_query", "book author is: " + author);
                        Log.d("SQLiteActivity_query", "book pages are: " + pages);
                        Log.d("SQLiteActivity_query", "book price is: " + price);
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}
