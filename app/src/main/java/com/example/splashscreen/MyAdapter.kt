package com.example.splashscreen

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(var context:Activity, var data:ArrayList<profiles>):
RecyclerView.Adapter<MyAdapter.MyViewHolder>()
{
    private lateinit var myListener:ItemClickListener
    interface ItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setItem(listener: ItemClickListener){
        myListener=listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemview=LayoutInflater.from(parent.context).inflate(R.layout.each_profile,parent,false)
        return MyViewHolder(itemview,myListener)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        var curritem=data[position]
        holder.himage.setImageResource(curritem.img)
        holder.hname.text=curritem.name
        holder.hrides.text= curritem.rides.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }
    class MyViewHolder(itemView: View, listener: ItemClickListener):RecyclerView.ViewHolder(itemView) {
        val himage=itemView.findViewById<ShapeableImageView>(R.id.pimg)
        val hname=itemView.findViewById<TextView>(R.id.pname)
        val hrides=itemView.findViewById<TextView>(R.id.prides)
        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }
}