package com.example.inouenaoto.bookmanager_android;

import android.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ListFragment;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getActionBar();

        setContentView(R.layout.activity_main);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab()
                .setText(R.string.book_list_title)
                .setTabListener(new TabListener<BookListFragment>(
                        this, "listTag", BookListFragment.class)));
        actionBar.addTab(actionBar.newTab()
                .setText(R.string.setting_title)
                .setTabListener(new TabListener<SettingFragment>(
                        this, "settingTag", SettingFragment.class)));
    }
}