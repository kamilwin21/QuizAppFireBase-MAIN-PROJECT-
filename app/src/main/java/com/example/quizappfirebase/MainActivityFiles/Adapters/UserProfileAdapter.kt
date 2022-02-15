package com.example.quizappfirebase.MainActivityFiles.Adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizappfirebase.MainActivityFiles.MainClasses.Statics
import com.example.quizappfirebase.R
import kotlinx.android.synthetic.main.layout_position_user_profile_adapter.view.*


class UserProfileAdapter(val context: Context, val userStatics: ArrayList<Statics> ): RecyclerView.Adapter<UserProfileHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserProfileHolder {
        val inflater = LayoutInflater.from(parent.context)
        val positionList = inflater.inflate(R.layout.layout_position_user_profile_adapter, parent, false)
        return UserProfileHolder(positionList)
    }

    override fun onBindViewHolder(holder: UserProfileHolder, position: Int) {

        val layoutUserProfileAdapter = holder.userHolderView.id_layout_position_user_profile_adapter
        val categoryName:TextView = holder.userHolderView.tw_statics_name
        val pointsReceived: TextView = holder.userHolderView.tw_statics_points_received

        categoryName.text = userStatics[position].quizName
        pointsReceived.text = userStatics[position].pointsReceived
        layoutUserProfileAdapter.setBackgroundColor(getColor(position))


        println("GET COLOR(): ${getColor(position)}")


    }

    override fun getItemCount(): Int {
       return userStatics.size
    }

}

class UserProfileHolder( val userHolderView: View): RecyclerView.ViewHolder(userHolderView){}

private fun getColor(position: Int): Int{

    var parsedColor: Int = 0
    when(position)
    {
        0 -> {
            parsedColor = Color.parseColor("#64c8a1")
          //  layoutUserProfileAdapter.setBackgroundColor(Color.parseColor("#64c8a1"))
        }
        1 -> {
           // layoutUserProfileAdapter.setBackgroundColor(Color.parseColor("#4169e1"))
            parsedColor = Color.parseColor("#4169e1")

        }
        2 -> {
           // layoutUserProfileAdapter.setBackgroundColor(Color.parseColor("#ffd700"))
            parsedColor = Color.parseColor("#ffd700")

        }
        3 -> {
          //  layoutUserProfileAdapter.setBackgroundColor(Color.parseColor("#f0f0f0"))
            parsedColor = Color.parseColor("#f0f0f0")

        }
    }

    return parsedColor

}