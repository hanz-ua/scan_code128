package com.bvblogic.selectdata.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;


import com.bvblogic.selectdata.R;
import com.bvblogic.selectdata.activity.core.BaseActivity;
import com.bvblogic.selectdata.bean.PermissionsManager;
import com.bvblogic.selectdata.mvp.core.FragmentById;
import com.bvblogic.selectdata.mvp.core.FragmentData;
import com.bvblogic.selectdata.mvp.manager.MainActivityManagerUI;
import com.bvblogic.selectdata.mvp.manager.core.ManagerUI;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;


@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    private boolean back;


    @AfterViews
    public void initCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
            new PermissionsManager(this).getPermission();
        } else {
            changeFragmentTo(new FragmentData(FragmentById.MAIN_FRAGMENT));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        if (grantResults.length > 1
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            changeFragmentTo(new FragmentData(FragmentById.MAIN_FRAGMENT));
        }
    }

    @Override
    protected ManagerUI getManagerUIToInit() {
        return new MainActivityManagerUI(this);
    }

    @Override
    public void onBackPressed() {
        if(!back){
            back = true;
            Toast.makeText(this, "Click back again to exit", Toast.LENGTH_SHORT).show();
        }else {
            super.onBackPressed();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                back = false;
            }
        }, 1000);
    }
}
