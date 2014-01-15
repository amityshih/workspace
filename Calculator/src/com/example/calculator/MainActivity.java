package com.example.calculator;


import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private EditText editText;
    boolean d_flag =false; //:/
    boolean mu_flag =false; //:*
    boolean m_flag =false; //:-
    boolean a_flag =false; //:+
    int ans = 0;        
    
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
           	editText.setText(editText.getText()+ "0"); 
        } else if (view.getId() == R.id.btn1) {
        	editText.setText(editText.getText()+ "1"); 
        } else if (view.getId() == R.id.btn2) {
           	editText.setText(editText.getText()+ "2"); 
        } else if (view.getId() == R.id.btn3) {
          	editText.setText(editText.getText()+ "3"); 
        } else if (view.getId() == R.id.btn4) {
         	editText.setText(editText.getText()+ "4"); 
        } else if (view.getId() == R.id.btn5) {
         	editText.setText(editText.getText()+ "5"); 
        } else if (view.getId() == R.id.btn6) {
        	editText.setText(editText.getText()+ "6"); 
        } else if (view.getId() == R.id.btn7) {
        	editText.setText(editText.getText()+ "7"); 
        } else if (view.getId() == R.id.btn8) {
        	editText.setText(editText.getText()+ "8"); 
        } else if (view.getId() == R.id.btn9) {
        	editText.setText(editText.getText()+ "9"); 
        } else if (view.getId() == R.id.btnC) {
    	    d_flag = false;
    	    mu_flag = false;
    	    m_flag = false;
    	    a_flag = false;
        	editText.setText(""); 
        } else if (view.getId() == R.id.btnD) {
    	    if ((d_flag == true) || (mu_flag == true) || (m_flag == true) || (a_flag == true)) {
    	    	d_flag = true;
       	    	mu_flag = false;
       	    	m_flag = false;
       	    	a_flag = false;    	    	
    	    } else {
       	       if ((d_flag == false) && (mu_flag == false) && (m_flag == false) && (a_flag == false) && editText.getText().toString()!= "") {
    	        	d_flag = true;
    	       }
    	       content = editText.getText().toString();
    	       ans= Integer.valueOf(content);
               editText.setText("");
    	    }  
        } else if (view.getId() == R.id.btnMu) {
    	    if ((d_flag == true) || (mu_flag == true) || (m_flag == true) || (a_flag == true)) {
    	    	d_flag = false;
       	    	mu_flag = true;
       	    	m_flag = false;
       	    	a_flag = false;    	    	
    	    } else {
       	       if ((d_flag == false) && (mu_flag == false) && (m_flag == false) && (a_flag == false) && editText.getText().toString()!= "") {
     	    	   mu_flag = true;
	           }
    	       content = editText.getText().toString();
    	       ans = Integer.valueOf(content);
               editText.setText("");
    	    }   
        } else if (view.getId() == R.id.btnM) {
    	    if ((d_flag == true) || (mu_flag == true) || (m_flag == true) || (a_flag == true)) {
    	    	d_flag = false;
       	    	mu_flag = false;
       	    	m_flag = true;
       	    	a_flag = false;    	    	
    	    } else {
        	   if ((d_flag == false) && (mu_flag == false) && (m_flag == false) && (a_flag == false) && editText.getText().toString()!= "") {
    	    	   m_flag = true;
	           }      	   
    	       content = editText.getText().toString();
    	       ans= Integer.valueOf(content);
        	   editText.setText("");
    	    }   
        } else if (view.getId() == R.id.btnA) {
    	    if ((d_flag == true) || (mu_flag == true) || (m_flag == true) || (a_flag == true)) {
    	    	d_flag = false;
       	    	mu_flag = false;
       	    	m_flag = false;
       	    	a_flag = true;    	    	
    	    } else {
      	       if ((d_flag == false) && (mu_flag == false) && (m_flag == false) && (a_flag == false) && editText.getText().toString()!= "") {
     	           a_flag = true;
               }    	       	   
   	           content = editText.getText().toString();
    	       ans= Integer.valueOf(content);
        	   editText.setText("");
    	    }   
        } else if (view.getId() == R.id.btnE) {
    	    if (d_flag == true) {
    	    	editText.setText(String.valueOf( ans / (Integer.valueOf(editText.getText().toString()))));
    	    } else if (mu_flag == true){
    	    	editText.setText(String.valueOf( ans * (Integer.valueOf(editText.getText().toString()))));    	    	
    	    } else if (m_flag == true){
    	    	editText.setText(String.valueOf( ans - (Integer.valueOf(editText.getText().toString()))));    	    	
    	    } else if (a_flag == true){
    	    	editText.setText(String.valueOf( ans + (Integer.valueOf(editText.getText().toString()))));    	    	
    	    }
    	    d_flag = false;
    	    mu_flag = false;
    	    m_flag = false;
    	    a_flag = false;    	    
        }
    }

}
