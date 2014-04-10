package com.example.demo01;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.location.Location;
import android.location.LocationManager;
import android.location.Criteria;
import android.widget.Toast;


public class Fragment6 extends Fragment {
    private View v;
    private WebView webviewloc;
	private LocationManager lms;
	private Location location;
    private String map_html;
    private String Lat;  
    private String Lon;  
    private String bestProvider = LocationManager.GPS_PROVIDER; 
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationServiceInitial();
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
        v = inflater.inflate(R.layout.fragment6, container, false);

        Button btnLoc1 = (Button) v.findViewById(R.id.btnLoc1);  
        Button btnLoc2 = (Button) v.findViewById(R.id.btnLoc2);  
        Button btnLoc3 = (Button) v.findViewById(R.id.btnLoc3);  
        Button btnLoc4 = (Button) v.findViewById(R.id.btnLoc4);  
        btnLoc1.setOnClickListener(Click);  
        btnLoc2.setOnClickListener(Click);  
        btnLoc3.setOnClickListener(Click);  
        btnLoc4.setOnClickListener(Click);  

        setupWebView();
        return v;
    }

    public Location locationServiceInitial() 
    { 
    	LocationManager status = (LocationManager) (getActivity().getSystemService(Context.LOCATION_SERVICE)); 
       
    	if (status.isProviderEnabled(LocationManager.GPS_PROVIDER) || status.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) 
    	{ 
    		lms = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE); 
    		Criteria criteria = new Criteria(); 
    		bestProvider = lms.getBestProvider(criteria, true); 
    		location = lms.getLastKnownLocation(bestProvider); 
        
    		if(location != null){ 
                return location; 
            }else{ 
                return null; 
            } 
    	} else { 
    		return null; 
    	}  
    } 
    
    private View.OnClickListener Click= new View.OnClickListener ()   
    {  
        @Override 
        public void onClick(View view)   
        {
    		switch(view.getId())  
            {  
            	case R.id.btnLoc1:  
            		Lat = "25.04563";  
            		Lon = "121.5485";
            		break;
            	case R.id.btnLoc2:
            		Lat = "24.141538";  
            		Lon = "120.6749";
            		break;
            	case R.id.btnLoc3:
            		Lat = "22.632233";  
            		Lon = "120.313039";
            		break;
            	
            	case R.id.btnLoc4:
            		if (location == null){
            			Toast.makeText(getActivity(), "無法定位座標", Toast.LENGTH_LONG).show(); 
            		}
            		else{
                		Lat = String.valueOf(location.getLatitude());  
                		Lon = String.valueOf(location.getLongitude());
            		}
            		break;
            }

          	setMapUrl();
           	webviewloc.loadData(map_html, "text/html", "utf-8");
        }  
    };  
    
    private void setupWebView(){
    	webviewloc = (WebView) v.findViewById(R.id.webviewloc);
    	webviewloc.getSettings().setJavaScriptEnabled(true);
    	setMapUrl();
    	webviewloc.loadData(map_html, "text/html", "utf-8");
    }
    
    private void setMapUrl()
    {
		if (Lat==null || Lon==null)
		{
			Lat = "25.04563";	
			Lon = "121.5485";
		}
			
		map_html = "<!DOCTYPE html>" +
				   "<html>" +
				   "<head><meta charset=\"utf-8\">" +
    			   "<title>Google Maps</title>" +
    			   "<script src=\"https://maps.googleapis.com/maps/api/js?sensor=false&v=3.9\"></script>" +
    			   "<script>function initialize()" +
    			       "{var lat = " + Lat +  "; " +
    			   	   " var lon = " + Lon + "; " +
    			   	   " var latlon = new google.maps.LatLng(lat,lon); " +
    			   	   " var mapOptions = { zoom: 16, mapTypeId: google.maps.MapTypeId.ROADMAP, center: latlon}; " +
    			   	   " map = new google.maps.Map(document.getElementById('map'),mapOptions); " +
    			   	   " var marker = new google.maps.Marker({position: latlon, map: map,});} " +
    			   "</script>" +
			   	   "</head>" +
			   	   "<body onload=\"initialize()\">" +
			   	   "<div id=\"map\" style=\"width: device-width; height: 450px\"></div>" +
			   	   "</body>" +
			   	   "</html>";
    }

}
