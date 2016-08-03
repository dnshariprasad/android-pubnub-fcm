package com.pubnubchat.manager;

import android.content.Context;
import android.util.Log;

import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;
import com.pubnubchat.service.PubNubService;
import com.pubnubchat.util.Constant;

/**
 * Created by Hari on 8/3/16.
 */
public class PubnubManager {
    private static final String TAG = "PubnubManager";
    //START - SINGLETON
    private static PubnubManager ourInstance = new PubnubManager();

    public static PubnubManager getInstance() {
        return ourInstance;
    }

    private PubnubManager() {
    }

    //END - SINGLETON
    private Pubnub pubnub;

    public void init() {
        pubnub = new Pubnub(Constant.pubnub.PUBLISH_KEY, Constant.pubnub.SUBSCRIBE_KEY);
    }

    public void subscribe(final Context context,String channel) {

        Callback callback = new Callback() {
            @Override
            public void connectCallback(String channel, Object message) {
                Log.d(TAG, "connectCallback() called with: " + "channel = [" + channel + "], message = [" + message + "]");
            }

            @Override
            public void disconnectCallback(String channel, Object message) {
                Log.i(TAG, "SUBSCRIBE : DISCONNECT on channel:" + channel + " : " + message.getClass() + " : " + message.toString());
            }

            public void reconnectCallback(String channel, Object message) {
                Log.i(TAG, "SUBSCRIBE : RECONNECT on channel:" + channel + " : " + message.getClass() + " : " + message.toString());
            }

            @Override
            public void successCallback(String channel, Object message) {
                Log.d(TAG, "SUBSCRIBE : " + channel + " : " + message.getClass() + " : " + message.toString());
                PubNubService.startReceiveMessage(context, message.toString());
            }

            @Override
            public void errorCallback(String channel, PubnubError error) {
                Log.e(TAG, "SUBSCRIBE : ERROR on channel " + channel + " : " + error.toString());
            }
        };
        try {
            pubnub.subscribe(channel, callback);
        } catch (PubnubException e) {
            Log.e(TAG, e.toString());
        }
    }

    public void unsubscribe(String channel) {
        pubnub.unsubscribe(channel);
    }

    public void publish(String channel, String message) {
        Callback callback = new Callback() {
            public void successCallback(String channel, Object response) {
                System.out.println(response.toString());
            }

            public void errorCallback(String channel, PubnubError error) {
                System.out.println(error.toString());
            }
        };
        pubnub.publish(channel, message, callback);
    }
}


