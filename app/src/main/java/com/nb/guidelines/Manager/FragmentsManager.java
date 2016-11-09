package com.nb.guidelines.Manager;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by admin on 2016/11/9.
 */
public class FragmentsManager {
    public static FragmentsManager mIstance;
    private final FragmentTransaction transaction;

    public static FragmentsManager getIstance(FragmentActivity context) {
        if (mIstance == null) {
            synchronized (FragmentsManager.class) {
                if (mIstance == null) {
                    mIstance = new FragmentsManager(context);
                }
            }
        }
        return mIstance;
    }

    private FragmentsManager(FragmentActivity context) {
        FragmentManager fragmentManager = context.getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
    }

    public void addFragment(Fragment fragment,int id) {
        transaction.add(id, fragment);
        transaction.commit();
    }

    public void removeFragment(Fragment fragment) {
        transaction.remove(fragment);
        transaction.commit();
    }

    public void replaceFragment(Fragment fragment,int id) {
        transaction.replace(id,fragment);
        transaction.commit();
    }

    public void hideFragment(Fragment fragment,int id) {
        transaction.hide(fragment);
        transaction.commit();
    }
}
