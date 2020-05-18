package org.evansroy.chronometerdemo;

import android.content.Context;
import android.content.SharedPreferences;

class SesseionManager {
    //Initialize Variables
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SesseionManager(Context context){
        //initialize share preferences
        sharedPreferences = context.getSharedPreferences("AppKey",0);
        //Initialize editor
        editor = sharedPreferences.edit();
        //Apply editor
        editor.apply();
    }

    public void setFlag(Boolean flag){
        //Put boolean value
        editor.putBoolean("KEY_FLAG",flag);
        //Commit editor
        editor.commit();
    }
    public boolean getFlag(){
        //Return boolean value
        return sharedPreferences.getBoolean("KEY_FLAG",false);//Default value is false
    }
    public void setCurrentTime(String currentTime){
        //Put String value
        editor.putString("KEY_TIME",currentTime);
        //commit editor
        editor.commit();

    }
    public String getCurrentTime(){
        //Return String Value
        return sharedPreferences.getString("KEY_TIME","");//Default value is empty
    }

}
