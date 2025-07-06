package com.zd.featuredemo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zd.featuredemo.R

class MyRecyclerViewAdapter: RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {
    private var mList = mutableListOf<String>()
    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val textView: TextView by lazy {
            view.findViewById(R.id.simple_text)
        }
    }

    fun setData(list:MutableList<String>){
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.simple_list_item01, parent, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = mList.get(position)
    }
}