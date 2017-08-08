package com.bastlibrary.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bastlibrary.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class LoadingFlashView extends FrameLayout {

    private View mFlashView;
    private TextView mLoad1;
    private TextView mLoad2;
    private TextView mLoad3;
    private TextView mLoad4;
    private AnimatorSet mAnimatorSet;

    public LoadingFlashView(Context context) {
        this(context, null);
    }

    public LoadingFlashView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingFlashView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mFlashView = LayoutInflater.from(context).inflate(R.layout.loading_flash_view, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLoad1 = ((TextView) findViewById(R.id.load1));
        mLoad2 = ((TextView) findViewById(R.id.load2));
        mLoad3 = ((TextView) findViewById(R.id.load3));
        mLoad4 = ((TextView) findViewById(R.id.load4));
        showLoading();
    }

    public void showLoading() {
        if (getVisibility() != View.VISIBLE)
            return;
        if (mAnimatorSet == null)
            initAnimation();
        if (mAnimatorSet.isRunning() || mAnimatorSet.isStarted())
            return;
        mAnimatorSet.start();
    }

    public void hideLoading() {
        if (mAnimatorSet == null)
            return;
        if ((!mAnimatorSet.isRunning()) && (!mAnimatorSet.isStarted()))
            return;
        mAnimatorSet.removeAllListeners();
        mAnimatorSet.cancel();
        mAnimatorSet.end();
    }


    private void initAnimation() {
        mAnimatorSet = new AnimatorSet();


        List<TextView> imageViewList = Arrays.asList(mLoad1, mLoad2, mLoad3, mLoad4);
        List<Animator> animatorList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ObjectAnimator loadAnimator = ObjectAnimator.ofFloat(imageViewList.get(i), "alpha", new float[]{1.0F, 0.5F}).setDuration(500L);
            loadAnimator.setStartDelay(100 * i);
            loadAnimator.setRepeatMode(ObjectAnimator.REVERSE);
            loadAnimator.setRepeatCount(-1);
            animatorList.add(loadAnimator);
        }
        mAnimatorSet.playTogether(animatorList);


//        ObjectAnimator loadAnimator1 = ObjectAnimator.ofFloat(mLoad1, "alpha", new float[]{1.0F, 0.5F}).setDuration(500L);
//        ObjectAnimator loadAnimator2 = ObjectAnimator.ofFloat(mLoad2, "alpha", new float[]{1.0F, 0.5F}).setDuration(500L);
//        ObjectAnimator loadAnimator3 = ObjectAnimator.ofFloat(mLoad3, "alpha", new float[]{1.0F, 0.5F}).setDuration(500L);
//        ObjectAnimator loadAnimator4 = ObjectAnimator.ofFloat(mLoad4, "alpha", new float[]{1.0F, 0.5F}).setDuration(500L);
//        loadAnimator1.setStartDelay(0L);
//        loadAnimator2.setStartDelay(100L);
//        loadAnimator3.setStartDelay(200L);
//        loadAnimator4.setStartDelay(300L);
//        loadAnimator1.setRepeatMode(ObjectAnimator.REVERSE);
//        loadAnimator2.setRepeatMode(ObjectAnimator.REVERSE);
//        loadAnimator3.setRepeatMode(ObjectAnimator.REVERSE);
//        loadAnimator4.setRepeatMode(ObjectAnimator.REVERSE);
//        loadAnimator1.setRepeatCount(-1);
//        loadAnimator2.setRepeatCount(-1);
//        loadAnimator3.setRepeatCount(-1);
//        loadAnimator4.setRepeatCount(-1);
//        mAnimatorSet.playTogether(new Animator[]{loadAnimator1, loadAnimator2, loadAnimator3, loadAnimator4});
    }

    public void setLoadingTheme(boolean isNight) {
        if (isNight) {
            mLoad1.setTextColor(R.color.subscribe_category_bar_bg_night);
            mLoad2.setTextColor(R.color.subscribe_category_bar_bg_night);
            mLoad3.setTextColor(R.color.subscribe_category_bar_bg_night);
            mLoad4.setTextColor(R.color.subscribe_category_bar_bg_night);
        } else {
            mLoad1.setTextColor(null);
            mLoad2.setTextColor(null);
            mLoad3.setTextColor(null);
            mLoad4.setTextColor(null);
        }
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility != VISIBLE)
            hideLoading();
    }
}
