package com.example.quizappfirebase.MainActivityFiles.MainClasses

import com.example.quizappfirebase.LevelPackage.Level

data class CurrentUser(
    var uid: String,
    var email: String,
    var name: String,
    var surname: String,
    var age: String,
    var level: Level


) {
    constructor(): this("-1","","","","", Level()){}

}