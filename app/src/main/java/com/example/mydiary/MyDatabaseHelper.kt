package com.example.mydiary

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper (val context: Context, name:String, version:Int):
SQLiteOpenHelper(context,name,null,version)
{

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table diary(id integer primary key autoincrement,title text,content text,time text)")
        Toast.makeText(context,"Create succeeded",Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}