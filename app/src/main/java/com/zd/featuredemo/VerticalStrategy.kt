package com.zd.featuredemo

import android.graphics.RectF
import android.util.Log
import androidx.recyclerview.widget.RecyclerView


class VerticalStrategy: IOrientationStrategy {

    override fun computeRecyclerViewTotalLength(recyclerView: RecyclerView): Int {
        return recyclerView.computeVerticalScrollRange()
    }

    override fun computeRecyclerViewCurrentLength(recyclerView: RecyclerView): Int {
        return recyclerView.computeVerticalScrollOffset()
    }

    override fun computeRecyclerViewExtent(recyclerView: RecyclerView): Int {
        return recyclerView.computeVerticalScrollExtent()
    }

    override fun createSlider(
        maxLength: Int,
        currentLength: Int,
        width: Int,
        height: Int,
        bindView: RecyclerView?
    ): RectF {
        Log.d("Lzd","maxLength:$maxLength , currentLength:$currentLength height$height bindView${bindView?.height}")
        val top = if (maxLength == 0) {
            0f
        } else {
            height * (currentLength.toFloat() / maxLength)
        }

        Log.d("Lzd","top "+height * (currentLength.toFloat() / maxLength))
        val bindViewHeight = try {
            bindView!!.height.toFloat()
        } catch (e: Exception) {
            0f
        }
        Log.d("Lzd","bindViewHeight "+bindViewHeight)
        Log.d("Lzd","maxLength "+maxLength)


        var scrollWidthRatio = if (maxLength == 0) {
            1f
        } else {
            bindViewHeight / maxLength
        }

        if (scrollWidthRatio>=1f){
            scrollWidthRatio = 1f
        }
        Log.d("Lzd","scrollWidthRatio "+scrollWidthRatio)
        return RectF(0f, top, width.toFloat(), top + scrollWidthRatio * height)
    }

    override fun createFixedSlider(
        sliderLength: Float,
        width: Int,
        height: Int,
        bindView: RecyclerView?
    ): RectF {

        val sliderHeight = if (sliderLength<=1.0){
            height * sliderLength
        }else{
            sliderLength
        }

        val top = if (bindView == null || computeRecyclerViewTotalLength(bindView) == 0) {
            0f
        } else {
            (computeRecyclerViewCurrentLength(bindView).toFloat() / (computeRecyclerViewTotalLength(bindView) - computeRecyclerViewExtent(bindView))) * (height - sliderHeight)
        }
        return RectF(0f,top,width.toFloat(),top+sliderHeight)
    }

    override fun canScroll(bindView: RecyclerView?): Boolean {
        return (bindView?.canScrollVertically(1) == true) or (bindView?.canScrollVertically(-1) == true)
    }
}