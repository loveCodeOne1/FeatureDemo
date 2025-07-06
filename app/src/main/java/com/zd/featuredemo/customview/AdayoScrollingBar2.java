package com.zd.featuredemo.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.zd.featuredemo.App;
import com.zd.featuredemo.R;

import java.util.List;

public class AdayoScrollingBar2 extends View {
    private static final String TAG = AdayoScrollingBar2.class.getSimpleName();
    private Paint containerPaint;
    private Paint trackPaint;
    private Paint thumbPaint;
    private float thumbY;
    private float dragThumbY;

    private float containerTop;
    private float containerBottom;
    private float containerLeft;
    private float containerRight;

    private float trackHeight;
    private float thumbHeight;
    private float trackTop;
    private float trackBottom;
    private float trackLeft;
    private float trackRight;
    private float thumbLeft;
    private float thumbRight;

    private float lastY;
    private boolean isDragging = false;
    private List<String> stringList = null;



    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ScrollView scrollView;
    OnScrollChangeListener scrollingBarListener;
    private Configuration mLastConfig;

    public AdayoScrollingBar2(Context context) {
        this(context, null);
    }

    public AdayoScrollingBar2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdayoScrollingBar2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLastConfig = new Configuration(getResources().getConfiguration());
        init();
    }

    @Override
    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        this.scrollingBarListener = onScrollChangeListener;
    }

    // 将 dp 转换为 px
    private int dpToPx(int dp) {
        //LogUtils.d(TAG, "dpToPx() called with: dp = [" + dp + "]");
        float density = getContext().getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //LogUtils.d(TAG, "onMeasure: ");
        // 获取固定的宽度值（20dp）
        int fixedWidth = dpToPx(20);
        // 获取高度的测量规格
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(fixedWidth, height);
    }

    OnScrollChangeListener onScrollChangeListener;

    @SuppressLint("ClickableViewAccessibility")
    public void attachToScrollView(ScrollView scrollView) {
        //LogUtils.d(TAG, "attachToScrollView: " + scrollView);
            super.setVisibility(VISIBLE);
        clearAttach();
        this.scrollView = scrollView;
        if (scrollView == null) {
            return;
        }

        setThumbPosition(0);
//        setVisibility(INVISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            scrollView.setVerticalScrollbarThumbDrawable(null);
            scrollView.setVerticalScrollbarTrackDrawable(null);
        }
        onScrollChangeListener = new OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (AdayoScrollingBar2.this.scrollingBarListener != null) {
                    AdayoScrollingBar2.this.scrollingBarListener.onScrollChange(v, scrollX, scrollY, oldScrollX, oldScrollY);
                }

                if (AdayoScrollingBar2.this.scrollView != null && !isDragging) {
                    updateScrollIndicator(scrollView, scrollY);
                }
            }
        };

        // 设置 ScrollView 的滚动监听器
        scrollView.setOnScrollChangeListener(onScrollChangeListener);

