package com.example.mplayertest;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MPlayerActivity extends Activity {

	final static String TAG = "MPlayerActivity";
	Button button;
	String inputData = "";
	int sourceType = SourceType.SOURCE_YOUKU;
	private ArrayAdapter<String> sourceListAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mplayer);
        Spinner sourceSelector = (Spinner) findViewById(R.id.spn_sourcetype);        
        sourceListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new String[]{ "youku", "tudo"});
        sourceListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceSelector.setAdapter(sourceListAdapter);
        sourceSelector.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
				sourceType =  position;
				Log.d(TAG, "sourceType Selected = " + Integer.toString(position) );
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				sourceType = SourceType.SOURCE_YOUKU;
				
			} 
        	
        });
        	         
        
        
        
        
        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.MyButton);
         
        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
 
            	/*
                Intent myIntent = new Intent(MPlayerActivity.this,
                        VideoViewActivity.class);
                */
            	
            	EditText hashcode = (EditText) findViewById(R.id.edt_hash);
            	Intent myIntent = new Intent(MPlayerActivity.this, VideoPlayActivity.class);
            	myIntent.putExtra("source", sourceType);
            	Log.d(TAG, "sourceType Extra = " + sourceType );
				
            	myIntent.putExtra("hashcode", (hashcode.getText().toString()));
            	
            	
                startActivity(myIntent);
                
            }
        });        
    }
    
    public class SourceType {
    	final static int SOURCE_YOUKU = 0;
    	final static int SOURCE_TUDO = 1; 	
    }
}
    