package tw.android;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class MyLayout extends LinearLayout {

	static public int appWindowWidth;
	static public int appWindowHeight;

	// �@�w�n�ϥγo��constructor�A�_�h�|�o�ͨҥ~���~�C
	public MyLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		appWindowWidth = getMeasuredWidth();
		appWindowHeight = getMeasuredHeight();

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

}
