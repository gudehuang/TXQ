package com.hzg.txq;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.hzg.txq.databinding.ActivityProjectdetailsBinding;
import com.hzg.txq.test.ProjectDetailsTest;

import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by hzg on 2017/6/7.
 */

public class ProjectDetailAct extends BaseAct {

    private String Uuid;
    private ProjectDetailBean.Project mProject;
    private ActivityProjectdetailsBinding mDataBind;
    private Fragment[] fragments = new Fragment[2];
    private String[] titils=new String[]{"基本信息","投资信息"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBind = DataBindingUtil.setContentView(this, R.layout.activity_projectdetails);

        Uuid = getIntent().getStringExtra(MyConfig.PROJECT_UUID);
        //模拟数据
        bindData(new ProjectDetailsTest().jsonToProject());
        dialog.hide();
        //从网络获取数据
        //initData();
    }

    private void initData() {
        String url = MyConfig.URL_GetProjectDetail + "?access_token=" + mSharePre.getString(MyConfig.ACCESS_TOKEN, "")
                + "&projectUuid=" + Uuid;
        System.out.println(Uuid);
        System.out.println(mSharePre.getString(MyConfig.ACCESS_TOKEN, ""));
        System.out.println(url);
        final FastJsonRequest request = new FastJsonRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ProjectDetailBean detailBean = new Gson().fromJson(response.toString(), ProjectDetailBean.class);
                bindData(detailBean.getProject());
                dialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.hide();
            }
        });
        mQueue.add(request);
    }

    private void bindData(ProjectDetailBean.Project project) {
        mDataBind.setProject(project);
        ProjectDetailPresenter presenter=  new ProjectDetailPresenter(project);
        mDataBind.setPresenter(presenter);
        mDataBind.createTime.setText(presenter.getDate());
        mDataBind.tablayout.setupWithViewPager(mDataBind.viewpager);
        mDataBind.viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                Fragment fragment = fragments[i];
                if (fragment == null) {
                    switch (i) {
                        case 0:
                            fragment = new BaseInfoFragment();
                            fragments[i] = fragment;
                            break;
                        case 1:
                            fragment = new MoreInfoFragment();
                            fragments[i] = fragment;
                            break;
                        default:
                            fragment = new BaseInfoFragment();
                            fragments[i] = fragment;
                    }

                }
                return fragment;
            }

            @Override
            public int getCount() {
                return fragments.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titils[position];
            }
        });
    }


}
