package com.bastlibrary.utils;

import android.content.Context;

import com.bastlibrary.R;

/**
 * Created by 监测工具 on 2017/6/27 0027.
 */

public class Confirm {

    /**监测网络*/
    public static boolean isNetwork(Context mContext){
        if(false==NetWorkUtils.checkNetWork(mContext)){
            //ToastUtils.showToast(mContext.getString(R.string.toast_nonetwork));
            return false;
        }
        return true;
    }

}
