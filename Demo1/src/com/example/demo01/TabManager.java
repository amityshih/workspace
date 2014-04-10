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

    	//Log.d("Amity Log", "mTabHost.getCurrentTab() =" + mTabHost.getCurrentTab());
        
        //if (mTabHost.getCurrentTab() == -1  || mTabHost.getCurrentTab() == 0) {

        info.fragment = mActivity.getSupportFragmentManager().findFragmentByTag(tag);
        info.MainActv = MainActv;
    	/*Log.d("Amity Log", "info.fragment =" + info.fragment);
    	if (info.fragment != null) {
            FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
   		    ft.remove(info.fragment);
   		    ft.commit();
   	    	Log.d("Amity Log", "info.fragment  ft.commit() = " + info.fragment);
    	}*/

        if (info.fragment != null && !info.fragment.isDetached()) {
            FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
        	//Log.d("Amity Log", "info.fragment =" + info.fragment);
            ft.detach(info.fragment);
            /*if (info.fragment.isAdded()) {
               ft.remove(info.fragment);
            }*/
            ft.commit();
        }

       	
    	//Log.d("Amity Log", "mTabs.put");
        mTabs.put(tag, info);
    	//Log.d("Amity Log", "mActivity.getSupportFragmentManager().popBackStack()");
    	//mActivity.getSupportFragmentManager().popBackStack();
    	//Log.d("Amity Log", "mTabHost.addTabStart");
        //Log.d("Amity Log", "info.fragment.isAdded() =" + info.fragment.isAdded());
        //if (! info.fragment.isAdded()){
        //if (info.fragment == null) {
        //    Log.d("Amity Log", "else info.fragment =" + info.fragment);
        //ft.remove(info.fragment);

        mTabHost.addTab(tabSpec);
        //}
        //}
    	//Log.d("Amity Log", "mTabHost.addTabEnd");
        //} 
        
        return info;
    }

    @Override
    public void onTabChanged(String tabId) {
     //Log.d("Amity Log","tabId =" + tabId);	
     TabInfo newTab = mTabs.get(tabId);
        //Log.d("Amity Log", "onTabChanged");

        if (mLastTab != newTab) {
            FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
            if (mLastTab != null) {
                if (mLastTab.fragment != null) {
           		    //ft.remove(mLastTab.fragment);
                    ft.detach(mLastTab.fragment);
           		    //ft.remove(mLastTab.fragment);
           		    //ft.commit();

                }
            }

            if (newTab != null) {
                newTab.fragment = Fragment.instantiate(mActivity,
                        newTab.clss.getName(), newTab.args);
                if (newTab.fragment == null) {
                	//Log.d("Amity Log", "Add fragment = " + newTab.fragment);
                    ft.add(mContainerId, newTab.fragment, newTab.tag);
                    ft.detach(mLastTab.fragment);
                } else {
                	//Log.d("Amity Log", "Replace fragment = " + newTab.fragment);
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
