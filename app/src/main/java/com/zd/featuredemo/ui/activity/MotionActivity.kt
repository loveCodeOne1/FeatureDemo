package com.zd.featuredemo.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import bindView
import com.zd.featuredemo.R
import com.zd.featuredemo.databinding.ActivityMotionLayoutBinding
import com.zd.featuredemo.ui.custmview.MyMotionView
import com.zd.featuredemo.ui.custmview.MyParentLinearLayout

class MotionActivity:AppCompatActivity() {
    private lateinit var  mBinding: ActivityMotionLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_motion_layout)

        val parentLayout = MyParentLinearLayout(this)
        parentLayout.layoutParams = ViewGroup.LayoutParams(1000,1000)
        parentLayout.setBackgroundColor(Color.RED)


        val childView = MyMotionView(this)
        childView.layoutParams = ViewGroup.LayoutParams(400,400)
        childView.setBackgroundColor(Color.GREEN)

        parentLayout.addView(childView)

        mBinding.myRootView.addView(parentLayout)



    }
}