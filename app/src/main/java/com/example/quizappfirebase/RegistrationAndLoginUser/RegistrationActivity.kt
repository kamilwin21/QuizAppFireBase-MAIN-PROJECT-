package com.example.quizappfirebase.RegistrationAndLoginUser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Statics
import com.example.quizappfirebase.R
import com.example.quizappfirebase.RegistrationAndLoginUser.Classes.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database

import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


        val editText_Name_MainActivity = findViewById<EditText>(R.id.editText_Name_MainActivity)
        val editText_Age_MainActivity = findViewById<EditText>(R.id.editText_Age_MainActivity)
        val editText_EmailAdress_MainActivity = findViewById<EditText>(R.id.editText_EmailAdress_MainActivity)
        val editText_password_MainActivity = findViewById<EditText>(R.id.editText_password_MainActivity)
        val editText_SurName_MainActivity = findViewById<EditText>(R.id.editText_SurName_MainActivity)


        val btn_register_RegisterActivity = findViewById<Button>(R.id.btn_register_RegisterActivity)



        btn_register_RegisterActivity.setOnClickListener {






            when{
                TextUtils.isEmpty(editText_Name_MainActivity.text.toString().trim() { it <= ' '}) ->{
                    Toast.makeText(applicationContext,"Proszę wprowadzić nazwę użytkownika",
                        Toast.LENGTH_SHORT).show()

                }
                TextUtils.isEmpty(editText_Age_MainActivity.text.toString().trim() { it <= ' '}) ->{
                    Toast.makeText(applicationContext,"Proszę wprowadzić wiek",
                        Toast.LENGTH_SHORT).show()

                }
                TextUtils.isEmpty(editText_EmailAdress_MainActivity.text.toString().trim() { it <= ' '}) ->{
                    Toast.makeText(applicationContext,"Proszę wprowadzić adres email",
                        Toast.LENGTH_SHORT).show()

                }
                TextUtils.isEmpty(editText_password_MainActivity.text.toString().trim() { it <= ' '}) ->{
                    Toast.makeText(applicationContext,"Proszę wprowadzić hasło",
                        Toast.LENGTH_SHORT).show()

                }

                else -> {




                    val name: String = editText_Name_MainActivity.text.toString()
                    val age:String = editText_Age_MainActivity.text.toString()
                    val email:String = editText_EmailAdress_MainActivity.text.toString()
                    val password:String = editText_password_MainActivity.text.toString()
                    val surname:String = editText_SurName_MainActivity.text.toString()

                    var tablica: ArrayList<Char> = ArrayList<Char>()
                    for (i in email)
                    {
                        tablica.add(i)
                    }
                    var warning:Int = 0
                    for (i in tablica)
                    {
                        if(i == '@')
                        {
                            warning += 1

                        }
                    }
                    if (warning > 1 || warning == 0)
                    {

                        Toast.makeText(applicationContext,"Niewłaściwy format maila", Toast.LENGTH_SHORT).show()

                    }else{

                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(){
                                    task ->
                                if (task.isSuccessful)
                                {
                                    val database = FirebaseDatabase.getInstance("https://quizfirebase-4cb19-default-rtdb.europe-west1.firebasedatabase.app/").reference


                                    val user =
                                        User(
                                            FirebaseAuth.getInstance().currentUser!!.uid,
                                            email,
                                            password,
                                            name,
                                            surname,
                                            age
                                        )

                                    //myRef.child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(user)
                                    database.child("Users").child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(user)


                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(applicationContext,"Pomyślnie zarejestrowano",
                                        Toast.LENGTH_SHORT).show()
                                    val intent = Intent(applicationContext, LoginActivity::class.java)
                                    startActivity(intent)
                                    finish()

                                }else{
                                    Toast.makeText(applicationContext,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT).show()
                                }

                            }

                    }
                    warning = 0



                }

            }


            //end of btn_register_RegisterActivity
        }
        //End of onCreate method
    }
}