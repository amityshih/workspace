package com.example.sampleuinew;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MessageActivity extends Activity {
   private TextView textView;	
   private ProgressDialog progress;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_message);
	
    textView = (TextView) findViewById(R.id.textView1);
    progress = new ProgressDialog(this);
    progress.show();

    String text = getIntent().getStringExtra("text");
//    writeFile(text);
//    
//    textView.setText(readFile());
	
    ParseObject obj = new ParseObject("Message");
    obj.put("text", text);
    obj.saveInBackground(new SaveCallback() {
		
		@Override
		public void done(com.parse.ParseException e) {
			// TODO Auto-generated method stub
			if (e == null) {
				  Log.d("debug","done");
				  setData();
			} else {
				  e.printStackTrace(); 
			}
			
		}
	});
	
   }
   
   private void setData() {  
     ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Message");
     query.findInBackground(new FindCallback<ParseObject>() {

		@Override
		public void done(List<ParseObject> objects, com.parse.ParseException e) {
			// TODO Auto-generated method stub
           if (e == null) {
               String all = "";
               for (ParseObject obj : objects) {
                       all += obj.getString("text") + "\n";
               }
               textView.setText(all);
               progress.dismiss();
           } else {
               e.printStackTrace();
           }
			
		}
	 });
   }  
   
//   private void writeFile(String text){
//	   try {
//		FileOutputStream fos = openFileOutput("message", Context.MODE_APPEND);
//		text +="\n";
//		fos.write(text.getBytes());
//		fos.close();
//    	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	    } catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	   }
//	   
//       Parse.initialize(this, "VuQUgvbgmQ9wSW8r1zVAyMwqKrbf53mqq0ZsuMWs", "WwQCkiYS93nLmREY5jTZMMPeShKfdXdhyUcNrRDN"); 
//       ParseAnalytics.trackAppOpened(getIntent());
//       ParseObject testObject = new ParseObject("Message");
//       testObject.put("Message", text);
//       testObject.saveInBackground();
//
//   }
//   
//   private String readFile() {
//           try {
//                   FileInputStream fis = openFileInput("message");
//                   byte[] buffer = new byte[1024];
//                   fis.read(buffer);
//                   
//                   return new String(buffer);
//           } catch (FileNotFoundException e) {
//                   e.printStackTrace();
//           } catch (IOException e) {
//                   e.printStackTrace();
//           }
//           return "";
//   }   
}
