package com.zd.featuredemo.ui.activity

import android.animation.AnimatorSet
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bindView
import com.zd.featuredemo.R
import com.zd.featuredemo.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity(){

    private val binding:ActivityMainBinding by lazy{
        bindView(R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initListener()



        }

    private fun test():Boolean{
        return false
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
                1->{
                    val intent = Intent()
                    intent.setClassName("com.night.coroutinenetdemo","com.night.coroutinenetdemo.MainActivity2")
                    startActivity(intent)
                }
                2->{
                    startActivity(Intent(this,AnimationActivity::class.java))
                }
            }
        }


    }
}

