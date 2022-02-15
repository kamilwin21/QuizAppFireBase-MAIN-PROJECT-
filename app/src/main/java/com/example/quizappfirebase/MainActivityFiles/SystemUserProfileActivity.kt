package com.example.quizappfirebase.MainActivityFiles

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizappfirebase.MainActivityFiles.Adapters.UserProfileAdapter
import com.example.quizappfirebase.MainActivityFiles.FunctionalClasses.SortingList
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Statics
import com.example.quizappfirebase.R
import com.example.quizappfirebase.RegistrationAndLoginUser.Classes.User
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_system_user_profile2.*
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList
import kotlin.streams.toList

class SystemUserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system_user_profile2)





        if (intent.hasExtra("userName") && intent.hasExtra("userSurname") &&
            intent.hasExtra("userId") && intent.hasExtra("userEmail") &&
            intent.hasExtra("userAge") && intent.hasExtra("userPassword"))
        {

            val userName: String = intent.getStringExtra("userName").toString()
            val userSurname: String = intent.getStringExtra("userSurname").toString()
            val userUid: String = intent.getStringExtra("userId").toString()
            val userEmail: String  = intent.getStringExtra("userEmail").toString()
            val userAge: String  = intent.getStringExtra("userAge").toString()
            val userPassword: String  = intent.getStringExtra("userPassword").toString()

            val currentUserProfile: User = User(userUid, userEmail, userPassword, userName,
                                            userSurname, userAge)

            tw_userName.text = currentUserProfile.name
            tw_userSurname.text = currentUserProfile.surname
            tw_userAge.text = currentUserProfile.age
            tw_userEmail.text = currentUserProfile.email


            var query: DatabaseReference = FirebaseDatabase.getInstance("https://quizfirebase-4cb19-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference("Users").child(currentUserProfile.uid).child("Statistics")

            query.addValueEventListener(object : ValueEventListener{
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists())
                    {
                        var userStatics: ArrayList<Statics> = arrayListOf()

                        for (static in snapshot.children)
                        {
                            var userStatic = static.getValue(Statics::class.java)
                            userStatics.add(userStatic!!)


                        }
                        var newUserStatics: List<Statics> = arrayListOf()
                        userStatics.sortWith(Comparator { o1, o2 -> o2!!.pointsReceived?.toInt() - o1?.pointsReceived!!.toInt() })



                        recycler_view_user_profile.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
                        recycler_view_user_profile.adapter = UserProfileAdapter(applicationContext,
                            userStatics
                        )


//                        for (i in userStatics)
//                        {
//                            println(":::::::::::::::::::::::::::::::::::::::::")
//                            println("${i.id}")
//                            println("${i.quizName}")
//                            println("${i.pointsReceived}")
//
//                            println(":::::::::::::::::::::::::::::::::::::::::")
//                        }

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        }









    }
}