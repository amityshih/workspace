package com.example.demo01;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class Fragment3 extends Fragment {
//	private TextView txt;
    private View v;
    private ImageButton fundEx;
    private ImageButton fundIn;
    
	//String[][]msgg1=new String[][]{{""}};//聲明引用
	//String[][]msgg2=new String[][]{{""}};//聲明引用
	List<String[]> list_in = new ArrayList<String[]>();
	List<String[]> list_ex = new ArrayList<String[]>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
 

    
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // TODO Auto-generated method stub
 
        v = inflater.inflate(R.layout.fragment3_list, container, false);
        //txt = (TextView)v.findViewById(R.id.textView1);
    	
    	AsyncCallWS task = new AsyncCallWS();
    	task.Observer = new Btn1Observer();
    	
    	PropertyInfo pi = new PropertyInfo();
    	pi.setName("arguments");
    	pi.setValue("<Tx>  <TxHead>     <HMSGID>P</HMSGID>     <HERRID>0000</HERRID>     <HSYDAY>0970108</HSYDAY>     <HSYTIME>153614</HSYTIME>     <HWSID>ROE_Client</HWSID>     <HSTANO>5617413</HSTANO>     <HDTLEN>0010</HDTLEN>     <HREQQ1>          </HREQQ1>     <HREPQ1>          </HREPQ1>     <HDRVQ1>          </HDRVQ1>     <HPVDQ1>          </HPVDQ1>     <HPVDQ2>          </HPVDQ2>     <HSYCVD>0970108</HSYCVD>     <HTLID>2005001   </HTLID>     <HTXTID>GL001   </HTXTID>     <HFMTID>0001 </HFMTID>     <HRETRN> </HRETRN>     <HSLGNF> </HSLGNF>     <HSPSCK>Y8</HSPSCK>     <HRTNCD> </HRTNCD>     <HSBTRF> </HSBTRF>     <HFILL> </HFILL>  </TxHead><TxBody>	<FUNDID></FUNDID>	<DDATE>20130902</DDATE>　　  </TxBody></Tx>");
    	task.requestProperty.add(pi);
    	
    	task.execute();
    	
    	fundIn = (ImageButton) v.findViewById(R.id.imageBtnFundIn);
    	fundEx = (ImageButton) v.findViewById(R.id.imageBtnFundEx);

    	fundIn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	        	fundIn.setBackgroundResource(R.drawable.fund_in_over);
	        	fundEx.setBackgroundResource(R.drawable.fund_ex);
				goToListView(list_in);//切換到查詢結果顯示界面ListView界面
				
			}
		});   
    	
    	fundEx.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	        	fundIn.setBackgroundResource(R.drawable.fund_in);
	        	fundEx.setBackgroundResource(R.drawable.fund_ex_over);
				goToListView(list_ex);//切換到查詢結果顯示界面ListView界面
				
			}
		});    	
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
    
    
/*    public void onClick(View view) {
    	Log.d("debug", "onCLick");
    	fundEx = (ImageButton) v.findViewById(R.id.imageBtnFundEx);
    	fundIn = (ImageButton) v.findViewById(R.id.imageBtnFundIn);
        if (view.getId() == R.id.imageBtnFundIn) {
        	fundIn.setBackgroundResource(R.drawable.fund_in_over);
        	fundEx.setBackgroundResource(R.drawable.fund_ex);
			//goToListView(list_in);//切換到查詢結果顯示界面ListView界面
        } 
        else {
        	fundIn.setBackgroundResource(R.drawable.fund_in);
        	fundEx.setBackgroundResource(R.drawable.fund_ex_over);
			//goToListView(list_ex);//切換到查詢結果顯示界面ListView界面
        }
    }  
  */  
    public class Btn1Observer implements IObserver{
        @Override
        public void success(Object data){
        	
	        //StringBuilder sb = new StringBuilder();
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
					String[] msgInfo=new String[nodeList.item(i).getChildNodes().getLength()];//新建和結果向量對應的數組
	        		NodeList fundChildNodes = nodeList.item(i).getChildNodes();
	        		for(int j = 0; j < fundChildNodes.getLength(); j++) {
	        			//sb.append(fundChildNodes.item(j).getTextContent() + " - ");
						msgInfo[j]=(String)fundChildNodes.item(j).getTextContent();
						
	        		}
					if (msgInfo[0].toString().charAt(0)=='T') {
						list_ex.add(msgInfo);
					}	
					else {
  						list_in.add(msgInfo);
					}
	        		
	        		//sb.append("\n");
	        	}

				goToListView(list_in);//切換到查詢結果顯示界面ListView界面
	        }

	        
	        
 //   		TextView txt = (TextView) findViewById(R.id.textView1);
 //       	txt.setText(sb.toString());        	
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
    
	 public void goToListView(List<String[]> mssg)//去ListView界面
	 {
//		 	msgg=mssg;//賦值引用給全局數組，用來實現返回按鈕功能
//	        setContentView(R.layout.listview);//切換界面
//	        curr=WhichView.LIST_VIEW;//標識界面
	        final List<String[]>lst =mssg;//新建數組，並賦值
	        final String[][] msg_tmp = new String[lst.size()][];	
	        lst.toArray(msg_tmp);
	        // 取得螢幕解析度       
			final DisplayMetrics metricsMethodTwo = getResources().getDisplayMetrics();
            
	        /*
	       	for (int i = 0; i < lst.size(); i++){
	       		String[] mag = lst.get(i);
        		for(int j = 0; j < mag.length; j++) {
           	        Log.d("mag[" + i + "][" + j + "]="+ mag[j],"ABCDEFG");       			
        		}
	       	}*/
	        final String[][] msg = new String[msg_tmp[0].length][msg_tmp.length];
	        //Log.d("msg_tmp[0].length" +msg_tmp[0].length,"ABCDEFG");
	        //Log.d("msg_tmp.length" +msg_tmp.length,"ABCDEFG");
        	for (int i = 0; i < msg_tmp[0].length; i++){
        		for(int j = 0; j < msg_tmp.length; j++) {
        			msg[i][j] = msg_tmp[j][i];
        	        //Log.d("msg[" + i + "][" + j + "]="+msg_tmp[j][i],"ABCDEFG");
       		    }
        	}	
	        ListView lv_detail=(ListView)v.findViewById(R.id.ListView_detail);//拿到ListView的引用
	        BaseAdapter ba_detail=new BaseAdapter()//新建適配器
	        {
				@Override
				public int getCount() 
				{
					return msg[0].length;//得到列表的長度
				}
				@Override
				public Object getItem(int arg0){return null;}
				@Override
				public long getItemId(int arg0){return 0;}
				@Override
				public View getView(int arg0, View arg1, ViewGroup arg2)//為每一項添加內容
				{
/*					LinearLayout ll1_detail=new LinearLayout(v.getContext());
					ll1_detail.setOrientation(LinearLayout.HORIZONTAL);		//設置朝向	
					ll1_detail.setPadding(5,5,5,5);//四周留白
					//Log.d("arg0" + arg0,"AAAAAAAAAAAAAA");
					//Log.d("lst.size" + lst.size(),"ZZZZZZZZZZZZZZ");
					TextView s1= new TextView(v.getContext());
					s1.setText("TEST");//TextView中顯示的文字
					s1.setTextSize(14);//字體大小
					s1.setTextColor(getResources().getColor(R.color.red));//字體顏色	
				    ll1_detail.addView(s1);//放入LinearLayout
*/				    

					LinearLayout ll_detail=new LinearLayout(v.getContext());
					ll_detail.setOrientation(LinearLayout.HORIZONTAL);		//設置朝向
					ll_detail.setClickable(false);
					//ll_detail.setPadding(5,5,5,5);//四周留白
					
					if (arg0%10 == 0) {
						TextView s= new TextView(v.getContext());
						s.setText("股票型");//TextView中顯示的文字
						s.setTextSize(14);//字體大小
						s.setTextColor(getResources().getColor(R.color.black));//字體顏色
						//s.setPadding(1,2,2,1);//四周留白
					    s.setWidth(metricsMethodTwo.widthPixels);//寬度
					    //Log.d("widthPixels = " + metricsMethodTwo.widthPixels,"ABCDEFG");
					    s.setBackgroundColor(getResources().getColor(R.color.gray));
					    s.setGravity(Gravity.LEFT);
					    s.setClickable(false);
					    ll_detail.addView(s);//放入LinearLayout						
					}
					else
					{						
						for(int i=1;i<msg.length;i++)//為每一行設置顯示的數據
						{					    
							TextView s= new TextView(v.getContext());
							if (i == 5 ){
	    						s.setText(msg[i][arg0]+"%");//TextView中顯示的文字
							}
							else{
	    						s.setText(msg[i][arg0]);//TextView中顯示的文字							
							}
								
		                    //Log.d("msg[" + arg0 + "][" + i + "]", msg[arg0][i]);
		                    //Log.d("msg[" + arg0 + "][" + i + "]", msg[arg0][i]);
							s.setTextSize(14);//字體大小
							//Log.d("Integer.valueOf(msg[5][arg0])" + msg[5][arg0], "DDDDDDDD");
							float irate = Float.parseFloat(msg[5][arg0]);
							if(irate > 0) {
								s.setTextColor(getResources().getColor(R.color.red));//字體顏色
							}
							else if(irate < 0) {
								s.setTextColor(getResources().getColor(R.color.green));//字體顏色
							}
							else {
								s.setTextColor(getResources().getColor(R.color.orange));//字體顏色									
							}
							//s.setTextColor(getResources().getColor(R.color.orange));//字體顏色																
							s.setPadding(1,2,2,1);//四周留白
							if (i == 1 ){
								if ((metricsMethodTwo.widthPixels/3*2)>320) {
								    s.setWidth(metricsMethodTwo.widthPixels- (int)((metricsMethodTwo.widthPixels/3*2)/4) * 4);//寬度								
								}
								else{
								    s.setWidth(160);//寬度									
								}
							    s.setGravity(Gravity.LEFT);
							}
							else{
								if ((metricsMethodTwo.widthPixels/3*2)>320) {
									s.setWidth(((metricsMethodTwo.widthPixels/3*2)/4));//寬度									
								}
								else {
									s.setWidth(80);//寬度									
								}
							    s.setGravity(Gravity.RIGHT);
							}
						    ll_detail.addView(s);//放入LinearLayout
						}
					}	
					return ll_detail;//將此LinearLayout返回
				}        	
	        };    
	        lv_detail.setAdapter(ba_detail);//將適配器添加進ListView
	        
	        lv_detail.setOnItemClickListener//為列表添加監聽
	        (
	           new OnItemClickListener()
	           {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,//當點擊列表中的某一項時調用此函數
						long arg3) //arg2為點擊的第幾項
				{
					if (arg2%10 != 0) {
						String cccx=msg[0][arg2];//取出對應項中對應的車次信息
						Toast.makeText(v.getContext(), cccx.toString(), Toast.LENGTH_SHORT).show();
					}
				}        	   
	           }
	        );
	 }
    
}