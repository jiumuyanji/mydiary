package com.example.mydiary

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_write.*

class WriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)
        val dbHelper=MyDatabaseHelper(this,"Diary.db",1)
        save1.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1= ContentValues().apply{
                put("title",title1.text.toString())
                put("content",content1.text.toString())
                put("time",time1.text.toString())
            }
            db.insert("diary",null,values1)
        }
        back1.setOnClickListener {
            finish()
        }
    }
}
