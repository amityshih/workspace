package com.example.demo01;

import java.util.HashMap;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.*;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class TabManager implements TabHost.OnTabChangeListener {
    private final FragmentActivity mActivity;
    private final TabHost mTabHost;
    private final int mContainerId;
    private final HashMap<String, TabInfo> mTabs = new HashMap<String, TabInfo>();
    TabInfo mLastTab;

    static final class TabInfo {
        private final String tag;
        private final Class<?> clss;
        private final Bundle args;
        private Fragment fragment;
        private MainActivity MainActv;

        TabInfo(String _tag, Class<?> _class, Bundle _args) {
            tag = _tag;
            clss = _class;
            args = _args;
        }
    }

    static class DummyTabFactory implements TabHost.TabContentFactory {
        private final Context mContext;

        public DummyTabFactory(Context context) {
            mContext = context;
        }

        @Override
        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }
    }

    public TabManager(FragmentActivity activity, TabHost tabHost, int containerId) {
        mActivity = activity;
        mTabHost = tabHost;
        mContainerId = containerId;
        mTabHost.setOnTabChangedListener(this);
    }

    public TabInfo addTab(TabHost.TabSpec tabSpec, Class<?> clss,MainActivity MainActv, Bundle args) {
        tabSpec.setContent(new DummyTabFactory(mActivity));
        String tag = tabSpec.getTag();

        TabInfo info = new TabInfo(tag, clss, args);


        info.fragment = mActivity.getSupportFragmentManager().findFragmentByTag(tag);
        info.MainActv = MainActv;

        if (info.fragment != null && !info.fragment.isDetached()) {
            FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
             ft.detach(info.fragment);
            ft.commit();
        }       	
        mTabs.put(tag, info);
        mTabHost.addTab(tabSpec);
        return info;
    }

    @Override
    public void onTabChanged(String tabId) {
     TabInfo newTab = mTabs.get(tabId);

        if (mLastTab != newTab) {
            FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
            if (mLastTab != null) {
                if (mLastTab.fragment != null) {
                    ft.detach(mLastTab.fragment);

                }
            }

            if (newTab != null) {
                newTab.fragment = Fragment.instantiate(mActivity,
                        newTab.clss.getName(), newTab.args);
                if (newTab.fragment == null) {
                    ft.add(mContainerId, newTab.fragment, newTab.tag);
                    ft.detach(mLastTab.fragment);
                } else {
                    mActivity.getSupportFragmentManager().popBackStack();
                    ft.replace(mContainerId, newTab.fragment);
                    ft.attach(newTab.fragment);
                    if (tabId == "Fragment5"){
                        Log.d("Amity Log", "tabId =" + tabId);
                    	Fragment5 f5 = (Fragment5)newTab.fragment;
                    	f5.MainActv = newTab.MainActv;
                    }
                }
            }
            
            mLastTab = newTab;
            ft.commit();
            mActivity.getSupportFragmentManager().executePendingTransactions();
        }
    
    }
}