//        this.setOnTouchListener((v, event) -> {
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    isDragging = true;
//                    lastY = event.getY();
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    if (this.scrollView == null) {
//                        return true;
//                    }
//                    if (isDragging) {
//                        float top ;
//                        //垂直偏移量
//                        float deltaY = event.getY() - lastY;
//                        //根据偏移量计算出新的thumbY到顶部的距离
//                        float newThumbY = thumbY + deltaY;
//
//
//
//                        float trackHeight = this.getTrackHeight();
//                        float trackBottom = this.getTrackBottom();
////                        float thumbHeight = this.getThumbHeight();
//                        Log.d("ZD_TEST " ,
//                                "event.getY: " +event.getY()+
//                                " lastY: " +lastY+
//                                " event.getY - lastY: " +(event.getY() - lastY)+
//                                " thumbY: " +thumbY+
//                                "" +
//                                "");
//
//
//
//                        top = getHeight() * (newThumbY / maxLength);
//                        //通过比例计算出thumb高度
//                        float thumbHeight =  getHeight() * scrollHeightRatio;
//
//
//                        if (newThumbY >= 0 && newThumbY <= maxLength - scrollView.getHeight() + scrollView.getPaddingBottom()) {
//                                this.setThumbPosition(newThumbY);
//////
////                            maxLength = scrollView.getChildAt(0).getHeight();
//                            int currentScrollDistance = (int) ((newThumbY / (trackBottom - thumbHeight)) * maxLength);
//////                            LogUtils.w("<<<3>>>", "333rrrr recyclerView.scrollToPosition::" + deltaY * (maxLength / (trackBottom - thumbHeight))
//////                                    + ", currentScrollDistance::" + currentScrollDistance
//////                                    + ", totalScrollDistance::" + maxLength
//////                                    + ", deltaY::" + deltaY
//////                                    + ", trackBottom::" + trackBottom + ", thumbHeight::" + thumbHeight);
//////                            // 使用 scrollBy 方法滚动 RecyclerView
//////                            LogUtil.d("deltaY: "+deltaY+" , 22："+(deltaY * (maxLength / (trackBottom))));
//                            int i = scrollView.getHeight() + scrollView.getPaddingBottom();
//                            int i1 = (int) (newThumbY / i) * getHeight();
////                            int i2 = (int) (deltaY * ((scrollView.getHeight() + scrollView.getPaddingBottom()) / trackBottom - thumbHeight));
////                            int i2 = (int) (deltaY * (maxLength - (scrollView.getHeight() + scrollView.getPaddingBottom()) / trackBottom - thumbHeight));
////                            int i2 = (int) (deltaY * (maxLength - (scrollView.getHeight() + scrollView.getPaddingBottom()) / trackBottom - thumbHeight));
//                            scrollView.scrollBy(0,(int)((deltaY) * (maxLength / (getHeight()* 1.0f))));
//                        }else {
//
//                        }
//                        lastY = event.getY();
//
//                    }
//                    break;
//                case MotionEvent.ACTION_UP:
//                    isDragging = false;
//                    break;
//            }
//            return true;
//        });
    }

    private void clearAttach() {
        if (this.scrollView != null) {
            this.scrollView.setOnScrollChangeListener(null);
        }
        if (this.scrollingBarListener != null) {
            this.scrollingBarListener = null;
        }
        if (this.recyclerView != null) {
            //zhiyu be forced
            if (this.adapter != null) {// && this.adapter != recyclerView.getAdapter()) {
                try {
                    this.adapter.unregisterAdapterDataObserver(observer);
                    //this.recyclerView.getAdapter().unregisterAdapterDataObserver(observer);
                } catch (Exception e) {
                }
            }
            this.recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(globalLayoutListener);
            this.recyclerView.removeOnScrollListener(onScrollListener);
        }
    }

    RecyclerView.AdapterDataObserver observer;
    ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;
    RecyclerView.OnScrollListener onScrollListener;


    private float lastPos = -1;
//    private float deltaY = -1;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int barStart = getWidth() - getPaddingEnd() * 2;
        int barEnd = getWidth();
