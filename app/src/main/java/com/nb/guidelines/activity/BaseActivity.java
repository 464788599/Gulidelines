package com.nb.guidelines.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.nb.guidelines.app.GiApplication;

public class BaseActivity extends Activity {

    GiApplication app = GiApplication.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Runtime.getRuntime().gc();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    //封装findViewById
    protected <T extends View> T findViewById(Class<T> type, int res) {
        View view = findViewById(res);
        if (view != null) {
            return (T) view;
        }
        return null;
    }

    final public void setClickListener(int res, View.OnClickListener listener) {
        findViewById(res).setOnClickListener(listener);
    }

    final public void setClickListeners(int[] res, View.OnClickListener listener) {
        for (int id : res) {
            findViewById(id).setOnClickListener(listener);
        }
    }

}
