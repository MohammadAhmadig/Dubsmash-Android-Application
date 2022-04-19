package com.example.test_video;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MediaRecorderRecipe extends Activity implements SurfaceHolder.Callback {
	
public static String VideoName = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()).replaceAll(" ", "_").replaceAll(":", "-");
public static int Sound;
public static String SoundName;
public static boolean cameraFront = false;
private final String VIDEO_PATH_NAME = Environment.getExternalStorageDirectory().toString() + "/Dubsmash_IRANI/Temp_Files/"+ VideoName +".mp4";
private MediaPlayer mp;
private MediaRecorder mMediaRecorder;
private Camera mCamera;
private SurfaceView mSurfaceView;
private SurfaceHolder mHolder;
private View mToggleButton;
private View mToggleButton2;
private boolean mInitSuccesful;
boolean recording = false;
private Context myContext;
private int duration;


@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.media_recorder_recipe);

	myContext = this;
    // we shall take the video in landscape orientation
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
    mHolder = mSurfaceView.getHolder();
    mHolder.addCallback(this);
    mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    //Toast.makeText(getApplicationContext(), String.valueOf(Camera.getNumberOfCameras()), 
	//		Toast.LENGTH_LONG).show();
    
    SoundName = String.valueOf(Sound);
    makeFile(Sound);///////////////////////////////////////////////////////////////////////////////
    
	//chek kardan ke aya doorbin darad
	if (! myContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
		Toast.makeText(getApplicationContext(), "doorbin nadarad!!!", 
    						Toast.LENGTH_LONG).show();
		finish();
	}
	
    mToggleButton = (ToggleButton) findViewById(R.id.toggleRecordingButton);
    mToggleButton.setOnClickListener(new OnClickListener() {
        @Override
        // toggle video recording
        public void onClick(View v)  {
            if (((ToggleButton)v).isChecked()) {
				recording = true;
				
                try {
					
                	mp = MediaPlayer.create(MediaRecorderRecipe.this, Sound);
    				duration=mp.getDuration();
    				mp.start();	
                    mMediaRecorder.start();
					 //Thread.sleep(1000);///////////////////////////////////////////////////concurency
					
                    Thread.sleep(duration); // This will recode for 10 seconds, if you don't want then just remove it.
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //finish();
				Intent intent = new Intent(MediaRecorderRecipe.this, Loading.class);
		        startActivity(intent);
            }
            else {
                mMediaRecorder.stop();
                mMediaRecorder.reset();
                
				try {
					initRecorder(mHolder.getSurface());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				recording = false;
            }
        }
    });     
	
	mToggleButton2 = (ToggleButton) findViewById(R.id.button_ChangeCamera);
	mToggleButton2.setOnClickListener(new OnClickListener() {
	@Override
		public void onClick(View v) {
			// get the number of cameras
			if (!recording) {
				int camerasNumber = Camera.getNumberOfCameras();
				if (camerasNumber > 1) {
					// release the old camera instance
					// switch camera, from the front and the back and vice versa
					//releaseMediaRecorder();///////////////////////////////////////////////////////////////////////////////////////////////////////
					releaseCamera();
					//shutdown();
					chooseCamera();
				} else {
					Toast toast = Toast.makeText(myContext, "Sorry, your phone has only one camera!", Toast.LENGTH_LONG);
					toast.show();
				}
			}
		}
	});  
}

private void makeFile(int s){
	byte[] buffer=null;
    int size=0;

    String filename= SoundName +".mp4";

    File filepath = Environment.getExternalStorageDirectory();

	// Create a new folder in SD Card
	File dir = new File(filepath.getAbsolutePath()
			+ "/Dubsmash_IRANI/");
	boolean exists = dir.exists();
    if (!exists){dir.mkdirs();}
    
    File dir2 = new File(filepath.getAbsolutePath()
			+ "/Dubsmash_IRANI/Temp_Files/");
	boolean exists2 = dir2.exists();
    if (!exists2){dir2.mkdirs();}
    
    File file2 = new File(dir2 , filename);
    
    InputStream fIn = getBaseContext().getResources().openRawResource(s);
    
    try {
     size = fIn.available();
     buffer = new byte[size];
     fIn.read(buffer);
     fIn.close();
    } catch (IOException e) {
     // TODO Auto-generated catch block
    }
	
    if (file2.exists () == false){
    FileOutputStream save;
    try {
     save = new FileOutputStream(filepath.getAbsolutePath() + "/Dubsmash_IRANI/Temp_Files/" + filename);
     save.write(buffer);
     save.flush();
     save.close();
    } catch (FileNotFoundException e) {
     // TODO Auto-generated catch block
    } catch (IOException e) {
     // TODO Auto-generated catch block
    }
    }  
}

/* Init the MediaRecorder, the order the methods are called is vital to
 * its correct functioning */
private void initRecorder(Surface surface) throws IOException {
    // It is very important to unlock the camera before doing setCamera
    // or it will results in a black preview
	int cameraCount = -1;
	if(mCamera == null) {
    	/*
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();
        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                try {
                    mCamera = Camera.open(camIdx);
                } catch (RuntimeException e) {
                	Toast.makeText(getApplicationContext(), "Faild open camera!!!", 
    						Toast.LENGTH_LONG).show();
                }
            }
        }
        */
		/*
		int cameraId = findFrontFacingCamera();
		if (cameraId >= 0) {
			// open the backFacingCamera
			// set a picture callback
			// refresh the preview

			mCamera = Camera.open(cameraId);
			// mPicture = getPictureCallback();
			//mPreview.refreshCamera(mCamera);
		}*/
		if(cameraFront){
			try {
			cameraCount = findFrontFacingCamera();
			if(cameraCount != -1){
				
				mCamera = Camera.open(cameraCount);
				mCamera.setDisplayOrientation(90);
				mCamera.lock();
				mCamera.unlock();
				//mCamera.setDisplayOrientation(90);
			}
			}
			catch (Exception e){
				// Camera is not available (in use or does not exist)
				Toast.makeText(getApplicationContext(), "Faild open camera!!!", 
								Toast.LENGTH_LONG).show();
			}
		}
		else if(!cameraFront){
			try {
			cameraCount = findBackFacingCamera();
				if(cameraCount != -1){
					
					mCamera = Camera.open(cameraCount);
					mCamera.setDisplayOrientation(90);
					mCamera.lock();
					mCamera.unlock();
					//mCamera.setDisplayOrientation(90);
				}
			}
			catch (Exception e){
				// Camera is not available (in use or does not exist)
				Toast.makeText(getApplicationContext(), "Faild open camera!!!", 
								Toast.LENGTH_LONG).show();
			}
		}	
		
		
    }
	
    if(mMediaRecorder == null)  mMediaRecorder = new MediaRecorder();
    mMediaRecorder.setPreviewDisplay(surface);
	
    mMediaRecorder.setCamera(mCamera);

	if(cameraFront){
		mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
		mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
    
		CamcorderProfile profile = CamcorderProfile.get(CameraInfo.CAMERA_FACING_FRONT,CamcorderProfile.QUALITY_HIGH);
		profile.fileFormat = MediaRecorder.OutputFormat.MPEG_4;
		profile.videoCodec = MediaRecorder.VideoEncoder.MPEG_4_SP;
		profile.videoFrameHeight = 480;
		profile.videoFrameWidth = 640;
		profile.videoBitRate = 30;
		//profile.videoFrameRate = 30;
		
    // Apply to MediaRecorder
		mMediaRecorder.setProfile(profile);
	}
	else if(!cameraFront){
		mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
		//mMediaRecorder.setProfile(CamcorderProfile.get(1,CamcorderProfile.QUALITY_HIGH));
   //       mMediaRecorder.setOutputFormat(8);
		mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		
		mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
    
		mMediaRecorder.setVideoEncodingBitRate(512 * 1000);
    
		mMediaRecorder.setVideoFrameRate(30);
		mMediaRecorder.setVideoSize(640, 480);
	}
    mMediaRecorder.setOutputFile(VIDEO_PATH_NAME);
    
    try {
        mMediaRecorder.prepare();
    } catch (IllegalStateException e) {
        // This is thrown if the previous calls are not called with the 
        // proper order
        e.printStackTrace();
    }

    mInitSuccesful = true;
}

private int findFrontFacingCamera() {
	int cameraId = -1;
	// Search for the front facing camera
	int numberOfCameras = Camera.getNumberOfCameras();
	for (int i = 0; i < numberOfCameras; i++) {
		CameraInfo info = new CameraInfo();
		Camera.getCameraInfo(i, info);
		if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
			cameraId = i;
			//cameraFront = true;
			break;
		}
	}
	return cameraId;
}

