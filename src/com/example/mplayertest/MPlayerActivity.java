package com.example.mplayertest;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MPlayerActivity extends Activity {

	Button button;
	 String inputData = "";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mplayer);
        
        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.MyButton);
        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
 
              //start video play
                Intent myIntent = new Intent(MPlayerActivity.this,
                        VideoViewActivity.class);
                startActivity(myIntent);
            }
        });        
    }
}
    