package com.liubo.jianyue.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.FrameLayout;

import com.baidu.mobad.feeds.BaiduNative;
import com.baidu.mobad.feeds.NativeErrorCode;
import com.baidu.mobad.feeds.NativeResponse;
import com.baidu.mobad.feeds.RequestParameters;
import com.bastlibrary.adapter.BaseAdapter;
import com.bastlibrary.api.AppConfig;
import com.bastlibrary.bast.BaseFragment;
import com.bastlibrary.utils.DebugLogs;
import com.bastlibrary.utils.ListUtils;
import com.bastlibrary.widget.TipView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liubo.jianyue.Details;
import com.liubo.jianyue.R;
import com.liubo.jianyue.adapter.MainAdapter;
import com.liubo.jianyue.bean.MainBean;
import com.liubo.jianyue.controller.JsoupTool;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 * 简书
 * Created by  on 2017/7/1 0001.
 */

public class JuanShuFragment extends BaseFragment implements XRecyclerView.LoadingListener{
    @Bind(R.id.tip_view)
    TipView mTipView;
    @Bind(R.id.home_recly_view)
    XRecyclerView mRecyclerView;
    MainAdapter mAdapter;
    List<MainBean> mDatas = new ArrayList<>();
    @Override
    protected int getLayoutResource() {
        return  R.layout.frag_juanshu;
    }

    @Override
    public void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotatePulse);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setLoadingListener(this);
       // fetchAd(getActivity());

    }

    @Override
    public void initData() {
        //mStateView.showLoading();
        OkHttpUtils
                .get()
                .url("http://www.jianshu.com")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mTipView.show("网络异常");//弹出提示
                        if (ListUtils.isEmpty(mDatas)) {
                            //如果一开始进入没有数据
                            mStateView.showRetry();//显示重试的布局
                        }
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        //DebugLogs.e("---->"+response);
                        //如果是第一次获取数据

                        parseHtml(response);
                    }
                });
    }
    private void parseHtml(String html) {
        mDatas = new JsoupTool().getJianshuList(html);
        if (ListUtils.isEmpty(mDatas)) {
            if (ListUtils.isEmpty(mDatas)) {
                //获取不到数据,显示空布局
                mStateView.showEmpty();
                return;
            }
           mTipView.show();//弹出提示
           mStateView.showContent();//显示内容
        }
        mAdapter = new  MainAdapter(mContext,mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // ToastUtils.showToast(mContext,"http://www.jianshu.com"+mDatas.get(position).getTargetUrl());
                Intent intent = new Intent();
                intent.putExtra("deurl","http://www.jianshu.com"+mDatas.get(position-1).getTargetUrl());
                intent.setClass(mContext, Details.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onRefresh() {
        mTipView.show();//弹出提示
        mRecyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        mRecyclerView.loadMoreComplete();
    }

    public void onResume() {
        super.onResume();
       // MobclickAgent.onPageStart("MainScreen"); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        //MobclickAgent.onPageEnd("MainScreen");
    }
    public void fetchAd(Activity activity) {
        /**
         * Step 1. 创建BaiduNative对象，参数分别为： 上下文context，广告位ID, BaiduNativeNetworkListener监听（监听广告请求的成功与失败）
         * 注意：请将YOUR_AD_PALCE_ID替换为自己的广告位ID
         */
        BaiduNative baidu = new BaiduNative(activity, AppConfig.YOUR_AD_PLACE_ID, new BaiduNative.BaiduNativeNetworkListener() {

            @Override
            public void onNativeFail(NativeErrorCode arg0) {
                DebugLogs.e("FeedNativeListViewActivity"+ "onNativeFail reason:" + arg0.name());
            }

            @Override
            public void onNativeLoad(List<NativeResponse> arg0) {
                // 一个广告只允许展现一次，多次展现、点击只会计入一次
                if (arg0 != null && arg0.size() > 0) {
                }
            }

        });

        /**
         * Step 2. 创建requestParameters对象，并将其传给baidu.makeRequest来请求广告
         */
        // 用户点击下载类广告时，是否弹出提示框让用户选择下载与否
        RequestParameters requestParameters =
                new RequestParameters.Builder()
                        .downloadAppConfirmPolicy(
                                RequestParameters.DOWNLOAD_APP_CONFIRM_ONLY_MOBILE).build();

        baidu.makeRequest(requestParameters);
    }
}
