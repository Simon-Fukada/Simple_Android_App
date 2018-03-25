package com.example.a772515.SimpleAndroidApp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
   /*Author: Simon Fukada
      Date: Fall 2018
      Purpose: DBHelper class that extends SQLiteOpenHelper in order to create or upgrade SQLiteDatabase
     */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "agents.db";
    private static final int DBVERSION = 1;

    public DBHelper(Context context)
    {
        super(context, DBNAME, null, DBVERSION);
    }
    //Creates SQLlite database with tables and data
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table agents " +
                "(agentId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "AgtFirstName TEXT, " +
                "AgtBusPhone TEXT, " +
                "AgtEmail TEXT);";
        db.execSQL(sql);
        sql = "INSERT INTO agents (AgtFirstName, AgtBusPhone, AgtEmail) VALUES ('Fred Smith', '403-555-3254', 'fSmith@eamil.com')";
        db.execSQL(sql);
        sql = "INSERT INTO agents (AgtFirstName, AgtBusPhone, AgtEmail) VALUES ('Jane Doe', '403-555-5698', 'Jdoe@eamil.com')";
        db.execSQL(sql);
        sql = "INSERT INTO agents (AgtFirstName, AgtBusPhone, AgtEmail) VALUES ('Joe Jones', '403-555-7745', 'JJones@eamil.com')";
        db.execSQL(sql);
        sql = "INSERT INTO agents (AgtFirstName, AgtBusPhone, AgtEmail) VALUES ('Kim Samantha', '403-555-5523', 'kSam@eamil.com')";
        db.execSQL(sql);
        sql = "INSERT INTO agents (AgtFirstName, AgtBusPhone, AgtEmail) VALUES ('Eric Bob', '403-555-1234', 'EBob@eamil.com')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS agents");
        onCreate(db);
    }
}
