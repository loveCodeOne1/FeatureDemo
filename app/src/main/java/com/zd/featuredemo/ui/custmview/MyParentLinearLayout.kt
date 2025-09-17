package com.zd.featuredemo.ui.custmview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

open class MyParentLinearLayout @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    def: Int = 0
) :
    LinearLayout(context, attr, def) {
    var TAG: String = "ZD_" + this::class.java.simpleName
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        /* val dispatchTouchEvent =
         Log.d(TAG,"layoutParentDispatchTouchEvent>>> $dispatchTouchEvent")*/
        Log.d(TAG, "layoutParentDispatchTouchEvent>>> false")
        return super.dispatchTouchEvent(ev)/*ev?.action.let {
            when(it){
                MotionEvent.ACTION_DOWN->{
                    Log.d(TAG,"ACTION_DOWN true")
                    false
                }
                MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL,MotionEvent.ACTION_MOVE->{
                    Log.d(TAG,"ACTION_UP & ACTION_CANCEL &ACTION_MOVE false" )
                    false
                }
                else->{
                    Log.d(TAG,"else false" )
                    false
                }
            }
        }*/

    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(TAG, "layoutParentOnInterceptTouchEvent>>> false")
        when(ev!!.action){
            MotionEvent.ACTION_MOVE->{
                Log.d(TAG,"layoutParentOnInterceptTouchEvent ACTION_MOVE")
                return true
            }
        }
        return super.onInterceptTouchEvent(ev)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val onTouchEvent = super.onTouchEvent(event)
        Log.d(TAG, "layoutParentOnTouchEvent>>> $onTouchEvent")
        return onTouchEvent
    }

}