package com.bastlibrary.bast;

import android.app.Application;

import com.bastlibrary.crash.Cockroach;
import com.bastlibrary.utils.DebugLogs;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/6/27 0027.
 */

public class BastApp extends Application {

    private static BastApp instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
        /**永不crash*/
        Cockroach.install(new Cockroach.ExceptionHandler() {

            @Override
            public void handlerException(final Thread thread, final Throwable throwable) {
                try {
                    DebugLogs.d("Cockroach" + thread + "\n" + throwable.toString());
                    throwable.printStackTrace();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // 单例模式获取唯一的MyApplication实例
    public static BastApp getInstance() {
        return instance;
    }
}
