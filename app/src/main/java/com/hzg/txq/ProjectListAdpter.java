package com.hzg.txq;

import android.view.View;

import com.hzg.txq.databinding.ItemProjectlistBinding;

import java.util.List;

/**
 * Created by hzg on 2017/6/6.
 */

public class ProjectListAdpter extends QuickRecyclerAdapter<ProjectListBean.ProjectInfo,QuickRecyclerAdapter.VH> {
    public ProjectListAdpter(List<ProjectListBean.ProjectInfo> mDatas) {
        super(mDatas);
    }

    @Override
    protected void convert(VH holder, int position, ProjectListBean.ProjectInfo bean) {
            ItemProjectlistBinding mBinding= (ItemProjectlistBinding) holder.mBinding;
            mBinding.projectNameType.setText(bean.getProjectName()+bean.getProjectType());
            mBinding.projectIntroduceBrief.setText(bean.getProjectIntroduceBrief());
    }

    @Override
    protected VH getViewHodler(View view, int type) {
        return  new VH(view);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_projectlist;
    }
}
