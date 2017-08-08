package com.bastlibrary.api;

import android.content.Context;

import com.bastlibrary.utils.Confirm;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

/**
 * Created by 控制器 on 2017/6/27 0027.
 */

public class MainControl {
    public static Context mContext;
    public MainControl(Context mContext){
        this.mContext = mContext;
    }


    /*** 版本更新*/
    public void getHome(StringCallback callBack){
        if(!Confirm.isNetwork(mContext)){
            return;
        }
//        String sign = "";
//        String m = time();//当前时间戳
//        String str = m.substring(m.length() - 5, m.length());//当前时间戳后五位
        HashMap<String,String> map = new HashMap<String, String>();
//        map.put("mobile", mobile);
//        map.put("password", password);
//        map.put("app_id", AppConfig.appId);
        OkHttpUtils.postString().url(AppConfig.home).build().execute(callBack);
    }


}
