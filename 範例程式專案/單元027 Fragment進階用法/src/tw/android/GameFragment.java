package tw.android;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameFragment extends Fragment {

	private Button mBtnScissors,
				   mBtnStone,
				   mBtnNet,
				   mBtnShowResult1,
				   mBtnShowResult2,
				   mBtnHiddenResult;
//	public boolean mbShowResult = false;
    private TextView mTxtComPlay,
	 				 mTxtResult;
    public TextView mEdtCountSet,
					 mEdtCountPlayerWin,
					 mEdtCountComWin,
					 mEdtCountDraw;
    private int miCountSet = 0,
				miCountPlayerWin = 0,
				miCountComWin = 0,
				miCountDraw = 0;
    
    private final static String TAG = "Result";
    private int mTagCount = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.game, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

        setupViewComponent();
    }

	private void setupViewComponent() {
    	mTxtComPlay = (TextView)getView().findViewById(R.id.txtComPlay);
        mTxtResult = (TextView)getView().findViewById(R.id.txtResult);
    	mBtnScissors = (Button)getView().findViewById(R.id.btnScissors);
    	mBtnStone = (Button)getView().findViewById(R.id.btnStone);
    	mBtnNet = (Button)getView().findViewById(R.id.btnNet);
    	mBtnShowResult1 = (Button)getView().findViewById(R.id.btnShowResult1);
    	mBtnShowResult2 = (Button)getView().findViewById(R.id.btnShowResult2);
    	mBtnHiddenResult = (Button)getView().findViewById(R.id.btnHiddenResult);

    	mBtnScissors.setOnClickListener(btnScissorsLin);
    	mBtnStone.setOnClickListener(btnStoneLin);
    	mBtnNet.setOnClickListener(btnNetLin);

    	mBtnShowResult1.setOnClickListener(btnShowResult1Lin);
    	mBtnShowResult2.setOnClickListener(btnShowResult2Lin);
    	mBtnHiddenResult.setOnClickListener(btnHiddenResultLin);
    }

	private Button.OnClickListener btnShowResult1Lin = new Button.OnClickListener() {
		public void onClick(View v) {
			GameResultFragment fragGameResult = new GameResultFragment();
	        FragmentTransaction fragTran = getFragmentManager().beginTransaction();
	        mTagCount++;
	        String sFragTag = TAG + new Integer(mTagCount).toString();
	        fragTran.replace(R.id.frameLayout, fragGameResult, sFragTag);
	        fragTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	        fragTran.addToBackStack(null);
	        fragTran.commit();

//	        mbShowResult = true;
		}
	};

	private Button.OnClickListener btnShowResult2Lin = new Button.OnClickListener() {
		public void onClick(View v) {
	    	GameResultFragment2 fragGameResult2 = new GameResultFragment2();
	    	FragmentTransaction fragTran = getFragmentManager().beginTransaction();
	        mTagCount++;
	        String sFragTag = TAG + new Integer(mTagCount).toString();
	        fragTran.replace(R.id.frameLayout, fragGameResult2, sFragTag);
	        fragTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	        fragTran.addToBackStack(null);
	        fragTran.commit();

//	        mbShowResult = true;
		}
	};

	private Button.OnClickListener btnHiddenResultLin = new Button.OnClickListener() {
		public void onClick(View v) {
//	        mbShowResult = false;

	        FragmentManager fragMgr = getFragmentManager();
	        String sFragTag = TAG + new Integer(mTagCount).toString();
	    	Fragment fragGameResult = (Fragment)fragMgr.findFragmentByTag(sFragTag);
    		FragmentTransaction fragTran = fragMgr.beginTransaction();
	        fragTran.remove(fragGameResult);
	        fragTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	        fragTran.addToBackStack(null);
	        fragTran.commit();
		}
	};

	private Button.OnClickListener btnScissorsLin = new Button.OnClickListener() {
		public void onClick(View v) {
			// Decide computer play.
			int iComPlay = (int)(Math.random()*3 + 1);
			
			miCountSet++;

			// 1 - scissors, 2 - stone, 3 - net.
			if (iComPlay == 1) {
				mTxtComPlay.setText(R.string.playScissors);
				mTxtResult.setText(getString(R.string.result) +
								  getString(R.string.playerDraw));
				miCountDraw++;
			}
			else if (iComPlay == 2) {
				mTxtComPlay.setText(R.string.playStone);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
				miCountComWin++;
			}
			else {
				mTxtComPlay.setText(R.string.playNet);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
				miCountPlayerWin++;
			}
			
			if (getActivity().findViewById(R.id.frameLayout).isShown()) {
				mEdtCountSet.setText(new Integer(miCountSet).toString());
				mEdtCountDraw.setText(new Integer(miCountDraw).toString());
				mEdtCountComWin.setText(new Integer(miCountComWin).toString());
				mEdtCountPlayerWin.setText(new Integer(miCountPlayerWin).toString());
			}
		}
	};
	
    private Button.OnClickListener btnStoneLin = new Button.OnClickListener() {
		public void onClick(View v) {
			int iComPlay = (int)(Math.random()*3 + 1);
			
			miCountSet++;

			// 1 - scissors, 2 - stone, 3 - net.
			if (iComPlay == 1) {
				mTxtComPlay.setText(R.string.playScissors);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
				miCountPlayerWin++;
			}
			else if (iComPlay == 2) {
				mTxtComPlay.setText(R.string.playStone);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerDraw));
				miCountDraw++;
			}
			else {
				mTxtComPlay.setText(R.string.playNet);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
				miCountComWin++;
			}
			
			if (getActivity().findViewById(R.id.frameLayout).isShown()) {
				mEdtCountSet.setText(new Integer(miCountSet).toString());
				mEdtCountDraw.setText(new Integer(miCountDraw).toString());
				mEdtCountComWin.setText(new Integer(miCountComWin).toString());
				mEdtCountPlayerWin.setText(new Integer(miCountPlayerWin).toString());
			}
		}
	};
	
    private Button.OnClickListener btnNetLin = new Button.OnClickListener() {
		public void onClick(View v) {
			int iComPlay = (int)(Math.random()*3 + 1);
			
			miCountSet++;

			// 1 - scissors, 2 - stone, 3 - net.
			if (iComPlay == 1) {
				mTxtComPlay.setText(R.string.playScissors);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerLose));
				miCountComWin++;
			}
			else if (iComPlay == 2) {
				mTxtComPlay.setText(R.string.playStone);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerWin));
				miCountPlayerWin++;
			}
			else {
				mTxtComPlay.setText(R.string.playNet);
				mTxtResult.setText(getString(R.string.result) +
						  getString(R.string.playerDraw));
				miCountDraw++;
			}
			
			if (getActivity().findViewById(R.id.frameLayout).isShown()) {
				mEdtCountSet.setText(new Integer(miCountSet).toString());
				mEdtCountDraw.setText(new Integer(miCountDraw).toString());
				mEdtCountComWin.setText(new Integer(miCountComWin).toString());
				mEdtCountPlayerWin.setText(new Integer(miCountPlayerWin).toString());
			}
		}
	};
}
