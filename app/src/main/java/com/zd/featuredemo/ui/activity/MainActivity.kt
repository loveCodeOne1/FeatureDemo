package com.zd.featuredemo.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import bindView
import com.zd.featuredemo.R
import com.zd.featuredemo.databinding.ActivityMainBinding
import com.zd.featuredemo.ui.fragment.MatchFragment

class MainActivity: AppCompatActivity(){

    private val binding:ActivityMainBinding by lazy{
        bindView(R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()



    }

    private fun initListener() {
        binding.tvFun.setOnClickListener {
            Toast.makeText(this,"gong",Toast.LENGTH_SHORT).show()
        }
    }

    private fun initView() {
        val arrayAdapter = ArrayAdapter<String>(
            this,
            R.layout.home_recycler_item,
            resources.getStringArray(R.array.function)
        )
        binding.listview.adapter = arrayAdapter

        binding.listview.setOnItemClickListener { parent, view, position, id ->
            when(position){
                0-> {
                    startActivity(Intent(this,MatchActivity::class.java))
                }
            }
        }


    }
}

