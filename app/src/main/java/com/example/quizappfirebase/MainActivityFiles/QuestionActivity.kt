package com.example.quizappfirebase.MainActivityFiles

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.ColorFilter
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.quizappfirebase.LevelPackage.Level
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Category
import com.example.quizappfirebase.MainActivityFiles.MainClasses.MyOwnButtonClickListener
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Question
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Statics
import com.example.quizappfirebase.R
import com.example.quizappfirebase.RegistrationAndLoginUser.Classes.User
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_about_me.*
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.activity_system_user_profile2.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.concurrent.schedule
import kotlin.random.Random
//===================================================================================

class QuestionActivity : AppCompatActivity() {
    private var doubleBackClick:Boolean = false
    var totalTimeOnQestion: Long = 25000 // 1000 = 1 seconds, 25000 = 25 seconds
    var currentProgress = 0
    var sizeOfQustionInQuest: Int = 3 // Count of question shows for user in one quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)



            //Properties of Progress Bar in questions
            //===================================================================================
            val progressBarTimer = findViewById<ProgressBar>(R.id.progress_bar_timer)
            var animation = ObjectAnimator.ofInt(progressBarTimer, "progress", 100,0)

            progressBarTimer.progressTintList = ColorStateList.valueOf(Color.parseColor("#CD038350"))

            animation.duration = totalTimeOnQestion
            animation.interpolator = DecelerateInterpolator()
            animation.start()

            //===================================================================================







