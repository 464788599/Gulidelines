package com.nb.guidelines.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.nb.guidelines.app.GiApplication;

/**
 * Created by admin on 2016/11/7.
 */
public class DeviceUtil {

    public static int dp2Px(int dp) {
        final float scale = GiApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2Dp(float px) {
        final float scale = GiApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static int sp2Px(float sp) {
        float fontScale = GiApplication.getInstance().getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }

    public static int px2Sp(float px) {
        float fontScale = GiApplication.getInstance().getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / fontScale + 0.5f);
    }

    public static int getScreenWidth(){
        WindowManager windowManager = (WindowManager) GiApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        return Math.min(displayMetrics.widthPixels,displayMetrics.heightPixels);
    }

    public static int getScreenHeight(){
        WindowManager windowManager = (WindowManager) GiApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        return Math.max(displayMetrics.widthPixels,displayMetrics.heightPixels);
    }
}
