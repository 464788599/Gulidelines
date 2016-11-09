package com.nb.guidelines.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.nb.guidelines.Manager.FragmentsManager;
import com.nb.guidelines.R;
import com.nb.guidelines.fragment.SafeFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        //添加Fragmet
        SafeFragment safeFragment = new SafeFragment();
        FragmentsManager.getIstance(this).addFragment(safeFragment,R.id.layout_fragment);
    }
}
