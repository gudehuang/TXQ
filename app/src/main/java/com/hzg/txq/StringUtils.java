package com.hzg.txq;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hzg on 2017/6/7.
 */

public class StringUtils {
    public  static  SpannableStringBuilder getColorSSB(String text,int color)
    {

        SpannableStringBuilder ssb = new SpannableStringBuilder(text);
        ssb.setSpan(new ForegroundColorSpan(color), 0, text.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return  ssb;
    }

    public  static  String  dateToString(long date)
    {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=new Date(date);
        return  format.format(date1);

    }
}
