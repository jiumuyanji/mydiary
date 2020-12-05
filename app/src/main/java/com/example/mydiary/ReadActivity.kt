package com.example.mydiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_read.*


class ReadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)
        val dbHelper=MyDatabaseHelper(this,"Diary.db",1)
        var extraData=intent.getStringExtra("extra_title")
        val db = dbHelper.writableDatabase
        val selectionArgs = arrayOf(extraData.toString())
        val cursor =db.query("diary",null,"title=?",selectionArgs,null,null,null,null)
        if(cursor.moveToFirst())
        {
            title2.text=extraData.toString()
            content2.text=cursor.getString(cursor.getColumnIndex("content"))
            time2.text=cursor.getString(cursor.getColumnIndex("time"))

        }
        back2.setOnClickListener {
            val intent= Intent(this,ListActivity::class.java)
            startActivity(intent)
            finish()
        }
        delete2.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("确认删除")
                setMessage("会删除所有与当前的日记同名的日记！")
                setCancelable(false)
                setPositiveButton("ok"){_, _->
                    db.delete("diary","title=?", arrayOf(extraData.toString()))
                }
                setNegativeButton("cancel",null)
                create()
                show()
            }
        }
    }
}
