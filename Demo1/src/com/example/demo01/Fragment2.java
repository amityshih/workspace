package com.example.demo01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
//import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.Bundle;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.util.Log;


public class Fragment2 extends ListFragment {
    public class ResultData
    {
       public String NewsType   = null;
       public String Title      = null;
       public String SubTitle   = null;
       public String Uri        = null;
       public int SeqNo         = 0;
       public Date  UpdateDate  = null;
 
    }	
	private static final String URL = "http://220.130.182.215/FsitAppWebSvc/AppSvc.asmx"; 
	private static final String NAMESPACE = "http://tempuri.org/";  
	private static final String SOAP_ACTION = "http://tempuri.org/GetFsitNews"; 
	private static final String METHOD_NAME = "GetFsitNews";
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String[] arr;
	private View v;
	//private DataTable Table;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        arr = new String[]{
           "H","I","J","K","L","M","N"
        };
        
        ArrayAdapter<String> adapter 
           = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,arr);
        setListAdapter(adapter);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
    	v = inflater.inflate(R.layout.fragment2_list, container, false);
    	getData();
    	
        return v;
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(getActivity(),"你按下"+ arr[position], Toast.LENGTH_SHORT).show();
    }
    
    public void getData()
    {    	
    	Thread networkThread = new Thread(new Runnable() {
			@Override
			public void run() {
				doService();
			}
        }); 
        
        networkThread.start();//Android 3.0後網路存取必須在Thread中run
    }

    private void doService()
    {
    	try
		{
	    	final SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			//設定傳入的參數
			//request.addProperty("dbId", "Fund");
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			//若WebService有輸入參數必須要加這一行否則會沒反應
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);
			//envelope.bodyOut = request;
	
			HttpTransportSE ht = new HttpTransportSE(URL);          
			ht.call(SOAP_ACTION, envelope);
			
			Object response = (Object) envelope.getResponse();
			SoapObject result = (SoapObject)response;
			
			//String[][] resultArray = parseXML(result);
	        List<ResultData>  resultArray = parseXML(result);
	        Collections.sort(resultArray, new Comparator<ResultData>(){
	            @Override
	            public int compare(ResultData o1, ResultData o2) {
		            String b= o1.NewsType;
		            String a= o2.NewsType;
		            return b.compareTo(a);
	            }  	
	           });
	        
	        
	        if (response != null) {
	            Log.d("----Success----", result.toString());
	            
	            for(int i = 0; i < resultArray.size(); i++){
            		Log.d("----Success----", resultArray.get(i).NewsType);
            		Log.d("----Success----", resultArray.get(i).Title);
            		Log.d("----Success----", resultArray.get(i).SubTitle);
            		Log.d("----Success----", resultArray.get(i).Uri);
            		Log.d("----Success----", String.valueOf(resultArray.get(i).SeqNo) );
            		Log.d("----Success----", String.valueOf(resultArray.get(i).UpdateDate));            		
	            }	
	        }
		}
		catch(Exception e)
		{
			Log.e("----Error---", e.getMessage());
			e.printStackTrace();  
		}
    }
    
    private List<ResultData> parseXML(SoapObject result)
    {
    	int rowSize = result.getPropertyCount();
    	//String[][] resultArray;
    	//resultArray = new String[rowSize][6];
        List<ResultData> resultArray = new ArrayList<ResultData>();
    	for(int i = 0; i < rowSize; i++)
    	{
            ResultData resultData = new ResultData();
    		SoapObject tempResult = ((SoapObject)result.getProperty(i));
    		
    		resultData.NewsType =tempResult.getProperty("NewsType").toString();
    		resultData.Title =tempResult.getProperty("Title").toString();
    		resultData.SubTitle =tempResult.getProperty("SubTitle").toString();
    		resultData.Uri =tempResult.getProperty("Uri").toString();
    		resultData.SeqNo =Integer.parseInt(tempResult.getProperty("SeqNo").toString()) ;
    		try {
				resultData.UpdateDate =sdf.parse(tempResult.getProperty("UpdateDate").toString().replace("T", " "));
				//Log.d("Amity Log", "UpdateDate =" + tempResult.getProperty("UpdateDate").toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		resultArray.add(resultData);
    	}
    	return resultArray;
    }
}