package com.example.listfragmenta;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class FragmentList extends ListFragment {		
	
	ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
	private HashMap<String,String> item;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub				
		super.onActivityCreated(savedInstanceState);
		ArrayAdapter<String> arradapt= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, ListData.name);		
		setListAdapter(arradapt);
		
		/*for(int i = 0; i < ListData.name.length ; i++){
			item = new HashMap<String,String>();
  			item.put( "line1", ListData.name[i].toString());
  			item.put( "line2", ListData.nameurl[i].toString());	  		
  		    list.add(item);
		}
		
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), list,
				android.R.layout.simple_list_item_2,
                new String[] { "line1","line2" },
                new int[] {R.id.txt_title, R.id.txt_subtitle});
		setListAdapter(adapter);
		*/
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		//super.onListItemClick(l, v, position, id);				
		Toast.makeText(getActivity(), ListData.name[position], Toast.LENGTH_SHORT).show();
		//showDetails(position);
		String clickedDetail = ListData.nameurl[position].toString();
		FragmentDetail  myDetailFragment = new FragmentDetail();
		Bundle bundle = new Bundle();
		bundle.putString("KEY_DETAIL", clickedDetail);
		myDetailFragment.setArguments(bundle);
		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();		
	    fragmentTransaction.replace(R.id.flst1, myDetailFragment);	    
	    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	    fragmentTransaction.addToBackStack(clickedDetail);
	    
		fragmentTransaction.commit();	 		
	}
	/*
	void showDetails(int index){
		getListView().setItemChecked(index, true);
		FragmentDetail df = FragmentDetail.newInstance(index) ;
		FragmentTransaction ft = getFragmentManager()
					.beginTransaction();
		ft.replace(R.id.list_detial_textvw1, df);  					
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
     	ft.commit();
		//mShownCheckPosition = index;		
	}*/
}
