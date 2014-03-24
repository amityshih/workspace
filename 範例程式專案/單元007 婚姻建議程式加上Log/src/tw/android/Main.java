package tw.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class Main extends Activity {

	private Button btnDoSug;
    private EditText edtSex, edtAge;
    private TextView txtResult;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // �q�귽���OR�����o��������
        btnDoSug = (Button)findViewById(R.id.btnDoSug);
        edtSex = (EditText)findViewById(R.id.edtSex);
        edtAge = (EditText)findViewById(R.id.edtAge);
        txtResult = (TextView)findViewById(R.id.txtResult);

   	    // �]�wbutton���󪺨ƥ�listener
   	    btnDoSug.setOnClickListener(btnDoSugOnClick);
    }

    private Button.OnClickListener btnDoSugOnClick = new Button.OnClickListener() {
    	public void onClick(View v) {
    		// ���U���s��n���檺�{���X
    		String strSex = edtSex.getText().toString();
    		int iAge = Integer.parseInt(edtAge.getText().toString());
    			
    		String strSug = "���G�G";
    		if (strSex.equals("�k"))
    			if (iAge < 28) {
    				strSug += "����";
    				Log.d("MarriSug", "man, don't hurry");
    			}
    			else if (iAge > 33) {
    				strSug += "���ֵ��B";
    				Log.d("MarriSug", "man, hurry to get married!");
    			}
    			else {
    				strSug += "�}�l���H";
    				Log.d("MarriSug", "man, start to find girlfriend!");
    			}
    		else
    			if (iAge < 25) {
    				strSug += "����";
    				Log.d("MarriSug", "woman, don't hurry!");
    			}
    			else if (iAge > 30) {
    				strSug += "���ֵ��B";
    				Log.d("MarriSug", "woman, hurry to get married!");
    			}
    			else {
    				strSug += "�}�l���H";
    				Log.d("MarriSug", "woman, start to find boyfriend!");
    			}
    			
    		txtResult.setText(strSug);
    	}
    };
}