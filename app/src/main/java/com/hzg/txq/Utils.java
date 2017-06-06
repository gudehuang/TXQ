package com.hzg.txq;

import android.content.Context;

/**
 * Created by hzg on 2017/6/6.
 */

public class Utils {
   public static  MyApp getMyapp(Context context)
   {
      return (MyApp) context.getApplicationContext();
   }
}
