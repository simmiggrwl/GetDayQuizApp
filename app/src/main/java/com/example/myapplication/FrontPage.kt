package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FrontPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_front_page)

        val startButton:Button=findViewById(R.id.start)
        startButton.setOnClickListener{
            val intent=Intent(this, Quizcode::class.java)
            startActivity(intent)
        }
    }
}