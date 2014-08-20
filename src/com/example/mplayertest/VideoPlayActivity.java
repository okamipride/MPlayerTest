package com.example.mplayertest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;





import com.example.mplayertest.MPlayerActivity.SourceType;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

/*
 * Video Play Activity which retrieved real film address from 3rd party service
 * 
 * */

public class VideoPlayActivity extends Activity {
	// Declare variables
	final static String TAG = "VideoPlayActivity";
    ProgressDialog pDialog;
    VideoView videoview;
    
    //String VideoURL = "http://pl.youku.com/playlist/m3u8?ts=1407998274&keyframe=0&vid=XNDQyNDc3MDg0&type=mp4&ep=eyaUHUCEXswH4SbfiT8bbi7qdSJcXJZ0gn7M%2F6YxA8ZQH%2BjQnj7Uzw%3D%3D&sid=9407998274501129281ec&token=7058&ctype=12&ev=1&oip=1931268481";
    
    // °ª²M m3u8 
    String VideoURL = "http://pl.youku.com/playlist/m3u8?ts=1408008280&keyframe=0&vid=XNTUzMDQzODky&type=hd2&ep=cCaUEkmMV88A4Svajj8bNSXmciYOXJZ0knrP%2FKYDSsRQE6HQnj%2FYwA%3D%3D&sid=240801110485612b946a1&token=7197&ctype=12&ev=1&oip=3079203019";

    static final String PRE_ADDR_YOUKU ="http://api.flvxz.com/site/youku/vid/";
    static final String PRE_ADDR_TODU ="http://api.flvxz.com/site/tudou/vid/";
    final String DEFAULT_YOUKU_HASH = "XNTUzMDQzODky";
    final String DEFAULT_TUDO_HASH = "5dwpMQeOYbw";
    
    String toBeTransAddr = PRE_ADDR_YOUKU + DEFAULT_YOUKU_HASH;
    //http://api.flvxz.com/site/youku/vid/XNTUzMDQzODky
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_view);	
		videoview = (VideoView) findViewById(R.id.VideoView);
		
        pDialog = new ProgressDialog(VideoPlayActivity.this);   
        pDialog.setTitle("Android Video Streaming Tutorial");
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
		
        MediaController mediacontroller = new MediaController(VideoPlayActivity.this);
        mediacontroller.setAnchorView(videoview);
        Uri video = Uri.parse(VideoURL);
        videoview.setMediaController(mediacontroller);
        //videoview.setVideoURI(video);
        videoview.requestFocus();
        videoview.setOnPreparedListener(new OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoview.start();
            }
        });		
	}
	   
	@Override
	protected void onResume() {
		super.onResume();
		Intent recieveIntent = this.getIntent();
		int source = recieveIntent.getIntExtra("source", SourceType.SOURCE_YOUKU);
		String hash = recieveIntent.getStringExtra("hashcode");
		//setup play string
		switch(source) {
			case SourceType.SOURCE_TUDO:
				if (hash != null && !hash.equals("")) {
					toBeTransAddr = PRE_ADDR_TODU + hash;
				} else {
					toBeTransAddr = PRE_ADDR_TODU + DEFAULT_TUDO_HASH;
				}
				break;
			case SourceType.SOURCE_YOUKU:
			default:
				if (hash != null && !hash.equals("")) {
					toBeTransAddr = PRE_ADDR_YOUKU + hash;
				} else {
					toBeTransAddr = PRE_ADDR_YOUKU + DEFAULT_YOUKU_HASH;
				}
			break;	
		}
		
		Log.e(TAG, "toBeTransAddr = " + toBeTransAddr);//
		new GetStreamAddr().execute(toBeTransAddr);
	}



	class GetStreamAddr extends AsyncTask <String, Void, String> {
		String ret = null;
		@Override
		protected String doInBackground(String... params) {
			String returnData = NetUtil.getHttpData(params[0]);
			
			try {
				ret = getAddressFromReturnYoukuTudo(returnData);
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(VideoPlayActivity.this,"no address", Toast.LENGTH_SHORT).show();
			} 
			
			return ret;
		}

		@Override
		protected void onPostExecute(String result) {
			if (result != null) {
				  Uri video = Uri.parse(result);
				  videoview.setVideoURI(video);
			}
		}
	}
	
	private String getAddressFromReturnYoukuTudo(String inputStr) throws SAXException, IOException, ParserConfigurationException {
		String ret = null;
		XMLDOMParser parser = new XMLDOMParser();
    	InputStream stream = new ByteArrayInputStream(inputStr.getBytes("UTF_8"));
    	Document doc = parser.getDocument(stream);
    	NodeList nodeList = doc.getElementsByTagName("video");
  
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				String type = (String) parser.getValue(element, "ftype");
				String quality = (String) parser.getValue(element, "yurl");
							
				if (type.equals("mp4")) {			 	  	          
					NodeList furl = element.getElementsByTagName("furl");
					Element line = (Element) furl.item(0);			
					ret = getCharacterDataFromElement(line);
					i = nodeList.getLength() + 1;
					Log.d(TAG,"getAddressFromReturn" + ret);
			     }
			}
		
		return ret;
	}
	  public static String getCharacterDataFromElement(Element e) {
		    Node child = (Node) e.getFirstChild();
		    if (child instanceof CharacterData) {
		      CharacterData cd = (CharacterData) child;
		      return cd.getData();
		    }
		    return "";
	}
}
