package com.example.quizappfirebase.MainActivityFiles.MainClasses

data class Category(
    var id:Long,
    var categoryName: String,
    val questions: List<Question>,
) {
    constructor(): this(-1, String(), arrayListOf()){

    }



}