package com.example.setupwizarddemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.setupwizarddemo.util.AppManager;
import com.example.setupwizarddemo.util.BaseActivity;

public class LoginActivity extends BaseActivity {
    private String TAG = "LoginActivity";
    private EditText editTextAccount,editTextPassword;
    private Button btnLogin, btnFinish;
    private String VAL_ACCOUNT, VAL_PASSWORD;
    private AppManager appManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        editTextAccount = (EditText) findViewById(R.id.edittext_account);
        editTextPassword = (EditText) findViewById(R.id.edittext_password);
        btnLogin = (Button) findViewById(R.id.button_login);
        btnFinish = (Button) findViewById(R.id.button_finish);

        getInputInfo();
        getLoginInfo();
        finishApp();

    }

    private void getLoginInfo() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideInput();
                //Toast.makeText(v.getContext(),"acc= " + VAL_ACCOUNT + ", pw= " + VAL_PASSWORD,Toast.LENGTH_SHORT).show();
                if(VAL_ACCOUNT.equals("1234") && VAL_PASSWORD.equals("5678")){
                    btnFinish.setVisibility(View.VISIBLE);
                }else{
                    btnFinish.setVisibility(View.GONE);

                }
            }
        });
    }

    private void getInputInfo() {
        editTextAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG,"beforeTextChanged >>> s = " + s + ", start = " + start + ", count = " + count + ", after= " + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Log.d(TAG,"onTextChanged >>> s = " + s + ", start = " + start + ", before = " + before + ", count= " + count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG,"afterTextChanged >>> s = " + s );
                VAL_ACCOUNT = s.toString();

            }
        });
        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG,"afterTextChanged >>> s = " + s );
                VAL_PASSWORD= s.toString();
            }
        });
    }
    /**
     * 隐藏键盘
     */
    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }

    }

    private void finishApp(){
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appManager.finishAllActivity();
                //appManager.selfDisable(v.getContext());
                selfDisable(v.getContext());
                Settings.Global.putInt(getContentResolver(), Settings.Global.DEVICE_PROVISIONED, 1);
            }
        });
    }

    public void selfDisable(Context context){
        final String USER_SETUP_COMPLETE = "user_setup_complete";
        // Add a persistent setting to allow other apps to know the device has been provisioned.
        Settings.Global.putInt(getContentResolver(), Settings.Global.DEVICE_PROVISIONED, 1);
        Settings.Secure.putInt(getContentResolver(), USER_SETUP_COMPLETE, 1);

        // remove this activity from the package manager.
        PackageManager pm = context.getPackageManager();
        ComponentName name = new ComponentName(context, MainActivity.class);
        pm.setComponentEnabledSetting(name, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

    }




}
