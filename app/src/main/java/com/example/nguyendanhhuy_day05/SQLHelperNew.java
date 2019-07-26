package com.example.nguyendanhhuy_day05;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLHelperNew extends SQLiteOpenHelper {
    final static String DB_NAME = "Login";
    final static int DB_VERSION = 1;
    final static String DB_TBNAME = "User";
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;

    public SQLHelperNew(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String querySQL = "CREATE TABLE User (" +
                "username TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "PRIMARY KEY(username)" +
                ");";

        sqLiteDatabase.execSQL(querySQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ DB_TBNAME);
            onCreate(sqLiteDatabase);
        }
    }

    public void onInsert(String username, String password){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put("username", username);
        contentValues.put("password", password);

        sqLiteDatabase.insert(DB_TBNAME, null, contentValues);
    }

    public List<User> getAllProductAdvanced(){
        List<User> userList = new ArrayList<>();
        User user;

        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.query(false, DB_TBNAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            user = new User(username, password);
            userList.add(user);
        }
        return userList;
    }
}
