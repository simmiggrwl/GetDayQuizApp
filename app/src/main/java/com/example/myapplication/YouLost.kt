package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class YouLost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_you_lost)
        val yourscore: TextView=findViewById(R.id.finalscore1)
        intent = getIntent()
        println(intent.getStringExtra("score"))
        val scorev= intent.getStringExtra("score")
        println(scorev)
        yourscore.text=scorev.toString()
        val tryButton: Button = findViewById(R.id.tryagain)
        tryButton.setOnClickListener{
            val intent= Intent(this,Quizcode::class.java)
            startActivity(intent)
        }
    }
}