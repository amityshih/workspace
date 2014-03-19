package com.example.demo01;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.*;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TabWidget;

public class MainActivity extends FragmentActivity {

	private TabHost mTabHost;
	private TabManager mTabManager;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //�]�m������
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//�]�m���̼Ҧ�
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);		

		setContentView(R.layout.activity_main);
        
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setWidege();
        
        mTabManager = new TabManager(this, mTabHost, R.id.realtabcontent);
        
        mTabHost.setCurrentTab(0);//�]�w�@�}�l�N����Ĥ@�Ӥ���
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment1").setIndicator("�s�D�T��"),
            Fragment1.class, null);
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment2").setIndicator("����b��"),
            Fragment2.class, null);
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment3").setIndicator("�ɮװT��"),
            Fragment3.class, null);
        
        mTabManager.addTab(
            mTabHost.newTabSpec("Fragment4").setIndicator("���ʰT��"),
            Fragment4.class, null);
        
        
        DisplayMetrics dm = new DisplayMetrics();
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
