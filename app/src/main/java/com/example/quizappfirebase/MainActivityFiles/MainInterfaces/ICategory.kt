package com.example.quizappfirebase.MainActivityFiles.MainInterfaces

import com.example.quizappfirebase.MainActivityFiles.MainClasses.Question

interface ICategory {
    var id: Long;
    var categoryName: String;
    var questions: List<Question>
}