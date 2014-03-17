package com.example.demo01;

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
	
	private static final String URL = "http://220.130.182.215/FsitAppWebSvc/AppSvc.asmx"; 
	private static final String NAMESPACE = "http://tempuri.org/";  
	private static final String SOAP_ACTION = "http://tempuri.org/GetTodayNav"; 
	private static final String METHOD_NAME = "GetTodayNav";
	
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
        Toast.makeText(getActivity(), "你按下"+arr[position], Toast.LENGTH_SHORT).show();
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
			request.addProperty("dbId", "Fund");
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			//若WebService有輸入參數必須要加這一行否則會沒反應
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);
			//envelope.bodyOut = request;
	
			HttpTransportSE ht = new HttpTransportSE(URL);          
			ht.call(SOAP_ACTION, envelope);
			
			Object response = (Object) envelope.getResponse();
			SoapObject result = (SoapObject)response;
			
			String[][] resultArray = parseXML(result);

	        if (response != null) {
	            Log.d("----Success----", result.toString());
	            
	            for(int i = 0; i < resultArray.length; i++)
	            	for(int j = 0; j < 9; j++)
	            		Log.d("----Success----", resultArray[i][j]);
	        }
		}
		catch(Exception e)
		{
			Log.e("----Error---", e.getMessage());
			e.printStackTrace();  
		}
    }
    
    private String[][] parseXML(SoapObject result)
    {
    	int rowSize = result.getPropertyCount();
    	String[][] resultArray;
    	resultArray = new String[rowSize][9];

    	for(int i = 0; i < rowSize; i++)
    	{
    		SoapObject tempResult = ((SoapObject)result.getProperty(i));
    		    		
    		resultArray[i][0] = tempResult.getProperty("FundId").toString();
    		resultArray[i][1] = tempResult.getProperty("ShareId").toString();
    		resultArray[i][2] = tempResult.getProperty("FundAlias1Share").toString();
    		resultArray[i][3] = tempResult.getProperty("Ddate").toString();
    		resultArray[i][4] = tempResult.getProperty("Nav").toString();
    		resultArray[i][5] = tempResult.getProperty("Pnav").toString();
    		resultArray[i][6] = tempResult.getProperty("PnavDiff").toString();
    		resultArray[i][7] = tempResult.getProperty("PnavRate").toString();
    		resultArray[i][8] = tempResult.getProperty("FundTypeSymbolName").toString();
    	}
    	
    	return resultArray;
    }
}