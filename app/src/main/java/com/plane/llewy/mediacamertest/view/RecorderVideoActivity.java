package com.plane.llewy.mediacamertest.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import com.plane.llewy.mediacamertest.R;
import com.plane.llewy.mediacamertest.presenter.RecorderVideoPresenter;
import com.plane.llewy.mediacamertest.util.UtilTest;

public class RecorderVideoActivity extends Activity implements View.OnClickListener {

    private RecorderVideoPresenter mRvideoPresenter;
    private Button mRecorderVideo, mStopRecorder;
    private TextureView mTextureView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder_video);

        mRecorderVideo = findViewById(R.id.btn_recorder);
        mStopRecorder = findViewById(R.id.btn_stop_recorder);
        mTextureView = findViewById(R.id.ttv_contain);
        UtilTest.updateButtonState(mStopRecorder, false);
        mRecorderVideo.setOnClickListener(this);
        mStopRecorder.setOnClickListener(this);

        mRvideoPresenter = new RecorderVideoPresenter(this, mTextureView);
    }

    @Override
    public void onClick(View v) {
        boolean isRecordering;
        switch (v.getId()){
            case R.id.btn_recorder:
                isRecordering = mRvideoPresenter.recorderVideo();
                UtilTest.updateButtonState(mRecorderVideo, !isRecordering);
                UtilTest.updateButtonState(mStopRecorder, isRecordering);
                break;
            case R.id.btn_stop_recorder:
                isRecordering = mRvideoPresenter.stopRecorderingVideo();
                UtilTest.updateButtonState(mRecorderVideo, !isRecordering);
                UtilTest.updateButtonState(mStopRecorder, isRecordering);
                break;
            default:
                break;
        }
    }
}
