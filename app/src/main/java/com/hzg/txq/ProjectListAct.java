package com.hzg.txq;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.google.gson.Gson;
import com.hzg.txq.databinding.ActivityProjectlistBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import static com.android.volley.Request.Method.GET;
import static com.hzg.txq.MyConfig.PROJECT_UUID;

/**
 * Created by hzg on 2017/6/6.
 */

public class ProjectListAct extends AppCompatActivity implements ProjectListAdpter.onClickListener, MyApp.ClientLocationListenr {
    private ActivityProjectlistBinding mDataBind;
    RecyclerView.Adapter mAdapter;
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
           getSupportActionBar().setTitle(address);
       }
   };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataBind=DataBindingUtil.setContentView(this,R.layout.activity_projectlist);
        mQueue=Utils.getMyapp(this).getRequestQueue();
        mSharePre=Utils.getMyapp(this).getSharePre();
         Utils.getMyapp(this).getLocation(this);
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
