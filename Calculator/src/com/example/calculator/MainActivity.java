package com.example.calculator;

import com.example.connectnewwork.R;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private EditText editText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        editText = (EditText) findViewById(R.id.editText1);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onClick(View view) {
    	Log.d("debug", "onCLick");
        String content = null;
        if (view.getId() == R.id.btn0) {
        } else if (view.getId() == R.id.btn1) {
        } else if (view.getId() == R.id.btn2) {
        } else if (view.getId() == R.id.btn3) {
        } else if (view.getId() == R.id.btn4) {
        } else if (view.getId() == R.id.btn5) {
        } else if (view.getId() == R.id.btn6) {
        } else if (view.getId() == R.id.btn7) {
        } else if (view.getId() == R.id.btn8) {
        } else if (view.getId() == R.id.btn9) {
        } else if (view.getId() == R.id.btn0) {
        }
        editText.setText(content);
    }

}
