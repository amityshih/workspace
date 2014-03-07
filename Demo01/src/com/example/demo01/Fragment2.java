package com.example.demo01;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


public class Fragment2 extends Fragment {
	private TextView txt;
    private View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // TODO Auto-generated method stub
 
        v = inflater.inflate(R.layout.fragment2_list, container, false);
        txt = (TextView)v.findViewById(R.id.textView1);
    	
    	AsyncCallWS task = new AsyncCallWS();
    	task.Observer = new Btn1Observer();
    	
    	PropertyInfo pi = new PropertyInfo();
    	pi.setName("arguments");
    	pi.setValue("<Tx>  <TxHead>     <HMSGID>P</HMSGID>     <HERRID>0000</HERRID>     <HSYDAY>0970108</HSYDAY>     <HSYTIME>153614</HSYTIME>     <HWSID>ROE_Client</HWSID>     <HSTANO>5617413</HSTANO>     <HDTLEN>0010</HDTLEN>     <HREQQ1>          </HREQQ1>     <HREPQ1>          </HREPQ1>     <HDRVQ1>          </HDRVQ1>     <HPVDQ1>          </HPVDQ1>     <HPVDQ2>          </HPVDQ2>     <HSYCVD>0970108</HSYCVD>     <HTLID>2005001   </HTLID>     <HTXTID>GL001   </HTXTID>     <HFMTID>0001 </HFMTID>     <HRETRN> </HRETRN>     <HSLGNF> </HSLGNF>     <HSPSCK>Y8</HSPSCK>     <HRTNCD> </HRTNCD>     <HSBTRF> </HSBTRF>     <HFILL> </HFILL>  </TxHead><TxBody>	<FUNDID></FUNDID>	<DDATE>20130902</DDATE>　　  </TxBody></Tx>");
    	task.requestProperty.add(pi);
    	
    	task.execute();
    	
        return v;
      }
    private class AsyncCallWS extends AsyncTask<Void, Void, String> {
        
    	/** 宣告連接 Web Service的基本資料*/
    	public String NAMESPACE = "http://tempuri.org/" ;
    	public String URL = "http://10.79.202.17/FundServices/wsFund.asmx";  

    	/** 宣告呼叫 Web Service 中的Method，本例子呼叫的Method名為: MemberLogin*/
    	/** Method MemberLogin Input Type : (String,String)*/
    	/** Method MemberLogin Output Type : (Boolean)*/
    	public String MemberLogin_SOAP_ACTION = "http://tempuri.org/FubonComService";
    	public String METHOD_NAME2 = "FubonComService";
    	public Element[] header = new Element[0];
    	public ArrayList<PropertyInfo> requestProperty = new ArrayList<PropertyInfo>();
 
    	public IObserver Observer = null;
    	
        @Override
        protected String doInBackground(Void... params) {
            Log.i("AsyncCallWS", "doInBackground");
            return callWebService();
        }

        @Override
        protected void onPostExecute(String result) {
        	if (Observer != null) {
        		Observer.success(result);
        	}
        	
            Log.i("AsyncCallWS", "onPostExecute");
        }

        @Override
        protected void onPreExecute() {
            Log.i("AsyncCallWS", "onPreExecute");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Log.i("AsyncCallWS", "onProgressUpdate");
        }

        public String callWebService(){
        	String retval = "";
        	/** 將EditText中的值傳給 Web Service，以執行 MemberLogin(String UserName,String Password)*/
        	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME2);
        	
        	for(PropertyInfo pi : requestProperty) {     		
        		request.addProperty(pi);
        	}

        	/** 設定呼叫 Web Service一些值*/
        	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        	envelope.headerOut = header;
        	envelope.dotNet = true;
        	envelope.setOutputSoapObject(request);
        	HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        	try
        	{
            	/** 開始呼叫 Web Service*/
            	androidHttpTransport.call(MemberLogin_SOAP_ACTION, envelope);

            	/** 得到回傳值，必須使用SoapPrimitive型態來儲存，本次宣告了一個Result(SoapPrimitive型態)來儲存回傳值*/
            	SoapPrimitive Result = (SoapPrimitive)envelope.getResponse();

            	/** 由於回傳值是SoapPrimitive型態，而我們需要的是Boolean值，所以在此做個轉換型態*/
            	retval = Result.toString();            	
            	Log.d("callWebService", retval);
        	}
        	catch(Exception e) 
        	{
            	Log.d("callWebService Exception", e.toString());
        	}

        	return retval;
        }            
    }
    
    public interface IObserver{
        public abstract void success(Object data);
        public abstract void fail(String msg);
        public abstract void exception(String msg);
    }
    
    public class Btn1Observer implements IObserver{
        @Override
        public void success(Object data){
        	
	        StringBuilder sb = new StringBuilder();
            //获取XPathFactory实例  
            XPathFactory factory = XPathFactory.newInstance();  
            //用工程生成XPath实例，解析xml  
            XPath xpath = factory.newXPath();
            InputSource source = new InputSource(new StringReader(data.toString())); 
            NodeList nodeList = null;
			try {
				nodeList = (NodeList) xpath.evaluate("//FUND", source, XPathConstants.NODESET);
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}                
			
	        if (nodeList != null) {
	        	for (int i = 0; i < nodeList.getLength(); i++){
	        		NodeList fundChildNodes = nodeList.item(i).getChildNodes();
	        		for(int j = 0; j < fundChildNodes.getLength(); j++) {
	        			sb.append(fundChildNodes.item(j).getTextContent() + " - ");
	        		}
	        		
	        		sb.append("\n");
	        	}
	        }

	        
	        
 //   		TextView txt = (TextView) findViewById(R.id.textView1);
        	txt.setText(sb.toString());        	
        };
        
        @Override
        public void fail(String msg){
        	Log.i("Btn1Observer", msg);
        };
        
        @Override
        public void exception(String msg){
        	Log.i("Btn1Observer", msg);
        }    
    }
    
    public class Btn2Observer implements IObserver{
        @Override
        public void success(Object data){
//    		TextView txt = (TextView) findViewById(R.id.textView1);
        	txt.setText(data.toString());        	
        };
        
        @Override
        public void fail(String msg){
        	Log.i("Btn2Observer", msg);
        };
        
        @Override
        public void exception(String msg){
        	Log.i("Btn2Observer", msg);
        }    
    }        
    
}