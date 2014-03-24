package tw.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Game extends Activity {

	private TextView txtResult;
	private ImageView imgComPlay;
    private ImageButton btnScissors,
    					btnStone,
    					btnNet;

    private int miCountSet = 0,
				miCountPlayerWin = 0,
				miCountComWin = 0,
				miCountDraw = 0;

    private Button btnOK, btnCancel;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        setupViewComponent();
    }

    private void setupViewComponent() {
    	imgComPlay = (ImageView)findViewById(R.id.imgComPlay);
//    	txtResult = (TextView)findViewById(R.id.txtResult);
    	btnScissors = (ImageButton)findViewById(R.id.btnScissors);
    	btnStone = (ImageButton)findViewById(R.id.btnStone);
    	btnNet = (ImageButton)findViewById(R.id.btnNet);

        btnScissors.setOnClickListener(btnScissorsLin);
        btnStone.setOnClickListener(btnStoneLin);
        btnNet.setOnClickListener(btnNetLin);

        btnOK = (Button)findViewById(R.id.btnOK);
    	btnCancel = (Button)findViewById(R.id.btnCancel);
    	btnOK.setOnClickListener(btnOKLis);
    	btnCancel.setOnClickListener(btnCancelLis);
    }

    private Button.OnClickListener btnScissorsLin = new Button.OnClickListener() {
		public void onClick(View v) {
			// �M�w�q���X��.
			int iComPlay = (int)(Math.random()*3 + 1);

			miCountSet++;
			
			// 1 �V �ŤM, 2 �V ���Y, 3 �V ��.
			if (iComPlay == 1) {
				imgComPlay.setImageResource(R.drawable.scissors);
				miCountDraw++;
//				txtResult.setText(getString(R.string.result) +
//				  			getString(R.string.playerDraw));
				Toast.makeText(Game.this, R.string.playerDraw, Toast.LENGTH_LONG)
					.show();
			}
			else if (iComPlay == 2) {
				imgComPlay.setImageResource(R.drawable.stone);
				miCountComWin++;
//				txtResult.setText(getString(R.string.result) +
//							getString(R.string.playerLose));
				Toast.makeText(Game.this, R.string.playerLose, Toast.LENGTH_LONG)
					.show();
			}
				else {
				imgComPlay.setImageResource(R.drawable.net);
				miCountPlayerWin++;
//				txtResult.setText(getString(R.string.result) +
//						  getString(R.string.playerWin));
				Toast.makeText(Game.this, R.string.playerWin, Toast.LENGTH_LONG)
					.show();
			}
		}
	};
	
    private Button.OnClickListener btnStoneLin = new Button.OnClickListener() {
		public void onClick(View v) {
			// �M�w�q���X��.
			int iComPlay = (int)(Math.random()*3 + 1);

			miCountSet++;
			
			// 1 �V �ŤM, 2 �V ���Y, 3 �V ��.
			if (iComPlay == 1) {
				imgComPlay.setImageResource(R.drawable.scissors);
				miCountPlayerWin++;
//				txtResult.setText(getString(R.string.result) +
//						  getString(R.string.playerWin));
				Toast.makeText(Game.this, R.string.playerWin, Toast.LENGTH_LONG)
				.show();
			}
			else if (iComPlay == 2) {
				imgComPlay.setImageResource(R.drawable.stone);
				miCountDraw++;
//				txtResult.setText(getString(R.string.result) +
//						  getString(R.string.playerDraw));
				Toast.makeText(Game.this, R.string.playerDraw, Toast.LENGTH_LONG)
				.show();
			}
			else {
				imgComPlay.setImageResource(R.drawable.net);
				miCountComWin++;
//				txtResult.setText(getString(R.string.result) +
//						  getString(R.string.playerLose));
				Toast.makeText(Game.this, R.string.playerLose, Toast.LENGTH_LONG)
				.show();
			}
		}
	};
	
    private Button.OnClickListener btnNetLin = new Button.OnClickListener() {
    	public void onClick(View v) {
			// �M�w�q���X��.
			int iComPlay = (int)(Math.random()*3 + 1);

			miCountSet++;
			
			// 1 �V �ŤM, 2 �V ���Y, 3 �V ��.
			if (iComPlay == 1) {
				imgComPlay.setImageResource(R.drawable.scissors);
				miCountComWin++;
//				txtResult.setText(getString(R.string.result) +
//						  getString(R.string.playerLose));
				Toast.makeText(Game.this, R.string.playerLose, Toast.LENGTH_LONG)
				.show();
			}
			else if (iComPlay == 2) {
				imgComPlay.setImageResource(R.drawable.stone);
				miCountPlayerWin++;
//				txtResult.setText(getString(R.string.result) +
//						  getString(R.string.playerWin));
				Toast.makeText(Game.this, R.string.playerWin, Toast.LENGTH_LONG)
				.show();
			}
			else {
				imgComPlay.setImageResource(R.drawable.net);
				miCountDraw++;
//				txtResult.setText(getString(R.string.result) +
//						  getString(R.string.playerDraw));
				Toast.makeText(Game.this, R.string.playerDraw, Toast.LENGTH_LONG)
				.show();
			}
		}
	};

	private Button.OnClickListener btnOKLis = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent it = new Intent();
			
			Bundle bundle = new Bundle();
			bundle.putInt("KEY_COUNT_SET", miCountSet);
			bundle.putInt("KEY_COUNT_PLAYER_WIN", miCountPlayerWin);
			bundle.putInt("KEY_COUNT_COM_WIN", miCountComWin);
			bundle.putInt("KEY_COUNT_DRAW", miCountDraw);
			it.putExtras(bundle);
			
			setResult(RESULT_OK, it);
			finish();
		}
	};

	private Button.OnClickListener btnCancelLis = new Button.OnClickListener() {
		public void onClick(View v) {
			setResult(RESULT_CANCELED);
			finish();
		}
	};
}
