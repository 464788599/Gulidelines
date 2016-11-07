package com.nb.guidelines.util;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by admin on 2017/11/5.
 */
public class ShellUtil {

    public static String getCurrentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager == null) {
            return "";
        }
        for (ActivityManager.RunningAppProcessInfo info : activityManager.getRunningAppProcesses()) {
            if (info.pid == pid){
                return info.processName;
            }
        }
        return "";
    }
}
