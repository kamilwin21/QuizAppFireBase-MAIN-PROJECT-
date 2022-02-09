package com.example.quizappfirebase.MainActivityFiles.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizappfirebase.R
import com.example.quizappfirebase.RegistrationAndLoginUser.Classes.User
import kotlinx.android.synthetic.main.layout_position_users_in_users_adapter.view.*


class UsersAdapter(val context: Context, val usersList: ArrayList<User>) : RecyclerView.Adapter<MyViewHolderUsers>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderUsers {
        val inflater = LayoutInflater.from(parent.context)
        val positionList = inflater.inflate(R.layout.layout_position_users_in_users_adapter,parent,false)
        return MyViewHolderUsers(positionList)
    }

    override fun onBindViewHolder(holder: MyViewHolderUsers, position: Int) {
        val tw = holder.view.tw_in_users_adapter
        tw.text = usersList[position].name
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

}

class MyViewHolderUsers(val view: View): RecyclerView.ViewHolder(view){}