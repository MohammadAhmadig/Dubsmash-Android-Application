package com.example.test_video;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class Share extends Activity {
	
	int counter;
	TextView tView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasbih);
        
        tView = (TextView) findViewById(R.id.textView3);
    }
}
