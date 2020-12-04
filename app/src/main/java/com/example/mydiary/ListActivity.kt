package com.example.mydiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val dbHelper=MyDatabaseHelper(this,"Diary.db",1)
        val db=dbHelper.writableDatabase
        val cursor=db.query("diary",null,null,null,null,null,null)
        val titlelist= ArrayList<String>()
        var title3=" "
        if(cursor.moveToFirst()) {
            do {
                title3 = cursor.getString(cursor.getColumnIndex("title"))
                titlelist.add(title3)
                Log.d("mainactivity",title3.toString())
            } while (cursor.moveToNext())
        }
        val adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,titlelist)
        list1.adapter=adapter

        list1.setOnItemClickListener { parent, view, position, id ->
            val title4=titlelist[position]
            val intent=Intent(this,ReadActivity::class.java)
            intent.putExtra("extra_title",title4)
            startActivity(intent)
        }
        add.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        val dbHelper=MyDatabaseHelper(this,"Diary.db",1)
        val db=dbHelper.writableDatabase
         val cursor=db.query("diary",null,null,null,null,null,null)
        val titlelist= ArrayList<String>()
        var title3=" "
        if(cursor.moveToFirst()) {
            do {
                title3 = cursor.getString(cursor.getColumnIndex("title"))
                titlelist.add(title3)
                Log.d("mainactivity",title3.toString())
            } while (cursor.moveToNext())
        }
        val adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,titlelist)
        list1.adapter=adapter
    }
}
