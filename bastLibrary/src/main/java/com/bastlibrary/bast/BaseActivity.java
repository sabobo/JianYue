package com.bastlibrary.bast;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.bastlibrary.api.MainControl;
import com.bastlibrary.utils.ConnectionChangeReceiver;
import com.bastlibrary.utils.Handler.CommonDoHandler;
import com.bastlibrary.utils.Handler.CommonHandler;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 基础类 on 2017/6/27 0027.
 */

public abstract class BaseActivity extends AppCompatActivity implements CommonDoHandler {
    /**上下文*/
    public Context mContext;
    /***更新UI*/
    protected CommonHandler<BaseActivity> uiHandler;
    protected CommonHandler<BaseActivity> backgroundHandler;
    /*** 页码*/
    public static int pageIndex = 0;
    /***网络监测*/
    ConnectionChangeReceiver myReceiver;
    public static BaseActivity activity;
    public static MainControl mainControl;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        mContext = getApplicationContext();
        mainControl = new MainControl(this);
        ButterKnife.bind(this);
        initView();
        initData();
    }
    /***
     * 加载布局
     **/
    protected abstract int getLayoutResource();

    /***
     * 展示UI
     **/
    public abstract void initView();

    /***
     * 数据初始化
     **/
    public abstract void initData();

    public void doHandler(Message msg) {
    }
    //私有方法区域
    private void init() {
        uiHandler = new CommonHandler<>(this);
        HandlerThread handlerThread = new HandlerThread(getClass().getName());
        handlerThread.start();
        backgroundHandler = new CommonHandler<>(this, handlerThread.getLooper());
    }
    private void registerReceiver() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        myReceiver = new ConnectionChangeReceiver();
        this.registerReceiver(myReceiver, filter);
    }
    private void unregisterReceiver() {
        this.unregisterReceiver(myReceiver);
    }

    /***
     * 打开页面
     **/
    public void showActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
        overridePendingTransition(0, 0);
    }

    /***
     * 关闭动画
     **/
    @Override
    public void finish() {
        super.finish();
    }


}
