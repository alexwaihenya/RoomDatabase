package com.lexie.roomdatabase.fragments.list

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lexie.roomdatabase.R
import com.lexie.roomdatabase.model.User

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


    private var userList = emptyList<User>()

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.itemView.findViewById<TextView>(R.id.textView).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.tvFirstName).text = currentItem.firstName
        holder.itemView.findViewById<TextView>(R.id.tvLastName).text = currentItem.lastName
        holder.itemView.findViewById<TextView>(R.id.tvAge).text = currentItem.age.toString()

        holder.itemView.findViewById<View>(R.id.rowLayout).setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment2(currentItem)

            holder.itemView.findNavController().navigate(action)
        }


    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}