            //Add connection from database and will read the questions here and will show it here
            //===================================================================================
            if (intent.hasExtra("question_category") )
            {



                val questionName: String = intent.getStringExtra("question_category").toString()
                val checkExisits: Boolean = intent.getBooleanExtra("exists", false)
                val idStatic:String = intent.getStringExtra("idStatic").toString()
                val quizNameStatic: String = intent.getStringExtra("quizNameStatic").toString()
                val pointsReceivedStatic: String = intent.getStringExtra("pointsReceivedStatic").toString()
                val userStatic: Statics = Statics(idStatic,quizNameStatic,pointsReceivedStatic)
                val userLevel: Level = Level()
                userLevel.setLevel(intent.getIntExtra("level",-1))
                userLevel.setExperiencePoints(intent.getIntExtra("points",-1))



                read_questions_in_category_from_database(object :MyCallback{
                    override fun onCallback(list: ArrayList<Question>) {

                        if (list.isEmpty())
                            {
                                Toast.makeText(applicationContext,"Nie udało się wczytać pytań", Toast.LENGTH_LONG).show()
                            }else{
                            //var newRandomQuestionList: ArrayList<Question> = randomQuestionListOnCreateActivity(list)
                                var randList: ArrayList<Question> = randQuestion(list)

                            for(q in randList)
                            {
                                println("===================================")
                                println("Długość tablicy: [${randList.size}]")
                                println("Pytanie: ${q.title}")
                                println("Answer: ${q.answer1}")
                                println("Answer: ${q.answer2}")
                                println("Answer: ${q.answer3}")
                                println("Answer: ${q.answer4}")
                                println("Correct Answer: ${q.correctAnswer}")
                                println("===================================")
                            }
                            println(":::::::::::::::::::::::::::::::::::::::::::::::::::::")






                            var listOfTextView:ArrayList<TextView> = arrayListOf (
                                tw_activity_question_question,
                                tw_activity_question_answer_1,
                                tw_activity_question_answer_2,
                                tw_activity_question_answer_3,
                                tw_activity_question_answer_4,
                                questionTimer
                            )



                            var currentIdQuestion: Int = 0
                                var counterCorrectQuestion: Int = 0



//                                var clickListener = MyOwnButtonClickListener(currentIdQuestion,listOfTextView,newRandomQuestionList,counterCorrectQuestion,
//                                    questionName,checkExisits,userStatic, questionTimer)

                           // println("Randomowana lista: ${newRandomQuestionList}")






                                var myOwnClickListener = MyOwnButtonClickListener(currentIdQuestion,listOfTextView,randList,counterCorrectQuestion,
                                                        questionName,checkExisits,userStatic, questionTimer, progressBarTimer, currentProgress, animation,
                                                        userLevel
                                                        )

                            showQuestion(randList, currentIdQuestion, myOwnClickListener)

                            var timerCountDownTimer: CountDownTimer = setIntervalTimer(listOfTextView[0], myOwnClickListener,progressBarTimer, animation)
                            var timerCountDownTimer1: CountDownTimer = setIntervalTimer(listOfTextView[0], myOwnClickListener,progressBarTimer, animation)
                            var timerCountDownTimer2: CountDownTimer = setIntervalTimer(listOfTextView[0], myOwnClickListener,progressBarTimer, animation)
                            var timerCountDownTimer3: CountDownTimer = setIntervalTimer(listOfTextView[0], myOwnClickListener,progressBarTimer, animation)
                            var timerCountDownTimer4: CountDownTimer = setIntervalTimer(listOfTextView[0], myOwnClickListener,progressBarTimer, animation)
                            timerCountDownTimer.start()


                           // var timer: CountDownTimer = setIntervalTimer(listOfTextView[0], )
                                //var timer: CountDownTimer = myOwnClickListener.setIntervalTimer(true)
                              //  timer.start()




                                tw_activity_question_answer_1.setOnClickListener{
                                    timerCountDownTimer.start()
                                    timerCountDownTimer1.cancel()
                                    timerCountDownTimer2.cancel()
                                    timerCountDownTimer3.cancel()
                                    timerCountDownTimer4.cancel()
                                    myOwnClickListener.onClick(tw_activity_question_answer_1)



                                    println("ID W QUESTION ACTIVITY ${myOwnClickListener.currentIdQuestion}")

                                }
                                //tw_activity_question_answer_1.setOnClickListener(myOwnClickListener)



                                //tw_activity_question_answer_2.setOnClickListener(myOwnClickListener)

                                tw_activity_question_answer_2.setOnClickListener{
                                    timerCountDownTimer.cancel()
                                    timerCountDownTimer1.start()
                                    timerCountDownTimer2.cancel()
                                    timerCountDownTimer3.cancel()
                                    timerCountDownTimer4.cancel()
                                    myOwnClickListener.onClick(tw_activity_question_answer_2)


                                    println("ID W QUESTION ACTIVITY ${myOwnClickListener.currentIdQuestion}")


                                }
                                //tw_activity_question_answer_3.setOnClickListener(myOwnClickListener)

                                tw_activity_question_answer_3.setOnClickListener{
                                    timerCountDownTimer.cancel()
                                    timerCountDownTimer1.cancel()
                                    timerCountDownTimer2.start()
                                    timerCountDownTimer3.cancel()
                                    timerCountDownTimer4.cancel()
                                    myOwnClickListener.onClick(tw_activity_question_answer_3)



                                    println("ID W QUESTION ACTIVITY ${myOwnClickListener.currentIdQuestion}")

                                }
                                //tw_activity_question_answer_4.setOnClickListener(myOwnClickListener)

                                tw_activity_question_answer_4.setOnClickListener{
                                    timerCountDownTimer.cancel()
                                    timerCountDownTimer1.cancel()
                                    timerCountDownTimer2.cancel()
                                    timerCountDownTimer3.start()
                                    timerCountDownTimer4.cancel()
                                    myOwnClickListener.onClick(tw_activity_question_answer_4)



                                    println("ID W QUESTION ACTIVITY ${myOwnClickListener.currentIdQuestion}")


                                }





                         }

                    }
                }, connection_from_database())


            }else{
                Toast.makeText(applicationContext,"Nie udało się załadować pytań", Toast.LENGTH_LONG).show()
            }



    }
    private fun randQuestion(questionList:ArrayList<Question>): ArrayList<Question>{
        var randQuestionList: ArrayList<Question> = arrayListOf()
        var hashSet: HashSet<Question> = HashSet()


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

        for(i in 0 until sizeOfQustionInQuest)
        {
            randQuestionList.add(helpList[i])
        }

       // randQuestionList = helpList




        return randQuestionList
    }





    private fun setIntervalTimer(textView: TextView?, myOwnButtonClickListener: MyOwnButtonClickListener, progressBar: ProgressBar, animation: ObjectAnimator): CountDownTimer{


        val timer = object : CountDownTimer(totalTimeOnQestion, 1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                var currentTime= animation.getAnimatedValue("progress").toString().toInt()

                var counter = millisUntilFinished / 1000
                if(currentTime in 21..45)
                {
                    progressBar.progressTintList = ColorStateList.valueOf(Color.parseColor("#CDFF920C"))
                }else if (currentTime <= 15)
                {
                    progressBar.progressTintList = ColorStateList.valueOf(Color.parseColor("#CDAF1010"))
                }

                println("ANIMATION: ${currentTime}")
                questionTimer.text = counter.toString()



            }

            override fun onFinish() {

                progressBar.progressTintList = ColorStateList.valueOf(Color.parseColor("#CD038350"))

                animation.start()

                cancel()
                println("Koniec zewnętrzne")
                if (myOwnButtonClickListener.currentIdQuestion <= myOwnButtonClickListener.questionsList.size)
                {
                    myOwnButtonClickListener.onClick(textView)
                    if (myOwnButtonClickListener.currentIdQuestion < myOwnButtonClickListener.questionsList.size)
                    {
                        start()
                    }
                }



            }

        }


        return timer

    }



    override fun onBackPressed() {
        if (doubleBackClick)
        {
            super.onBackPressed()
            return
        }
        doubleBackClick= true
        Toast.makeText(applicationContext,"Na pewno chcesz wyjsć z quizu?", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackClick = false },2000)


    }






    private fun showQuestion(randomQuestionsList: ArrayList<Question>, id: Int, myOwnButtonClickListener: MyOwnButtonClickListener){

        var randomAnswers= myOwnButtonClickListener.randAnswers(randomQuestionsList[id])

        tw_activity_question_question.text = randomQuestionsList[id].title
        tw_activity_question_answer_1.text = randomAnswers.answer1
        tw_activity_question_answer_2.text = randomAnswers.answer2
        tw_activity_question_answer_3.text = randomAnswers.answer3
        tw_activity_question_answer_4.text = randomAnswers.answer4
//        tw_activity_question_question.text = randomQuestionsList[id].title
//        tw_activity_question_answer_1.text = randomQuestionsList[id].answer1
//        tw_activity_question_answer_2.text = randomQuestionsList[id].answer2
//        tw_activity_question_answer_3.text = randomQuestionsList[id].answer3
//        tw_activity_question_answer_4.text = randomQuestionsList[id].answer4

    }


    private fun connection_from_database():DatabaseReference{

        var categoryId = intent.getLongExtra("id",-1)
        var databaseConnection = FirebaseDatabase.getInstance("https://quizfirebase-4cb19-default-rtdb.europe-west1.firebasedatabase.app/")
            .reference.child("Categories").child(categoryId.toString()).child("questions")
        return databaseConnection
    }

    interface MyCallback {
        fun onCallback(list: ArrayList<Question>)
    }
    private fun read_questions_in_category_from_database(myCallback: MyCallback,databaseConnection: DatabaseReference) {

        databaseConnection.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    var count = 0
                    var list: ArrayList<Question> = arrayListOf()

                    var question: Question?
                    for (h in snapshot.children) {
                        count++
                        question = h.getValue(Question::class.java)
                        list.add(question!!)


                    }

                    myCallback.onCallback(list)

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })


    }




}
