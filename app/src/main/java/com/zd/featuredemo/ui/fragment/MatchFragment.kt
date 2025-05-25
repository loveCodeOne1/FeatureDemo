package com.zd.featuredemo.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import com.zd.featuredemo.R

class MatchFragment :ListFragment(){

    var mCallBack:OnTitleSelectedListener? = null
    interface OnTitleSelectedListener {
        fun onEventsSelected(position:Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCallBack = context as OnTitleSelectedListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val strings = arrayOf<String>("item1","item2","item3")
        listAdapter = activity?.let {
            ArrayAdapter(it,R.layout.home_recycler_item,strings)
        }

    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        Log.d("Lzd","onListItemClick : " +position)
        mCallBack?.onEventsSelected(position)
    }

}