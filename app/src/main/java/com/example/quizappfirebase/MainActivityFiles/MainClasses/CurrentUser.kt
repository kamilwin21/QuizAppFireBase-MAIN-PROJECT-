package com.example.quizappfirebase.MainActivityFiles.MainClasses

data class CurrentUser(
    var uid: String,
    var email: String,
    var password: String,
    var name: String,
    var surname: String,
    var age: String


) {
    constructor(): this("-1","","","","",""){}

}