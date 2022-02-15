package com.example.quizappfirebase.MainActivityFiles.FunctionalClasses

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Question
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Statics
import java.util.*
import java.util.stream.Collector
import kotlin.collections.ArrayList
import kotlin.concurrent.fixedRateTimer
import kotlin.streams.toList

class SortingList {

    var arrayList: ArrayList<Statics>? = null

    constructor(arrayList: ArrayList<Statics>)
    {
        this.arrayList = arrayList
    }

    fun reverseSort(): Unit
    {

            return this.arrayList!!.sortWith(compareBy { it.pointsReceived })
    }

}