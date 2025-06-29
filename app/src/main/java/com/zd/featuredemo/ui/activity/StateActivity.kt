package com.zd.featuredemo.ui.activity

import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zd.featuredemo.R
import com.zd.featuredemo.databinding.ActivityStateBinding


class StateActivity : AppCompatActivity() {
    lateinit var mBD:ActivityStateBinding;
    private lateinit var windowManager: WindowManager
    private lateinit var floatingButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBD = DataBindingUtil.setContentView(this,R.layout.activity_state)


        mBD.stateParent.isEnabled = false
        mBD.stateParent.isActivated = true
        mBD.stateParent.isSelected = false

        mBD.stateChild.isEnabled = false
        mBD.stateChild.isActivated = true
        mBD.stateChild.isSelected = false



        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        params.gravity = Gravity.START or Gravity.TOP

        
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        
        // 添加调试信息
        Log.d("ZD", "按钮位置: x=${params.x}, y=${params.y}")
        Log.d("ZD", "屏幕尺寸: ${resources.displayMetrics.widthPixels} x ${resources.displayMetrics.heightPixels}")
        
        // 添加 View 到窗口
        floatingButton = Button(this)
        floatingButton.background = ColorDrawable(Color.RED)  // 改为红色更容易看到
        floatingButton.setTextColor(Color.WHITE)
        floatingButton.text = "左上角"
        floatingButton.layoutParams = ViewGroup.LayoutParams(100,100)
        
        // 添加按钮点击事件来显示位置信息
        floatingButton.setOnClickListener {
            mBD.stateParent.apply {
                Log.d("ZD","""
                 parent isActivated:  $isActivated
                  isSelected: $isSelected
                  isEnabled: $isEnabled
                """.trimIndent())
            }

            mBD.stateChild.apply {
                Log.d("ZD","""
                 child  isActivated:  $isActivated
                  isSelected: $isSelected
                  isEnabled: $isEnabled
                """.trimIndent())
            }
        }

        windowManager.addView(floatingButton, params)

    }
    
    override fun onDestroy() {
        super.onDestroy()
        // 移除悬浮窗口，避免内存泄漏
        if (::windowManager.isInitialized && ::floatingButton.isInitialized) {
            try {
                windowManager.removeView(floatingButton)
            } catch (e: Exception) {
                Log.e("ZD", "移除窗口失败: ${e.message}")
            }
            Log.d("ZD","getHeight "+ getStatusBarHeight())
        }
    }
    
    /**
     * 获取状态栏高度
     */
    private fun getStatusBarHeight(): Int {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId)
        } else {
            // 如果无法获取状态栏高度，使用默认值
            50
        }
    }
}