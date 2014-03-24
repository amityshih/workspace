package tw.android;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TabHost.TabSpec;

public class Main extends Activity {

	private Button btnDoSug;
    private RadioGroup radGSex, radGAge;
    private RadioButton radBtnAgeRng1, radBtnAgeRng2, radBtnAgeRng3;
    private TextView txtResult1;

	private TextView txtComPlay, txtResult2;
    private Button btnScissors;
    private Button btnStone;
    private Button btnNet;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setupViewComponent();
    }
    
    private void setupViewComponent() {
        // �q�귽���OR�����o��������
    	TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
    	tabHost.setup();

    	TabSpec spec=tabHost.newTabSpec("tab1");
    	spec.setContent(R.id.tab1);
    	spec.setIndicator("�B�ë�ĳ",
    			getResources().getDrawable(android.R.drawable.ic_lock_idle_alarm));
    	tabHost.addTab(spec);
    	
    	spec=tabHost.newTabSpec("tab2");
    	spec.setIndicator("�q���q���C��",
    			getResources().getDrawable(android.R.drawable.ic_dialog_alert));
    	spec.setContent(R.id.tab2);
    	tabHost.addTab(spec);

    	tabHost.setCurrentTab(0);

    	// �]�wtab���Ҫ��r��j�p
    	TabWidget tabWidget = (TabWidget)tabHost.findViewById(android.R.id.tabs);
    	View tabView = tabWidget.getChildTabViewAt(0);
    	TextView tab = (TextView)tabView.findViewById(android.R.id.title);
    	tab.setTextSize(20);
    	tabView = tabWidget.getChildTabViewAt(1);
    	tab = (TextView)tabView.findViewById(android.R.id.title);
    	tab.setTextSize(20);

    	// �q�B�ë�ĳ�{���ƻs���{���X
        btnDoSug = (Button)findViewById(R.id.btnDoSug);
        radGSex = (RadioGroup)findViewById(R.id.radGSex);
        radGAge = (RadioGroup)findViewById(R.id.radGAge);
        radBtnAgeRng1 = (RadioButton)findViewById(R.id.radBtnAgeRng1);
        radBtnAgeRng2 = (RadioButton)findViewById(R.id.radBtnAgeRng2);
        radBtnAgeRng3 = (RadioButton)findViewById(R.id.radBtnAgeRng3);
        txtResult1 = (TextView)findViewById(R.id.txtResult1);
    	
        // �]�w�ƥ�listener
        btnDoSug.setOnClickListener(btnDoSugOnClick);
        radGSex.setOnCheckedChangeListener(radGSexOnCheChanLis);

        // �q�q���q���C���{���ƻs���{���X
        txtComPlay = (TextView)findViewById(R.id.txtComPlay);
        txtResult2 = (TextView)findViewById(R.id.txtResult2);
        btnScissors = (Button)findViewById(R.id.btnScissors);
        btnStone = (Button)findViewById(R.id.btnStone);
        btnNet = (Button)findViewById(R.id.btnNet);

        btnScissors.setOnClickListener(btnScissorsLin);
        btnStone.setOnClickListener(btnStoneLin);
        btnNet.setOnClickListener(btnNetLin);
    }

    private RadioGroup.OnCheckedChangeListener radGSexOnCheChanLis = new RadioGroup.OnCheckedChangeListener () {
    	public void onCheckedChanged(RadioGroup group, int checkedId)
    	{
    	    if (checkedId == R.id.radMale) {
    		    radBtnAgeRng1.setText(getString(R.string.maleAgeRng1));
    			radBtnAgeRng2.setText(getString(R.string.maleAgeRng2));
    			radBtnAgeRng3.setText(getString(R.string.maleAgeRng3));
    		}
    		else if (checkedId == R.id.radFemale) {
    			radBtnAgeRng1.setText(getString(R.string.femaleAgeRng1));
    			radBtnAgeRng2.setText(getString(R.string.femaleAgeRng2));
    			radBtnAgeRng3.setText(getString(R.string.femaleAgeRng3));
    		}
    	}
    };

    private Button.OnClickListener btnDoSugOnClick = new Button.OnClickListener() {
    	public void onClick(View v) {
    		// ���U���s��n���檺�{���X
			int iCheckedRadBtn = radGSex.getCheckedRadioButtonId();
			
			String strSug = getString(R.string.sugResult);
			switch (iCheckedRadBtn)
			{
			case R.id.radMale:
				switch (radGAge.getCheckedRadioButtonId())
				{
				case R.id.radBtnAgeRng1:
					strSug += getString(R.string.sugNotHurry);
					break;
				case R.id.radBtnAgeRng3:
					strSug += getString(R.string.sugGetMarried);
					break;
				default:
					strSug += getString(R.string.sugFindCouple);
				}
				break;
			case R.id.radFemale:
				switch (radGAge.getCheckedRadioButtonId())
				{
				case R.id.radBtnAgeRng1:
					strSug += getString(R.string.sugNotHurry);
					break;
				case R.id.radBtnAgeRng3:
					strSug += getString(R.string.sugGetMarried);
					break;
				default:
					strSug += getString(R.string.sugFindCouple);
				}
				break;
			}
    			
    		txtResult1.setText(strSug);
    	}
    };

    private Button.OnClickListener btnScissorsLin = new Button.OnClickListener() {
		public void onClick(View v) {
			// �M�w�q���X��.
			int iComPlay = (int)(Math.random()*3 + 1);
			
			// 1 �V �ŤM, 2 �V ���Y, 3 �V ��.
			if (iComPlay == 1) {
				txtComPlay.setText(R.string.playScissors);
				txtResult2.setText(getString(R.string.result) +
								  getString(R.string.playerDraw));
			}
			else if (iComPlay == 2) {
				txtComPlay.setText(R.string.playStone);
				txtResult2.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
			}
			else {
				txtComPlay.setText(R.string.playNet);
				txtResult2.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
			}
		}
	};
	
    private Button.OnClickListener btnStoneLin = new Button.OnClickListener() {
		public void onClick(View v) {
			// �M�w�q���X��.
			int iComPlay = (int)(Math.random()*3 + 1);
			
			// 1 �V �ŤM, 2 �V ���Y, 3 �V ��.
			if (iComPlay == 1) {
				txtComPlay.setText(R.string.playScissors);
				txtResult2.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
			}
			else if (iComPlay == 2) {
				txtComPlay.setText(R.string.playStone);
				txtResult2.setText(getString(R.string.result) +
						  getString(R.string.playerDraw));
			}
			else {
				txtComPlay.setText(R.string.playNet);
				txtResult2.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
			}
		}
	};
	
    private Button.OnClickListener btnNetLin = new Button.OnClickListener() {
    	public void onClick(View v) {
			// �M�w�q���X��.
			int iComPlay = (int)(Math.random()*3 + 1);
			
			// 1 �V �ŤM, 2 �V ���Y, 3 �V ��.
			if (iComPlay == 1) {
				txtComPlay.setText(R.string.playScissors);
				txtResult2.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
			}
			else if (iComPlay == 2) {
				txtComPlay.setText(R.string.playStone);
				txtResult2.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
			}
			else {
				txtComPlay.setText(R.string.playNet);
				txtResult2.setText(getString(R.string.result) +
						  getString(R.string.playerDraw));
			}
		}
	};
}