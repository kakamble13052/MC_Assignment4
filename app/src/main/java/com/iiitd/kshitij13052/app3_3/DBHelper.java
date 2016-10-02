package com.iiitd.kshitij13052.app3_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Dr ASK on 10/2/2016.
 */
public class DBHelper extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME = "Information.db";
    public static final String TABLE_NAME = "info";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table info " + "(id integer primary key, name text,age text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS info");
        onCreate(sqLiteDatabase);
    }

    public boolean insertInfo(String name, String age)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("age", age);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean deleteInfo ()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("info", "id = (select max(id) from info)", null);
        return true;
    }


    public ArrayList<String> getAllNames()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from info", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllAges()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from info", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_AGE)));
            res.moveToNext();
        }
        return array_list;
    }

}
