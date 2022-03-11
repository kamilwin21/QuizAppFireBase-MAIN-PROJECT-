package com.example.quizappfirebase.RegistrationAndLoginUser.Classes

import com.example.quizappfirebase.LevelPackage.ExperiencePointsPerLevel
import com.example.quizappfirebase.LevelPackage.Level
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Statics
import java.util.*
import kotlin.collections.ArrayList

data class User(
    var uid: String,
    var email: String,
    var name: String,
    var surname: String,
    var age: String,
    var level:Level = Level(),
    var userStatistics: ArrayList<Statics>?
    //var statistics: ArrayList<Statics>

    ) {

   //constructor():this(String(), String(), String(), String(), String(), String()){}
   constructor():this("-1","","","","", Level(), arrayListOf()){}

}
