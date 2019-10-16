package com.example.setupwizarddemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.setupwizarddemo.util.BaseActivity;

public class MainActivity extends BaseActivity {
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //      WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent = new Intent(MainActivity.this, WifiActivity.class);
                Log.d("hefang", "1015 click");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("hefang", ""+intent.getFlags());

    }


}
