package com.liubo.jianyue;

import android.os.Build;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bastlibrary.bast.BaseActivity;
import com.bastlibrary.utils.DebugLogs;
import com.bumptech.glide.Glide;
import com.liubo.jianyue.controller.JsoupTool;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/29 0029.
 */

public class Details  extends BaseActivity {
    @Bind(R.id.top_back)
    TextView topBack;
    @Bind(R.id.top_title)
    TextView topTitle;
    @Bind(R.id.article)
    TextView article;
    @Bind(R.id.article_img)
    ImageView article_img;
    @Bind(R.id.article_WebView)
    WebView mWebview;
    WebSettings webSettings;
    String url = "";
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_details;
    }
    @OnClick(R.id.top_back)
    void top_back() {
        finish();
    }
    @Override
    public void initView() {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        topBack.setVisibility(View.VISIBLE);
        //topTitle.setText("详情");
        article.setMovementMethod(ScrollingMovementMethod.getInstance());
        url = getIntent().getStringExtra("deurl");
        DebugLogs.e("----url>" + url);

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

        webSettings.setAppCachePath(getFilesDir() + getPackageName() + "/cache");
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
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                parseHtml(response);
                            }
                        });
                    }
                });
    }
    private void parseHtml(String html) {
        new JsoupTool().getJianshuListDetails(html, new JsoupTool.call() {
            @Override
            public void callStr(String title, String body, String img) {
                DebugLogs.e("----title>"+title);
                DebugLogs.e("----body>"+body);
                DebugLogs.e("----img>"+img);
                topTitle.setText(title);
                Glide.with(mContext).load("http:"+img).into(article_img);
                mWebview.loadData(body, "text/html; charset=UTF-8", null);
            }
        });

    }


//    private void loadMyBlog() {
//        Call<ResponseBody> call;
//        //分页处理
//        if (page == 1) {
//            call = apiStores.loadMyBlog();
//        } else {
//            call = apiStores.loadMyBlog(page);
//        }
//        call.enqueue(new RetrofitCallback<ResponseBody>() {
//            @Override
//            public void onSuccess(ResponseBody responseBody) {
//                try {
//                    Document document = Jsoup.parse(new String(responseBody.bytes(), "UTF-8"));
//                    List<Element> titleElementList = new ArrayList<>();
//                    Elements titleElements = document.getElementsByClass("post-title-link");
//                    for (Element element : titleElements) {
//                        titleElementList.add(element);
//                        //LogUtil.d("text=" + element.text());
//                        //LogUtil.d("href=" + element.attr("href"));
//                    }
//                    List<Element> timeElementList = new ArrayList<>();
//                    Elements timeElements = document.getElementsByClass("post-time");
//                    for (Element element : timeElements) {
//                        //LogUtil.d("time=" + element.getElementsByTag("time").text());
//                        timeElementList.add(element);
//                    }
//                    //Elements categoryElements = document.getElementsByClass("post-category");
//                    //for (Element element : categoryElements) {
//                    //    LogUtil.d("category=" + element.getElementsByTag("a").text());
//                    //}
//                    List<Element> bodyElementList = new ArrayList<>();
//                    Elements bodyElements = document.getElementsByClass("post-body");
//                    for (Element element : bodyElements) {
//                        LogUtil.d("body=" + element.html());
//                        bodyElementList.add(element);
//                    }
//                    if (page == 1) {
//                        dataAdapter.clear();
//                    }
//                    dataAdapter.addAll(titleElementList, timeElementList, bodyElementList);
//                    if (titleElementList.size() < 8) {
//                        //因为我的博客一页8条数据，当小于8时，说明没有下一页了
//                        pullLoadMoreRecyclerView.setHasMore(false);
//                    } else {
//                        pullLoadMoreRecyclerView.setHasMore(true);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            @Override
//            public void onFailure(int code, String msg) {
//                toastShow(msg);
//            }
//            @Override
//            public void onThrowable(Throwable t) {
//                toastShow(t.getMessage());
//            }
//            @Override
//            public void onFinish() {
//                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
//            }
//        });
//        addCalls(call);
//    }
}
