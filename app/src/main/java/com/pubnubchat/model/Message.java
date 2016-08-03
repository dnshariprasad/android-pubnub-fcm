package com.pubnubchat.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Hari on 8/4/16.
 */
public class Message {
    private static final String TAG = "Message";

    @Expose
    @SerializedName("sender_name")
    private String senderName;
    @Expose
    @SerializedName("devise_token")
    private String deviseToken;

    @Expose
    @SerializedName("text")
    private String text;

    public Message(String deviseToken, String senderName, String text) {
        this.deviseToken = deviseToken;
        this.senderName = senderName;
        this.text = text;
    }

    public String getDeviseToken() {
        return deviseToken;
    }

    public void setDeviseToken(String deviseToken) {
        this.deviseToken = deviseToken;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public static Message fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, Message.class);
    }

    public JSONObject toJson() {
        String jsonRepresentation = new Gson().toJson(this, Message.class);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonRepresentation);
        } catch (JSONException e) {
            Log.e(TAG, "Error converting to JSON: " + e.getMessage());
        }
        return jsonObject;
    }
}
