package com.example.quizappfirebase.MainActivityFiles.MainClasses

import com.example.quizappfirebase.MainActivityFiles.MainInterfaces.IQuestion

class Question: IQuestion  {
    override var id: Long = -1
    override var title: String = String()
    override var correctAnswer: String = String()
    override var answer1: String = String()
    override var answer2: String = String()
    override var answer3: String = String()
    override var answer4: String = String()

    constructor(id: Long, title:String, correctAnswer: String, answer1:String, answer2:String, answer3:String, answer4:String ){
        this.id = id
        this.title = title
        this.correctAnswer = correctAnswer
        this.answer1 = answer1
        this.answer2 = answer2
        this.answer3 = answer3
        this.answer4 = answer4
    }
    constructor(){}
}