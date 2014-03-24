package tw.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MeasureAppWindowSize extends Activity {

	final Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.measure_app_window_size);

		// ������post Runnable���󪺤覡�ҰʥD�{���A�o��
		// ���յ{�������j�p�� Activity�~�|�~�򧹦��إ�view���u�@�C
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent it = new Intent(
						MeasureAppWindowSize.this, Main.class);
				startActivity(it);
				finish();
			}				
		});
	}

}
