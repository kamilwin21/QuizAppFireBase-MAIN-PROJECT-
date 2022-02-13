package com.example.quizappfirebase.MainActivityFiles

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Question
import com.example.quizappfirebase.R
import kotlinx.android.synthetic.main.activity_about_me.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
import kotlin.random.Random.Default.nextInt
import kotlin.streams.toList

import kotlin.random.Random

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
    private fun randQuestion(questionList:ArrayList<Question>): ArrayList<Question>{
        var randQuestionList: ArrayList<Question> = arrayListOf()
        var hashSet: HashSet<Question> = HashSet()
        //randQuestionList = questionList

        for (i in 0 until questionList.size)
        {
            var randId: Int = Random.nextInt(0, questionList.size)
            randQuestionList.add(questionList[randId])

        }
        hashSet = randQuestionList.toHashSet()
        var helpList: ArrayList<Question> = arrayListOf()


        if(hashSet.size < questionList.size)
        {
            while (hashSet.size < questionList.size)
            {
                var randomId: Int = Random.nextInt(0, questionList.size)
                hashSet.add(questionList[randomId])
            }
        }

        for (hs in hashSet)
        {
            helpList.add(hs)
        }
        randQuestionList = arrayListOf()
        randQuestionList = helpList


        return randQuestionList
    }


    private fun rand(): Set<Int>{
        var size: Int = 6
        var list: ArrayList<Int> = arrayListOf(1,0,4,5,2,3)
        var helpList: ArrayList<Int> = arrayListOf()
        var set:MutableSet<Int> = mutableSetOf()
        println("In rand()")

        //var number: Int = Random().ints(0,size).distinct().toString().toInt()

        for(i in list)
        {
            var randId = Random.nextInt(0, list.size)
            helpList.add(list[randId])
        }


        set = helpList.toMutableSet()

        while (set.size < list.size)
        {
            var randId = Random.nextInt(0, list.size)
            set.add(list[randId])
        }

        for (i in helpList)
        {
            println(i)
        }
        println()
        println("===============================")




        return set
    }
}