package com.zd.featuredemo.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.zd.featuredemo.R
import com.zd.featuredemo.databinding.ActivityScrollLayoutBinding
import com.zd.featuredemo.ui.list.ListAdapter
import com.zd.featuredemo.ui.list.model.ListModel
import java.util.Random

class ScrollActivity:AppCompatActivity() {

    lateinit var mBinding:ActivityScrollLayoutBinding

    var mList = mutableListOf<ListModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_scroll_layout)
        mList = getList();
        val adapter = ListAdapter(mList)


        mBinding.scrollRecyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.scrollRecyclerView.adapter = adapter
        mBinding.scrollBar.bindScrollView(mBinding.scrollRecyclerView)

        mBinding.scrollBack.setOnClickListener {
            finish()
        }

        mBinding.scrollAdd.setOnClickListener {
            mList.addAll(getList())
            adapter.notifyDataSetChanged();
        }
        mBinding.scrollMinus.setOnClickListener {
            mList.removeAt(0)
            adapter.notifyDataSetChanged();
        }

    }

    fun getList():MutableList<ListModel>{
        return mutableListOf<ListModel>().apply {
            for (i in 0..5) {
                add(ListModel(i,getRandColor()))
            }

        }
    }

    val random = Random()
fun getRandColor():String{
    var R:String
    var G:String
    var B:String
    R = Integer.toHexString(random.nextInt(256)).toUpperCase()
    G = Integer.toHexString(random.nextInt(256)).toUpperCase()
    B = Integer.toHexString(random.nextInt(256)).toUpperCase()

    R = if(R.length == 1) "0"+R else R
    G = if(G.length == 1) "0"+G else G
    B = if(B.length == 1) "0"+B else B
    return "#$R$G$B"
}

}