package com.wonderful.myfirstcode.chapter7.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.wonderful.myfirstcode.chapter6.sqlite_persistence.MyDatabaseHelper;

public class DataBaseProvider extends ContentProvider {

    public static final int BOOK_DIR = 0; //访问 book 表中的所有数据
    public static final int BOOK_ITEM = 1;//访问 book 表中的单条数据
    public static final int CATEGORY_DIR = 3;
    public static final int CATEGORY_ITEM = 4;

    public static final String AUTHORITY = "com.wonderful.myfirstcode.chapter7.provider";

    private static UriMatcher uriMatcher;

    private MyDatabaseHelper dbHelper;

    static {
        // 创建 UriMatcher 实例
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 调用 addURI() 方法，此方法接收3个参数：authority、path、自定义代码
        uriMatcher.addURI(AUTHORITY,"book",BOOK_DIR);
        uriMatcher.addURI(AUTHORITY,"book/#",BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY,"category",CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY,"category/#",CATEGORY_ITEM);
    }

    /**
     * 初始化内容提供器
     */
    @Override
    public boolean onCreate() {
        // 创建 MyDatabaseHelper 实例
        dbHelper = new MyDatabaseHelper(getContext(),"BookStore.db",null,2);
        // 返回true表示完成了创建或升级数据库
        return true;
    }

    /**
     * 查询数据
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                // 查询 book 表中的所有数据
                cursor = db.query("book",projection,selection,selectionArgs,
                        null,null,sortOrder);
                break;

            case BOOK_ITEM:
                // 查询 book 表中的单条数据
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("book",projection,"id = ?",new String[]{bookId},
                        null,null,sortOrder);
                break;

            case CATEGORY_DIR:
                cursor = db.query("category",projection,selection,selectionArgs,
                        null,null,sortOrder);
                break;

            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query("category",projection,"id = ?",new String[]
                        {categoryId}, null,null,sortOrder);
                break;

            default:
                break;
        }
        return cursor;
    }

    /**
     * 添加数据
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert("book",null,values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/book" + newBookId);
                break;

            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId = db.insert("category",null,values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/category" +
                        newCategoryId);
                break;

            default:
                break;
        }
        return uriReturn;
    }

    /**
     * 更新数据
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int updatedRows = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                updatedRows = db.update("book",values,selection,selectionArgs);
                break;

            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updatedRows = db.update("book",values,"id = ?",new String[]{bookId});
                break;

            case CATEGORY_DIR:
                updatedRows = db.update("category",values,selection,selectionArgs);
                break;

            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updatedRows = db.update("category",values,"id = ?",new String[]
                        {categoryId});
                break;

            default:
                break;
        }
        return updatedRows;
    }

    /**
     * 删除数据
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int deletedRows = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                deletedRows = db.delete("book",selection,selectionArgs);
                break;

            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deletedRows = db.delete("book","id = ?",new String[]{bookId});
                break;

            case CATEGORY_DIR:
                deletedRows = db.delete("category",selection,selectionArgs);
                break;

            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deletedRows = db.delete("category","id = ?",new String[]
                        {categoryId});
                break;

            default:
                break;
        }
        return deletedRows;
    }

    /**
     * 获取 Uri 对象所对应的 MIME 类型
     */
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.wonderful.myfirstcode." +
                        "chapter7.provider.book";

            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.wonderful.myfirstcode." +
                        "chapter7.provider.book";

            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.wonderful.myfirstcode." +
                        "chapter7.provider.category";

            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.wonderful.myfirstcode." +
                        "chapter7.provider.category";
        }
        return null;
    }

}
