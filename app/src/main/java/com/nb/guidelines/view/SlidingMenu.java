package com.nb.guidelines.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nb.guidelines.util.DeviceUtil;

/**
 * Created by admin on 2016/11/5.
 */
public class SlidingMenu extends HorizontalScrollView {
    private final String LOG_GET = "SlidingMenu";
    public final int SLIDINGMENU_WIDTH = DeviceUtil.dp2Px(300);
    public final int LEFT_TO_RIGHT = 0;
    public final int RIGHT_TO_LEFT = LEFT_TO_RIGHT + 1;
    public int orientation;
    float downX = 0;
    float upX = 0;
    private LinearLayout mSlidingContentView;
    private RelativeLayout mMainContentView;

    public SlidingMenu(Context context) {
        super(context);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LinearLayout rootView = (LinearLayout) getChildAt(0);
        mSlidingContentView = (LinearLayout) rootView.getChildAt(0);
        mMainContentView = (RelativeLayout) rootView.getChildAt(1);
        //侧滑菜单部分
        LinearLayout.LayoutParams slidingMenuParams = (LinearLayout.LayoutParams) rootView.getChildAt(0).getLayoutParams();
        slidingMenuParams.height = LinearLayout.LayoutParams.MATCH_PARENT;
        slidingMenuParams.width = SLIDINGMENU_WIDTH;

        //主内容部分
        LinearLayout.LayoutParams mainContentParams = (LinearLayout.LayoutParams) rootView.getChildAt(1).getLayoutParams();
        mainContentParams.width = DeviceUtil.getScreenWidth();
        mainContentParams.height = LinearLayout.LayoutParams.MATCH_PARENT;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        scrollTo(SLIDINGMENU_WIDTH, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if ((ev.getX() - downX) > DeviceUtil.dp2Px(10)) {
                    orientation = LEFT_TO_RIGHT;
                } else if ((downX - ev.getX()) > DeviceUtil.dp2Px(10)) {
                    orientation = RIGHT_TO_LEFT;
                }
                break;
            case MotionEvent.ACTION_UP:
                upX = ev.getX();
                //LEFT_TO_RIGHT
                if (upX > downX) {
                    if ((upX - downX) >= DeviceUtil.dp2Px(150)) {
                        mySmoothScrollTo(0, 0);
                    } else if (getScrollX() != 0) {
                        mySmoothScrollTo(SLIDINGMENU_WIDTH, 0);
                    }
                }

                //RIGHT_TO_LEFT
                if (downX > upX) {
                    if ((downX - upX) >= DeviceUtil.dp2Px(100)) {
                        mySmoothScrollTo(SLIDINGMENU_WIDTH, 0);
                    } else if (getScrollX() < SLIDINGMENU_WIDTH) {
                        mySmoothScrollTo(0, 0);
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void mySmoothScrollTo(final int a, final int b) {
        post(new Runnable() {
            @Override
            public void run() {
                smoothScrollTo(a, b);
            }
        });
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (orientation == LEFT_TO_RIGHT) {
            float shrinkScale = 0.7f + 0.3f * (getScrollX() / (float) SLIDINGMENU_WIDTH);//1~0.3
            float magnifyScale = 0.7f + 0.3f * (1 - (getScrollX() / (float) SLIDINGMENU_WIDTH));//0.3-1
            Log.i(LOG_GET, "shrinkScale:" + shrinkScale);
            //主内容缩小
            mMainContentView.setScaleX(shrinkScale);
            mMainContentView.setScaleY(shrinkScale);
            mMainContentView.setAlpha(shrinkScale);
            //侧滑菜单部分放大
            mSlidingContentView.setScaleX(magnifyScale);
            mSlidingContentView.setScaleY(magnifyScale);
            mSlidingContentView.setAlpha(magnifyScale);
            mSlidingContentView.setTranslationX(getScrollX()/3);


        } else if (orientation == RIGHT_TO_LEFT) {
            float shrinkScale = 0.7f + 0.3f * (getScrollX() / (float) SLIDINGMENU_WIDTH);//0.3~1
            float magnifyScale = 0.7f + 0.3f * (1 - (getScrollX() / (float) SLIDINGMENU_WIDTH));//1-0.3
            //主内容放大
            mMainContentView.setScaleX(shrinkScale);
            mMainContentView.setScaleY(shrinkScale);
            mMainContentView.setAlpha(shrinkScale);
            //侧滑菜单部分缩小
            mSlidingContentView.setScaleX(magnifyScale);
            mSlidingContentView.setScaleY(magnifyScale);
            mSlidingContentView.setAlpha(magnifyScale);
            mSlidingContentView.setTranslationX(getScrollX()/3);
        }
    }
}
