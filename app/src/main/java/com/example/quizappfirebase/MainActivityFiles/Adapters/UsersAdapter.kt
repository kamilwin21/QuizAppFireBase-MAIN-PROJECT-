package com.example.quizappfirebase.MainActivityFiles.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizappfirebase.MainActivityFiles.SystemUserProfileActivity
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
        val userLayout = holder.view.id_layout_position_users_in_users_adapter
        tw.text = "${usersList[position].name} ${usersList[position].surname}"

        userLayout.setOnClickListener {


            val user: User = usersList[position]
            val intentUser = Intent(holder.view.context.applicationContext, SystemUserProfileActivity::class.java)
            intentUser.putExtra("userId", usersList[position].uid)
            intentUser.putExtra("userEmail", usersList[position].email)
            intentUser.putExtra("userPassword", usersList[position].password)
            intentUser.putExtra("userName", usersList[position].name)
            intentUser.putExtra("userSurname", usersList[position].surname)
            intentUser.putExtra("userAge", usersList[position].age)
            holder.view.context.startActivity(intentUser)

        }



    }

    override fun getItemCount(): Int {
        return usersList.size
    }

}

class MyViewHolderUsers(val view: View): RecyclerView.ViewHolder(view){}