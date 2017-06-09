package com.hzg.txq;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.google.gson.Gson;
import com.hzg.txq.adapter.ProjectListAdpter;
import com.hzg.txq.adapter.QuickRecyclerAdapter;
import com.hzg.txq.app.MyApp;
import com.hzg.txq.app.MyConfig;
import com.hzg.txq.bean.ProjectListBean;
import com.hzg.txq.databinding.ActivityProjectlistBinding;
import com.hzg.txq.request.FastJsonRequest;
import com.hzg.txq.utils.Utils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.Method.GET;
import static com.hzg.txq.app.MyConfig.PROJECT_UUID;

/**
 * Created by hzg on 2017/6/6.
 */

public class ProjectListAct extends AppCompatActivity implements ProjectListAdpter.onClickListener, MyApp.ClientLocationListenr {
    private ActivityProjectlistBinding mDataBind;
  ProjectListAdpter mAdapter;
    List<ProjectListBean.ProjectInfo> mData;
    private int currentpage=1;
    private RequestQueue mQueue;
    private Dialog dialog;
   SharedPreferences mSharePre;
   private  String address="";
   Handler handler=new Handler()
   {
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           mDataBind.address.setText(address);
       }
   };
    private boolean isDownRefresh;
    private  LinearLayoutManager mLinearLayoutManager;
    private int totalPages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBind=DataBindingUtil.setContentView(this,R.layout.activity_projectlist);
        setSupportActionBar(mDataBind.toolbar);
        mDataBind.title.setText(getTitle());
        mData=new ArrayList<>();
        mAdapter=new ProjectListAdpter(mData,ProjectListAct.this,ProjectListAct.this);
        mAdapter.setLoadingMore(true);
        mDataBind.recyclerView.setAdapter(mAdapter);
        mLinearLayoutManager=(new LinearLayoutManager(this));
        mDataBind.recyclerView.setLayoutManager(mLinearLayoutManager);
        //上拉加载
        mDataBind.recyclerView.addOnScrollListener(new EndLessOnScrollListener(mLinearLayoutManager) {
            @Override
            void onLoadMore(int currentPage1) {

                if (currentPage1<=totalPages)
                {
                    currentpage=currentPage1;
                    mAdapter.setLoadingMore(true);
                    getData();
                }
               else {
                    Snackbar.make(mDataBind.recyclerView,"没有更多的数据了",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        mDataBind.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        mQueue= Utils.getMyapp(this).getRequestQueue();
        mSharePre=Utils.getMyapp(this).getSharePre();
        //下拉加载
        mDataBind.swipeResfreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isDownRefresh=true;
                getData();
            }
        });
        //百度定位,获取位置信息
      LocationClient client= Utils.getMyapp(this).getLocationClient(this);
             client .registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                System.out.println(bdLocation.getAddress().address);
               address=bdLocation.getAddress().address;
               handler.sendEmptyMessage(0x1);
            }

                 @Override
                 public void onConnectHotSpotMessage(String s, int i) {

                 }


             });
       client.start();
       initData();
    }

    private void getAdress(BDLocation location) {
        OnGetGeoCoderResultListener geoCoderListener = new OnGetGeoCoderResultListener() {
            public void onGetGeoCodeResult(GeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    //没有检索到结果
                }
                //获取地理编码结果
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    //
                    System.out.println("没有找到检索结果");
                }
                System.out.println(result.getAddress());
            }
        };
        GeoCoder mSerch=GeoCoder.newInstance();
        mSerch.setOnGetGeoCodeResultListener(geoCoderListener);
        mSerch.reverseGeoCode(new ReverseGeoCodeOption().location(new LatLng(location.getLatitude(),location.getLongitude())));
    }

    private  void initData()
    {

           getData();
    }

    private void getData() {
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
                      totalPages=projectListBean.getProject().getPageable().getTotalPages();
                      //首次加载
                      if (currentpage==1)
                      {
                          //下拉刷新,清除所有数据
                          if (isDownRefresh)
                          {
                               mData.clear();
                               isDownRefresh=false;
                               Snackbar.make(mDataBind.coordinatorLayout,"数据已更新",Snackbar.LENGTH_SHORT).show();

                          }

                          mData.addAll(projectListBean.getProject().getProjectList());
                          mAdapter.notifyDataSetChanged();
                      }
                      else {
                          mData.addAll(projectListBean.getProject().getProjectList());
                          mAdapter.notifyDataSetChanged();
                      }

                  }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    hideDialog();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
            }
        });
        mQueue.add(request);
    }

    private void showDialog() {
        if (!dialog.isShowing())
        dialog.show();
    }

    private void hideDialog() {

        if (mDataBind.swipeResfreshLayout.isRefreshing())
            mDataBind.swipeResfreshLayout.setRefreshing(false);
        mAdapter.setLoadingMore(false);
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

    @Override
    public void handleLocation(BDLocation location) {

        StringBuffer sb = new StringBuffer(256);

        sb.append("time : ");
        sb.append(location.getTime());    //获取定位时间

        sb.append("\nerror code : ");
        sb.append(location.getLocType());    //获取类型类型

        sb.append("\nlatitude : ");
        sb.append(location.getLatitude());    //获取纬度信息

        sb.append("\nlontitude : ");
        sb.append(location.getLongitude());    //获取经度信息

        sb.append("\nradius : ");
        sb.append(location.getRadius());    //获取定位精准度

        if (location.getLocType()==BDLocation.TypeNetWorkLocation)
        {
            sb.append("\n地址信息：");
            sb.append(location.getAddress().address);
            sb.append("\n运营商信息");
            sb.append(location.getOperators());
            address=location.getAddress().address;
            handler.sendEmptyMessage(0x1);
        }
        sb.append(location.getLocationDescribe());
        //getAdress(location);
        Log.i("BDLocation",sb.toString());
    }




    @Override
    public void getAddress(ReverseGeoCodeResult result) {

    }
}
