package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DBhelper extends SQLiteOpenHelper {


    private static  final int DB_VERSION=1;
    private  static  final String DB_NAME="my_database.db";
    private  static  final String COLUMN1="id";
    private  static  final String COLUMN2="name";
    private  static  final String COLUMN3="email";



    public DBhelper(@Nullable Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//       create table command
        String query ="CREATE TABLE student (id integer primary key ,name text,email text)";
        db.execSQL(query);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS student");
        onCreate(db);

    }

//    for insert data
    public boolean insertData(String id,String name,String email)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN1,id);
        values.put(COLUMN2,name);
        values.put(COLUMN3,email);

        long check=db.insert("student",null,values);

        if (check==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }


    }



//    for view all data

    public Cursor getAllData()
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor result;
        result=db.rawQuery("SELECT * FROM STUDENT",null);
        return result;
    }


    public ArrayList<HashMap<String,String>> getData() {
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM STUDENT", null);

        if (result.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", result.getString(0));
                map.put("name", result.getString(1));
                map.put("email", result.getString(2));
                data.add(map);

            } while (result.moveToNext());
        }

        db.close();
        return data;

    }


}
