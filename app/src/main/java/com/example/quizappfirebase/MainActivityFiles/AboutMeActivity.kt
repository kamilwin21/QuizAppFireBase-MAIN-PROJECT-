package com.example.quizappfirebase.MainActivityFiles

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ProgressBar
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

    var currentProgress: Int = 10
    var totalTime = 5000.toLong()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)



        var startBtn: Button = findViewById<Button>(R.id.startTimer)
        var stopBtn: Button = findViewById<Button>(R.id.stopTimer)
        var timeResult: TextView = findViewById(R.id.timer)

        var progressBar = findViewById<ProgressBar>(R.id.progress_bar_timer)


        var animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 100)
        animation.setDuration(10000)
        animation.setInterpolator(DecelerateInterpolator())
        animation.start()
//        var timerObject = ObjectAnimator.ofInt(progressBar,"progress", currentProgress)
//        timerObject.start()


        var timer = object: CountDownTimer(10000, 1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                timer.text = "${millisUntilFinished/1000}"


//                timer.text = "${timerObject.currentPlayTime}"


            }

            override fun onFinish() {
                cancel()
            }

        }.start()

        startBtn.setOnClickListener{

        timer.start()
        animation.start()

//            currentProgress+=10



        }
        stopBtn.setOnClickListener{



        }

        setTimer.setOnClickListener{
            totalTime = 7000


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