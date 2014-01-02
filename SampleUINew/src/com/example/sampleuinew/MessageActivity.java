package com.example.sampleuinew;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends Activity {
   private TextView textView;	
	
   @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_message);
	
    textView = (TextView) findViewById(R.id.textView1);
    
    String text = getIntent().getStringExtra("text");
    textView.setText(text);
	
   }
   private void writeFiel(String text){
	   try {
		FileOutputStream fos = openFileOutput("message", Context.MODE_APPEND);
		text +="\n";
		fos.write(text.getBytes());
		fos.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   }
}
