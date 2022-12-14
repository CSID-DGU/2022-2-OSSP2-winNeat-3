package com.example.winneat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Rv_adapter(val review_list: ArrayList<Review>) : RecyclerView.Adapter<Rv_adapter.CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Rv_adapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_review, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: Rv_adapter.CustomViewHolder, position: Int) {
        holder.user.text = review_list.get(position).User
        holder.user_rate.rating = review_list.get(position).User_rate
        holder.rv_content.text = review_list.get(position).Rv_content
    }

    override fun getItemCount(): Int {
        return review_list.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val user = itemView.findViewById<TextView>(R.id.tv_user)
        val user_rate = itemView.findViewById<RatingBar>(R.id.tv_user_rate)
        val rv_content = itemView.findViewById<TextView>(R.id.tv_rv_content)
    }
}

class Review (val User: String, val User_rate: Float, val Rv_content: String)