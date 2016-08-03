package com.pubnubchat.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.pubnubchat.R;
import com.pubnubchat.adapter.ChatAdapter;
import com.pubnubchat.manager.PubnubManager;
import com.pubnubchat.model.Message;
import com.pubnubchat.service.PubNubService;
import com.pubnubchat.util.Constant;
import com.pubnubchat.util.Util;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private class ChatReceiver extends BroadcastReceiver {
        // Prevents instantiation
        private ChatReceiver() {
        }

        // Called when the BroadcastReceiver gets an Intent it's registered to receive
        public void onReceive(Context context, Intent intent) {

            switch (intent.getAction()) {
                case PubNubService.ACTION_RECEIVE_MESSAGE:
                    list.add(Message.fromJson(intent.getStringExtra(PubNubService.EXTRA_MESSAGE)));
                    buildList();
                    if (list.size() > 1)
                        rv_chat.scrollToPosition(list.size() - 1);
                    break;
            }
        }
    }


    private ImageView iv_send;
    private EditText et_message;
    private RecyclerView rv_chat;
    private ChatAdapter chatAdapter;
    private List<Message> list;
    private String udid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_send:
                String message = et_message.getText().toString();
                if (!Util.isNotNullAndNotEmpty(message.trim())) {
                    Toast.makeText(MainActivity.this, R.string.toast_empty_message, Toast.LENGTH_SHORT).show();
                    return;
                }

                PubnubManager.getInstance().publish(
                        Constant.pubnub.CHANNEL,
                        new Message(udid, message).toJson().toString());

                et_message.setText("");
                break;
        }
    }

    private void init() {
        udid = Util.getUdid(this);

        //pull views
        iv_send = (ImageView) findViewById(R.id.iv_send);
        et_message = (EditText) findViewById(R.id.et_message);
        rv_chat = (RecyclerView) findViewById(R.id.rv_chat);
        //init listeners
        iv_send.setOnClickListener(this);

        ChatReceiver chatReceiver = new ChatReceiver();
        IntentFilter receiveMessageIntentFilter = new IntentFilter(PubNubService.ACTION_RECEIVE_MESSAGE);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(chatReceiver, receiveMessageIntentFilter);

        list = new ArrayList<>();
        //subscribe to pubnub
        PubnubManager.getInstance().subscribe(this, Constant.pubnub.CHANNEL);
    }

    private void buildList() {
        if (null == rv_chat.getAdapter()) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rv_chat.setLayoutManager(linearLayoutManager);
            chatAdapter = new ChatAdapter(this, list);
            rv_chat.setAdapter(chatAdapter);
        } else {
            chatAdapter.notifyList(list);
        }
    }

}
