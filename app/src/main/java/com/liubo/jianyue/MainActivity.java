package com.liubo.jianyue;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.liubo.jianyue.Fragment.AboutFragment;
import com.liubo.jianyue.Fragment.AdvertFragment;
import com.liubo.jianyue.Fragment.JuanShuFragment;
import com.liubo.jianyue.Fragment.OneTextFragment;
import com.vector.update_app.UpdateAppManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setTitle(getString(R.string.tngou_news));
        switchFragment(new JuanShuFragment());
       // imageView = (ImageView)findViewById(R.id.top_imageView) ;
//        String img = "http://upload-images.jianshu.io/upload_images/2542851-f1c2aaf98092e68c.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/480";
//        Glide.with(this).load(img).into(imageView);

//        new UpdateAppManager
//                .Builder()
//                //当前Activity
//                .setActivity(this)
//                //更新地址
//                .setUpdateUrl("https://android-arsenal.com/?page=2&sort=created")
//                //实现httpManager接口的对象
//                .setHttpManager(new UpdateAppHttpUtil())
//                .build()
//                .update();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            setTitle(getString(R.string.tngou_news));
            switchFragment(new JuanShuFragment());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            setTitle(getString(R.string.tngou_news));
            switchFragment(new JuanShuFragment());
        } else if (id == R.id.nav_gallery) {
            setTitle(getString(R.string.tngou_girl));
            switchFragment(new OneTextFragment());
        } else if (id == R.id.nav_slideshow) {
            setTitle(getString(R.string.tngou_game));
           // startActivity(new Intent(MainActivity.this,MainFragment.class));
        }else if (id == R.id.nav_share) {
            setTitle(getString(R.string.my_blog));
            switchFragment(new AdvertFragment());
        } else if (id == R.id.nav_send) {
            setTitle(getString(R.string.my_about));
            switchFragment(new AboutFragment());
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
    /**
     * 切换Fragment
     */

    public void switchFragment(Fragment newFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, newFragment).commit();
    }
    public void onResume() {
        super.onResume();
       // MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
       //MobclickAgent.onPause(this);
    }

}