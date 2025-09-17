package com.zd.featuredemo.ui.custmview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent

class MyChildLinearLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    def: Int = 0
) : MyParentLinearLayout(context, attributeSet, def) {


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val layoutChildTouchEvent = super.dispatchTouchEvent(ev)
        Log.d(TAG,"layoutChildTouchEvent>>> :$layoutChildTouchEvent")
        return layoutChildTouchEvent
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        val layoutChildOnInterceptTouchEvent = super.onInterceptTouchEvent(ev)
        Log.d(TAG,"layoutChildOnInterceptTouchEvent>>> :$layoutChildOnInterceptTouchEvent")
        return layoutChildOnInterceptTouchEvent
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val layoutChildOnTouchEvent = super.onTouchEvent(event)
        Log.d(TAG,"layoutChildOnTouchEvent>>> :$layoutChildOnTouchEvent")
        return layoutChildOnTouchEvent
    }
}