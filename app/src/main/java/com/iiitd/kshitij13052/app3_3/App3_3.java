package com.iiitd.kshitij13052.app3_3;

import android.app.Application;
import android.content.Context;

/**
 * Created by Dr ASK on 10/2/2016.
 */
public class App3_3 extends Application{

    private static Context mContext;

    /*
    public static App3 getInstance() {
        return instance;
    }
    */

    public static Context getContext() {
        //  return instance.getApplicationContext();
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //  instance = this;
        mContext = getApplicationContext();
    }
}

