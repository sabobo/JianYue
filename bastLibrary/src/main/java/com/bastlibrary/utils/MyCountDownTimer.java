package com.bastlibrary.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public class MyCountDownTimer extends CountDownTimer {
    /**
     * @param millisInFuture
     *      表示以「 毫秒 」为单位倒计时的总数
     *      例如 millisInFuture = 1000 表示1秒
     *
     * @param countDownInterval
     *      表示 间隔 多少微秒 调用一次 onTick()
     *      例如: countDownInterval = 1000 ; 表示每 1000 毫秒调用一次 onTick()
     *
     */
    private TextView mCountDownTextView;

    public MyCountDownTimer(long millisInFuture, long countDownInterval,TextView mCountDownTextView) {
        super(millisInFuture, countDownInterval);
        this.mCountDownTextView = mCountDownTextView;
    }


    public void onFinish() {
        mCountDownTextView.setText("0s 跳过");
    }

    public void onTick(long millisUntilFinished) {
        mCountDownTextView.setText( millisUntilFinished / 1000 + "s 跳过");
    }

}