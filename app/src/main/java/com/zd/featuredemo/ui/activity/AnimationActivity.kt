package com.zd.featuredemo.ui.activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.databinding.DataBindingUtil
import com.zd.featuredemo.R
import com.zd.featuredemo.databinding.ActivityAnimationBinding

class AnimationActivity : AppCompatActivity() {
    private val animatorSet: AnimatorSet by lazy {
        AnimatorSet()
    }
     val mBinding:ActivityAnimationBinding by lazy {
        DataBindingUtil.setContentView(this,R.layout.activity_animation)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.animationStart.setOnClickListener {
            animatorSet.start()
        }
        val trans = ObjectAnimator.ofFloat(mBinding.animationView,"translationX",-800f,0f)
        trans.interpolator = AccelerateDecelerateInterpolator()
        trans.setDuration(4300)
        animatorSet.playTogether(trans)
        val alphasTo0 = ObjectAnimator.ofFloat(mBinding.animationView,"alpha",0f,0f)
        alphasTo0.setDuration(1100)
        animatorSet.playTogether(alphasTo0)

        val alphasTo1 = ObjectAnimator.ofFloat(mBinding.animationView,"alpha",1f,1f)
        alphasTo1.setDuration(2000)

        animatorSet.playTogether(alphasTo1)


    }
}