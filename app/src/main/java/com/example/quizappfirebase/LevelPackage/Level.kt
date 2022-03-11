package com.example.quizappfirebase.LevelPackage

class Level {
    private var level: Int = 0
    private var experiencePoints: Int = 0

    constructor(){}

    fun setLevel(level: Int){
        this.level = level
    }
    fun setExperiencePoints(experiencePoints: Int){
        this.experiencePoints = experiencePoints
    }
    fun getLevel(): Int{
        return this.level
    }
    fun getExperiencePoints(): Int{
        return this.experiencePoints
    }
    fun returnNewLevelAfterCheck(currentUserExperiencePoints: Int, pointsRequireToNewLevelList: List<Int>): Int{

        var newUserLevel: Int = this.level


        if(this.level < pointsRequireToNewLevelList.size -5)
        if (currentUserExperiencePoints >= pointsRequireToNewLevelList[this.level+1] || currentUserExperiencePoints>= pointsRequireToNewLevelList[this.level+2]
            || currentUserExperiencePoints>= pointsRequireToNewLevelList[this.level+3]  || currentUserExperiencePoints>= pointsRequireToNewLevelList[this.level+4]      )
        {
            newUserLevel += 1
        }

        return newUserLevel
    }



}