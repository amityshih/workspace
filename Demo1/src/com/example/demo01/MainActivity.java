package com.example.demo01;

import com.example.demo01.TabManager.TabInfo;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.*;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	private TabHost mTabHost;
	private TabManager mTabManager;
 	private int CurrentTab=0;
 	
 	public String f5param;
 	public int f5FirstVisibleItem;
 	public int f5FirstVisibleItemPer;
 	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d("Amity Log", "onCreate");
        //Log.d("MainActivityonCreateStart", "MainActivityonCreateStart");
        //設置為全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//設置直屏模式
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);		

		setContentView(R.layout.activity_main);
        
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setWidege();
    	//Log.d("Amity Log", "mTabHost.getCurrentTab() =" + mTabHost.getCurrentTab());
    	
        //if (mTabHost.getCurrentTab() == -1 ) {
        //if (savedInstanceState == null){
        mTabManager = new TabManager(this, mTabHost, R.id.realtabcontent);
        //}
       
        //Log.d("Amity Log", "info.fragment.isAdded() =" + info.fragment.isAdded());

        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment1").setIndicator("基金介紹"),
            Fragment1.class, this,null);
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment2").setIndicator("投資月報"),
            Fragment2.class, this, null);
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment3").setIndicator("公告事項"),
            Fragment3.class, this, null);       
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment4").setIndicator("活動看板"),
            Fragment4.class, this, null);
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment5").setIndicator("基金淨值"),
            Fragment5.class, this, null);
        
//        info.MainActv = this;
        
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment6").setIndicator("營業據點"),
            Fragment6.class, this, null);
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment7").setIndicator("線上交易"),
            Fragment7.class, this, null);
        //}
        //}
	
       if (savedInstanceState != null) {
          mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
          f5param=savedInstanceState.getString("f5param");
          f5FirstVisibleItemPer=savedInstanceState.getInt("f5FirstVisibleItem");
   	      //Log.d("Amity Log", "f5FirstVisibleItemPer = " +  f5FirstVisibleItemPer);
       }	
       
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
        /*if (count > 3) {   
            for (int i = 0; i < 3; i++) {   
                tabWidget.getChildTabViewAt(i)
                      .setMinimumWidth((screenWidth)/3);//設定每一個分頁最小的寬度   
            }   
        }*/
        //Log.d("MainActivityonCreateEnd", "MainActivityonCreateEnd");
    }

	
	private void setWidege()
	{
		mTabHost = (TabHost)findViewById(android.R.id.tabhost);
		
		//Log.d( "Amity Log","mTabHost.getCurrentTab() =" + mTabHost.getCurrentTab());
        mTabHost.setup();
	}
	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
   @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        //Log.d("Amity Log", "onSaveInstanceState");
        savedInstanceState.putInt("CurrentTab", mTabHost.getCurrentTab());
        savedInstanceState.putString("tab", mTabHost.getCurrentTabTag());
        if (this.f5param != null){
           	savedInstanceState.putString("f5param", this.f5param);
        }
         else {
        	savedInstanceState.putString("f5param", "in");
        }	
      	savedInstanceState.putInt("f5FirstVisibleItem", this.f5FirstVisibleItem);

        //if (this.getSupportFragmentManager().findFragmentByTag("Fragment1") != null)
        //    savedInstanceState.putF("CurrentTab", mTabHost.getCurrentTab());
    }
 
    /*@Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        CurrentTab = savedInstanceState.getInt("CurrentTab");
        //Log.d("Amity Log", "onRestoreInstanceState");
        mTabHost.setCurrentTab(CurrentTab);//設定一開始就跳到第一個分頁
    }*/
    /*    @Override
  public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
     
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }*/

}
