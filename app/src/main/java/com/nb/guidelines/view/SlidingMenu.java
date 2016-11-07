package com.nb.guidelines.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nb.guidelines.util.DeviceUtil;

/**
 * Created by admin on 2016/11/5.
 */
public class SlidingMenu extends HorizontalScrollView {
    private final String LOG_GET = "SlidingMenu";
    public final int LEFT_TO_RIGHT = 0;
    public final int RIGHT_TO_LEFT = LEFT_TO_RIGHT + 1;
    public int orientation;
    float downX = 0;
    float upX = 0;
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
        //侧滑菜单部分
        LinearLayout.LayoutParams slidingMenuParams = (LinearLayout.LayoutParams) rootView.getChildAt(0).getLayoutParams();
        slidingMenuParams.height = LinearLayout.LayoutParams.MATCH_PARENT;
        slidingMenuParams.width = DeviceUtil.dp2Px(300);

        //主内容部分
        LinearLayout.LayoutParams mainContentParams = (LinearLayout.LayoutParams) rootView.getChildAt(1).getLayoutParams();
        mainContentParams.width = DeviceUtil.getScreenWidth();
        mainContentParams.height = LinearLayout.LayoutParams.MATCH_PARENT;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        scrollTo(DeviceUtil.dp2Px(300), 0);
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
                    Log.i(LOG_GET,"LEFT_TO_RIGHT");
                } else if ((downX - ev.getX()) > DeviceUtil.dp2Px(10)) {
                    orientation = RIGHT_TO_LEFT;
                    Log.i(LOG_GET,"RIGHT_TO_LEFT");
                }
                break;
            case MotionEvent.ACTION_UP:
                upX = ev.getX();
                //LEFT_TO_RIGHT
                if (upX > downX) {
                    if ((upX - downX) >= DeviceUtil.dp2Px(150)) {

                        mySmoothScrollTo(0, 0);
                    } else {
                        mySmoothScrollTo(DeviceUtil.dp2Px(300), 0);
                    }
                }

                //RIGHT_TO_LEFT
                if (downX > upX) {
                    if ((downX - upX) >= DeviceUtil.dp2Px(100)) {
                        mySmoothScrollTo(DeviceUtil.dp2Px(300), 0);
                    } else {
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

    }
}
