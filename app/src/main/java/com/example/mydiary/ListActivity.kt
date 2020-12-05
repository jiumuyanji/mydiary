package com.example.mydiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val dbHelper=MyDatabaseHelper(this,"Diary.db",1)
        val db=dbHelper.writableDatabase
        val cursor=db.query("diary",null,null,null,null,null,null)
        val titlelist= ArrayList<String>()
        if(cursor.moveToFirst()) {
            do {
                var title3 = cursor.getString(cursor.getColumnIndex("title"))
                titlelist.add(title3)
            } while (cursor.moveToNext())
        }
        val adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,titlelist)
        list1.adapter=adapter

        list1.setOnItemClickListener { _, _, position, _ ->
            val title4=titlelist[position]
            val intent=Intent(this,ReadActivity::class.java)
            intent.putExtra("extra_title",title4)
            startActivity(intent)
            finish()
        }
        add.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
            finish()
        }
        clean.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("确认删除")
                setMessage("会清除所有日记！")
                setCancelable(false)
                setPositiveButton("ok"){ _, _ ->
                    db.delete("diary",null,null)
                    onRestart()
                }
                setNegativeButton("cancel",null)
                create()
                show()
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        val dbHelper=MyDatabaseHelper(this,"Diary.db",1)
        val db=dbHelper.writableDatabase
        val cursor=db.query("diary",null,null,null,null,null,null)
        val titlelist= ArrayList<String>()
        if(cursor.moveToFirst()) {
            do {
                var title3 = cursor.getString(cursor.getColumnIndex("title"))
                titlelist.add(title3)
            } while (cursor.moveToNext())
        }
        val adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,titlelist)
        list1.adapter=adapter
    }
}
