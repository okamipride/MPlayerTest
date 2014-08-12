package com.example.mplayertest;


import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends Activity {
	
	// Declare variables
    ProgressDialog pDialog;
    VideoView videoview;
    
    String VideoURL = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
    /*
    String VideoURL = "http://v.youku.com/player/getM3U8/vid/187257590/type/hd2/sid/140687766842010208/K/71e4868d88b1cb411829bdeb/video.m3u8";
    String rtsp01 = "rtsp://r6---sn-o097zues.c.youtube.com/CiILENy73wIaGQkwZjUJ4GIq8hMYESARFEgGUgZ2aWRlb3MM/0/0/0/video.3gp";
    String filepth01 = "android:resource://com.example.mplayertest/raw/video_h264";
    String filepth02 = "android.resource://com.example.mplayertest/raw/video_h264.mp4";
    String VideoURL02 = "http://www.dailymotion.com/cdn/H264-320x240/video/x22krda.mp4?auth=1407061119-2562-be9l3pmy-1e29c333a65b0db18b315deaf2bfef82";
    String VideoURL03 = "http://devm4.corp.miiicasa.com/~kevin_zhuang/youku/sample.html?vid=XNzQ5MDMwMzYw"; //youku js ª©
    */
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_view);
		//String url = getIntent().getStringExtra("videourl");
		
		videoview = (VideoView) findViewById(R.id.VideoView);
		
        pDialog = new ProgressDialog(VideoViewActivity.this);   
        pDialog.setTitle("Android Video Streaming Tutorial");
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        //pDialog.show();
		
        MediaController mediacontroller = new MediaController(VideoViewActivity.this);
        mediacontroller.setAnchorView(videoview);
        Uri video = Uri.parse(VideoURL);
        videoview.setMediaController(mediacontroller);
        
        //Uri videofromfile = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_h264);
        //videoview.setVideoPath("file:///android_res/raw/video_h264.mp4");
        videoview.setVideoURI(video);
        videoview.requestFocus();
        videoview.setOnPreparedListener(new OnPreparedListener() {
            // Close the progress bar and play the videomm
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoview.start();
            }
        });
		
	}
	
}
