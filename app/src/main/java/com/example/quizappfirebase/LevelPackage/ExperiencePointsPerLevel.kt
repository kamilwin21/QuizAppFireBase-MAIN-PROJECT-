package com.example.quizappfirebase.LevelPackage

data class ExperiencePointsPerLevel(
    private var experiencePointsPerLevel: ArrayList<Int> = arrayListOf()
) {


    fun getExperiencePointsPerLevel(): ArrayList<Int>{
        return this.experiencePointsPerLevel
    }
    fun setExperiencePointsPerLevel(experiencePointsPerLevel: ArrayList<Int>){
        this.experiencePointsPerLevel = experiencePointsPerLevel
    }
    fun getPointsPerLevelAtIndex(index: Int): Int{
        return this.experiencePointsPerLevel[index]
    }
    fun setPointsPerLevelAtIndex(index: Int){
        this.experiencePointsPerLevel[index]
    }


}