package com.bastlibrary.bast;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bastlibrary.R;
import com.github.nukc.stateview.StateView;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/1 0001.
 */

public abstract class BaseFragment extends Fragment {
    protected View rootView;
    public static Context mContext;
    protected StateView mStateView;//用于显示加载中、网络异常，空布局、内容布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();;
        if (rootView == null){
            rootView = inflater.inflate(getLayoutResource(), container, false);
            ButterKnife.bind(this, rootView);
            mStateView = StateView.inject(getStateViewRoot());
            if (mStateView != null){
                mStateView.setLoadingResource(R.layout.page_loading);
                mStateView.setRetryResource(R.layout.page_net_error);
            }
            initView();
            initData();
        }else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        return rootView;
    }

    /**StateView的根布局，默认是整个界面，如果需要变换可以重写此方法*/
    public View getStateViewRoot() {
        return rootView;
    }
    protected abstract int getLayoutResource();
    /***展示UI**/
    public abstract void initView();
    /***数据初始化**/
    public abstract void initData();

    public void showActivity(Class<?> cls){
        startActivity(new Intent(mContext, cls));
        getActivity().overridePendingTransition(0, 0);
    }
    public void finish() {
        finish();
        // 关闭动画
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
