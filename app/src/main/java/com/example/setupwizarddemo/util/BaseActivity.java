package com.example.setupwizarddemo.util;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppManager.addActivity(this);
    }
}
