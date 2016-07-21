package jp.seesaa.sample.opencv;

import android.app.Application;

import org.opencv.android.OpenCVLoader;

public class MyApplication extends Application {

    static {
        OpenCVLoader.initDebug();
    }

}
