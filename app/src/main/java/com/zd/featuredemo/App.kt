package com.zd.featuredemo

import android.app.Application
import android.content.Context
import androidx.recyclerview.widget.ListAdapter

class App:Application() {
    lateinit var context: Context

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        context = this
    }
    override fun onCreate() {
        super.onCreate()
    }
}