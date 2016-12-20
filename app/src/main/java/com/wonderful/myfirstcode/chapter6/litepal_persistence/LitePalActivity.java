package com.wonderful.myfirstcode.chapter6.litepal_persistence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.R;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class LitePalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_pal);

        // 创建数据库
        Button btn_create_database = (Button) findViewById(R.id.btn_create_database);
        btn_create_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connector.getDatabase();
            }
        });

        // 添加数据
        Button btn_add_data = (Button) findViewById(R.id.btn_add_data);
        btn_add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建 Book 实例
                Book book = new Book();
                // 设置数据
                book.setName("第一行代码");
                book.setAuthor("郭霖");
                book.setPages(570);
                book.setPrice(79.00);
                book.setPress("人民邮电出版社");
                // 添加数据
                book.save();
            }
        });

        /*// 更新数据
        Button btn_update_data = (Button) findViewById(R.id.btn_update_data);
        btn_update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setName("第一行代码");
                book.setAuthor("郭霖");
                book.setPages(570);
                book.setPrice(79.00);
                book.setPress("人民邮电出版社");
                book.save();
                // 修改书的价格
                book.setName("第二行代码");
                book.setPrice(88.88);
                book.save();
            }
        });*/

        // 更新数据
        Button btn_update_data = (Button) findViewById(R.id.btn_update_data);
        btn_update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setName("第二行代码");
                book.setPrice(88.88);
                book.updateAll("name = ? and author = ?","第一行代码","郭霖");
            }
        });

        // 删除数据
        Button btn_delete_data = (Button) findViewById(R.id.btn_delete_data);
        btn_delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 删除 Book 表中价格低于15的书
                DataSupport.deleteAll(Book.class, "price < ?", "15");
            }
        });

        // 查询数据
        Button btn_query_data = (Button) findViewById(R.id.btn_query_data);
        btn_query_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 查询 Book 表
                List<Book> books = DataSupport.findAll(Book.class);
                // 打印 Book 表中的信息
                for (Book book: books){
                    Log.d("LitePalActivity_query", "book name is: " + book.getName());
                    Log.d("LitePalActivity_query", "book author is: " + book.getAuthor());
                    Log.d("LitePalActivity_query", "book pages are: " + book.getPages());
                    Log.d("LitePalActivity_query", "book price is: " + book.getPrice());
                    Log.d("LitePalActivity_query", "book press is: " + book.getPress());
                }

            }
        });
    }
    // 查询 Book 表中第11-20跳满足页数大于450这个条件的 name、author 和 pages 这3列数据，
    // 并将查询结果按照页数升序排序
    List<Book> books = DataSupport.select("name","author","pages")
            .where("pages > ?","450")
            .order("pages")
            .limit(10)
            .offset(10)
            .find(Book.class);
}
