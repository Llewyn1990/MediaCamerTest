package com.plane.llewy.mediacamertest.presenter;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;

import com.plane.llewy.mediacamertest.util.PlayVideoThread;
import com.plane.llewy.mediacamertest.util.UtilTest;

import java.io.File;
import java.io.IOException;

public class RecorderVideoPresenter {

    private static final String TAG = "RecorderVideoPresenter";
    private Context mContext;
    private TextureView mTextureView;
    private MediaRecorder mMediaRecorder;
    private Camera mCamera;
    private String mFilePath;
    private boolean mIsRecording = false;
    public RecorderVideoPresenter(Context context, TextureView textureView) {
        mContext = context;
        mTextureView = textureView;
    }

    public boolean recorderVideo() {
        if(mIsRecording) {
            stopRecorderingVideo();
        }
        if(mMediaRecorder == null) {
            mMediaRecorder = new MediaRecorder();
        }
        if(mCamera == null) {
            mCamera = Camera.open();
        }

        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPreviewSize(mTextureView.getWidth(), mTextureView.getHeight());
        mCamera.setParameters(parameters);
        try {
            mCamera.setPreviewTexture(mTextureView.getSurfaceTexture());
        } catch (IOException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
        mCamera.unlock();
        mMediaRecorder.setCamera(mCamera);
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
        mMediaRecorder.setVideoSize(UtilTest.RECORDER_VIDEO_WIGTH, UtilTest.RECORDER_VIDEO_HEIGHT);
        mMediaRecorder.setVideoFrameRate(UtilTest.RECORDER_VIDEO_FRAME_RATE);
        mMediaRecorder.setVideoEncodingBitRate(UtilTest.RECORDER_VIDEO_ENCODING_BIT_RATE);
        mFilePath = UtilTest.getSDPath();
        if(mFilePath != null) {
            File dir = new File(mFilePath + UtilTest.FILE_PATH);
            if (!dir.exists()) {
                dir.mkdir();
            }
            mFilePath = dir + "/" + UtilTest.getDate() + ".mp4";
            mMediaRecorder.setOutputFile(mFilePath);
            try {
                mMediaRecorder.prepare();
            } catch (IOException e) {
                Log.e(TAG, Log.getStackTraceString(e));
            }
            mMediaRecorder.start();
            mIsRecording = true;
        }
        return mIsRecording;
    }

    public boolean stopRecorderingVideo(){
        if(mMediaRecorder != null) {
            try {
                mMediaRecorder.setOnErrorListener(null);
                mMediaRecorder.setOnInfoListener(null);
                mMediaRecorder.setPreviewDisplay(null);
                mMediaRecorder.stop();
            } catch (IllegalStateException e) {
                Log.e(TAG, Log.getStackTraceString(e));
            } catch (RuntimeException e) {
                Log.e(TAG, Log.getStackTraceString(e));
            } catch (Exception e) {
                Log.e(TAG, Log.getStackTraceString(e));
            }
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
        }
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
        mIsRecording = false;
        return mIsRecording;
    }

}
