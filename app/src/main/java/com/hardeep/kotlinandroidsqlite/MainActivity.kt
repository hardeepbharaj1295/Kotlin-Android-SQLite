package com.hardeep.kotlinandroidsqlite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName: EditText = findViewById(R.id.etName)
        val addText: Button = findViewById(R.id.btnAddToDb)
        val showText: Button = findViewById(R.id.btnShowDatafromDb)
        val tvDisplayName: TextView = findViewById(R.id.tvDisplayName)

        addText.setOnClickListener {
            val dbHandler = DBOpenHelper(this, null)
            val user = Name(etName.text.toString())
            dbHandler.addName(user)
            Toast.makeText(this, etName.text.toString() + "Added to database", Toast.LENGTH_LONG).show()
        }
        showText.setOnClickListener {
            tvDisplayName.text = ""
            val dbHandler = DBOpenHelper(this, null)
            val cursor = dbHandler.getAllName()
            cursor!!.moveToFirst()
            tvDisplayName.append((cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME))))
            while (cursor.moveToNext()) {
                tvDisplayName.append((cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME))))
                tvDisplayName.append("\n")
            }
            cursor.close()
        }
    }
}
