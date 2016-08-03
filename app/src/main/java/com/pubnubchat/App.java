package com.pubnubchat;

import android.app.Application;
import android.content.SharedPreferences;

import com.pubnubchat.manager.PubnubManager;
import com.pubnubchat.manager.SharedPreferenceManager;

/**
 * Created by Hari on 8/3/16.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferenceManager.getInstance().init(this);
        PubnubManager.getInstance().init();
    }
}
