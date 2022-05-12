package com.riyaspullur.learningrestapimethods

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row_list.view.*

class RecyclerViewAdapter(val clickListener:OnItemClickListener) :RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var userList= mutableListOf<User>()

    class MyViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val textViewName=view.textViewName
        val textViewEmail=view.textViewEmail
        val textViewStatus=view.textViewStatus
        val textViewGender=view.textViewGender
        val textViewId=view.textViewID
        fun bind(data:User){
            textViewName.text=data.name
            textViewEmail.text=data.email
            textViewStatus.text=data.status
            textViewGender.text=data.gender
            textViewId.text="id = ${data.id}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val inflater=LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_list,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(userList[position])
        holder.itemView.setOnClickListener {
            clickListener.onItemEditClick(userList[position])
        }
    }

    override fun getItemCount(): Int {
       return userList.size
    }

    interface  OnItemClickListener{
        fun onItemEditClick(user:User)
    }
}