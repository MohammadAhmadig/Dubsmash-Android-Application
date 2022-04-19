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

public class Interview extends ActionBarActivity {
	
	//private Context context;
	int pos = -1;
	GridView gridView;
	Activity context = this;
	private MediaPlayer mp;
	
	
	static final Integer[] MOBILE_OS = new Integer[] { 
		0 ,1,2, 3,4,5,6,7,8};
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kids);
		
		android.support.v7.app.ActionBar bar2 = getSupportActionBar();
		bar2.setBackgroundDrawable(new ColorDrawable(Color.rgb(240, 107, 91)));
		
		getActionBar().setTitle(R.string.interview);   
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
		
		gridView = (GridView) findViewById(R.id.gridView1);
 
		gridView.setAdapter(new InterviewAdapter(this));
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				 
				pos = position; 
				if (position > -1 && position < 9) {
					MediaRecorderRecipe.Sound = CurrentSound();
					Intent intent = new Intent(Interview.this, MediaRecorderRecipe.class);
			        startActivity(intent);
				}
			}
		});
		gridView.setOnItemLongClickListener(new OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                    int position, long arg3) {
            	int lastSound ;
            	pos = position; 
				if (position > -1 && position < 9) {
					lastSound = CurrentSound();
					stopPlaying();
				 	mp = MediaPlayer.create(Interview.this, lastSound);
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
			soundNow = (R.raw.h0);    
		} else if (pos == 1) {
			soundNow = (R.raw.h1);     
		} else if (pos == 2) {
			soundNow = (R.raw.h2);       
		} else if (pos == 3) {
			soundNow = (R.raw.h3);
		} else if (pos == 4) {
			soundNow = (R.raw.h4);
		} else if (pos == 5) {	
			soundNow = (R.raw.h5);
		} else if (pos == 6) {		
			soundNow = (R.raw.h6);
		} else if (pos == 7) {		
			soundNow = (R.raw.h7);      
		} else {
			soundNow = (R.raw.h8);
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
	
	public class InterviewAdapter extends BaseAdapter {
		private Context context;
	 
		public InterviewAdapter(Context context) {
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
			Picasso.with(context).load(R.drawable.z7).noFade().resize(200, 200).into(imageView);
			if (mobile.equals(0)) {
				textView.setText("معصومی نژاد");	
			} else if (mobile.equals(1)) {
				textView.setText("نگین اسمشو");
			} else if (mobile.equals(2)) {
				textView.setText("بیا");		
			} else if (mobile.equals(3)) {
				textView.setText("دیر پز");		
			} else if (mobile.equals(4)) {
				textView.setText("درسته یا نه");	
			} else if (mobile.equals(5)) {
				textView.setText("قدس");	
			} else if (mobile.equals(6)) {
				textView.setText("خودیاری");
			} else if (mobile.equals(7)) {
				textView.setText("نوشابه");					
			} else {
				textView.setText("پولو دادین");
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
