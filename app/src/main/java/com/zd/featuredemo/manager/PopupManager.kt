package com.zd.featuredemo.manager

import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.annotation.RequiresApi

object PopupManager {

    private val mPop:PopupWindow by lazy {
        PopupWindow()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    public fun show(author:View, showContentView:View, x:Int, y:Int){
        if(mPop.isShowing){
            mPop.dismiss()
        }else{
            mPop.apply {
                contentView = showContentView
                width = ViewGroup.LayoutParams.WRAP_CONTENT
                height = ViewGroup.LayoutParams.WRAP_CONTENT
                isFocusable = true
                isTouchModal = true
                isOutsideTouchable =  true
                showAtLocation(author,Gravity.NO_GRAVITY,x,y)
            }



        }


    }

}