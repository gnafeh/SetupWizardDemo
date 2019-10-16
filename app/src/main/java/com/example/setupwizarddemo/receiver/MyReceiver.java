package com.example.setupwizarddemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    private String TAG = "LocaleReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.example.mymessage")) {
            Log.d(TAG, "My message received!");
        }
    }
}
