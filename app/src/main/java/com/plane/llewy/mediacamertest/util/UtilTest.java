package com.plane.llewy.mediacamertest.util;

import android.os.Environment;
import android.widget.Button;

import java.io.File;
import java.util.Calendar;

public class UtilTest {
    public static final int RECORDER_VIDEO_WIGTH = 640;
    public static final int RECORDER_VIDEO_HEIGHT = 480;
    public static final int RECORDER_VIDEO_FRAME_RATE = 30;
    public static final int RECORDER_VIDEO_ENCODING_BIT_RATE = 3 * 1024 * 1024;
    public static final String FILE_PATH = "/recordtest/";

    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();
            return sdDir.toString();
        }

        return null;
    }

    public static String getDate() {
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH);
        int day = ca.get(Calendar.DATE);
        int minute = ca.get(Calendar.MINUTE);
        int hour = ca.get(Calendar.HOUR);
        int second = ca.get(Calendar.SECOND);

        String date = "" + year + (month) + day + hour + minute + second;

        return date;
    }

    public static void updateButtonState(Button button, boolean isClickable) {
        if(button != null) {
            button.setClickable(isClickable);
            button.setAlpha(isClickable ? 1f : 0.5f);
        }
    }
}
