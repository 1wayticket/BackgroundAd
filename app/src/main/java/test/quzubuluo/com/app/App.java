package test.quzubuluo.com.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/8/14.
 */

public class App extends Application {private static App app;

    public static Context getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

}
