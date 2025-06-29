package com.zd.featuredemo.ui.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zd.featuredemo.R
import com.zd.featuredemo.customview.ListFloatView
import com.zd.featuredemo.databinding.ActivityTestLayoutBinding
import com.zd.featuredemo.manager.PopupManager

class TestActivity: AppCompatActivity() {
    private lateinit var mBinding:ActivityTestLayoutBinding
    private var flag = false


    fun location(athor:View):Pair<Int,Int>{
        val arr = IntArray(2)
        athor.getLocationOnScreen(arr)
        val x:Int = arr[0] - getDimen(R.dimen.stat_float_width)
        val y:Int = arr[1] + athor.height
        return Pair(x,y)
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_test_layout)


        mBinding.tvFlag.setOnClickListener {
               val listFloatView = ListFloatView(this)
               val(x,y) = location(mBinding.tvFlag)
               Log.d("ZD","x: $x , y: $y")
               PopupManager.show(mBinding.tvFlag,listFloatView,x,y)

        }


    }







    fun dpTo2Px(dp:Int):Int{
        return (resources.displayMetrics.density * dp + 0.5).toInt()
    }
    fun getDimen(resId :Int):Int{
        return resources.getDimension(resId).toInt()
    }

}