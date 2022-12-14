package com.example.winneat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Madapter(val menu_list: ArrayList<Menu>) : RecyclerView.Adapter<Madapter.CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Madapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_menu, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: Madapter.CustomViewHolder, position: Int) {
        holder.food.setImageResource(menu_list.get(position).food)
        holder.Mname.text = menu_list.get(position).Mname
        holder.cost.text = menu_list.get(position).cost
    }

    override fun getItemCount(): Int {
        return menu_list.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val food = itemView.findViewById<ImageView>(R.id.iv_food)
        val Mname = itemView.findViewById<TextView>(R.id.tv_Mname)
        val cost = itemView.findViewById<TextView>(R.id.tv_cost)
    }
}

class Menu (val food: Int, val Mname: String, val cost: String)