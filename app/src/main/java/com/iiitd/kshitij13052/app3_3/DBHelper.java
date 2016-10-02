package com.iiitd.kshitij13052.app3_3;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
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
    Context context = App3_3.getContext();
    public static final String PART = "Database";
    public static final String NAME = "Name";
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
        SharedPreferences settings = context.getSharedPreferences(PART, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString(NAME, DATABASE_NAME);
        editor.commit();


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS info");
        onCreate(sqLiteDatabase);
    }

    public boolean insertInfo(String name, String age)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("age", age);
        db.insert(TABLE_NAME, null, cv);
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
        ArrayList<String> names = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur =  db.rawQuery( "select * from info", null );
        cur.moveToFirst();

        while(cur.isAfterLast() == false){
            names.add(cur.getString(cur.getColumnIndex(COLUMN_NAME)));
            cur.moveToNext();
        }
        return names;
    }

    public ArrayList<String> getAllAges()
    {
        ArrayList<String> ages = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur =  db.rawQuery( "select * from info", null );
        cur.moveToFirst();

        while(cur.isAfterLast() == false){
            ages.add(cur.getString(cur.getColumnIndex(COLUMN_AGE)));
            cur.moveToNext();
        }
        return ages;
    }

}
