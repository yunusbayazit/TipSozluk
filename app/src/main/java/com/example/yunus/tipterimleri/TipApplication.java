package com.example.yunus.tipterimleri;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

/**
 * Created by yunus on 6/21/2016.
 */
public class TipApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
