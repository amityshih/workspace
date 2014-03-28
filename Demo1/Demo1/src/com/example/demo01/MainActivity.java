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
        //�]�m������
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_main);
        
        setWidege();

        mTabManager = new TabManager(this, mTabHost, R.id.realtabcontent);

        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment1").setIndicator("�������"),
            Fragment1.class, this,null);
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment2").setIndicator("�����"),
            Fragment2.class, this, null);
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment3").setIndicator("���i�ƶ�"),
            Fragment3.class, this, null);       
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment4").setIndicator("���ʬݪO"),
            Fragment4.class, this, null);
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment5").setIndicator("����b��"),
            Fragment5.class, this, null);
         mTabManager.addTab(
            mTabHost.newTabSpec("Fragment6").setIndicator("��~���I"),
            Fragment6.class, this, null);
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment7").setIndicator("�u�W���"),
            Fragment7.class, this, null);
        
       if (savedInstanceState != null) {
          mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
          f5param=savedInstanceState.getString("f5param");
          f5FirstVisibleItemPer=savedInstanceState.getInt("f5FirstVisibleItem");
       }	
       
 /*       DisplayMetrics dm = new DisplayMetrics();
      //�����o�ù��ѪR��  
        getWindowManager().getDefaultDisplay().getMetrics(dm);
      //���o�ù����e
        int screenWidth = dm.widthPixels;
        
      //���otab������
        TabWidget tabWidget = mTabHost.getTabWidget();
      //���otab���������X��
        int count = tabWidget.getChildCount();
        
        //���]�i�H�ݨ�T��Tab
        if (count > 3) {   
            for (int i = 0; i < 3; i++) {   
                tabWidget.getChildTabViewAt(i)
                      .setMinimumWidth((screenWidth)/3);//�]�w�C�@�Ӥ����̤p���e��   
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
