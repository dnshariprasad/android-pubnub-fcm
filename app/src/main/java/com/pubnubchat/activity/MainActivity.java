package com.pubnubchat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pubnubchat.R;
import com.pubnubchat.manager.SharedPreferenceManager;
import com.pubnubchat.util.Constant;
import com.pubnubchat.util.Util;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Util.isNotNullAndNotEmpty(SharedPreferenceManager.getInstance().getString(Constant.preference.NAME, ""))) {
            ChatActivity.start(this);
            finish();
        }
        setContentView(R.layout.activity_main);
        et_text = (EditText) findViewById(R.id.et_text);
        findViewById(R.id.btn_continue).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_continue:
                String name = et_text.getText().toString();
                if (!Util.isNotNullAndNotEmpty(name)) {
                    Toast.makeText(MainActivity.this, R.string.toast_name_empty, Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferenceManager.getInstance().putString(Constant.preference.NAME, name);
                ChatActivity.start(this);
                break;
        }
    }
}
