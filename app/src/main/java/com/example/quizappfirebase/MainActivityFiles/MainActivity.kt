package com.example.quizappfirebase.MainActivityFiles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.quizappfirebase.LevelPackage.ExperiencePerLevel
import com.example.quizappfirebase.LevelPackage.Level
import com.example.quizappfirebase.MainActivityFiles.Fragments.CategoriesFragment
import com.example.quizappfirebase.MainActivityFiles.Fragments.MainPageFragment

import com.example.quizappfirebase.MainActivityFiles.Fragments.UsersFragment
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Category
import com.example.quizappfirebase.MainActivityFiles.MainClasses.CurrentUser
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Question
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Quiz
import com.example.quizappfirebase.R
import com.example.quizappfirebase.RegistrationAndLoginUser.Classes.User
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.layout_nav_menu_profile.*
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    private var mainPageFragment = MainPageFragment()
    private var usersFragment = UsersFragment()
    private var categoriesFragment = CategoriesFragment()
    private var doubleBackClick: Boolean = false
//    lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startFragment(categoriesFragment)

        var dataTimeId = Date().time

        var questions: List<Question> = listOf(
            Question(0, "2 + 5","7","7", "10" , "-4", "2"),
            Question(1, "10 - 6" , "4", "4", "5" ,"12", "8"),
            Question(2, "11 + 6" , "17", "17", "3", "2", "7"),
            Question(3, "11 + 6" , "17", "17", "3", "2", "7")

        )
        var questions2: List<Question> = listOf(
            Question(0, "Ile państw należy do Europy?", "30", "30", "46" , "54", "42"),
            Question(1, "Ile jest kontynentów na Ziemi?" ,"7", "7", "5" ,"12", "6"),
            Question(2, "11 + 6" , "17","17", "3", "2", "7")

        )
        var questions3: List<Question> = listOf(
            Question(0, "Co znaczy słowo apple?", "jabłko", "winogron", "gruszka" , "jabłko", "orzech"),
            Question(1, "Co znaczy go?" ,"iść", "biegać", "spacerować" ,"maszerować", "iść"),
            Question(2, "Jak po angielsku powiemy drugi?" , "second","two", "first", "second", "thirth"),
            Question(3, "Jaki to czas teraźniejszy prosty?" , "Present Simple","Past Simple", "Present Simple", "Present Perfect", "Future Continuous")

        )
        var mathCategoryList: List<Question> = listOf(
            Question(0, "5 + 4 = ?", "9", "3","9","10","12"),
            Question(1,"log( 10 ) = ?","1" , "10","20","1","4"),
            Question(2, "Przedział: <-2,10). Jaki x należy do tego przedziału?" ,"-2","-1","10","-2","11" ),
            Question(3 , "x = 2(5-3)-1. Ile wynosi x?","3","0","1","3","6"),
            Question(4, "Ln( 1 ) = ?","1","0","-oo","+oo","1"),
            Question(5, "Ile wynosi PI?","${Math.PI}","3.0","3.1024126386","${Math.PI}","2.82351252"),
            Question(6, "Czego użyjemy chcąc obliczyć pole powierzchni skomplikownych kształtów?","Całek","Wzroru na trapez","Wzoru na prostokąt","Wzoru na trójkąt","Żadna z tych"),
            Question(7, "Rozwiązaniem funkcji  f(2)= 2x + 3 jest: ", "7", "7","10","12","0"),
            Question(8, "Funkcja y = 3x + 3 przecina oś OY w punkcie: ", "3", "0","2","6","3")

        )

        //var category = Category(dataTimeId, "Matematyka", mathCategoryList)
       // var quiz = Quiz(dataTimeId+100,questions)

        //val textView = findViewById<TextView>(R.id.tw_activity_main)
        val drawerLayout = findViewById<DrawerLayout>(R.id.main_Activity_drawer_layout)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        var database = FirebaseDatabase.getInstance("https://quizfirebase-4cb19-default-rtdb.europe-west1.firebasedatabase.app/").reference
        //database.child("Categories").child(category.id.toString()).setValue(category)






        var query:Query = FirebaseDatabase.getInstance("https://quizfirebase-4cb19-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)



        query.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists())
                {println("REF USER: ${snapshot}")
                    var count = 0
                    var currentUser: ArrayList<Pair<String?, String>> = arrayListOf()
                    var name = String()
                    var user: CurrentUser? = CurrentUser()
                    for (u in snapshot.children)
                    {
                      // user1 = u.getValue(User::class.java)
                    }

                    for (h in snapshot.children)
                    {


                        currentUser.add(Pair(h.key, h.value.toString()))
                        if (currentUser[count].first == "name")
                        {
                            user?.name = currentUser[count].second
                        }
                        if (currentUser[count].first == "surname")
                        {
                            user?.surname = currentUser[count].second
                        }
                        if (currentUser[count].first == "age")
                        {
                            user?.age = currentUser[count].second
                        }
                        if (currentUser[count].first == "uid")
                        {
                            user?.uid = currentUser[count].second
                        }
                        if (currentUser[count].first == "email")
                        {
                            user?.email = currentUser[count].second
                        }
                        if(currentUser[count].first == "level")
                        {
//                            user?.level.setLevel() = currentUser[count].second
                            user?.level = h.getValue(Level::class.java)!!

                        }


                        println("==================================================")
                        println("USER: edit ${user}")
                        println("==================================================")
                        println("USER LEVEL: ${user?.level?.getExperiencePoints()}")

                        //Toast.makeText(applicationContext, "${}", Toast.LENGTH_LONG).show()
                        if (currentUser[count].first == "name")
                        {
                            name = currentUser[count].second
                        }
                            count++
                    }
                    println("CURRENT USER arrayLIst: ${currentUser}")


                    if (name.isNotBlank())
                    {
                        tw_layout_nav_menu_profile.text = "${user!!.name} ${user!!.surname}"
                    }else{
                        tw_layout_nav_menu_profile.text = "No User"
                    }
                    tw_layout_nav_menu_profile_level.text = "${user!!.level.getLevel()} Lvl"

                    tw_layout_nav_menu_profile_experience.text = "${user.level.getExperiencePoints()}/ ${ExperiencePerLevel.experiencePerLevel[user.level.getLevel()+1]}"
                    var totalScoreToReachNextLevel: Int = ExperiencePerLevel.experiencePerLevel[user.level.getLevel()+1] - ExperiencePerLevel.experiencePerLevel[user.level.getLevel()]
                    var userCurrentExperiencePoints: Int = ExperiencePerLevel.experiencePerLevel[user.level.getLevel()+1] - user.level.getExperiencePoints()

                    println("totalScore: ${totalScoreToReachNextLevel}")
                    println("userCurrent: ${userCurrentExperiencePoints}")
                    progress_bar_nav_menu_profile.max = totalScoreToReachNextLevel
                    progress_bar_nav_menu_profile.progress = totalScoreToReachNextLevel - userCurrentExperiencePoints

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    //Reading from database user level and experience points
    var queryLevel: Query = FirebaseDatabase.getInstance("https://quizfirebase-4cb19-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
        .child(FirebaseAuth.getInstance().currentUser!!.uid).child("level")

        queryLevel.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {

                    var level: Level = Level()
                    for (i in snapshot.children)
                    {
                        if(i.key == "level")
                        {
                            level.setLevel(i.value.toString().toInt())
                        }
                        if(i.key == "experiencePoints")
                        {
                            level.setExperiencePoints(i.value.toString().toInt())
                        }


                    }



                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.navigation_main_page -> {
                    startFragment(categoriesFragment)
                    drawerLayout.closeDrawers()
                }
                R.id.navigation_second -> {
                    startFragment(usersFragment)
                    drawerLayout.closeDrawers()
                }
                R.id.navigation_abotu_me -> {
                    startActivity(Intent(applicationContext,AboutMeActivity::class.java))
                    drawerLayout.closeDrawers()
                }


            }
            true
        }


        // onCreate()
    }


    private fun startFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.main_activity_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {

        if (doubleBackClick)
        {
            finishAffinity()
            //super.onBackPressed()
           // return
        }
        doubleBackClick = true
        Toast.makeText(applicationContext,"Na pewno chcesz wyjsć z aplikacji?", Toast.LENGTH_SHORT).show()
        Timer("Set_value_true", false).schedule(10000){
            doubleBackClick = false
        }



    }




}