package com.zd.featuredemo.ui.custmview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class MyMotionView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    def: Int = 0
) : View(context, attr, def) {
    private val TAG:String = "ZD_MyMotionView"

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(TAG,"motionView dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d(TAG,"motionView onTouchEvent")

        when(event!!.action){
            MotionEvent.ACTION_UP->{
                Log.d(TAG,"motionView ACTION_UP")
            }
            MotionEvent.ACTION_CANCEL->{
                Log.d(TAG,"motionView ACTION_CANCEL")
            }
        }
        return true

    }
}