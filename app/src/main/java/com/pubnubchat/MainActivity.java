package com.pubnubchat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_subscribe).setOnClickListener(this);
        findViewById(R.id.btn_unsubscribe).setOnClickListener(this);
        findViewById(R.id.btn_send).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_subscribe:
                PubnubManager.getInstance().subscribe(Constant.pubnub.CHANNEL);
                break;
            case R.id.btn_unsubscribe:
                PubnubManager.getInstance().unsubscribe(Constant.pubnub.CHANNEL);
                break;
            case R.id.btn_send:
                PubnubManager.getInstance().publish(Constant.pubnub.CHANNEL, "Hi123");
                break;
        }
    }
}
