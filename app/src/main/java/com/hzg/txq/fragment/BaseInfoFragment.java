package com.hzg.txq.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzg.txq.R;
import com.hzg.txq.adapter.ProjectListAdpter;
import com.hzg.txq.adapter.QuickRecyclerAdapter;
import com.hzg.txq.bean.ProjectDetailBean;
import com.hzg.txq.databinding.FragmentProjectBaseinfoBinding;

import java.util.List;

/**
 * Created by hzg on 2017/6/7.
 */

public class BaseInfoFragment extends android.support.v4.app.Fragment {

    private ProjectDetailBean.Company company;
    private List<ProjectDetailBean.TeamList> teamDatas;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_project_baseinfo,container,false);
        FragmentProjectBaseinfoBinding mDatabind=DataBindingUtil.bind(view);
        mDatabind.projectIntroduce.setText(company.getProjectIntroduce());
        mDatabind.teamList.setAdapter(new QuickRecyclerAdapter(teamDatas) {
            @Override
            protected void convert(RecyclerView.ViewHolder holder, int position, Object bean) {

            }

            @Override
            protected RecyclerView.ViewHolder getViewHodler(View view, int type) {
                return null;
            }

            @Override
            protected int getLayoutId(int viewType) {
                return 0;
            }
        });
        return view;
    }
}
