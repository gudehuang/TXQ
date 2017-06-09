package com.hzg.txq.app;

/**
 * Created by hzg on 2017/6/6.
 */

public interface MyConfig {
    String PASSWORD = "password";
    String AppName = "TXQ";
    String ACCOUNT = "account";
    String ACCESS_TOKEN="access_token";
    String URL_Image="https://www.ibstart.com";
    String URL = true ? "http://test.app.ibstart.com" : "https://www.ibstart.com";
    String URL_GetVCProjectList_New="http://test.app.ibstart.com/api/project/angel/list";
    String  PROJECT_UUID="uuid";
    String URL_GetProjectDetail=URL + "/api/project/angel/detail";
}
