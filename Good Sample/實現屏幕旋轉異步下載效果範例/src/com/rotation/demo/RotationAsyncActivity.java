package com.rotation.demo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Androidʵ����Ļ��ת�첽����Ч��
 * @Description: Androidʵ����Ļ��ת�첽����Ч��

 * @File: RotationAsyncActivity.java

 * @Package com.rotation.demo

 * @Author Hanyonglu

 * @Date 2012-03-28 ����08:14:57

 * @Version V1.0
 */
public class RotationAsyncActivity extends Activity {
	// ������
	private ProgressBar progressBar=null;
	// �첽������
	private RotationAsyncTask asyncTask=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    
	    progressBar=(ProgressBar)findViewById(R.id.progress);
	    // ��ȡ����
	    asyncTask=(RotationAsyncTask)getLastNonConfigurationInstance();
	    
	    if (asyncTask==null) {
	    	asyncTask=new RotationAsyncTask(this);
	    	asyncTask.execute();
	    }else {
	    	asyncTask.attach(this);
	    	updateProgress(asyncTask.getProgress());
	    
	    	if (asyncTask.getProgress()>=100) {
	    		markAsDone();
	    	}
	    }
	}
	
	/**
	 * �������
	 */
	@Override
	public Object onRetainNonConfigurationInstance() {
		asyncTask.detach();
		
		return asyncTask;
	}
	  
	private void updateProgress(int progress) {
		progressBar.setProgress(progress);
	}
	  
	private void markAsDone() {
		findViewById(R.id.completed).setVisibility(View.VISIBLE);
	}
	 
	// �첽������
	private static class RotationAsyncTask extends AsyncTask<Void, Void, Void> {
		private RotationAsyncActivity activity=null;
		private int progress=0;
		
		/**
		 * Ĭ�ϵĹ�����
		 */
		public RotationAsyncTask() {
			// TODO Auto-generated constructor stub
		}
	    
		/**
		 * ���ι�����
		 * @param activity
		 */
	    public RotationAsyncTask(RotationAsyncActivity activity) {
	    	attach(activity);
	    }
	    
	    @Override
	    protected Void doInBackground(Void... unused) {
	    	for (int i=0;i<20;i++) {
	    		SystemClock.sleep(500);
	    		publishProgress();
	    	}
	      
	    	return null;
	    }
	    
	    @Override
	    protected void onProgressUpdate(Void... unused) {
	    	if (activity==null) {
	    		Log.w("RotationAsyncActivity", "onProgressUpdate()");
	    	}else {
	    		progress += 5;
	    		activity.updateProgress(progress);
	    	}
	    }
	    
	    @Override
	    protected void onPostExecute(Void unused) {
	    	if (activity==null) {
	    		Log.w("RotationAsyncActivity", "onPostExecute()");
	    	}else {
	    		activity.markAsDone();
	    	}
	    }
	    
	    protected void detach() {
	    	activity = null;
	    }
	    
	    protected void attach(RotationAsyncActivity activity) {
	    	this.activity = activity;
	    }
	    
	    protected int getProgress() {
	    	return progress;
	    }
	}
}
