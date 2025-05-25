package com.zd.featuredemo.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import bindView
import com.zd.featuredemo.R
import com.zd.featuredemo.databinding.ActivityMatchBinding
import com.zd.featuredemo.ui.fragment.EVENT_POSITION
import com.zd.featuredemo.ui.fragment.MatchDetailFragment
import com.zd.featuredemo.ui.fragment.MatchFragment

/**
 * 比赛Activity
 */
class MatchActivity:AppCompatActivity() ,MatchFragment.OnTitleSelectedListener{

    private val binding:ActivityMatchBinding
    by lazy {
        bindView(R.layout.activity_match)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toString()
        if(savedInstanceState != null) return

//        val beginTransaction = supportFragmentManager.beginTransaction()
//        beginTransaction.add(R.)

        /* if(savedInstanceState == null){
             val beginTransaction = supportFragmentManager.beginTransaction()
             beginTransaction
                 .add(R.id.fragment_match, MatchFragment())
                 .add(R.id.fragment_match_detail,MatchDetailFragment())
                 .commit()


         }
 */
    }

    override fun onEventsSelected(position: Int) {
        Log.d("Lzd","onEventsSelected")
        val fragmentManager = supportFragmentManager
        val detailFragment = fragmentManager.findFragmentById(R.id.fragment_match_detail)
        if(detailFragment != null){
            Log.d("Lzd","table")
            (detailFragment as MatchDetailFragment).updateView(position)
        }else {
            Log.d("Lzd","no table")
            val matchDetailFragment = MatchDetailFragment()
            val arg = Bundle()
            arg.putInt(EVENT_POSITION,position)
            matchDetailFragment.arguments = arg
            val beginTransaction = fragmentManager.beginTransaction()
//            beginTransaction.replace(R.id.frag)
        }

    }
}