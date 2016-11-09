package com.nb.guidelines.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nb.guidelines.R;
import com.nb.guidelines.app.GiApplication;

/**
 * Created by admin on 2016/11/9.
 */
public class SafeFragment extends BaseFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(GiApplication.getInstance()).inflate(R.layout.layout_fragment_safe,null);
        return view;
    }
}
