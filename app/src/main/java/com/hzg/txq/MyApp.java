package com.hzg.txq;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by hzg on 2017/6/6.
 */

public class MyApp extends Application {
    RequestQueue mQuue;

    ImageLoader mImageLoader;
    SharedPreferences mSharePreference;

    @Override
    public void onCreate() {
        super.onCreate();
        mQuue = Volley.newRequestQueue(this);
        mSharePreference = getSharedPreferences(MyConfig.AppName, MODE_PRIVATE);
        mImageLoader=new ImageLoader(mQuue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });


    }

    public RequestQueue getRequestQueue() {
        return mQuue;
    }

    public SharedPreferences getSharePre() {
        return mSharePreference;
    }

    public void putStingInSharePre(String key, String values) {
        mSharePreference.edit().putString(key, values).apply();
    }
}
