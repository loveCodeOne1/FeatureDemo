package com.zd.featuredemo.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.zd.featuredemo.R

class ListFloatView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    def: Int = 0,
) :ConstraintLayout(context, attrs,def){

    init {
       LayoutInflater.from(context).inflate(R.layout.layout_statusbar_float_list,this,true)
    }


}