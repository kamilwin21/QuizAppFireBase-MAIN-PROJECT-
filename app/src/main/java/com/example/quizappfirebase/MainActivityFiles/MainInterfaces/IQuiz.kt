package com.example.quizappfirebase.MainActivityFiles.MainInterfaces

import com.example.quizappfirebase.MainActivityFiles.MainClasses.Question

interface IQuiz {
    var id: Long;
    var name: String;
    var categories: String;
    var questions: List<Question>


}