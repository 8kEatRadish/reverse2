package com.example.reverse;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (!LeakCanary.isInAnalyzerProcess(this)){
            LeakCanary.install(this);
            return;
        }
    }
    public static RefWatcher getRefWatcher(){
        return LeakCanary.installedRefWatcher();
    }
    private void installLeakCanary(){
        RefWatcher refWatcher = LeakCanary.refWatcher(this)
                .watchActivities(false)
                .buildAndInstall();
    }
}
