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
    
   // String VideoURL = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
    // From Parsing JS Youku
   //String VideoURL = "http://v.youku.com/player/getM3U8/vid/182656611/type/hd2/sid/140789772294018903/K/a97cb1a180e870db1411e700/video.m3u8";
   //String VideoURL = "http://v.youku.com/player/getM3U8/vid/187300118/type/hd2/sid/140790002613314935/K/b11f2e4b49c2a2f3161dd658/video.m3u8"; // ios 
   // String VideoURL = "http://r8---sn-ipoxu-un5s.googlevideo.com/videoplayback?ipbits=0&mm=31&sver=3&id=o-AKND6zl5MJVnkq3m_l8ywmwK5Q-oOoAWJ6hSTEZkZI45&mt=1407904663&expire=1407926324&signature=14241880E485ADB95148D6D1F8B4E3F21BCB3779.58AA984660939";
     //String VideoURL = "http://r8---sn-ipoxu-un5s.googlevideo.com/videoplayback?upn=j6QjttaacA4&ip=125.227.251.150&key=yt5&itag=22&ratebypass=yes&signature=2993CF1631588EC3ED440BFF053D50BACC855A5C.DDC9B387B20BB41B6446B95A14F9BED1D924C357&ms=au&sver=3&fexp=902408%2C927622%2C931983%2C934024%2C934030%2C946013%2C949402&mm=31&expire=1407929166&id=o-AFDGtfs9cxphmX-nVpChOHTkumyg2PdnoY-vfWivgmiP&mv=m&initcwndbps=2772000&source=youtube&sparams=id%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Cratebypass%2Csource%2Cupn%2Cexpire&mws=yes&ipbits=0&mt=1407907504&signature=undefined&title=DesignBytes%3A+Material+Design+in+the+Google+I%2FO+App";
   
    //FLVXZ
   // String VideoURL = "http://pl.youku.com/playlist/m3u8?ts=1407995010&keyframe=0&vid=XNzMwNjI2NDQ0&type=mp4&ep=ciaUHUCEU84A7CrWgD8bMyXmfXMLXJZ0vGLC%2F4gbAsVQKejQnD%2FSzw%3D%3D&sid=040799500999812d94944&token=5138&ctype=12&ev=1&oip=3079203019";
   //String VideoURL = "http://pl.youku.com/playlist/m3u8?ts=1407998274&keyframe=0&vid=XNDQyNDc3MDg0&type=mp4&ep=eyaUHUCEXswH4SbfiT8bbi7qdSJcXJZ0gn7M%2F6YxA8ZQH%2BjQnj7Uzw%3D%3D&sid=9407998274501129281ec&token=7058&ctype=12&ev=1&oip=1931268481";
     String VideoURL = "http://k.youku.com/player/getFlvPath/sid/340833026178512b7cdfd_02/st/mp4/fileid/03000802015187BE738F6F0230E416AB237B8B-4D0C-7D2A-0E75-2CF3633882E3?K=9415c3f4ea146f7e2829c8eb&hd=1&ymovie=1&myp=0&ts=241&ypp=2&ctype=12&ev=1&token=6729&oip=3079203019&ep=cSaUEkqOVswG5CTXjT8bNSuxICFbXP4J9h%2BFidJgALohSeC460vWxJXGSIlCFPoZByYHFpqDqdPgGEhmYfQ3qRsQ2DqqSPrgi%2Ffm5a8gspMGZRo%2Bes%2Bit1SYRDL4";
    //youtube live
   //String VideoURL = "http://smanifest.googlevideo.com/api/manifest/hls_variant/ipbits/0/maudio/1/key/yt5/ip/218.161.19.240/source/yt_live_broadcast/pmbypass/yes/id/SwrKzkRUlaw.5/upn/nUDomajssos/sparams/gcr%2Cid%2Cip%2Cipbits%2Citag%2Cmaudio%2Cplaylist_type%2Cpmbypass%2Csource%2Cexpire/fexp/902408%2C908549%2C913430%2C914084%2C924635%2C927622%2C931983%2C934024%2C934030%2C938008%2C941367%2C946023/gcr/tw/sver/3/expire/1407873490/signature/7F75329686046E1703B01833CAA48EC5B5BF33D9.459C89737337E60EAE68FDFBDA83073B19772DBA/playlist_type/LIVE/itag/0/file/index.m3u8";
    
    //String VideoURL = "http://v.youku.com/player/getM3U8/vid/187257590/type/hd2/sid/140784118163610846/K/e0c0b484b84d2d831829c53c/video.m3u8";
    //From Parsing JS DailyMotion
    //String VideoURL = "http://www.dailymotion.com/cdn/H264-848x480/video/x23da8k.mp4?auth=1408013179-2562-u9m0nbqn-fd05371d36e83de2e222a523fe01a2c5";
    //String VideoURL = "http://devm4.corp.miiicasa.com/~kevin_zhuang/youku/sample.html?vid=XNzQ5MDMwMzYw";
    /*
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
        pDialog.setTitle("Android Video Streaming");
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
       // pDialog.show();
		
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
