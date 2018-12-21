package com.example.jiaguoqiang20181210;

import android.app.Application;
import android.content.Context;

public class EHApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
        context = this;
    }
    public static Context getContext() {
        return context;
    }

}
