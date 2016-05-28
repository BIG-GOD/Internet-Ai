package com.example.eason.navigation_fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by eason on 2016-04-27.
 */
public class Db extends SQLiteOpenHelper {
    private static Db instance=null;
    private final static String DB_NAME="my_info";
    private final static int VERSION =1;

    public Db(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, 1);
    }

    public static Db getInstance(Context context)
    {
        if (instance==null)
        {
            instance=new Db(context,DB_NAME,null,VERSION);
        }
        return instance;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("drop table if exists history");
        db.execSQL("CREATE TABLE history("+
                "historyid INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "temprature TEXT,"+
                "humidity TEXT,"+
                "updatetime TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
