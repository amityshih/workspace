package com.example.demo01;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class Fragment2 extends Fragment {
	private static final String URL = "http://220.130.182.215/FsitAppWebSvc/AppSvc.asmx"; 
	private static final String NAMESPACE = "http://tempuri.org/";  
	private static final String SOAP_ACTION = "http://tempuri.org/GetTodayNav"; 
	private static final String METHOD_NAME = "GetTodayNav";

	private View v;
    private ImageButton fundEx;
    private ImageButton fundIn;
        
	List<String[]> list_in = new ArrayList<String[]>();
	List<String[]> list_ex = new ArrayList<String[]>();
	//private String[][] list_in ;
	//private String[][] list_ex ;
	List<String> fund_type_in = new ArrayList<String>();
	List<String> fund_type_ex = new ArrayList<String>();

	 final Handler cwjHandler = new Handler();

	 final Runnable mUpdateResults = new Runnable() {
        public void run() {
 	       goToListView(list_in,fund_type_in);
        }
    };
	
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
 
        //取得螢幕解析度
		final DisplayMetrics metricsMethodTwo = getResources().getDisplayMetrics();
       
		//LinearLayout Linear_dtl01 = (LinearLayout)v.findViewById(R.id.LinearLayout_detail01); 
		//Log.d("metricsMethodTwo.widthPixels=" + metricsMethodTwo.widthPixels , "AAAAAAAAAAAAAAAAAAAAAA");
    	getData();
    	    	
    	fundIn = (ImageButton) v.findViewById(R.id.imageBtnFundIn);
    	fundEx = (ImageButton) v.findViewById(R.id.imageBtnFundEx);

    	fundIn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	        	fundIn.setBackgroundResource(R.drawable.fund_in_over);
	        	fundEx.setBackgroundResource(R.drawable.fund_ex);
				goToListView(list_in,fund_type_in);//切換到查詢結果顯示界面ListView界面
				
			}
		});   
    	
    	fundEx.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	        	fundIn.setBackgroundResource(R.drawable.fund_in);
	        	fundEx.setBackgroundResource(R.drawable.fund_ex_over);
				goToListView(list_ex,fund_type_ex);//切換到查詢結果顯示界面ListView界面
				
			}
		});    	
        return v;
      }
 
    public void getData()
    {    	
    	Thread networkThread = new Thread(new Runnable() {
			@Override
			public void run() {
				doService();
                cwjHandler.post(mUpdateResults); 
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
	            for(int i = 0; i < resultArray.length; i++){
	            	if (resultArray[i][0].toString().charAt(0)=='T') {
	            		list_ex.add(resultArray[i]);
		            	if (fund_type_ex.indexOf(resultArray[i][8]) == -1 ){
		            		fund_type_ex.add(resultArray[i][8]);
		        		}
	            	}    	
	            	else {
	        		    list_in.add(resultArray[i]);
		            	if (fund_type_in.indexOf(resultArray[i][8]) == -1 ){
		            		fund_type_in.add(resultArray[i][8]);
		            	}
	            	}
	            }
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
    
    
	 public void goToListView(List<String[]> mssg,List<String> ftype)//去ListView界面
	 {
//		 	msgg=mssg;//賦值引用給全局數組,用來實現返回按鈕功能
//	        setContentView(R.layout.listview);//切換界面
//	        curr=WhichView.LIST_VIEW;//標識界面
	        //final List<String[]>lst =mssg;//新建數組,並賦值
	        //final String[][] msg_tmp = new String[mssg.size()][];	
	        //mssg.toArray(msg_tmp);
	        //final List<String[]>lst;//新建數組,並賦值
	    	final List<String[]> lst = new ArrayList<String[]>();
	        
	        for (int i = 0; i < ftype.size();i++){
			    String[]data_tmp = new String[7] ;
	        	data_tmp[0]=ftype.get(i);
	        	data_tmp[1]="";
	        	data_tmp[2]="";
	        	data_tmp[3]="";
	        	data_tmp[4]="";
	        	data_tmp[5]="";
	        	data_tmp[6]="";
	        	lst.add(data_tmp);
	        	for (int j = 0;j < mssg.size();j++){
	        		if (mssg.get(j)[8]==ftype.get(i)){
	        		    String[]data_tmp1 = new String[7] ;
	        			data_tmp1[0] = mssg.get(j)[0];                                           //FundId
	           	  		data_tmp1[1] = mssg.get(j)[1];                                           //ShareId
	           	  		data_tmp1[2] = mssg.get(j)[2];                                           //FundAlias1Share
	           	  		data_tmp1[3] = mssg.get(j)[5].substring(0,mssg.get(j)[5].length()-2);    //Pnav
	           	  		data_tmp1[4] = mssg.get(j)[6].substring(0,mssg.get(j)[6].length()-2);    //PnavDiff
	           	  	    DecimalFormat fnum = new DecimalFormat("##0.00"); 
	                	String dd=fnum.format((float)(Math.round(Float.parseFloat(mssg.get(j)[7]) * 10000))/100); 
	           	  		data_tmp1[5] = dd;          //PnavRate	           	  		
	           	  		data_tmp1[6] = mssg.get(j)[3].substring(0, 10).replace("-", "");         //Ddate	        				  	        				  
	           	  		lst.add(data_tmp1);	        		  
	        		}
	        	}	        
	        }

	        //取得螢幕解析度
			final DisplayMetrics metricsMethodTwo = getResources().getDisplayMetrics();
           			
	        ListView lv_detail=(ListView)v.findViewById(R.id.ListView_detail);//拿到ListView的引用
	        BaseAdapter ba_detail=new BaseAdapter()//新建適配器
	        {
				@Override
				public int getCount() 
				{
					return lst.size();//得到列表的長度
				}
				@Override
				public Object getItem(int arg0){return null;}
				@Override
				public long getItemId(int arg0){return 0;}
				@Override
				public View getView(int arg0, View arg1, ViewGroup arg2)//為每一項添加內容
				{

					LinearLayout ll_detail=new LinearLayout(v.getContext());
					ll_detail.setOrientation(LinearLayout.HORIZONTAL);		//字體顏色
					ll_detail.setClickable(false);
					//ll_detail.setPadding(5,5,5,5);//四周留白
					
					if (lst.get(arg0)[1] == "") {
						TextView s= new TextView(v.getContext());
						s.setText(lst.get(arg0)[0]);//TextView中顯示的文字
						s.setTextSize(12);//字體大小
						s.setTextColor(getResources().getColor(R.color.white));//字體顏色
						//s.setPadding(1,2,2,1);//四周留白
					    s.setWidth(metricsMethodTwo.widthPixels);//寬度
					    //Log.d("widthPixels = " + metricsMethodTwo.widthPixels,"ABCDEFG");
					    s.setBackgroundColor(getResources().getColor(R.color.gray));
					    s.setGravity(Gravity.LEFT);
					    s.setClickable(false);
					    ll_detail.addView(s);//放入LinearLayout						
					}
					else
					{						
						for(int i = 2;i<lst.get(arg0).length;i++)//為每一行設置顯示的數據
						{					    
							TextView s= new TextView(v.getContext());
							if (i == 5 ){
	    						s.setText(lst.get(arg0)[i] + "%");//TextView中顯示的文字
							}
							else{
	    						s.setText(lst.get(arg0)[i]);//TextView中顯示的文字						
							}
								
		                    //Log.d("msg[" + arg0 + "][" + i + "]", msg[arg0][i]);
		                    //Log.d("msg[" + arg0 + "][" + i + "]", msg[arg0][i]);
							s.setTextSize(10);//字體大小
							//Log.d("Integer.valueOf(msg[5][arg0])" + msg[5][arg0], "DDDDDDDD");
							if (i == 2)
								s.setTextColor(getResources().getColor(R.color.yollow));//字體顏色
							else if (i == 6)	
								s.setTextColor(getResources().getColor(R.color.white));//字體顏色
							else {	
								float irate = Float.parseFloat(lst.get(arg0)[4]);
								if(irate > 0) {
									s.setTextColor(getResources().getColor(R.color.red));//字體顏色
								}
								else if(irate < 0) {
									s.setTextColor(getResources().getColor(R.color.green));//字體顏色
								}
								else {
									s.setTextColor(getResources().getColor(R.color.orange));//字體顏色									
								}
							}	
							//s.setTextColor(getResources().getColor(R.color.orange));//字體顏色																
							if (i == 2 ){
								if ((metricsMethodTwo.widthPixels/3*2)>320) {
								    s.setWidth(metricsMethodTwo.widthPixels- (int)((metricsMethodTwo.widthPixels/3*2)/4) * 4);								
								}
								else{
								    s.setWidth(metricsMethodTwo.widthPixels-320);//寛度								
								}
							    //s.setWidth(metricsMethodTwo.widthPixels-340);//寛度					
								if ((metricsMethodTwo.widthPixels/3*2)>320) 
									s.setPadding(3,2,1,2);//四周留白   
								else
									s.setPadding(3,2,1,2);//四周留白 
								    
							    s.setGravity(Gravity.LEFT);
							}
							/*else if (i == 6){
								s.setWidth(100);//寛度									
							    s.setGravity(Gravity.RIGHT);
							}*/
							else{
								if ((metricsMethodTwo.widthPixels/3*2)>320) {
									s.setWidth(((metricsMethodTwo.widthPixels/3*2)/4));//寛度									
								}
								else {
									s.setWidth(80);//寛度									
								}
								//s.setWidth(80);//寛度	
								if  (i == 6){
									if ((metricsMethodTwo.widthPixels/3*2)>320) 
										s.setPadding(1,2,3,2);//四周留白   
									else
										s.setPadding(0,2,0,2);//四周留白   										
								    s.setGravity(Gravity.RIGHT);
								}    
								else {	
									s.setPadding(1,2,1,2);//四周留白
							        s.setGravity(Gravity.RIGHT);
								}    
							}
						    ll_detail.addView(s);//放入LinearLayout
						}
					}	
					return ll_detail;//放入LinearLayout返回
				}        	
	        };    
	        lv_detail.setAdapter(ba_detail);//將適配器添加進ListView
	        
	        lv_detail.setOnItemClickListener//為列表添加監聽
	        (
	           new OnItemClickListener()
	           {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,//當點擊列表中的某一項時調用此函數
						long arg3) //arg2為點擊的第幾項
				{
					if (arg2%10 != 0) {
						String cccx=lst.get(arg2)[0];
						Toast.makeText(v.getContext(), cccx.toString(), Toast.LENGTH_SHORT).show();
					}
				}        	   
	           }
	        );
	 }
    
}