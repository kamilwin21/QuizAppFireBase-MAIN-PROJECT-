package com.example.quizappfirebase.RealTimeDataBaseFireBase

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizappfirebase.MainActivityFiles.Adapters.AdapterCategory
import com.example.quizappfirebase.MainActivityFiles.Adapters.UsersAdapter
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Category
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Statics
import com.example.quizappfirebase.RegistrationAndLoginUser.Classes.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.fragment_users.*

class FireBaseDataBase {
    var query: Query? =   null
    var database: DatabaseReference? = null


    constructor(query: Query){
        this.query = query
    }
    constructor(databaseReference: DatabaseReference)
    {
        this.database = databaseReference
    }

    fun getUsersForUsersAdapter(context: Context, recyclerView: RecyclerView ){
        this.database?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists())
                {
                    var usersList: ArrayList<User> = arrayListOf()
                    for (h in snapshot.children)
                    {
                        val user = h.getValue(User::class.java)
                        usersList.add(user!!)
                        recyclerView.layoutManager =
                            LinearLayoutManager(context)
                        recyclerView.adapter =
                            UsersAdapter(context, usersList)

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }



    fun readCategoriesFromFirebaseDatabase(context: Context, recyclerView: RecyclerView, categoriesList: ArrayList<Category>){
        database?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists()) {
                    for (h in snapshot.children) {
                        val category = h.getValue(Category::class.java)
                        categoriesList.add(category!!)

                        val query: Query = FirebaseDatabase.getInstance("https://quizfirebase-4cb19-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
                            .child(FirebaseAuth.getInstance().currentUser!!.uid).child("Statistics")
                        var statics: ArrayList<Statics?> = arrayListOf()

                        query.addValueEventListener(object : ValueEventListener{
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if (snapshot.exists())
                                {

                                    for (s in snapshot.children)
                                    {
                                        val static = s.getValue(Statics::class.java)
                                        statics.add(static)
                                    }

                                    recyclerView.layoutManager =
                                        GridLayoutManager(context,2)
                                    recyclerView.adapter =
                                        AdapterCategory(context, categoriesList,statics)

                                    println("Statystyki= ${statics}")
                                }else if (!snapshot.exists())
                                {

                                    recyclerView.layoutManager =
                                        GridLayoutManager(context,2)
                                    recyclerView.adapter =
                                        AdapterCategory(context, categoriesList,statics)
                                }



                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        })

                        println("Wczytane kategorie przez użytkownika: ${categoriesList}")
                        println("Categories size: ${categoriesList.size}")
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}