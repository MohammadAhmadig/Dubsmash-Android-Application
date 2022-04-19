package com.example.test_video;

import android.app.Activity;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class VideoShow extends Activity  implements TextureView.SurfaceTextureListener {
    private String pathToVideo = Environment.getExternalStorageDirectory().toString() + "/Dubsmash_IRANI/"+ "O"+ MediaRecorderRecipe.VideoName +".mp4";;
    private TextureView textureView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_view);
        textureView = (TextureView)findViewById(R.id.VVSimpleVideo);
        textureView.setSurfaceTextureListener(this);
        if(MediaRecorderRecipe.cameraFront){
        	textureView.setRotation(90.0f);
            textureView.setScaleX(-1);
        }
        else{
        	textureView.setRotation(270.0f);
            textureView.setScaleX(-1);
        }
        
        //pathToVideo = getIntent().getStringExtra("path");
        /*
        Button btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener() {
        	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//IsPlaying = true;
				MediaPlayer mediaPlayer = new MediaPlayer();
		        //mediaPlayer.setSurface(new Surface(surface));

		       try{
		        mediaPlayer.setDataSource(pathToVideo);
		        
		        mediaPlayer.prepare();

		            //Display display = getWindowManager().getDefaultDisplay();
		            //Point size = new Point();
		            //display.getSize(size);//x = 1080, y = 1920

		            //Point videoDimensions = new Point(mediaPlayer.getVideoWidth(),mediaPlayer.getVideoHeight());//x = 320, y = 240
		            //Point resultParams = VideoHelpers.getScaledDimension(new Point(videoDimensions.y * 1000, videoDimensions.x * 1000), size);//x = 1080, y = 1440
		            //ViewGroup.LayoutParams params = textureView.getLayoutParams();
		            //params.height = resultParams.y;//1440
		            //params.width = resultParams.x;//1080
		            //textureView.setLayoutParams(params);
		            
		        mediaPlayer.start();
		        //IsPlaying = false;
		       }catch(Exception e) {
		            e.printStackTrace();
		        }
		       
		            
			}
		});
        */
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new OnClickListener() {
        	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				share();
			}
		});
        
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setSurface(new Surface(surface));

        try {
            mediaPlayer.setDataSource(pathToVideo);

            mediaPlayer.prepare();

            //Display display = getWindowManager().getDefaultDisplay();
            //Point size = new Point();
            //display.getSize(size);//x = 1080, y = 1920

            //Point videoDimensions = new Point(mediaPlayer.getVideoWidth(),mediaPlayer.getVideoHeight());//x = 320, y = 240
            //Point resultParams = VideoHelpers.getScaledDimension(new Point(videoDimensions.y * 1000, videoDimensions.x * 1000), size);//x = 1080, y = 1440
            ViewGroup.LayoutParams params = textureView.getLayoutParams();
            params.height = 400;//1440
            params.width = 500;//1080
            textureView.setLayoutParams(params);
           // if(IsPlaying){
            	
           	mediaPlayer.start();
           	//mediaPlayer.pause();
           	//IsPlaying = false;
           // }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {return false;}
    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {}
    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {}
    
    public void share(){
    	Intent share = new Intent(Intent.ACTION_SEND);
		share.putExtra(Intent.EXTRA_STREAM, Uri.parse(pathToVideo));
		share.setType("video/*");
		startActivity(Intent.createChooser(share, (String) getText(R.string.share)));
    }
    
    @Override
    public void onBackPressed() {
        //Display alert message when back button has been pressed
    	finish();
        
    }
}

/*
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.widget.MediaController;
import android.widget.VideoView;


public class VideoShow extends Activity {

    //SurfaceHolder _videoHolder;
	private MediaPlayer mediaPlayer = new MediaPlayer();
    private TextureView videoView;
    
    
    
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.video_view);

        String path = Environment.getExternalStorageDirectory().toString() + "/Dubsmash_IRANI/"+ "Out"+ MediaRecorderRecipe.VideoName +".mp4";
        //MediaPlayer _mediaPlayer;
        //String _path = "/sdcard/abc.3gp";
        //_videoHolder = _UIVideo.getHolder();
        
       
        
        VideoView videoView = (VideoView)this.findViewById(R.id.VVSimpleVideo);
        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoView);
        mc.setMediaPlayer(videoView);
        videoView.setMediaController(mc);

        videoView.setVideoPath(path);


        videoView.requestFocus();
        videoView.start();
        
    }
}
*/