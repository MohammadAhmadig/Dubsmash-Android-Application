package com.example.test_video;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
 
public class SharedPrefence {
 
    public static final String PREFS_NAME = "AOP_PREFSS";
    public static final String PREFS_KEY = "AOP_PREFSS_String";
    
    public SharedPrefence() {
        super();
    }
 
    public void save(Context context, String text) {
        SharedPreferences settings;
        Editor editor;
        
        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
 
        editor.putString(PREFS_KEY, text); //3
 
        editor.commit(); //4
    }
 
    public String getValue(Context context) {
        SharedPreferences settings;
        String text;
 
        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        text = settings.getString(PREFS_KEY, null);
        return text;
    }
    
    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        Editor editor;
 
        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
 
        editor.clear();
        editor.commit();
    }
 
    public void removeValue(Context context) {
        SharedPreferences settings;
        Editor editor;
 
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
 
        editor.remove(PREFS_KEY);
        editor.commit();
    }    
}
