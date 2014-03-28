package com.example.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
	private Button backBtn,nextBtn;
	private int page = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		backBtn = (Button)findViewById(R.id.back_button);
		nextBtn = (Button)findViewById(R.id.next_button);
		changeFragment(DetailsFragment.newInstance(page));
		backBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(page>1){
					changeFragment(DetailsFragment.newInstance(--page));
				}
				else{
					changeFragment(DetailsFragment.newInstance(page));
				}
			}
			
		});
		nextBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				changeFragment(DetailsFragment.newInstance(++page));
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void changeFragment(Fragment f) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_container, f);
		transaction.commitAllowingStateLoss();
	}
}
