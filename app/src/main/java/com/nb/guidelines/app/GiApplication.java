package com.nb.guidelines.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;

import com.nb.guidelines.util.ShellUtil;

/**
 * Created by zhq on 2017/11/5.
 */
public class GiApplication extends Application {

    private static GiApplication sInstance;
    private long mInitialTime = System.currentTimeMillis();

    public static GiApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        String currentProcess = ShellUtil.getCurrentProcessName(this);
        if (TextUtils.isEmpty(currentProcess) || !currentProcess.contains(":")) {
            //没用运行，进行初始化工作
            initWork();
        } else {
            //已经运行
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //程序终止时执行
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //低内存时执行
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        //清理内存时执行
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //配置改变时执行

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    private void initWork() {
        initAsync();//初始化异步任务
        initSync();//初始化同步任务
    }

    private void initSync() {
    }

    private void initAsync() {
    }

    public long getInitialTme(){
        return mInitialTime;
    }
}
