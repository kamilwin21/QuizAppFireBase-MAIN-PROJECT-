package com.example.quizappfirebase.MainActivityFiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.quizappfirebase.R
import kotlinx.android.synthetic.main.activity_about_me.*

class AboutMeActivity : AppCompatActivity() {
    var totalTime = 5000.toLong()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)

        var startBtn: Button = findViewById<Button>(R.id.startTimer)
        var stopBtn: Button = findViewById<Button>(R.id.stopTimer)
        var timeResult: TextView = findViewById(R.id.timer)



        var timer1 = object : CountDownTimer(5000, 1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                timer.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                timer.text = "Koniec odliczania"
                println("Koniec")
                cancel()
            }

        }
        var timer2 = object : CountDownTimer(8000, 1000)
        {
            override fun onTick(millisUntilFinished: Long) {

                timer.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                timer.text = "Koniec odliczania"
                println("Koniec")
                cancel()
            }

        }
        var timer3 = object : CountDownTimer(12000, 1000)
        {
            override fun onTick(millisUntilFinished: Long) {

                timer.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                timer.text = "Koniec odliczania"
                println("Koniec")
                cancel()
            }

        }

        startBtn.setOnClickListener{


            timer1.start()
            timer2.cancel()
            timer3.cancel()

        }
        stopBtn.setOnClickListener{


            timer1.cancel()
            timer2.start()
            timer3.cancel()

           //timer.onFinish()
        }

        setTimer.setOnClickListener{
            totalTime = 7000

            timer1.cancel()
            timer2.cancel()
            timer3.start()
        }

    }

    private var startButtonListener = View.OnClickListener {



    }
}