//        double scaleSize = maxLength / (scrollView.getHeight()+scrollView.getPaddingBottom() * 1.0f);
//        int barTop = (int) (scrollView.getScrollY()/scaleSize);
//        int barBottom = (int) (barTop + scrollView.getChildAt(0).getHeight() / scaleSize);
        int actionMasked = ev.getActionMasked();
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN: {
//                thumbY = scrollView.getScrollY();
                isDragging = true;
                lastY = ev.getY();
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                // 1. 对象是thumb， 需要对thumb的位置进行判断
                if (isDragging) {
                    float deltaY = ev.getY() - lastY;
                    float newThumbY = deltaY + thumbY;
                    if(newThumbY > 0 && newThumbY < trackBottom - trackBottom * scrollHeightRatio){
                        setThumbPosition(newThumbY);
                        scrollView.scrollBy(0,(int) (deltaY * maxLength/getHeight()) );
                    }
                    lastY = ev.getY();
                }
                return true;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isDragging = false;
                break;
        }
        return super.onTouchEvent(ev);

    }

    int maxLength;
    private void updateScrollIndicator(ScrollView scrollView, int scrollY) {
        //LogUtils.d(TAG, "updateScrollIndicator() called with: scrollView = [" + scrollView + "], scrollY = [" + scrollY + "]");

        float currentScrollDistance = scrollY;

//        float thumbHeight = this.getThumbHeight();
//        float trackHeight = this.getTrackHeight();
//        float trackBottom = this.getTrackBottom();

     try {
         setVisibility(scrollView.getHeight() < maxLength ? VISIBLE : INVISIBLE);
     }catch (Exception e){

     }
//        float thumbY1 = (currentScrollDistance * (trackBottom - thumbHeight)) / (maxLength - trackBottom);


//        LogUtils.w("<<<2>>>", "currentScrollDistance::" + currentScrollDistance + "，totalScrollDistance::" + maxLength);
//        if (currentScrollDistance + scrollView.getHeight() >= maxLength - 1) {//到底矫正
//            thumbY1 = trackBottom - thumbHeight;
//        }
//        if (thumbY1 > this.getHeight() - thumbHeight) {
//            thumbY1 = this.getHeight() - thumbHeight;
//        }
        thumbY = (scrollY*1.0f/maxLength)*getHeight();
        this.setThumbPosition(thumbY);
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if ((newConfig.diff(mLastConfig) & ActivityInfo.CONFIG_UI_MODE) > 0) {
            if (trackPaint != null) {
                trackPaint.setColor(Color.RED); // 轨道颜色
            }
            if (trackPaint != null) {
                thumbPaint.setColor(Color.WHITE); // 滑块颜色
            }
        }
        mLastConfig = new Configuration(newConfig);
    }


    private void init() {
        //LogUtils.d(TAG, "init: ");
        containerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        containerPaint.setColor(Color.TRANSPARENT);
        trackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        trackPaint.setColor(Color.RED); // 轨道颜色
        thumbPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        thumbPaint.setColor(Color.WHITE); // 滑块颜色
        trackHeight = 600f;
        thumbHeight = 80f;//80f;

        containerLeft = 0;
        containerTop = 0;
//        containerRight = isInEditMode() ? getResources().getDimension(R.dimen.uix_spacing_s) : UIX.getDimension(R.dimen.uix_spacing_s);
//        containerBottom = getHeight();

        trackTop = 0;
        trackBottom = getHeight();
        trackLeft = /*isInEditMode() ? getResources().getDimension(R.dimen.uix_spacing_3xs) : UIX.getDimension(R.dimen.uix_spacing_3xs);*/ getResources().getDimension(R.dimen.spacing_8xs);
        trackRight = /*isInEditMode() ? getResources().getDimension(R.dimen.uix_spacing_2xs) : UIX.getDimension(R.dimen.uix_spacing_2xs);*/ getResources().getDimension(R.dimen.spacing_12xs);

        thumbLeft = /*isInEditMode() ? getResources().getDimension(R.dimen.uix_spacing_3xs) : UIX.getDimension(R.dimen.uix_spacing_3xs);*/ getResources().getDimension(R.dimen.spacing_8xs);
        thumbRight = /*isInEditMode() ? getResources().getDimension(R.dimen.uix_spacing_2xs) : UIX.getDimension(R.dimen.uix_spacing_2xs);*/ getResources().getDimension(R.dimen.spacing_12xs);
        thumbY = trackTop;

//        if (!UIX.getScrollingBarShowEnable()) {
            super.setVisibility(GONE);
//        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //LogUtils.d(TAG, "onSizeChanged() called with: w = [" + w + "], h = [" + h + "], oldw = [" + oldw + "], oldh = [" + oldh + "]");
        trackBottom = h;
        maxLength = scrollView.getChildAt(0).getHeight();
        //trackBottom 1400.0 , maxLength : 13720 getHeight :1400 thumbHeight = 178.57143 maxScrollY = 11970
    }

    float  scrollHeightRatio;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //canvas.drawRect(containerLeft, containerTop, containerRight, containerBottom, containerPaint);
        float top;
       if(maxLength == 0){
           top = 0f;
       }else {
              //求出每段thumb到顶部的距离
               top = getHeight() * (thumbY / getHeight());
       }
       //因为底部设置了padding，需要减去
        int bindViewHeight = scrollView.getHeight() - scrollView.getPaddingBottom() - scrollView.getPaddingTop();

       scrollHeightRatio = maxLength == 0 ? 1f:(float) bindViewHeight / maxLength;


        float thumbY1 = thumbY * ((float) getHeight() / maxLength);
        RectF rectTrackF = new RectF(trackLeft, trackTop, trackRight, trackBottom);
        //canvas.drawRect(trackLeft, trackTop, trackRight, trackBottom, trackPaint);
        canvas.drawRoundRect(rectTrackF, 4, 4, trackPaint);
        RectF rectF = new RectF(thumbLeft, top, thumbRight, top+getHeight()*scrollHeightRatio);