private int findBackFacingCamera() {
		int cameraId = -1;
		// Search for the back facing camera
		// get the number of cameras
		int numberOfCameras = Camera.getNumberOfCameras();
		// for every camera check
		for (int i = 0; i < numberOfCameras; i++) {
			CameraInfo info = new CameraInfo();
			Camera.getCameraInfo(i, info);
			if (info.facing == CameraInfo.CAMERA_FACING_BACK) {
				cameraId = i;
				//cameraFront = false;
				break;
			}
		}
		return cameraId;
	}

private Camera openFrontFacingCameraGingerbread() {
    int cameraCount = 0;
    Camera cam = null;
    Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
    cameraCount = Camera.getNumberOfCameras();
    for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
        Camera.getCameraInfo(camIdx, cameraInfo);
        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            try {
                cam = Camera.open(camIdx);
            } catch (RuntimeException e) {
            	Toast.makeText(getApplicationContext(), "Faild open camera!!!", 
						Toast.LENGTH_LONG).show();
            }
        }
    }

    return cam;
}

public void chooseCamera() {
		// if the camera preview is the front
		if (cameraFront) {
			int cameraId = findBackFacingCamera();
			if (cameraId >= 0) {
				// open the backFacingCamera
				// set a picture callback
				// refresh the preview
				cameraFront=false;
				shutdown2();
	            try {
					initRecorder(mHolder.getSurface());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*
				mCamera = Camera.open(cameraId);
				mCamera.setDisplayOrientation(90);
				mCamera.lock();
				mCamera.unlock();*/
			}
			else{
				Toast.makeText(getApplicationContext(), "doorbin nadarad!!!", 
						Toast.LENGTH_LONG).show();
			}
		} else {
			int cameraId = findFrontFacingCamera();
			if (cameraId >= 0) {
				// open the backFacingCamera
				// set a picture callback
				// refresh the preview
				cameraFront=true;
				shutdown2();
	            try {
					initRecorder(mHolder.getSurface());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*
				mCamera = Camera.open(cameraId);
				mCamera.setDisplayOrientation(90);
				mCamera.lock();
				mCamera.unlock();*/
			}
			else{
				Toast.makeText(getApplicationContext(), "doorbin jolo nadarad!!!", 
						Toast.LENGTH_LONG).show();
			}
		}
	}

@Override
public void surfaceCreated(SurfaceHolder holder) {
	
    try {
        if(!mInitSuccesful)
            initRecorder(mHolder.getSurface());
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@Override
public void surfaceDestroyed(SurfaceHolder holder) {
    shutdown();
}

@Override
public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

private void shutdown() {
    // Release MediaRecorder and especially the Camera as it's a shared
    // object that can be used by other applications
    mMediaRecorder.reset();
    mMediaRecorder.release();
    mCamera.release();
	//mCamera.lock();

    // once the objects have been released they can't be reused
    mMediaRecorder = null;
    mCamera = null;
}
private void shutdown2() {
    // Release MediaRecorder and especially the Camera as it's a shared
    // object that can be used by other applications
    mMediaRecorder.reset();
    mMediaRecorder.release();
    //mCamera.release();
	//mCamera.lock();

    // once the objects have been released they can't be reused
    mMediaRecorder = null;
    mCamera = null;
}
private void releaseCamera() {
		// stop and release camera
		if (mCamera != null) {
			mCamera.release();
			mCamera = null;
		}
	}

}
