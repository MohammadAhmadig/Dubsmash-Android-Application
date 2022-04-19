package com.example.test_video;

import com.squareup.picasso.Picasso;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class Music extends ActionBarActivity {
	
	//private Context context;
	int pos = -1;
	GridView gridView;
	Activity context = this;
	private MediaPlayer mp;
	
	
	static final Integer[] MOBILE_OS = new Integer[] { 
		0 ,1,2, 3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kids);
		
		android.support.v7.app.ActionBar bar2 = getSupportActionBar();
		bar2.setBackgroundDrawable(new ColorDrawable(Color.rgb(255, 165, 0)));
		
		getActionBar().setTitle(R.string.music);   
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
		
		gridView = (GridView) findViewById(R.id.gridView1);
 
		gridView.setAdapter(new MusicAdapter(this));
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				 
				pos = position; 
				if (position > -1 && position < 20) {
					MediaRecorderRecipe.Sound = CurrentSound();
					Intent intent = new Intent(Music.this, MediaRecorderRecipe.class);
			        startActivity(intent);
				}
			}
		});
		gridView.setOnItemLongClickListener(new OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                    int position, long arg3) {
            	int lastSound ;
            	pos = position; 
				if (position > -1 && position < 20) {
					lastSound = CurrentSound();
					stopPlaying();
				 	mp = MediaPlayer.create(Music.this, lastSound);
				 	mp.start();	
				}
				
            	
                //set the image as wallpaper 
                return true;
            }
        }); 
			
	}
	
	private void stopPlaying() {
	    if (mp != null) {
	        mp.stop();
	        mp.release();
	        mp = null;

	    }
	}
	
	private int CurrentSound(){
		int soundNow;
		if (pos == 0) {
			soundNow = (R.raw.b0);    
		} else if (pos == 1) {
			soundNow = (R.raw.b1);     
		} else if (pos == 2) {
			soundNow = (R.raw.b2);       
		} else if (pos == 3) {
			soundNow = (R.raw.b3);
		} else if (pos == 4) {
			soundNow = (R.raw.b4);
		} else if (pos == 5) {	
			soundNow = (R.raw.b5);
		} else if (pos == 6) {		
			soundNow = (R.raw.b6);
		} else if (pos == 7) {		
			soundNow = (R.raw.b7);
		} else if (pos == 8) {		
			soundNow = (R.raw.b8);
		} else if (pos == 9) {		
			soundNow = (R.raw.b9);
		} else if (pos == 10) {		
			soundNow = (R.raw.b10);
		} else if (pos == 11) {		
			soundNow = (R.raw.b11);
		} else if (pos == 12) {		
			soundNow = (R.raw.b12);
		} else if (pos == 13) {		
			soundNow = (R.raw.b13);
		} else if (pos == 14) {			
			soundNow = (R.raw.b14);
		} else if (pos == 15) {		
			soundNow = (R.raw.b15);      
		} else if (pos == 16) {		
			soundNow = (R.raw.b16);
		} else if (pos == 17) {		
			soundNow = (R.raw.b17);
		} else if (pos == 18) {		
			soundNow = (R.raw.b18);
		} else {
			soundNow = (R.raw.b19);
		}
		
		return soundNow;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean bRet=false;//set true is menu selection handled
		switch (item.getItemId()) {
		
		case android.R.id.home:
			stopPlaying();
            this.finish();
            return true;
		default:
			bRet=super.onOptionsItemSelected(item);
		}
		return bRet;
		
	}
	
	public class MusicAdapter extends BaseAdapter {
		private Context context;
	 
		public MusicAdapter(Context context) {
			this.context = context;
		}
	 
		public View getView(int position, View convertView, ViewGroup parent) {

			View gridView;
	 
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				
				gridView = new View(context);
	 
				// get layout from mobile.xml
				gridView = inflater.inflate(R.layout.kids_mobile, parent , false);
	 
				// set value into textview
				
	 
			} else {
				gridView = (View) convertView;
			}
			
			TextView textView = (TextView) gridView
					.findViewById(R.id.grid_item_label1);
			//textView.setText(mobileValues[position]);

			textView.setLines(2);//////////////////////////////////////////
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image1);
			

			Integer mobile = MOBILE_OS[position];
			Picasso.with(context).load(R.drawable.z0).noFade().resize(200, 200).into(imageView);
			if (mobile.equals(0)) {
				textView.setText("گلنار");	
			} else if (mobile.equals(1)) {
				textView.setText("گوشیو بردار");
			} else if (mobile.equals(2)) {
				textView.setText("حالا 1 2 3");		
			} else if (mobile.equals(3)) {
				textView.setText("هه هه");		
			} else if (mobile.equals(4)) {
				textView.setText("کاروان");	
			} else if (mobile.equals(5)) {
				textView.setText("مادر من");	
			} else if (mobile.equals(6)) {
				textView.setText("الهه ناز");
			} else if (mobile.equals(7)) {
				textView.setText("مرغ سحر");		
			} else if (mobile.equals(8)) {
				textView.setText("یارا گاهی");		
			} else if (mobile.equals(9)) {
				textView.setText("باریکلا");		
			} else if (mobile.equals(10)) {
				textView.setText("بابا دورودو");			
			} else if (mobile.equals(11)) {
				textView.setText("دینگار دادینگ");		
			} else if (mobile.equals(12)) {
				textView.setText("crazy frog");		
			} else if (mobile.equals(13)) {
				textView.setText("carzy frog2");		
			} else if (mobile.equals(14)) {
				textView.setText("سوت");	
			} else if (mobile.equals(15)) {
				textView.setText("یالا یالا");	
			} else if (mobile.equals(16)) {
				textView.setText("عمو سبزی فروش");
			} else if (mobile.equals(17)) {
				textView.setText("چه حالی داره");
			} else if (mobile.equals(18)) {
				textView.setText("گلنار 2");
			} else {
				textView.setText("منا منا");
			}
			
			return gridView;
		}
	 
		@Override
		public int getCount() {
			return MOBILE_OS.length;
		}
	 
		@Override
		public Object getItem(int position) {
			return null;
		}
	 
		@Override
		public long getItemId(int position) {
			return 0;
		}
	 
	}
}
