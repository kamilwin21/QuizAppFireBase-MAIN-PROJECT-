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
import org.w3c.dom.Text
import java.util.*
import java.util.logging.Handler
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
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
    var listOfUserAnswers: ArrayList<String> = arrayListOf()




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

        println("INDEKS: ${currentIdQuestion+1}")
        currentIdQuestion++

        if (currentIdQuestion <= questionsList.size)
        {
            this.listOfUserAnswers.add(v!!.findViewById<TextView>(v!!.id).text.toString())
            println("Odpowiedzi użytkownika: ${this.listOfUserAnswers.toString()}")
        }

        //Do something if user click on view(This example is textView with choose answer in question)
        if (v?.findViewById<TextView>(v!!.id) == textViewList[0])
        {
            setDefaultXMLOption(v)
        }else{
            mySetXmlParametersOnClickListener(v)
        }



        if (currentIdQuestion < questionsList.size)
        {
            showQuestions(questionsList, currentIdQuestion)


        }else if (currentIdQuestion == questionsList.size){
            counterCorrectQuestion = countCorrectUserAnswerWithList(questionsList, listOfUserAnswers)
            goToResultsQuizActivity(v, counterCorrectQuestion, questionsList.size, questionName, checkExists,userStatic)


        }


    }


    fun randAnswers(question:Question): Question{
        var randomHelpList: ArrayList<String> = arrayListOf(
            question.answer1,
            question.answer2,
            question.answer3,
            question.answer4
        )


        var randomIdList: ArrayList<Int> = arrayListOf(0,1,2,3)
        var helpList: ArrayList<Int> = arrayListOf()
        var mutableSet: MutableSet<Int> = mutableSetOf()

        for (id in 0 until randomIdList.size)
        {
            var randId: Int = Random.nextInt(0, randomIdList.size)
            helpList.add(randomIdList[randId])

        }

        mutableSet = helpList.toMutableSet()

        if(mutableSet.size < randomIdList.size)
        {
            while (mutableSet.size < randomIdList.size)
            {
                var randomId: Int = Random.nextInt(0, randomIdList.size)
                mutableSet.add(randomIdList[randomId])
            }
        }
        randomIdList = arrayListOf()

        for (hs in mutableSet)
        {
            randomIdList.add(hs)
        }

        var randQuestionList: Question = Question()
        randQuestionList.answer1 = randomHelpList[randomIdList[0]]
        randQuestionList.answer2 = randomHelpList[randomIdList[1]]
        randQuestionList.answer3 = randomHelpList[randomIdList[2]]
        randQuestionList.answer4 = randomHelpList[randomIdList[3]]

        for (i in randomIdList)
        {
            println("Random List Id: ${i}")
        }














        return randQuestionList
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

    private fun countCorrectUserAnswerWithList(questionsList: ArrayList<Question>, userAnswers: ArrayList<String>): Int{
        //Counter correctUserAnswers
        println("QuestionList: ${questionsList.size}")
        println("UserAnswers: ${userAnswers.size}")
        var counter: Int = 0

        println("=======================================================")
        println("Pytania:")
        for (q in 0 until questionsList.size)
        {
            println("Pytanie: ${questionsList[q].title}")
            println("Odpowiedź: ${questionsList[q].answer1}")
            println("Odpowiedź: ${questionsList[q].answer2}")
            println("Odpowiedź: ${questionsList[q].answer3}")
            println("Odpowiedź: ${questionsList[q].answer4}")
            println("Poprawna odpowiedź: ${questionsList[q].correctAnswer}")
        }

        for (q in 0 until questionsList.size)
        {
            if (questionsList[q].correctAnswer == userAnswers[q])
            {
                counter++
            }
        }



        return counter
    }

    private fun showQuestions(questionsList: ArrayList<Question>, id: Int){
        //Show current question in QuestionActivity

        var randomAnswers: Question = randAnswers(questionsList[id])

        textViewList[0].text = questionsList[id].title
        textViewList[1].text = randomAnswers.answer1
        textViewList[2].text = randomAnswers.answer2
        textViewList[3].text = randomAnswers.answer3
        textViewList[4].text = randomAnswers.answer4


//        textViewList[0].text = questionsList[id].title
//        textViewList[1].text = questionsList[id].answer1
//        textViewList[2].text = questionsList[id].answer2
//        textViewList[3].text = questionsList[id].answer3
//        textViewList[4].text = questionsList[id].answer4



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
        v!!.background = ContextCompat.getDrawable(v!!.context.applicationContext, R.drawable.radius_default)
    }


}