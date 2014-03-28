package com.givemepass.FragmentTabs;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TabHost;
import android.widget.TabWidget;

/**
 * This demonstrates how you can implement switching between the tabs of a
 * TabHost through fragments.  It uses a trick (see the code below) to allow
 * the tabs to switch between fragments instead of simple views.
 */
public class FragmentTabs extends FragmentActivity {
    private TabHost mTabHost;
    private TabManager mTabManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.fragment_tabs);
        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();
        
        mTabManager = new TabManager(this, mTabHost, R.id.realtabcontent);
        
        mTabHost.setCurrentTab(0);
        mTabManager.addTab(mTabHost.newTabSpec("Fragment1").setIndicator("Fragment1",this.getResources().getDrawable(R.drawable.butterfly)),
        		Fragment1.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("Fragment2").setIndicator("Fragment2",this.getResources().getDrawable(R.drawable.cloud_sun)),
        		Fragment2.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("Fragment3").setIndicator("Fragment3",this.getResources().getDrawable(R.drawable.pink_flower)),
        		Fragment3.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("Fragment4").setIndicator("Fragment4",this.getResources().getDrawable(R.drawable.tree)),
        		Fragment4.class, null);

        
        
        DisplayMetrics dm = new DisplayMetrics();   
        getWindowManager().getDefaultDisplay().getMetrics(dm); //�����o�ù��ѪR��  
        int screenWidth = dm.widthPixels;   //���o�ù����e
           
           
        TabWidget tabWidget = mTabHost.getTabWidget();   //���otab������
        int count = tabWidget.getChildCount();   //���otab���������X��
        if (count > 3) {   //�p�G�W�L�T�ӴN�ӳB�z�ư�
            for (int i = 0; i < count; i++) {   
                tabWidget.getChildTabViewAt(i).setMinimumWidth((screenWidth) / 3);//�]�w�C�@�Ӥ����̤p���e��   
            }   
        }

    }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK){
			getSupportFragmentManager().popBackStack();
		}
		return true;
	}
    
}