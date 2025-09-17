package com.zd.featuredemo

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.view.View

object AnimationUtils {
    /**
     * duration : 如果AnimatorSet设置了时长，子动画将继承这个时长，而子动画自己设置的duration将失效。
     *
     * before : 先执行前面的animation play ($） 在执行 before（$）
     *
     * after(long(time)) 是执行间隔 和 duration是不同的概念， 它俩口可以同时存在
     *
     * Builder链式调用中会先执行after函数中的动画（有多个同时执行），然后执行play和with函数（有多个同时执行）中的动画，最后执行before函数中的动画（有多个同时执行）
     */

    private var animatorSet: AnimatorSet? = null


    fun startScaleAnimation(view:View){
        animatorSet = AnimatorSet()

        val colorAnimation = ObjectAnimator.ofArgb(view,"backgroundColor",Color.WHITE,Color.RED)
        colorAnimation.setDuration(3000)

        val colorAnimation2 = ObjectAnimator.ofArgb(view,"backgroundColor",Color.WHITE,Color.BLACK)
        colorAnimation2.setDuration(3000)

        val colorAnimation3 = ObjectAnimator.ofArgb(view,"backgroundColor",Color.GREEN,Color.CYAN)
        colorAnimation3.setDuration(3000)

        animatorSet?.let {
           it.play(colorAnimation) // 2
               .after(colorAnimation2) // 1
               .before(colorAnimation3) // 3
               .after(10000)

            it.start()

            // white -> black -> green -> cran -> white->red
        }
    }


}