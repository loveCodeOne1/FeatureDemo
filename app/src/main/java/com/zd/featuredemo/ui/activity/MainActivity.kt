package com.zd.featuredemo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import bindView
import com.zd.featuredemo.R
import com.zd.featuredemo.databinding.ActivityMainBinding
import com.zd.featuredemo.ui.adapter.MyRecyclerViewAdapter

class MainActivity: AppCompatActivity(){

    private val binding:ActivityMainBinding by lazy{
        bindView(R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toString()
        initView()
        initListener()
        initData()


        }
    data class User(var number:Int)

    private fun initData() {
        val list = listOf<User>(
            User(3),
            User(1),
            User(1),
            User(8),
            User(3),
            User(2),
            User(4),
        )
        val num = 10
        val min = Math.min(num, 100)
        println(min)

        // 使用stream流筛选number大于1小于9的user，并转换成list
        for ((index, user) in list.withIndex()) {
            print(index)
        }
        
        // 打印结果
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
        val myRecyclerViewAdapter = MyRecyclerViewAdapter()
        binding.scrollRecyclerView.adapter = myRecyclerViewAdapter
        binding.scrollRecyclerView.layoutManager = LinearLayoutManager(this)

        myRecyclerViewAdapter.setData(resources.getStringArray(R.array.function).toMutableList())
        binding.scrolingBar.attachToScrollView(binding.scrollView)

//        binding.scrollRecyclerView.setOnItemClickListener { parent, view, position, id ->
//            when(position){
//                0-> {
//                    startActivity(Intent(this,MatchActivity::class.java))
//                }
//                1->{
//                    val intent = Intent()
//                    intent.setClassName("com.zd.featuredemo","com.zd.featuredemo.ui.activity.StateActivity")
//                    startActivity(intent)
//                }
//                2->{
//                    startActivity(Intent(this,AnimationActivity::class.java))
//                }
//                3->{
//                    startActivity(Intent(this,TestActivity::class.java))
//                }
//            }
//        }


    }
}

