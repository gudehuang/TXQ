package com.hzg.txq.utils;

import android.content.Context;

import com.hzg.txq.app.MyApp;

/**
 * Created by hzg on 2017/6/6.
 */

public class Utils {
   public static MyApp getMyapp(Context context)
   {
      return (MyApp) context.getApplicationContext();
   }
}
