package com.hzg.txq;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import com.hzg.txq.databinding.ItemProjectlistBinding;

import java.util.List;

/**
 * Created by hzg on 2017/6/6.
 */

public class ProjectListAdpter extends QuickRecyclerAdapter<ProjectListBean.ProjectInfo, QuickRecyclerAdapter.VH> {
    MyApp mApp;
    onClickListener mListener;
    public ProjectListAdpter(List<ProjectListBean.ProjectInfo> mDatas, Context context,onClickListener listener) {
        super(mDatas);
        mApp=Utils.getMyapp(context);
        this.mListener=listener;
    }

    public void setOnClicListener(onClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    protected void convert(VH holder, final int position, ProjectListBean.ProjectInfo bean) {
        final ItemProjectlistBinding mBinding = (ItemProjectlistBinding) holder.mBinding;
        mBinding.projectNameType.setText(bean.getProjectName() + bean.getProjectType());
        mBinding.projectIntroduceBrief.setText(bean.getProjectIntroduceBrief());
        mApp.bindImage(MyConfig.URL_Image + bean.getProjectIcon(), mBinding.projectIcon);
        mBinding.projectState.setText(bean.getProjectState());
        SpannableStringBuilder ssb1=new SpannableStringBuilder("融资金额：\n");
        SpannableStringBuilder ssb = new SpannableStringBuilder(bean.getFinanceAmount()+"");
        ssb.setSpan(new ForegroundColorSpan(Color.BLUE), 0, ssb.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mBinding.financeAmount.setText(ssb1.append(ssb).append("万"));

        mBinding.intentionAmount.setText("意向金额：\n" + bean.getIntentionAmount());

        mBinding.transfereQuity.setText("出让股权：\n" + bean.getTransfereQuity());

        mBinding.followNum.setText(bean.getFollowNum()+"关注");

        mBinding.readNum.setText(bean.getReadNum()+"浏览");

        mBinding.projectTeamIntroduce.setText("团队："+bean.getProjectTeamIntroduce());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (mListener!=null)
               {
                   mListener.onClick(position);
               }
            }
        });
    }

    @Override
    protected VH getViewHodler(View view, int type) {
        return new VH(view);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_projectlist;
    }
    public  interface  onClickListener{
       void onClick(int position);
    }
}
