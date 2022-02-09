package com.example.quizappfirebase.RegistrationAndLoginUser.Classes

import com.example.quizappfirebase.MainActivityFiles.MainClasses.Statics
import java.util.*
import kotlin.collections.ArrayList

data class User(
    var uid: String,
    var email: String,
    var password: String,
    var name: String,
    var surname: String,
    var age: String
    //var statistics: ArrayList<Statics>

    ) {

   //constructor():this(String(), String(), String(), String(), String(), String()){}
   constructor():this("-1","","","","",""/*, arrayListOf<Statics>()*/){}

}
