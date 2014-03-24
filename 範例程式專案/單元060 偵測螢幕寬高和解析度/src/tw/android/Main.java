package tw.android;

import android.app.*;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.*;

public class Main extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // ���o�˸m�ù����e�M��
        Display defDisp = getWindowManager().getDefaultDisplay();
        int dispWidth = defDisp.getWidth();
        int dispHeight = defDisp.getHeight();
        String s;
        if (dispWidth > dispHeight)
        	s = "��V�ù�";
        else if (dispWidth < dispHeight)
        	s = "�����ù�";
        else
        	s = "����οù�";
        Toast.makeText(Main.this, s + System.getProperty("line.separator") + "w = " + dispWidth + " h = " + dispHeight, 
        		Toast.LENGTH_LONG)
			.show();

        // ��ܵ{���i�ΰϰ쪺�e�M��
        Toast.makeText(Main.this, "appw = " + MyLayout.appWindowWidth + " apph = " + MyLayout.appWindowHeight, 
        		Toast.LENGTH_LONG)
			.show();

		// ���o�˸m�ù�����ڸѪR��
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        // �]�i�H�Q��dm.heightPixels�Mdm.widthPixels���o�ù����e�M��
		Toast.makeText(Main.this, "����dpi = " + dm.xdpi + " ����dpi = " + dm.ydpi, 
        		Toast.LENGTH_LONG)
			.show();

		// ���o�˸m�ù����ѪR�פ���
        int screenSizeClass = dm.densityDpi;
		switch(screenSizeClass){
		case DisplayMetrics.DENSITY_LOW:
			Toast.makeText(Main.this, "low density", 
	        		Toast.LENGTH_LONG)
				.show();
			break;
		case DisplayMetrics.DENSITY_MEDIUM:
			Toast.makeText(Main.this, "medium density", 
	        		Toast.LENGTH_LONG)
				.show();
			break;
		case DisplayMetrics.DENSITY_HIGH:
			Toast.makeText(Main.this, "high density", 
	        		Toast.LENGTH_LONG)
				.show();
			break;
		case DisplayMetrics.DENSITY_XHIGH:
			Toast.makeText(Main.this, "x-high density", 
	        		Toast.LENGTH_LONG)
				.show();
			break;
		}
    }

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);

		Intent it = new Intent(this, MeasureAppWindowSize.class);
		startActivity(it);
		finish();
	}
}