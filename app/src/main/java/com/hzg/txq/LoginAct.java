package com.hzg.txq;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.hzg.txq.databinding.ActivityLoginBinding;

import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.Request.Method.GET;

/**
 * Created by hzg on 2017/6/6.
 */

public class LoginAct extends AppCompatActivity {


    private ActivityLoginBinding loginBinding;
    RequestQueue mQueue;

    ProgressDialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
       String account = getSharedPreferences(MyConfig.AppName, MODE_PRIVATE).getString(MyConfig.ACCOUNT, "");
       String  password = getSharedPreferences(MyConfig.AppName, MODE_PRIVATE).getString(MyConfig.PASSWORD, "");
        loginBinding.setAccount(account);
        loginBinding.setPassword(password);

        mQueue =Utils.getMyapp(this).getRequestQueue();
        loginBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()) {
                    setErrorDefalut();
                    goLogin();
                }
            }
        });
    }

    private void goLogin() {
        if (dialog == null) {
            dialog = ProgressDialog.show(LoginAct.this, null, "登录中", false, false);
        }
        dialog.show();
        String url = MyConfig.URL + "/oauth/token?client_id=app-client&grant_type=password&scope=read" +
                "&username=" + loginBinding.getAccount()
                + "&password=" + loginBinding.getPassword();
        final FastJsonRequest request = new FastJsonRequest(GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
                try {
                    String access_token = response.getString("access_token");
                    getSharedPreferences(MyConfig.AppName, MODE_PRIVATE).edit()
                            .putString(MyConfig.ACCOUNT, loginBinding.getAccount())
                            .putString(MyConfig.PASSWORD, loginBinding.getPassword())
                            .putString(MyConfig.ACCESS_TOKEN, access_token)
                            .apply();

                    goToNextAct(ProjectListAct.class);

                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    dialog.hide();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null) {
                    if (error.networkResponse.statusCode == 400) {
                        setErrorDefalut();
                        loginBinding.TILPassword.setError("密码错误");


                    } else if (error.networkResponse.statusCode == 401) {
                        setErrorDefalut();
                        loginBinding.TILAccount.setError("用户名不存在");


                    }
                    System.out.println(error.networkResponse.statusCode);
                    System.out.println(new String(error.networkResponse.data));
                }
                dialog.hide();
            }
        });
        mQueue.add(request);
    }

    private void goToNextAct(Class target) {
        Intent intent=new Intent(this,target);
        startActivity(intent);
        finish();
    }

    private boolean checkInput() {
        if (!TextUtils.isEmpty(loginBinding.getAccount()) && !TextUtils.isEmpty(loginBinding.getPassword()))
            return true;
        ;
        if (TextUtils.isEmpty(loginBinding.getAccount()))
            loginBinding.TILAccount.setError("用户名不能为空");

        if (TextUtils.isEmpty(loginBinding.getPassword()))
            loginBinding.TILPassword.setError("密码不能为空");

        return false;
    }

    private void setErrorDefalut() {
        loginBinding.TILAccount.setError(null);
        loginBinding.TILPassword.setError(null);

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
