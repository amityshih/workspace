package com.example.demo01;


import android.os.Bundle;
import android.support.v4.app.*;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;

public class MainActivity extends FragmentActivity {

	private TabHost mTabHost;
	private TabManager mTabManager;
 	
 	public String f5param;
 	public int f5FirstVisibleItem;
 	public int f5FirstVisibleItemPer;
 	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //設置為全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_main);
        
        setWidege();

        mTabManager = new TabManager(this, mTabHost, R.id.realtabcontent);

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
         mTabManager.addTab(
            mTabHost.newTabSpec("Fragment6").setIndicator("營業據點"),
            Fragment6.class, this, null);
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment7").setIndicator("線上交易"),
            Fragment7.class, this, null);
        
       if (savedInstanceState != null) {
          mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
          f5param=savedInstanceState.getString("f5param");
          f5FirstVisibleItemPer=savedInstanceState.getInt("f5FirstVisibleItem");
       }	
       
 /*       DisplayMetrics dm = new DisplayMetrics();
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
        }*/
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
    
   @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("tab", mTabHost.getCurrentTabTag());
        if (this.f5param != null){
           	savedInstanceState.putString("f5param", this.f5param);
        }
         else {
        	savedInstanceState.putString("f5param", "in");
        }	
      	savedInstanceState.putInt("f5FirstVisibleItem", this.f5FirstVisibleItem);
    }
}
