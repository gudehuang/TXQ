package com.hzg.txq;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by hzg on 2017/6/7.
 */

public class BitmapCache implements ImageLoader.ImageCache {
    int size=10*1024*1024;
    LruCache<String ,Bitmap> mCache=new LruCache<String, Bitmap>(size){
        @Override
        protected int sizeOf(String key, Bitmap value) {

            return  value.getRowBytes()*value.getHeight();
        }
    };
    @Override
    public Bitmap getBitmap(String url) {
        return  mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url,bitmap);
    }
}

