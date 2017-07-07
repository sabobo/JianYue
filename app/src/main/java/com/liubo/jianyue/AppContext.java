package com.liubo.jianyue;

import com.bastlibrary.bast.BastApp;
/**
 * Created by liubo on 2017/6/27 0027.
 */

public class AppContext extends BastApp {
    private static AppContext instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    // 单例模式获取唯一的MyApplication实例
    public static AppContext getInstance() {
        return instance;
    }
}
