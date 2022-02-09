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
        var category = Category(dataTimeId, "Angielski", questions3)
       // var quiz = Quiz(dataTimeId+100,questions)

        //val textView = findViewById<TextView>(R.id.tw_activity_main)
        val drawerLayout = findViewById<DrawerLayout>(R.id.main_Activity_drawer_layout)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        var database = FirebaseDatabase.getInstance("https://quizfirebase-4cb19-default-rtdb.europe-west1.firebasedatabase.app/").reference







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
                        if (currentUser[count].first == "password")
                        {
                            user?.password = currentUser[count].second
                        }
                        println("==================================================")
                        println("USER: edit ${user}")
                        println("==================================================")


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
                        tw_layout_nav_menu_profile.text = user!!.name
                    }else{
                        tw_layout_nav_menu_profile.text = "No User"
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