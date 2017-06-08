package com.hzg.txq;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

/**
 * Created by hzg on 2017/6/6.
 */

public class MyApp extends Application {
    RequestQueue mQuue;
    ImageLoader mImageLoader;
    SharedPreferences mSharePreference;
    LocationClient mLocationClient;
    MyBDLocationListener mBDLocationListener;
    public  interface  ClientLocationListenr
    {
        void handleLocation(BDLocation location);
        void getAddress(ReverseGeoCodeResult result);
    }
    @Override
    public void onCreate() {
        super.onCreate();
     //   SDKInitializer.initialize(getApplicationContext());
        mQuue = Volley.newRequestQueue(this);
        mSharePreference = getSharedPreferences(MyConfig.AppName, MODE_PRIVATE);
        mImageLoader=new ImageLoader(mQuue, new BitmapCache());
        mLocationClient=new LocationClient(this);
        initLocationOption();
    }
    private  void initLocationOption()
    {
        LocationClientOption option=new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        option.setCoorType("bd09ll");
        //是否获取地址信息
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
        option.setIsNeedLocationDescribe(true);
        mBDLocationListener=new MyBDLocationListener();
        mLocationClient.registerLocationListener(mBDLocationListener);
    }

    public void getLocation(ClientLocationListenr listenr)
    {
        mBDLocationListener.setListenr(listenr);
        mLocationClient.start();


    }
   public  void  bindImage(String url, ImageView imageView)
   {
       ImageLoader.ImageListener listener=mImageLoader.getImageListener(imageView,R.drawable.loading,R.drawable.loading_error);
       mImageLoader.get(url,listener);
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
    class  MyBDLocationListener implements  BDLocationListener
    {
        ClientLocationListenr listenr;

        public MyBDLocationListener() {

        }

        public MyBDLocationListener(ClientLocationListenr listenr) {
            this();
            this.listenr = listenr;
        }

        public void setListenr(ClientLocationListenr listenr) {
            this.listenr = listenr;
        }

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            if (listenr!=null)
                listenr.handleLocation(bdLocation);
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }
}
