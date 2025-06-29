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
import com.zd.featuredemo.R

class FloatTestActivity : AppCompatActivity() {
    private lateinit var windowManager: WindowManager
    private lateinit var floatingButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_float_test)

        val params = WindowManager.LayoutParams(
            300,
            300,
            WindowManager.LayoutParams.TYPE_APPLICATION,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )
        params.gravity = Gravity.START or Gravity.TOP

        // 计算状态栏高度
        val statusBarHeight = getStatusBarHeight()
        Log.d("ZD", "状态栏高度: $statusBarHeight")

//        // 设置按钮位置在屏幕左上角（考虑状态栏）
//        params.x = 0
//        params.y = statusBarHeight

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
            Log.d("ZD", "按钮被点击，当前位置: x=${params.x}, y=${params.y}")
            // 点击时移动按钮到右下角测试
            params.x = 0
            params.y = 800
            windowManager.updateViewLayout(floatingButton, params)
            floatingButton.text = "右下角"
        }

// 添加 View 到窗口
        windowManager.addView(floatingButton, params)

        // 添加延迟检查按钮位置
        floatingButton.post {
            val location = IntArray(2)
            floatingButton.getLocationOnScreen(location)
            Log.d("ZD", "按钮实际屏幕位置: x=${location[0]}, y=${location[1]}")
        }
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