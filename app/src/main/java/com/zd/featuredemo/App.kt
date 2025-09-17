package com.zd.featuredemo

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.ListAdapter

class App:Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        context = this
    }
    override fun onCreate() {
        super.onCreate()
        Log.d("App","onCreate")
    }

    companion object{
        lateinit var context: Context
    }
}