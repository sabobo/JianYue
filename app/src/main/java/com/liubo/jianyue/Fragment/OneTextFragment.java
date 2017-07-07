package com.liubo.jianyue.Fragment;

import android.os.Build;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.bastlibrary.bast.BaseFragment;
import com.bastlibrary.utils.DebugLogs;
import com.liubo.jianyue.R;
import com.liubo.jianyue.controller.JsoupTool;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by 每日一文 on 2017/7/1 0001.
 */

public class OneTextFragment extends BaseFragment {
    @Bind(R.id.top_back)
    TextView topBack;
    @Bind(R.id.top_title)
    TextView topTitle;
    @Bind(R.id.top_recod)
    TextView top_recod;
    @Bind(R.id.oneText_WebView)
    WebView mWebview;
    WebSettings webSettings;
    String url = "https://meiriyiwen.com/random";
    protected int getLayoutResource() {
        return R.layout.activity_onetext;
    }

    @Override
    public void initView() {
        topTitle.setText("");
        top_recod.setVisibility(View.VISIBLE);
        top_recod.setText("随机");
    }
    @OnClick(R.id.top_recod)
    void top_recod() {
        setResponse();
    }
    @Override
    public void initData() {
        webSettings = mWebview.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(true);
        }
        //启用javascript支持 用于访问页面中的javascript
        webSettings.setJavaScriptEnabled(false);
        //设置脚本是否允许自动打开弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);

        webSettings.setAppCachePath(mContext.getFilesDir() + mContext.getPackageName() + "/cache");
        //设置H5的缓存打开,默认关闭
        webSettings.setAppCacheEnabled(false);
        webSettings.setDatabaseEnabled(false);

        //让WebView支持缩放
        webSettings.setSupportZoom(false);
        //启用WebView内置缩放功能
        webSettings.setBuiltInZoomControls(false);
        //设置webview自适应屏幕大小
        webSettings.setUseWideViewPort(false);
        //webSettings.setDefaultFontSize(40);
        //设置webview自适应屏幕大小
        webSettings.setLoadWithOverviewMode(false);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //关闭webview中缓存 LOAD_DEFAULT/无缓存 /无网络时，使用LOAD_CACHE_ELSE_NETWORK
        webSettings.setCacheMode(WebSettings.LOAD_NORMAL);
        //支持自动加载图片
        webSettings.setLoadsImagesAutomatically(false);
        //支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        //设置WebView使用内置缩放机制时，是否展现在屏幕缩放控件上
        webSettings.setDisplayZoomControls(false);
        //设置在WebView内部是否允许访问文件
        webSettings.setAllowFileAccess(false);

        // 设置编码格式
        webSettings.setDefaultTextEncodingName("UTF-8");
        //开启DOM缓存，关闭的话H5自身的一些操作是无效的
        webSettings.setDomStorageEnabled(false);
        //WebView硬件加速导致页面渲染闪烁
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }*/
        //设置加载完页面后再加载图片 加快HTML网页加载完成速度
        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setLoadsImagesAutomatically(false);
        } else {
            webSettings.setLoadsImagesAutomatically(false);
        }
        if (null != url && !"".equals(url)) {
            //mWebview.loadUrl("https://meiriyiwen.com/random");

        }
        mWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //get the newProgress and refresh progress bar
            }
        });
        mWebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                // topTitle.setText(title);//a textview
            }
        });
        setResponse();
    }
    public void setResponse(){
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(final String response, int id) {
                        DebugLogs.e("---->"+response);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                parseHtml(response);
                            }
                        });
                    }
                });

    }

    private void parseHtml(String html) {
        new JsoupTool().getOneText(html,new JsoupTool.Onecall() {
            @Override
            public void callStr(String title, String body) {
                DebugLogs.e("----title>"+title);
                DebugLogs.e("----body>"+body);
                mWebview.loadData(body, "text/html; charset=UTF-8", null);
                topTitle.setText(title);
            }
        });

    }
    public void onResume() {
        super.onResume();
       // MobclickAgent.onPageStart("MainScreen"); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
       // MobclickAgent.onPageEnd("MainScreen");
    }
}
