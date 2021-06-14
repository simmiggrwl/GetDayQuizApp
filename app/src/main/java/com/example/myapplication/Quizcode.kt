package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.*
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date


class Quizcode() : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizcode)

        randomdateoption()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    private fun randomdateoption() {
        val relativeLayout: RelativeLayout = findViewById(R.id.rl)

        var rdate = 0
        val randomyear: TextView = findViewById(R.id.year)
        relativeLayout.setBackgroundResource(R.color.blue)
        val yearselector = (1990..2999).random()
        randomyear.text = yearselector.toString()
        val randommonth: TextView = findViewById(R.id.month)
        val monthselector = (1..12).random()
        if (monthselector in 1..9) {
            randommonth.text = "0$monthselector"
        } else {
            randommonth.text = monthselector.toString()
        }
        val thirtyonemonth = arrayOf(1, 3, 5, 7, 8, 10, 12)
        val thirtymonth = arrayOf(4, 6, 9, 11)
        val randomdate: TextView = findViewById(R.id.date)
        if (monthselector in thirtyonemonth) {
            val dateselector = (1..31).random()
            rdate = dateselector
            if (dateselector in 1..9) {
                randomdate.text = "0$dateselector"
            } else {
                randomdate.text = dateselector.toString()
            }
        } else if (monthselector in thirtymonth) {
            val dateselector = (1..30).random()
            rdate = dateselector
            if (dateselector in 1..9) {
                randomdate.text = "0$dateselector"
            } else {
                randomdate.text = dateselector.toString()
            }
        } else {
            if (yearselector % 400 == 0) {
                val dateselector = (1..29).random()
                rdate = dateselector
                if (dateselector in 1..9) {
                    randomdate.text = "0$dateselector"
                } else {
                    randomdate.text = dateselector.toString()
                }
            } else if (yearselector % 4 == 0 && yearselector % 100 != 0) {
                val dateselector = (1..29).random()
                rdate = dateselector
                if (dateselector in 1..9) {
                    randomdate.text = "0$dateselector"
                } else {
                    randomdate.text = dateselector.toString()
                }
            } else {
                val dateselector = (1..28).random()
                rdate = dateselector
                if (dateselector in 1..9) {
                    randomdate.text = "0$dateselector"
                } else {
                    randomdate.text = dateselector.toString()
                }
            }
        }
        println(rdate)
        println(monthselector)
        println(yearselector)
        val dategenerated: Date = Date(yearselector - 1900, monthselector - 1, rdate)
        println(dategenerated)
        val sdf: SimpleDateFormat = SimpleDateFormat("EEEE")
        val d: Date = dategenerated
        val dayOfTheWeek: String = sdf.format(d)
        println(dayOfTheWeek)
        val option1: Button = findViewById(R.id.option1)
        val option2: Button = findViewById(R.id.option2)
        val option3: Button = findViewById(R.id.option3)
        val option4: Button = findViewById(R.id.option4)
        val options = arrayOf(option1, option2, option3, option4)
        options.shuffle()
        options[0].text = dayOfTheWeek
        val days = arrayOf(
            "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY",
            "SATURDAY", "SUNDAY"
        )
        days.shuffle()

        for (i in 0..2) {
            println(days[i])
            if (days[i] == dayOfTheWeek.toUpperCase()) {
                days[i] = days[6]
                println(days[i])
            }
        }

        options[1].text = days[0]
        options[2].text = days[1]
        options[3].text = days[2]
        val score: TextView = findViewById(R.id.score1)
        var v1 = score.text
        val optionchose = arrayOf(option1, option2, option3, option4)
        for (i in 0..3) {
            optionchose[i].setOnClickListener {
                if (optionchose[i].text == dayOfTheWeek) {
                    vibratephone()
                    relativeLayout.setBackgroundResource(R.color.green)
                    var q = Integer.parseInt(v1.toString());
                    q += 1
                    score.text = q.toString()
                    Handler().postDelayed({
                        randomdateoption()
                    }, 2000)
                } else {
                    vibratephone()
                    relativeLayout.setBackgroundResource(R.color.red)
                    intent = Intent(this, YouLost::class.java).apply {
                        val scorevalue = score.text.toString()
                        println(scorevalue)
                        intent.putExtra("score", scorevalue)
                        println(intent.getStringExtra("score"))
                    }
                    startActivity(intent)
                }
            }
        }
    }

    private fun vibratephone() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            println("yes")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        200,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                vibrator.vibrate(200)
            }
        }
    }
}




