package com.liubo.jianyue;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bastlibrary.bast.BaseActivity;
import com.bastlibrary.utils.MyCountDownTimer;
import com.bastlibrary.utils.SPreTool;

import butterknife.Bind;

/**
 * Created by 欢迎 on 2017/6/27 0027.
 */

public class SplashActivity extends BaseActivity {
    @Bind(R.id.iv_splash_background)
    ImageView mImageView;
    @Bind(R.id.adsRl)
    RelativeLayout adsParent;
    @Bind(R.id.appName)
    TextView appName;
    Animation mAnimation;
    @Bind(R.id.start_skip_count_down)
    TextView mCountDownTextView;
    private MyCountDownTimer mCountDownTimer;
    Handler tmpHandler =new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String firstBoot = SPreTool.getInstance().getStringValue(SplashActivity.this, "firstBoot");
        if (firstBoot == null || !"1".equals(firstBoot)) {
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
            finish();
            return;
        }
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.welcome_alpha);
        appName.setAnimation(mAnimation);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
            }
        });
        mCountDownTextView.setText("3s 跳过");
        //创建倒计时类
        mCountDownTimer = new MyCountDownTimer(3000, 1000, mCountDownTextView);
        mCountDownTimer.start();
        //这是一个 Handler 里面的逻辑是从 Splash 界面跳转到 Main 界面，这里的逻辑每个公司基本上一致
        tmpHandler.postDelayed(runnable, 3000);
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
    Runnable runnable =new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finish();
        }
    };

    /**
     * 当设置开屏可点击时，需要等待跳转页面关闭后，再切换至您的主窗口。故此时需要增加canJumpImmediately判断。 另外，点击开屏还需要在onResume中调用jumpWhenCanClick接口。
     */
    public boolean canJumpImmediately = false;

    private void jumpWhenCanClick() {
        Log.d("test", "this.hasWindowFocus():" + this.hasWindowFocus());
        if (canJumpImmediately) {
            this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
            this.finish();
        } else {
            canJumpImmediately = true;
        }

    }
    /**
     * 不可点击的开屏，使用该jump方法，而不是用jumpWhenCanClick
     */
    private void jump() {
        this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
        this.finish();
    }
    public void onResume() {
        super.onResume();
        //MobclickAgent.onResume(this);
        if (canJumpImmediately) {
            jumpWhenCanClick();
        }
        canJumpImmediately = true;
    }
    public void onPause() {
        super.onPause();
       // MobclickAgent.onPause(this);
        canJumpImmediately = false;
    }
    @Override
    protected void onDestroy() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        super.onDestroy();
    }

}
