package com.hzg.txq;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.hzg.txq.databinding.ActivityProjectlistBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.android.volley.Request.Method.GET;
import static com.hzg.txq.MyConfig.PROJECT_UUID;

/**
 * Created by hzg on 2017/6/6.
 */

public class ProjectListAct extends AppCompatActivity implements ProjectListAdpter.onClickListener {
    private ActivityProjectlistBinding mDataBind;
    RecyclerView.Adapter mAdapter;
    List<ProjectListBean.ProjectInfo> mData;
    private int currentpage=1;
    private RequestQueue mQueue;
    private Dialog dialog;
   SharedPreferences mSharePre;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBind=DataBindingUtil.setContentView(this,R.layout.activity_projectlist);
        mQueue=Utils.getMyapp(this).getRequestQueue();
        mSharePre=Utils.getMyapp(this).getSharePre();

       initData();
    }

    private  void initData()
    {
       if (dialog==null)
           dialog= ProgressDialog.show(this,"","",false,false);
        getData();
    }

    private void getData() {
        dialog.show();
        String url= MyConfig.URL_GetVCProjectList_New+"?access_token="+mSharePre.getString(MyConfig.ACCESS_TOKEN,"")
			             +"&pageSize="+"10"
                         +"&pageIndex="+currentpage;
        final FastJsonRequest request = new FastJsonRequest(GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
                try {
                  ProjectListBean projectListBean=new Gson().fromJson(response.toString(),ProjectListBean.class);
                  if(projectListBean.isSuccess())
                  {
                      mData=projectListBean.getProject().getProjectList();
                      mAdapter=new ProjectListAdpter(mData,ProjectListAct.this,ProjectListAct.this);

                      mDataBind.recyclerView.setAdapter(mAdapter);
                      mDataBind.recyclerView.setLayoutManager(new LinearLayoutManager(ProjectListAct.this));
                  }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                  dialog.hide();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                dialog.hide();
            }
        });
        mQueue.add(request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog!=null) {
            dialog.dismiss();
            dialog=null;
        }
    }

    @Override
    public void onClick(int position) {
        Intent intent=new Intent(this,ProjectDetailAct.class);
        intent.putExtra(PROJECT_UUID,mData.get(position).getProjectUuid());
        startActivity(intent);
    }
}
