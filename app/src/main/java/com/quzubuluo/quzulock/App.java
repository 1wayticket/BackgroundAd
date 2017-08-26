package com.quzubuluo.quzulock;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/8/14.
 */

public class App extends Application {private static App app;

    public static Context getInstance() {
        return app;
    }
    public static Bitmap bitmap;

    public static Bitmap getBitmap() {
        return bitmap;
    }

    public static void setBitmap(Bitmap bitmap) {
        App.bitmap = bitmap;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

}
