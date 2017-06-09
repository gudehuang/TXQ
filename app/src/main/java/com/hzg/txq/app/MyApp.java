package com.hzg.txq.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.hzg.txq.BitmapCache;
import com.hzg.txq.R;

/**
 * Created by hzg on 2017/6/6.
 */

public class MyApp extends Application {
    RequestQueue mQuue;
    ImageLoader mImageLoader;
    SharedPreferences mSharePreference;
    LocationClient mLocationClient;
    MyBDLocationListener mBDLocationListener;

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
         //位置语义化描述
        option.setIsNeedLocationDescribe(true);
        mLocationClient.setLocOption(option);
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
       ImageLoader.ImageListener listener=mImageLoader.getImageListener(imageView, R.drawable.loading,R.drawable.loading_error);

       mImageLoader.get(url,listener);

   }

    public LocationClient getLocationClient() {
        return mLocationClient;
    }
    public LocationClient getLocationClient(Context context) {
        LocationClientOption option=new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        option.setCoorType("bd09ll");
        //是否获取地址信息
        option.setIsNeedAddress(true);
        //位置语义化描述
        option.setIsNeedLocationDescribe(true);
        LocationClient client=new LocationClient(this,option);
        return client;
    }

    public RequestQueue getRequestQueue() {
        return mQuue;
    }

    public SharedPreferences getSharePre() {
        return mSharePreference;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
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
    public  interface  ClientLocationListenr
    {
        void handleLocation(BDLocation location);
        void getAddress(ReverseGeoCodeResult result);
    }
}
