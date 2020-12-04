package com.example.mydiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbHelper=MyDatabaseHelper(this,"Diary.db",1)
        begin.setOnClickListener {
            dbHelper.writableDatabase
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
    }
}
