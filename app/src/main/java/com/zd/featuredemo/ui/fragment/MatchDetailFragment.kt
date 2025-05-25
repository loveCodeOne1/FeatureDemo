package com.zd.featuredemo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.zd.featuredemo.R


const val EVENT_POSITION = "position"
class MatchDetailFragment :Fragment(){

    val array = arrayOf("item1-Detail","item2-Detail","item3-detail")
    private var mPosition = -1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(savedInstanceState != null){
            //恢复之前保存的列表项
            mPosition = savedInstanceState.getInt(EVENT_POSITION)
        }
        return inflater.inflate(R.layout.fragment_match_detail1,container,false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(EVENT_POSITION,mPosition)
    }

    override fun onStart() {
        super.onStart()
        val args  = arguments
        if(args != null){
            updateView(args.getInt(EVENT_POSITION))
        }else if(mPosition != -1){
            updateView(mPosition)
        }
    }

    fun updateView(position:Int){
        val s = array[position]
        val activity = this.activity
        val textView = activity?.findViewById<TextView>(R.id.match_detail_tv)
        textView?.text = s
        mPosition = position
    }
}