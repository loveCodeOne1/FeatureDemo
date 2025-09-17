package com.zd.featuredemo.ui.activity

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zd.featuredemo.R
import com.zd.featuredemo.databinding.ActivityTestBinding
import java.util.LinkedList
import java.util.Queue
import kotlin.random.Random


class TestActivity : AppCompatActivity() {
    lateinit var mBinding : ActivityTestBinding
    var animaiton1 :ObjectAnimator? = null
    var animaiton2 :ObjectAnimator? = null

    val contDownTimer = object : CountDownTimer(10 * 1000,1000){
        override fun onTick(millisUntilFinished: Long) {
            Log.d("Lzd","millisUntilFinished: $millisUntilFinished")
        }

        override fun onFinish() {
            Log.d("Lzd","onFinish: ")
        }

    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_test)
        window.insetsController?.let {
            // 隐藏状态栏
            it.hide(WindowInsets.Type.statusBars())
            // 保持其他行为正常，确保布局稳定
            it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = window.insetsController

            mBinding.btnHide.setOnClickListener {
//                controller?.hide(WindowInsets.Type.navigationBars())
//                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//                val decorView = window.decorView
//                val uiOptions = (View.SYSTEM_UI_FLAG_FULLSCREEN
//                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
//                decorView.systemUiVisibility = uiOptions
                setImmersiveMode()
            }
            mBinding.btnShow.setOnClickListener {
                showSystemUI();
//                controller?.show(WindowInsets.Type.navigationBars())

            }

            mBinding.btnClearAnimation.setOnClickListener {
                clearAnimaiton()
            }
            mBinding.btnStartAnimation.setOnClickListener {
                startAnimation()
                animaiton1 = startAnimationByObjectAnimator()
                animaiton2 = startAnimationByObjectAnimator2()
            }
            mBinding.btnStartTimer.setOnClickListener {
                contDownTimer.start()
            }
            mBinding.btnFinishTimer.setOnClickListener {
                contDownTimer.cancel()
            }
        }

    }

    private fun clearAnimaiton() {
        mBinding.rotationView.clearAnimation()
        animaiton1?.cancel()
        animaiton2?.cancel()
    }

    private fun startAnimation() {
        val timeArray =  arrayOf(160L,160L,160L)
        val rotateArray = arrayOf(arrayOf(1f,-1f), arrayOf(-1f,1f))
        val rotateRandom = Random.nextInt(2)
        val fromDegrees = rotateArray[rotateRandom][0]
        val toDegrees = rotateArray[rotateRandom][1]
        val rotateAnimation = RotateAnimation(fromDegrees, toDegrees,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
        Log.d("Lzd","startAnimation fromDegrees:$fromDegrees , toDegrees: $toDegrees")
        rotateAnimation.duration = timeArray[Random.nextInt(3)]
        Log.d("Lzd","time1 "+timeArray[Random.nextInt(3)])

        rotateAnimation.repeatCount = Animation.INFINITE
        rotateAnimation.interpolator = LinearInterpolator()
        rotateAnimation.repeatMode = Animation.REVERSE
        mBinding.rotationView.startAnimation(rotateAnimation)
    }

    @SuppressLint("SuspiciousIndentation")
    private fun startAnimationByObjectAnimator():ObjectAnimator{
        val durations = arrayOf(160L,200L,240L)
        val rotateArray = arrayOf(arrayOf(4f,-4f), arrayOf(-4f,4f))
        val rotateRandom = Random.nextInt(2)
        val fromDegrees = rotateArray[rotateRandom][0]
        val toDegrees = rotateArray[rotateRandom][1]

        val wiggleAnimator = ObjectAnimator.ofFloat(mBinding.rotationView2,"rotation",0f,fromDegrees, toDegrees)
            wiggleAnimator.setDuration(durations[Random.nextInt(3)])
            Log.d("Lzd","time2 "+durations[Random.nextInt(3)])
            wiggleAnimator.repeatCount = ValueAnimator.INFINITE
            wiggleAnimator.repeatMode = ValueAnimator.REVERSE
            wiggleAnimator.start()
            return wiggleAnimator
    }
    @SuppressLint("SuspiciousIndentation")
    private fun startAnimationByObjectAnimator2():ObjectAnimator{
        val durations = arrayOf(160L,200L,240L)
        val rotateArray = arrayOf(arrayOf(1f,-1f), arrayOf(-1f,1f))
        val rotateRandom = Random.nextInt(2)

        val fromDegrees = rotateArray[rotateRandom][0]
        val toDegrees = rotateArray[rotateRandom][1]

//        var rotationAngle = 1f
//        val initialDirection = if(Random.nextBoolean()) 1 else -1
        var duration = durations[Random.nextInt(durations.size)]
        val wiggleAnimator = ObjectAnimator.ofFloat(mBinding.rotationView3,"rotation",fromDegrees, toDegrees)
        wiggleAnimator.setDuration(durations[Random.nextInt(3)])
        Log.d("Lzd","time3 "+durations[Random.nextInt(3)])
        wiggleAnimator.repeatCount = ValueAnimator.INFINITE
        wiggleAnimator.repeatMode = ValueAnimator.REVERSE
        wiggleAnimator.start()
        return wiggleAnimator
    }

    private fun setImmersiveMode() {
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    private fun showSystemUI() {
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}