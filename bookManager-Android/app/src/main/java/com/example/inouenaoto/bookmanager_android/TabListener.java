package com.example.inouenaoto.bookmanager_android;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.ListFragment;


public class TabListener<T extends Fragment> implements ActionBar.TabListener {
    private Fragment mFragment;
    private final Activity mActivity;
    private final String mTag;
    private int mTabJudge;

    private int LIST_TAG_NUMBER = 1;
    private int SETTING_TAG_NUMBER = 2;

    //コンストラクタ
    public TabListener(Activity activity, String tag, Class<T> clz) {
        mActivity = activity;
        mTag = tag;
        mFragment = mActivity.getFragmentManager().findFragmentByTag(mTag);
    }

    //タブが選択されたとき
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        if (mTag == "listTag") {
            mTabJudge = LIST_TAG_NUMBER;
            switchFragment(mTabJudge);
        } else if (mTag == "settingTag") {
            mTabJudge = SETTING_TAG_NUMBER;
            switchFragment(mTabJudge);
        }
    }

    //タブの選択が解除されたとき
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (mFragment != null) {
            FragmentManager fm = mActivity.getFragmentManager();
            fm.beginTransaction().detach(mFragment).commit();
        }
    }

    //選択されているタブが選択されたとき
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    public void switchFragment(int judgeNumber) {
        Fragment fragment = new Fragment();
        if(judgeNumber == 1) {
            fragment = new BookListFragment();
        }
        else if(judgeNumber == 2) {
            fragment = new SettingFragment();
        }
        FragmentManager manager = mActivity.getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}