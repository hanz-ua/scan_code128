package com.bvblogic.selectdata.fragment;

import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.bvblogic.selectdata.R;
import com.bvblogic.selectdata.bean.ScanBean;
import com.bvblogic.selectdata.fragment.core.BaseFragment;
import com.bvblogic.selectdata.mvp.core.ToolBarById;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.logging.Handler;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

@EFragment(R.layout.fragment_main)
public class MainFragment extends BaseFragment {

    private ZXingScannerView mScannerView;

    @ViewById(R.id.info)
    ExpandableLayout info;

    @Bean
    ScanBean scanBean;

    @ViewById(R.id.scan)
    FrameLayout contentFrame;

    @AfterViews
    public void init() {
        initToolBar(ToolBarById.SIMPLE);
        mScannerView = scanBean.initQR(contentFrame, this);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                info.setSelected(true);
                info.expand();
            }
        }, 300);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(scanBean);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

}
