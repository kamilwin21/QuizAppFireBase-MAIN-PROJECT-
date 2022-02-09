package com.example.quizappfirebase.MainActivityFiles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TestLooperManager
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Statics
import com.example.quizappfirebase.R
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_results_quiz.*
import java.util.*
import kotlin.collections.ArrayList

class ResultsQuizActivity : AppCompatActivity() {
    private lateinit var TOTALSCOREFORCURRENTQUIZ: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_results_quiz)


        setValueForTextViews()
        btn_return_activity_results_quiz.setOnClickListener(listenerToSaveDataInDataBaseFireBase)

       // btn_return_activity_results_quiz.setOnClickListener(MyOnClickListener)
//            btn_return_activity_results_quiz.setOnClickListener {
//                val randomId = Date().time
//                val userStatics: Statics = Statics(randomId.toString(),intent.getStringExtra("quiz_name").toString(), TOTALSCOREFORCURRENTQUIZ )
//                val saveUserStatisticsInFireBase = FirebaseDatabase.getInstance("https://quizfirebase-4cb19-default-rtdb.europe-west1.firebasedatabase.app/")
//                    .getReference("Users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("Statistics").child(randomId.toString())
//                saveUserStatisticsInFireBase.setValue(userStatics)
//
//            }

    }

    private fun setValueForTextViews(){

        val twNameQuiz: TextView = tw_name_quiz_in_activity_results_quiz
        val twPointsToReceived: TextView = tw_points_received_quiz_in_activity_results_quiz
        val twTotalPointsToReceived:TextView = tw_total_points_to_received_quiz_in_activity_results_quiz
        val btnRetun: Button = btn_return_activity_results_quiz
        if (intent.hasExtra("points_received") && intent.hasExtra("total_points")
            && intent.hasExtra("quiz_name"))
        {

            twTotalPointsToReceived.text = intent.getIntExtra("total_points", 0).toString()
            twPointsToReceived.text = intent.getStringExtra("points_received").toString()
            TOTALSCOREFORCURRENTQUIZ = twPointsToReceived.text.toString()
            twNameQuiz.text = intent.getStringExtra("quiz_name").toString()
        }else{
            val nodata: String = "Brak danych"
            twTotalPointsToReceived.text = nodata
            twPointsToReceived.text = nodata
            twNameQuiz.text = nodata
        }

    }


    private val listenerToSaveDataInDataBaseFireBase:View.OnClickListener = View.OnClickListener{
        val randomId: Long = Date().time
//        val query: Query = FirebaseDatabase.getInstance("https://quizfirebase-4cb19-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
//            .child(FirebaseAuth.getInstance().currentUser!!.uid).child("Statistics").orderByChild("quizName").equalTo(intent.getStringExtra("quiz_name").toString())




        if (intent.hasExtra("exists"))
        {
            if (!intent.getBooleanExtra("exists", false))
            {
                val userStatics: Statics = Statics(randomId.toString(),intent.getStringExtra("quiz_name").toString(), TOTALSCOREFORCURRENTQUIZ )
                //if current user statistics are not exists, will add new value of points received
                val saveUserStatisticsInFireBase: DatabaseReference = FirebaseDatabase.getInstance("https://quizfirebase-4cb19-default-rtdb.europe-west1.firebasedatabase.app/")
                    .getReference("Users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("Statistics").child(randomId.toString())
                saveUserStatisticsInFireBase.setValue(userStatics)

                println("EXISTS: FALSE")
            }else if (intent.getBooleanExtra("exists",false))
            {
                val userStaticID = intent.getStringExtra("userStaticID").toString()
                val userStaticQuizName = intent.getStringExtra("userStaticQuizName").toString()
                val userStaticPointsReceive = intent.getStringExtra("userStaticPointsReceived").toString()
                var newPointsReceived: Int = userStaticPointsReceive.toInt() + TOTALSCOREFORCURRENTQUIZ.toInt()
                val userStatic: Statics = Statics(userStaticID, userStaticQuizName, newPointsReceived.toString())

                val saveUserStatisticsInFireBase: DatabaseReference = FirebaseDatabase.getInstance("https://quizfirebase-4cb19-default-rtdb.europe-west1.firebasedatabase.app/")
                    .getReference("Users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("Statistics").child(userStaticID)
                saveUserStatisticsInFireBase.setValue(userStatic)
                println("RESULTS ACTIVITY: ${userStatic}")

                println("EXISTS: TRUE")




            }



        }else{

        }

        startActivity(Intent(applicationContext,MainActivity::class.java))




    }


}



