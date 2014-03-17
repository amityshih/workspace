package com.example.demo01;

import android.os.Bundle;
import android.support.v4.app.*;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabWidget;

public class MainActivity extends FragmentActivity {

	private TabHost mTabHost;
	private TabManager mTabManager;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setWidege();
        
        mTabManager = new TabManager(this, mTabHost, R.id.realtabcontent);
        
        mTabHost.setCurrentTab(0);//設定一開始就跳到第一個分頁
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment1").setIndicator("新聞訊息"),
            Fragment1.class, null);
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment2").setIndicator("基金淨值"),
            Fragment2.class, null);
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment3").setIndicator("檔案訊息"),
            Fragment3.class, null);
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment4").setIndicator("活動訊息"),
            Fragment4.class, null);
        
        
        DisplayMetrics dm = new DisplayMetrics();
      //先取得螢幕解析度  
        getWindowManager().getDefaultDisplay().getMetrics(dm);
      //取得螢幕的寬
        int screenWidth = dm.widthPixels;
        
      //取得tab的物件
        TabWidget tabWidget = mTabHost.getTabWidget();
      //取得tab的分頁有幾個
        int count = tabWidget.getChildCount();
        
        //假設可以看到三個Tab
        if (count > 3) {   
            for (int i = 0; i < 3; i++) {   
                tabWidget.getChildTabViewAt(i)
                      .setMinimumWidth((screenWidth)/3);//設定每一個分頁最小的寬度   
            }   
        }
    }

	
	private void setWidege()
	{
		mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();
	}
	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
