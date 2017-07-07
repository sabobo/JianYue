package com.liubo.jianyue;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.bastlibrary.adapter.GuideViewPagerAdapter;
import com.bastlibrary.bast.BaseActivity;
import com.bastlibrary.utils.SPreTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 导向 on 2017/6/27 0027.
 */

public class GuideActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager vp;

    // 引导页图片资源
    private static final int[] pics = {com.bastlibrary.R.layout.guid_view1,
            com.bastlibrary.R.layout.guid_view2, com.bastlibrary.R.layout.guid_view3};
    @Override
    protected int getLayoutResource() {
        return com.bastlibrary.R.layout.activity_guide;
    }

    @Override
    public void initView() {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        List<View> views = new ArrayList<>();
        // 初始化引导页视图列表
        for (int i = 0; i < pics.length; i++) {
            View view = LayoutInflater.from(this).inflate(pics[i], null);
            if (i == pics.length - 1) {
                Button startBtn = (Button) view.findViewById(com.bastlibrary.R.id.btn_login);
                startBtn.setTag("enter");
                startBtn.setOnClickListener(this);
            }
            views.add(view);
        }
        vp = (ViewPager) findViewById(com.bastlibrary.R.id.vp_guide);
        // 初始化adapter
        GuideViewPagerAdapter adapter = new GuideViewPagerAdapter(views);
        vp.setAdapter(adapter);
        vp.addOnPageChangeListener(new PageChangeListener());
    }

    @Override
    public void initData() {

    }
    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        // 当滑动状态改变时调用
        @Override
        public void onPageScrollStateChanged(int position) {
            // arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。

        }

        // 当前页面被滑动时调用
        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {
            // arg0 :当前页面，及你点击滑动的页面
            // arg1:当前页面偏移的百分比
            // arg2:当前页面偏移的像素位置

        }

        // 当新的页面被选中时调用
        @Override
        public void onPageSelected(int position) {
        }

    }
    @Override
    public void onClick(View v) {
        if (v.getTag().equals("enter")) {
            enterMainActivity();
            return;
        }
        int position = (Integer) v.getTag();
        setCurView(position);
    }
    /**
     * 设置当前view
     *
     * @param position
     */
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        vp.setCurrentItem(position);
    }
    private void enterMainActivity() {
        SPreTool.getInstance().putValue(GuideActivity.this,"firstBoot","1");
        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void onResume() {
        super.onResume();
        //MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
       // MobclickAgent.onPause(this);
    }

}
