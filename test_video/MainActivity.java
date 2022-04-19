package com.example.test_video;


import com.squareup.picasso.Picasso;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ActionBarActivity {
	
	//private Context context;
	int pos = -1;
	GridView gridView;
	private SharedPrefence sharedPreference;
	private SharedPrefence sharedPreference2;
	Activity context = this;
	private String text="shortcut_icon";
	private String text2="";
	
	static final Integer[] MOBILE_OS = new Integer[] { 
		0 ,1,2, 3,4,5,6,7,8,9,10};
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		android.support.v7.app.ActionBar bar2 = getSupportActionBar();
		bar2.setBackgroundDrawable(new ColorDrawable(Color.rgb(56, 141, 135)));
		
		//33, 150, 243
        //getActionBar().setTitle();   
        sharedPreference = new SharedPrefence();
		sharedPreference2 = new SharedPrefence();
		
		text2 = sharedPreference2.getValue(context);
		
        if( text2 == null ){
        	ShortcutIcon();
        }
        
		gridView = (GridView) findViewById(R.id.gridView0);
 
		gridView.setAdapter(new MainAdapter(this));
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				 
				pos = position; 
				if (position == 0) {
					Intent intent = new Intent(MainActivity.this, Music.class);
			        startActivity(intent);
				}else if(position == 1){
					Intent intent = new Intent(MainActivity.this, Kids.class);
			        startActivity(intent);
				}else if(position == 2){
					Intent intent = new Intent(MainActivity.this, TV.class);
			        startActivity(intent);
				}else if(position == 3){
					Intent intent = new Intent(MainActivity.this, Football.class);
			        startActivity(intent);
				}else if(position == 4){
					Intent intent = new Intent(MainActivity.this, Animal.class);
			        startActivity(intent);
				}else if(position == 5){
					Intent intent = new Intent(MainActivity.this, JenabKhan.class);
			        startActivity(intent);
				}else if(position == 6){
					Intent intent = new Intent(MainActivity.this, Funny.class);
			        startActivity(intent);
				}else if(position == 7){
					Intent intent = new Intent(MainActivity.this, Interview.class);
			        startActivity(intent);
				}else if(position == 8){
					Intent intent = new Intent(MainActivity.this, Nostalgia.class);
			        startActivity(intent);
				}else if(position == 9){
					AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
			                MainActivity.this);
			        // Setting Dialog Title
			        alertDialog2.setTitle(R.string.darbare);
			        
			        // Setting Dialog Message
			        alertDialog2.setMessage(R.string.email);
			        // Setting Icon to Dialog
			        alertDialog2.setIcon(R.drawable.ic_launcher);
			        // Setting Positive "Yes" Button
			        alertDialog2.show();
				}else {
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(
			                MainActivity.this);
			        // Setting Dialog Title
			        alertDialog.setTitle(R.string.guide);
			        
			        // Setting Dialog Message
			        alertDialog.setMessage(R.string.guide2);
			        // Setting Icon to Dialog
			        alertDialog.setIcon(R.drawable.ic_launcher);
			        // Setting Positive "Yes" Button
			        alertDialog.show();
				
				}
			}
		});
			
	}
	
	private void ShortcutIcon(){

	    Intent shortcutIntent = new Intent(getApplicationContext(), MainActivity.class);
	    shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

	    Intent addIntent = new Intent();
	    addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
	    addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, (String) getText(R.string.app_name));
	    addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.ic_launcher));
	    addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
	    addIntent.putExtra("duplicate", false);
	    
	    getApplicationContext().sendBroadcast(addIntent);
	    sharedPreference.save(context, text);////////////////////////////
	}
	
	@Override
    public void onBackPressed() {
        finish();
    }
	
	
	public class MainAdapter extends BaseAdapter {
		private Context context;
	 
		public MainAdapter(Context context) {
			this.context = context;
		}
	 
		public View getView(int position, View convertView, ViewGroup parent) {

			View gridView;
	 
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				
				gridView = new View(context);
	 
				// get layout from mobile.xml
				gridView = inflater.inflate(R.layout.main_adapter, parent , false);
	 
				// set value into textview
				
	 
			} else {
				gridView = (View) convertView;
			}
	
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image0);

			Integer mobile = MOBILE_OS[position];
			
			if (mobile.equals(0)) {
				Picasso.with(context).load(R.drawable.z0).noFade().resize(400, 400).into(imageView);	
			} else if (mobile.equals(1)) {
				Picasso.with(context).load(R.drawable.z1).noFade().resize(400, 400).into(imageView);	
			} else if (mobile.equals(2)) {
				Picasso.with(context).load(R.drawable.z2).noFade().resize(400, 400).into(imageView);	
			} else if (mobile.equals(3)) {
				Picasso.with(context).load(R.drawable.z3).noFade().resize(400, 400).into(imageView);
			} else if (mobile.equals(4)) {
				Picasso.with(context).load(R.drawable.z4).noFade().resize(400, 400).into(imageView);	
			} else if (mobile.equals(5)) {
				Picasso.with(context).load(R.drawable.z5).noFade().resize(400, 400).into(imageView);	
			} else if (mobile.equals(6)) {
				Picasso.with(context).load(R.drawable.z6).noFade().resize(400, 400).into(imageView);
			} else if (mobile.equals(7)) {
				Picasso.with(context).load(R.drawable.z7).noFade().resize(400, 400).into(imageView);
			} else if (mobile.equals(8)) {
				Picasso.with(context).load(R.drawable.z8).noFade().resize(400, 400).into(imageView);	
			} else if (mobile.equals(9)) {
				Picasso.with(context).load(R.drawable.z9).noFade().resize(400, 400).into(imageView);		
			} else {
				Picasso.with(context).load(R.drawable.z10).noFade().resize(400, 400).into(imageView);
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
