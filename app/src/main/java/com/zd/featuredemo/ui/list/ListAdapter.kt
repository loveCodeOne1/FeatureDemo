package com.zd.featuredemo.ui.list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zd.featuredemo.R
import com.zd.featuredemo.ui.list.model.ListModel

class ListAdapter(val data:MutableList<ListModel>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private val mData:MutableList<ListModel> by lazy { data }


    fun setData(data:MutableList<ListModel>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_list,parent,false))
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listModel = mData[position]
        holder.colorView.setBackgroundColor(Color.parseColor(listModel.color))
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
           val colorView:View by lazy {
            itemView.findViewById(R.id.recycler_list_view)
        }
    }
}