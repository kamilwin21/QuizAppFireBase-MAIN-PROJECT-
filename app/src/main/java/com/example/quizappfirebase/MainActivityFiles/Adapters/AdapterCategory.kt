package com.example.quizappfirebase.MainActivityFiles.Adapters


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.quizappfirebase.MainActivityFiles.MainActivity
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Category
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Question
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Statics
import com.example.quizappfirebase.MainActivityFiles.QuestionActivity
import com.example.quizappfirebase.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_main_page.view.*
import kotlinx.android.synthetic.main.layout_position_categories_in_adaptercategory.view.*
import java.io.Serializable
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

class AdapterCategory (val context: Context,val categoriesList: ArrayList<Category>, val statics: ArrayList<Statics?>): RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val positionList = inflater.inflate(R.layout.layout_position_categories_in_adaptercategory,parent,false)
        return MyViewHolder(positionList)
    }


    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {

        val layout = holder.view.linear_layout_id_position_categories
        val tw= holder.view.tw_category
        //layout.setBackgroundColor(colors.get(position))




        tw.text = categoriesList[position].categoryName

        if (position == 0)
        {
            tw.setTextColor(Color.MAGENTA)
        }else if (position == 1)
        {
            tw.setTextColor(Color.CYAN)
        }else if (position == 1)
        {
            tw.setTextColor(Color.GREEN)
        }


        layout.setOnClickListener {
            println("ADAPTER STATICS: ${statics}")

            //Go to QuestionActivity
            val query: Query = FirebaseDatabase.getInstance("https://quizfirebase-4cb19-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid).child("Statistics").orderByChild("quizName").equalTo(categoriesList[position].categoryName)
            var static: Statics? = Statics()
            var checkExists: Boolean = false



                for (s in statics)
                {
                    if (s?.quizName == categoriesList[position].categoryName)
                    {
                        static = s
                        checkExists = true
                        val intentQuestion =  Intent(holder.view.context.applicationContext, QuestionActivity::class.java)
                        intentQuestion.putExtra("question_category", categoriesList[position].categoryName)
                        intentQuestion.putExtra("id", categoriesList[position].id)
                        intentQuestion.putExtra("idStatic", static?.id)
                        intentQuestion.putExtra("quizNameStatic", static?.quizName)
                        intentQuestion.putExtra("pointsReceivedStatic", static?.pointsReceived)
                        //println("ADAPTER: ${statics}")
                        intentQuestion.putExtra("exists", checkExists)
                        holder.view.context.startActivity(intentQuestion)
                        it.background = ContextCompat.getDrawable(holder.view.context, R.drawable.radius2)



                    }

//                    else {
//                        val intentQuestion =  Intent(holder.view.context.applicationContext, QuestionActivity::class.java)
//                        intentQuestion.putExtra("question_category", categoriesList[position].categoryName)
//                        intentQuestion.putExtra("id", categoriesList[position].id)
//                        //println("ADAPTER: ${statics}")
//                        intentQuestion.putExtra("exists", checkExists)
//                        holder.view.context.startActivity(intentQuestion)
//                        it.background = ContextCompat.getDrawable(holder.view.context, R.drawable.radius2)
//                    }
                }



                val intentQuestion =  Intent(holder.view.context.applicationContext, QuestionActivity::class.java)
                intentQuestion.putExtra("question_category", categoriesList[position].categoryName)
                intentQuestion.putExtra("id", categoriesList[position].id)
                intentQuestion.putExtra("idStatic", static?.id)
                intentQuestion.putExtra("quizNameStatic", static?.quizName)
                intentQuestion.putExtra("pointsReceivedStatic", static?.pointsReceived)
                //println("ADAPTER: ${statics}")
                intentQuestion.putExtra("exists", checkExists)
                holder.view.context.startActivity(intentQuestion)
                it.background = ContextCompat.getDrawable(holder.view.context, R.drawable.radius2)



        }

    }




    //Size of recycler elements
    override fun getItemCount(): Int {
        return categoriesList.size
    }
    //Listener lets go to QuestionActivity
    private val myOnClickListener = View.OnClickListener {
        val intentQuestion = Intent(context.applicationContext, QuestionActivity::class.java)
        context.startActivity(intentQuestion)
        //Here, I have no idea how I can take holder and position variables TO CHANGE!!

    }



}



class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){}