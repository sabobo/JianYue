package com.liubo.jianyue.Fragment;


import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.baidu.mobads.CpuInfoManager;
import com.bastlibrary.bast.BaseFragment;
import com.bastlibrary.utils.DebugLogs;
import com.liubo.jianyue.R;

import butterknife.Bind;

/**
 * Created by liubo on 2017/7/4 0004.
 */

public class AdvertFragment extends BaseFragment {
    // 测试id
    private static String getAppsid = "d77e414";
    private static int getValue = 1001;//财经频道
    @Bind(R.id.mwebview)
    WebView mWebView;
    private RelativeLayout mRootRelativeLayout;
    @Override
    protected int getLayoutResource() {
      return   R.layout.frag_advert;
    }

    @Override
    public void initView() {
        showSelectedCpuWebPage();
    }

    @Override
    public void initData() {

    }
    /**
     * 调用SDK接口，获取内容联盟页面URL
     */
    private void showSelectedCpuWebPage() {
        // 内容联盟url获取后只能展示一次，多次展示需要每次通过以下接口重新获取
        // 媒体伙伴必须在MSSP业务端选择接入内容联盟的应用与频道类型，以便在接入内容页中生成广告，从而获得广告收益。
        // 不进行相关操作，将无法获得内容联盟页面的广告收益。
        CpuInfoManager.getCpuInfoUrl(getActivity(),getAppsid,getValue, new CpuInfoManager.UrlListener() {

            @Override
            public void onUrl(String url) {
                handleWebViewLayout(url);
                DebugLogs.e("url=="+url);
            }
        });
    }
    /**
     * 根据内容联盟url，渲染页面
     *
     * @param url
     */
    private void handleWebViewLayout(String url) {
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 如果是图片频道，则必须设置该接口为true，否则页面无法展现
        webSettings.setDomStorageEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl(url);

    }
}
