package com.hzg.txq;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;

/**
 * Created by hzg on 2017/6/7.
 */

public class BaseAct extends AppCompatActivity {
    protected Dialog dialog;
    protected RequestQueue mQueue;
    protected SharedPreferences mSharePre;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        dialog= ProgressDialog.show(this,"","",false,false);
        mQueue=Utils.getMyapp(this).getRequestQueue();
        mSharePre=Utils.getMyapp(this).getSharePre();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog!=null) {
            dialog.dismiss();
            dialog=null;
        }
    }
}
