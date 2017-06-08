package com.hzg.txq;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

/**
 * Created by hzg on 2017/6/7.
 */

public class ProjectDetailPresenter {
    ProjectDetailBean.Project mProject;

    public ProjectDetailPresenter(ProjectDetailBean.Project project) {
        this.mProject = project;
    }

    public  CharSequence getDate()
    {
        SpannableStringBuilder ssb=new SpannableStringBuilder("上线时间：");
        ForegroundColorSpan span=new ForegroundColorSpan(Color.rgb(0x00,0xbc,0xd4));
        ssb.setSpan(span,ssb.length(),ssb.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ssb.append(StringUtils.dateToString(mProject.getCreateTime()));
        return  ssb;
    }
    public  String getFinanceAmount()
    {
        return  "融资目标："+mProject.getFinanceAmount()+"万";
    }

      public  String getIntentionAmount()
    {
        return  "意向金额："+mProject.getIntentionAmount()+"万";
    }
     public  String getProvinceCityName()
     {
         return  mProject.getProvinceName()+""+mProject.getCityName();
     }
    public  String getTransferEquity()
    {
        return mProject.getTransferEquity()+"%\n"+"出让股权";
    }
    public  String getFollowNum()
    {
        return mProject.getFollowNum()+"\n"+"关注人数";
    }  public  String getInvestNum()
    {
        return mProject.getInvestNum()+"\n"+"投资人数";
    }
    public  String getInterviewNum()
    {
        return mProject.getInterviewNum()+"\n"+"约谈人数";
    }
    public  String getProvinceAndCity()
    {
        return mProject.getProvinceName()+" "+mProject.getCityName();
    }
    public  int  getProgess()
    {
        return (int) (mProject.getIntentionAmount()*1f/mProject.getFinanceAmount());
    }

}
