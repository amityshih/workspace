package tw.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;

public class Main extends Activity 
	implements OnClickListener {

	private Button mBtnOpenUrl,
	   			   mBtnGoBack,
	   			   mBtnGoForward,
	   			   mBtnStop,
	   			   mBtnReload;

	private EditText mEdtUrl;

	private WebView mWebView;

	/** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ����{���i�H�b���D�C��ܾ�i�צC
		// ���O�q���u������������ݰj��
//      requestWindowFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.main);

        // ���O�q���{�� - �����������ݰj��
		setProgressBarIndeterminateVisibility(false);

		setupViewComponent();
    }

	private void setupViewComponent() {
		mBtnOpenUrl = (Button)findViewById(R.id.btnOpenUrl);
		mBtnGoBack = (Button)findViewById(R.id.btnGoBack);
		mBtnGoForward = (Button)findViewById(R.id.btnGoForward);
		mBtnStop = (Button)findViewById(R.id.btnStop);
		mBtnReload = (Button)findViewById(R.id.btnReload);
		mEdtUrl = (EditText)findViewById(R.id.edtUrl);
		mWebView = (WebView)findViewById(R.id.webView);

		// �ϥΦۭq�� MyWebViewClient �i�H�z��b�{����
		// �s���������A�άO�Ұʥ~�����s����
		mWebView.setWebViewClient(new MyWebViewClient()
						.setupViewComponent(this, 
											mWebView,
											mBtnGoBack, 
											mBtnGoForward, 
											mBtnReload,
											mBtnStop));
		mWebView.setWebChromeClient(new WebChromeClient() {
			   public void onProgressChanged(WebView view, int progress) {
				     // Activity�MWebViews���i�׭ȨϥΤ��P����ܭȡA
				     // �ҥH�������W100�A���F100%�i�צC�|�۰ʮ���
				     setProgress(progress * 100);
				   }
				 });


		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(true);
		webSettings.setBuiltInZoomControls(true);

		mBtnOpenUrl.setOnClickListener(this);
    	mBtnGoBack.setOnClickListener(this);
    	mBtnGoForward.setOnClickListener(this);
    	mBtnStop.setOnClickListener(this);
		mBtnReload.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
    	case R.id.btnOpenUrl:
    		mWebView.loadUrl(mEdtUrl.getText().toString());
    		break;
    	case R.id.btnGoBack:
    		mWebView.goBack();
    		mEdtUrl.setText(mWebView.getUrl());
    		break;
    	case R.id.btnGoForward:
    		mWebView.goForward();
    		mEdtUrl.setText(mWebView.getUrl());
    		break;
    	case R.id.btnReload:
    		mWebView.reload();
    		break;
    	case R.id.btnStop:
    		mWebView.stopLoading();

    		// ���O�q���{�� - �����������ݰj��
    		setProgressBarIndeterminateVisibility(false);
    		setTitle(R.string.app_name);
    		mBtnReload.setEnabled(true);
    		mBtnStop.setEnabled(false);
    		break;
		}
	}
}