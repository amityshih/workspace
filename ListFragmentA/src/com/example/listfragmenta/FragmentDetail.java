package com.example.listfragmenta;

import java.io.File;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentDetail extends Fragment {

	TextView textDetail;
	String mURL = "";   
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);										
	
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		//String detail =savedInstanceState.getString("KEY_DETAIL");		
		//Log.v("detail","onActivityCreated");		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_list_detail, null);
		textDetail = (TextView)view.findViewById(R.id.list_detial_textvw1);
		WebView myWebView = (WebView)view.findViewById(R.id.pageInfo);		
		Bundle bundle = getArguments();
		if(bundle != null){
			String detail =bundle.getString("KEY_DETAIL");		
			WebSettings websettings = myWebView.getSettings();
			websettings.setJavaScriptEnabled(true);
	    	websettings.setSupportZoom(true);  
	    	websettings.setBuiltInZoomControls(true);   
	    	websettings.setDomStorageEnabled(true);
	    	websettings.setAllowFileAccess(true);
	    	websettings.setUseWideViewPort(true);
	    	websettings.setLoadWithOverviewMode(true);	    	
	    	textDetail.setText(detail);
	    	if (detail.contains(".pdf")){
	    		myWebView.loadUrl("http://docs.google.com/gview?embedded=true&url="+detail );
	    	}else{
	    		myWebView.loadUrl(detail);
	    	}
	    	
		}
		
		myWebView.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				// TODO Auto-generated method stub
				super.onReceivedError(view, errorCode, description, failingUrl);
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				return super.shouldOverrideUrlLoading(view, url);
			}			
		});
		
		myWebView.setDownloadListener(new DownloadListener(){

			@Override
			public void onDownloadStart(String arg0, String arg1, String arg2,
					String arg3, long arg4) {
				// TODO Auto-generated method stub
				System.out.println("=========>¶}©l¤U¸ü url =" + arg0);
                Uri uri = Uri.parse(arg0);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);           
			}
			
		});
		
	    return view;		
	}
		
}
