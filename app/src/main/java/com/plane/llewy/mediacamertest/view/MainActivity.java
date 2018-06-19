package com.plane.llewy.mediacamertest.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.plane.llewy.mediacamertest.R;
import com.plane.llewy.mediacamertest.view.RecorderVideoActivity;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button mOpenRecorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOpenRecorder = findViewById(R.id.btn_open_recorder);
        mOpenRecorder.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_open_recorder:
                intent.setClass(this,RecorderVideoActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
