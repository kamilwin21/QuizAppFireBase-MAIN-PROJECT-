package com.example.quizappfirebase.MainActivityFiles.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizappfirebase.LevelPackage.Level
import com.example.quizappfirebase.MainActivityFiles.Adapters.AdapterCategory
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Category
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Statics
import com.example.quizappfirebase.R
import com.example.quizappfirebase.RealTimeDataBaseFireBase.FireBaseDataBase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_categories.*
import java.util.*
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoriesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }




    }

    override fun onStart() {

        var categoriesList: ArrayList<Category> = arrayListOf<Category>()

        var database = FirebaseDatabase.getInstance("https://quizfirebase-4cb19-default-rtdb.europe-west1.firebasedatabase.app/")
                    .getReference("Categories")
        val dbConnection = FireBaseDataBase(database)
        dbConnection.readCategoriesFromFirebaseDatabase(requireContext(), recycler_view_categories_fragment, categoriesList)
//        ReadCategoriesFromFirebaseDatabase(database, categoriesList)



        super.onStart()
    }






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_categories, container, false)


    }





    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CategoriesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CategoriesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }




    //Function takes connection to Firebase Database and list of categories and connects to database
    //and show the categories in adapterCategory
    private fun ReadCategoriesFromFirebaseDatabase(
        database: DatabaseReference,
        categoriesList: ArrayList<Category>
    ) {
        database.addValueEventListener(object : ValueEventListener {
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

                                var queryLevel: Query = FirebaseDatabase.getInstance("https://quizfirebase-4cb19-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
                                    .child(FirebaseAuth.getInstance().currentUser!!.uid).child("level")
                                queryLevel.addValueEventListener(object: ValueEventListener {
                                    override fun onDataChange(snapshot1: DataSnapshot) {
                                        if(snapshot1.exists())
                                        {
                                            var level: Level? = Level()
                                            for (user in snapshot1.children)
                                            {
                                                level = user.getValue(Level::class.java)


                                            }


                                            if (snapshot.exists())
                                            {

                                                for (s in snapshot.children)
                                                {
                                                    val static = s.getValue(Statics::class.java)
                                                    statics.add(static)
                                                }

                                                recycler_view_categories_fragment.layoutManager =
                                                    GridLayoutManager(context,2)
                                                recycler_view_categories_fragment.adapter =
                                                    AdapterCategory(requireContext(), categoriesList,statics, level)

                                                println("Statystyki= ${statics}")
                                            }else if (!snapshot.exists())
                                            {

                                                recycler_view_categories_fragment.layoutManager =
                                                    GridLayoutManager(context,2)
                                                recycler_view_categories_fragment.adapter =
                                                    AdapterCategory(requireContext(), categoriesList,statics, level)
                                            }




                                        }
                                    }

                                    override fun onCancelled(error: DatabaseError) {
                                        TODO("Not yet implemented")
                                    }


                                })




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