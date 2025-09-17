package com.zd.featuredemo.ui.activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.databinding.DataBindingUtil
import com.zd.featuredemo.AnimationUtils
import com.zd.featuredemo.R
import com.zd.featuredemo.databinding.ActivityAnimationBinding

class AnimationActivity : AppCompatActivity() {

     val mBinding:ActivityAnimationBinding by lazy {
        DataBindingUtil.setContentView(this,R.layout.activity_animation)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.animationStart.setOnClickListener {
        AnimationUtils.startScaleAnimation(mBinding.animationView)
        }



    }
}