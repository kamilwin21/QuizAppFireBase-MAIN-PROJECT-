package com.example.quizappfirebase.MainActivityFiles.MainClasses

import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.text.BoringLayout
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.quizappfirebase.MainActivityFiles.ResultsQuizActivity
import com.example.quizappfirebase.R
import kotlinx.android.synthetic.main.activity_question.*
import java.util.*
import java.util.logging.Handler
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule
import kotlin.coroutines.coroutineContext
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class MyOwnButtonClickListener : View.OnClickListener {
    var currentIdQuestion: Int = 0
    var textViewList: ArrayList<TextView> = arrayListOf()
    var questionsList: ArrayList<Question> = arrayListOf()
    var counterCorrectQuestion: Int = 0
    var questionName: String = ""
    var checkExists: Boolean = false
    var userStatic: Statics = Statics()
    var timerTextView: TextView
    var timer: Long= 0.toLong()
    var questionIsActive: Boolean = false
    var timeCountDownTimer: CountDownTimer? = null
    var clickListener: MyOwnButtonClickListener? = null
//    var clickListener: MyOwnButtonClickListener? = MyOwnButtonClickListener(this.currentIdQuestion, this.textViewList, this.questionsList,
//                                this.counterCorrectQuestion, this.questionName, this.checkExists, this.userStatic, this.textViewList[5])



    var checkTimer: Boolean = false



    constructor(currentIdQuestion: Int, textViewList: ArrayList<TextView>, questionsList: ArrayList<Question>,
                counterCorrectQuestion: Int, questionName: String, checkExists: Boolean,
                userStatic:Statics, timerTextView: TextView,
                clickListener: MyOwnButtonClickListener

    ){
        this.currentIdQuestion = currentIdQuestion
        this.textViewList = textViewList
        this.questionsList = questionsList
        this.counterCorrectQuestion = counterCorrectQuestion
        this.questionName = questionName
        this.checkExists = checkExists
        this.userStatic = userStatic
        this.timerTextView = timerTextView
        this.timeCountDownTimer = timeCountDownTimer
        this.clickListener = clickListener






    }
    constructor(currentIdQuestion: Int, textViewList: ArrayList<TextView>, questionsList: ArrayList<Question>,
                counterCorrectQuestion: Int, questionName: String, checkExists: Boolean,
                userStatic:Statics, timerTextView: TextView

    ){
        this.currentIdQuestion = currentIdQuestion
        this.textViewList = textViewList
        this.questionsList = questionsList
        this.counterCorrectQuestion = counterCorrectQuestion
        this.questionName = questionName
        this.checkExists = checkExists
        this.userStatic = userStatic
        this.timerTextView = timerTextView







    }


    override fun onClick(v: View?) {

        println("ClickListener Object: ${this.clickListener} ")

//        if (this.clickListener!!.currentIdQuestion!! > this.currentIdQuestion)
//        {
//            this.currentIdQuestion = clickListener?.currentIdQuestion!! + 1
//        }


        println("INDEKS: ${currentIdQuestion+1}")
        currentIdQuestion++


        if (currentIdQuestion <= questionsList.size)
        {

            counterCorrectQuestion = countCorrectUserAnswer(v, questionsList[currentIdQuestion-1].correctAnswer, counterCorrectQuestion)

        }

        //Do something if user click on view(This example is textView with choose answer in question)
        mySetXmlParametersOnClickListener(v)




        if (currentIdQuestion < questionsList.size)
        {
            showQuestions(questionsList, currentIdQuestion)


        }else if (currentIdQuestion == questionsList.size){
            goToResultsQuizActivity(v, counterCorrectQuestion, questionsList.size, questionName, checkExists,userStatic)


        }




    }



    private fun setIntervalTimer(v:View?,textView: TextView? , questionisActivate: Boolean): CountDownTimer{

        var counterQuestion:Int = 0

        val timer = object : CountDownTimer(5000, 1000)
        {
            override fun onTick(millisUntilFinished: Long) {

                var counter = millisUntilFinished / 1000
                timer = counter
                timerTextView.text = counter.toString()


            }

            override fun onFinish() {

                if (v?.hasOnClickListeners() == false )
                {


                }


            }

        }


        return timer

    }

    private fun goToResultsQuizActivity(v: View?,pointsReceived: Int, totalPoints: Int, quizName: String, checkExisits: Boolean, userStatic:Statics){
        //Function allows user go to ResultsQuizActivity
        val intentResultsQuizActivity = Intent(v!!.context.applicationContext, ResultsQuizActivity::class.java)
        var pointsReceivedString: String = pointsReceived.toString()
        intentResultsQuizActivity.putExtra("points_received", pointsReceivedString)
        intentResultsQuizActivity.putExtra("total_points", totalPoints)
        intentResultsQuizActivity.putExtra("quiz_name", quizName)
        intentResultsQuizActivity.putExtra("exists", checkExisits)
        intentResultsQuizActivity.putExtra("userStaticID", userStatic.id)
        intentResultsQuizActivity.putExtra("userStaticQuizName", userStatic.quizName)
        intentResultsQuizActivity.putExtra("userStaticPointsReceived", userStatic.pointsReceived)
        intentResultsQuizActivity.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        v!!.context.applicationContext.startActivity(intentResultsQuizActivity)
    }

    private fun countCorrectUserAnswer(v: View?, correctAnswer: String, pointsReceived: Int): Int{
        //Counter correctUserAnswers

        var counter = pointsReceived
        if (v!!.findViewById<TextView>(v!!.id).text == correctAnswer)
        {
            counter++
        }
        return counter
    }

    //Funkcja do przerobienia. Źle losuje niewłaściwe pytania
//    private fun incorrectAnswer(textViewList: ArrayList<TextView>, correctAnswer: String):TextView?{
//        var incorrectAnswerList: ArrayList<TextView> = arrayListOf()
//        var incorrect: TextView? = null
//
//        var id: Int = 0
//        while (textViewList[id].text.trim().toString() != correctAnswer.trim() && id < 2 )
//        {
//            incorrectAnswerList.add(textViewList[id])
//            id++
//        }
////        for(i in 1..4)
////        {
////            if (textViewList[i].text.trim().toString() != correctAnswer)
////            {
////               incorrectAnswerList.add(textViewList[i])
////               // incorrect = textViewList[i]
////
////            }
////        }
//
//        println("===========================================================")
////        println("Niewłaściwa odpowiedź 1: ${incorrectAnswerList[0].text}")
////        println("Niewłaściwa odpowiedź 2: ${incorrectAnswerList[1].text}")
////        println("Niewłaściwa odpowiedź 3: ${incorrectAnswerList[2].text}")
//        println("Nieprawidłowa odpowiedź: ${incorrectAnswerList[0].text}")
//        println("Prawidłowa odpowiedź: ${correctAnswer}")
//        //println("Niewłaściwa odpowiedź 4: ${incorrectAnswerList[3].text}")
//        println("===========================================================")
//
//
//       // var randomId: Int = Random.nextInt(0,2)
//
//
//
//
//        return incorrectAnswerList[0]
//        //return incorrect
//    }

    private fun showQuestions(questionsList: ArrayList<Question>, id: Int){
        //Show current question in QuestionActivity
        if (id==1)
        {

        }else if (id >=0)
        {

//            textViewList[0].text = questionsList[id+1].title
//            textViewList[1].text = questionsList[id+1].answer1
//            textViewList[2].text = questionsList[id+1].answer2
//            textViewList[3].text = questionsList[id+1].answer3
//            textViewList[4].text = questionsList[id+1].answer4
        }
        textViewList[0].text = questionsList[id].title
        textViewList[1].text = questionsList[id].answer1
        textViewList[2].text = questionsList[id].answer2
        textViewList[3].text = questionsList[id].answer3
        textViewList[4].text = questionsList[id].answer4



    }
    private fun mySetXmlParametersOnClickListener(v: View?){
        //change xml parameters if user clicked on something view like button or text_view
        v!!.background = ContextCompat.getDrawable(v!!.context.applicationContext, R.drawable.radius2)
        Timer("Set_xml_value", false).schedule(250){
            v!!.background = ContextCompat.getDrawable(v!!.context.applicationContext, R.drawable.radius)
        }
        //Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackClick = false },2000)
    }

    private fun setDefaultXMLOption(v: View?)
    {
        v!!.background = ContextCompat.getDrawable(v!!.context.applicationContext, R.drawable.radius)
    }


}