//        LogUtil.d("thumbLeft" + thumbLeft + " ,  thumbY," + thumbY + " thumbRight , " + thumbRight + ", thumbY" + thumbY + ", thumbHeight" + (top + scrollHeightRatio * getHeight()));

        canvas.drawRoundRect(rectF, 4, 4, thumbPaint);
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                setVisibility(INVISIBLE);
            }
        }
    };

    //ValueAnimator animator;
    public void setThumbPosition(float y) {
        //LogUtils.d(TAG, "setThumbPosition() called with: y = [" + y + "]");
        if (!isDragging) { //touch时不执行动画
            if (handler.hasMessages(1)) {
                handler.removeMessages(1);
            }
            handler.sendEmptyMessageDelayed(1, 3000);

//      if (animator != null) {
//        float currentFraction = animator.getAnimatedFraction();
//        animator.cancel();
//
//        // 保持相同的动画时长
//        animator = ValueAnimator.ofFloat(thumbY, y);
//        animator.setDuration(100); // 设置动画持续时间
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//          @Override
//          public void onAnimationUpdate(ValueAnimator animation) {
//            thumbY = (float) animation.getAnimatedValue();
//            invalidate(); // 触发视图重绘
//          }
//        });
//
//        // 设置当前进度以确保动画从正确的位置开始
//        animator.setCurrentFraction(currentFraction);
//        animator.start();
//      }
            thumbY = y;
            invalidate();
        } else {
            if (handler.hasMessages(1)) {
                handler.removeMessages(1);
            }
            Log.w("<<<2>>>", "recyclerView.scrollToPosition::" + thumbY);
            thumbY = y;
            invalidate();
        }
    }

    public float getThumbPosition() {
        //LogUtils.d(TAG, "getThumbPosition() returned: " + thumbY);
        return thumbY;
    }

    public float getThumbHeight() {
        //LogUtils.d(TAG, "getThumbHeight() returned: " + thumbHeight);
        return thumbHeight;
    }

    public float getTrackHeight() {
        //LogUtils.d(TAG, "getTrackHeight() returned: " + trackHeight);
        return trackHeight;
    }

    public float getTrackBottom() {
        //LogUtils.d(TAG, "getTrackBottom() returned: " + trackBottom);
        return trackBottom;
    }

    @Override
    public void setVisibility(int visibility) {
        //LogUtils.d(TAG, "setVisibility() called with: visibility = [" + visibility + "]");
            super.setVisibility(visibility);
    }
}
