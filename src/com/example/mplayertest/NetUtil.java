package com.example.mplayertest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class NetUtil {
	final static String TAG = "NetUtil";
	
	public static String getHttpData(String addr) {
		String ret = null;
	
		try {

			URI url = new URI(addr);
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(new HttpGet(url));
			StatusLine statusLine = response.getStatusLine();
			if(statusLine.getStatusCode() == HttpStatus.SC_OK){
			    ByteArrayOutputStream out = new ByteArrayOutputStream();
			    response.getEntity().writeTo(out);
			    out.close();
			    ret = out.toString();
			    Log.d(TAG, "response string " + ret);
			    //..more logic
			} else{
				    //Closes the connection.
				    response.getEntity().getContent().close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}
}
