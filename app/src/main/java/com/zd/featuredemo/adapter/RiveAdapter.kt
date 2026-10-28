package com.zd.featuredemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zd.featuredemo.R
import com.zd.featuredemo.bean.RiveBean

class RiveAdapter(val context: Context,val list:List<RiveBean>?): RecyclerView.Adapter<RiveAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        p0: ViewGroup,
        p1: Int
    ): ViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.rive_item_layout,null,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        p0: ViewHolder,
        p1: Int
    ) {
        if(list == null) return
        p0.text.text = list[p1].property
    }

    override fun getItemCount(): Int = list?.size?:0


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var text: TextView = itemView.findViewById<TextView>(R.id.rive_text)
    }
}