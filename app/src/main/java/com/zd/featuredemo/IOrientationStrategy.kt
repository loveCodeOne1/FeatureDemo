package com.zd.featuredemo

import android.graphics.RectF
import androidx.recyclerview.widget.RecyclerView

interface IOrientationStrategy {

    fun computeRecyclerViewTotalLength(recyclerView: RecyclerView): Int

    fun computeRecyclerViewCurrentLength(recyclerView: RecyclerView): Int

    fun computeRecyclerViewExtent(recyclerView: RecyclerView):Int

    fun createSlider(
        maxLength: Int,
        currentLength: Int,
        width: Int,
        height: Int,
        bindView: RecyclerView?
    ): RectF

    fun createFixedSlider(sliderLength:Float ,
                          width: Int,
                          height: Int,
                          bindView: RecyclerView?): RectF

    fun canScroll(bindView: RecyclerView?): Boolean

    companion object{

        fun createStrategy(orientation: Int): IOrientationStrategy {
            return VerticalStrategy()
        }
    }
}