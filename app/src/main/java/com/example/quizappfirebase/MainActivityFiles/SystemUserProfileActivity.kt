package com.example.quizappfirebase.MainActivityFiles

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizappfirebase.LevelPackage.ExperiencePerLevel
import com.example.quizappfirebase.LevelPackage.Level
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
            intent.hasExtra("userId") && intent.hasExtra("userEmail"))
        {


            val userName: String = intent.getStringExtra("userName").toString()
            val userSurname: String = intent.getStringExtra("userSurname").toString()
            val userUid: String = intent.getStringExtra("userId").toString()
            val userEmail: String  = intent.getStringExtra("userEmail").toString()
            val userAge: String  = intent.getStringExtra("userAge").toString()
            val userLevel: Level = Level()
            userLevel.setLevel(intent.getIntExtra("userLevel",-1))
            userLevel.setExperiencePoints(intent.getIntExtra("userExperiencePoints", -1))

            //val userPassword: String  = intent.getStringExtra("userPassword").toString()

            val currentUserProfile: User = User(userUid, userEmail, userName,
                                            userSurname, userAge, userLevel, null
            )

            tw_userName.text = currentUserProfile.name
            tw_userSurname.text = currentUserProfile.surname
            tw_userAge.text = currentUserProfile.age
            tw_userEmail.text = currentUserProfile.email
            user_level_at_user_profile.text = currentUserProfile.level.getLevel().toString()
            user_experience_points_at_user_profile.text = "${currentUserProfile.level.getExperiencePoints()} / ${ExperiencePerLevel.experiencePerLevel[currentUserProfile.level.getLevel()+1]}"

            var totalScoreToReachNextLevel: Int = ExperiencePerLevel.experiencePerLevel[currentUserProfile.level.getLevel()+1] - ExperiencePerLevel.experiencePerLevel[currentUserProfile.level.getLevel()]
            var userCurrentExperiencePoints: Int = ExperiencePerLevel.experiencePerLevel[currentUserProfile.level.getLevel()+1] - currentUserProfile.level.getExperiencePoints()
            progress_bar_timer_user_profile.max = totalScoreToReachNextLevel
            progress_bar_timer_user_profile.progress = totalScoreToReachNextLevel - userCurrentExperiencePoints


           // progress_bar_timer_user_profile.progress =


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




                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        }









    }
}