package com.pubnubchat;

import android.app.Application;

/**
 * Created by Hari on 8/3/16.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PubnubManager.getInstance().init();
    }
}
