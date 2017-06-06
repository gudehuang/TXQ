package com.hzg.txq;

/**
 * Created by hzg on 2017/6/6.
 */

public interface MyConfig {
    String PASSWORD = "password";
    String AppName = "TXQ";
    String ACCOUNT = "account";
    String ACCESS_TOKEN="access_token";
    String URL = true ? "http://test.app.ibstart.com" : "https://www.ibstart.com";
    String URL_GetVCProjectList_New="http://test.app.ibstart.com/api/project/angel/list";
}
