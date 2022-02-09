package com.example.quizappfirebase.MainActivityFiles.MainClasses

data class Quiz(
    var id:Long,
    var questions: List<Question>

) {
    constructor(): this(-1, arrayListOf())

    //var id: Int = -1
   // var questions: List<Question> = arrayListOf()


}