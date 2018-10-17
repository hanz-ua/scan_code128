package com.bvblogic.selectdata.bean;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bvblogic.selectdata.R;
import com.bvblogic.selectdata.bean.core.BaseBean;
import com.bvblogic.selectdata.fragment.MainFragment;
import com.google.zxing.Result;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.Touch;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.core.ViewFinderView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

@EBean
public class ScanBean extends BaseBean implements ZXingScannerView.ResultHandler {
    @ViewById(R.id.name)
    TextInputEditText name;
    @ViewById(R.id.order)
    TextInputEditText order;
    @ViewById(R.id.location)
    TextInputEditText location;
    @ViewById(R.id.product)
    TextInputEditText product;
    @ViewById(R.id.extra)
    TextInputEditText extra;
    @ViewById(R.id.count)
    TextInputEditText count;

    @Bean
    FileBean fileBean;

    @Click(R.id.done)
    public void done() {
        if (!TextUtils.isEmpty(name.getText().toString())
                && !TextUtils.isEmpty(order.getText().toString())
                && !TextUtils.isEmpty(location.getText().toString())
                && !TextUtils.isEmpty(product.getText().toString())
                && !TextUtils.isEmpty(extra.getText().toString())) {

            Collection<String[]> data = new ArrayList<>();
            data.add(new String[]{"name", name.getText().toString()});
            data.add(new String[]{"order", order.getText().toString()});
            data.add(new String[]{"location", location.getText().toString()});
            data.add(new String[]{"product", product.getText().toString()});
            data.add(new String[]{"extra", extra.getText().toString()});
            if (TextUtils.isEmpty(count.getText().toString()))
                data.add(new String[]{"count", "0"});
            else
                data.add(new String[]{"count", count.getText().toString()});

            fileBean.saveData(data, order.getText().toString());

            name.setText("");
            order.setText("");
            location.setText("");
            product.setText("");
            extra.setText("");
            count.setText("");
            nextFocus(name);

            clickToOther();
        } else {
            Toast.makeText(baseActivity, "not all data set", Toast.LENGTH_SHORT).show();
        }
    }

    @AfterViews
    public void initView() {
        nextFocus(name);
        name.setEnabled(true);
        order.setShowSoftInputOnFocus(false);
        order.setEnabled(true);
        location.setShowSoftInputOnFocus(false);
        location.setEnabled(true);
        product.setShowSoftInputOnFocus(false);
        product.setEnabled(true);
        extra.setShowSoftInputOnFocus(false);
        extra.setEnabled(true);

        count.setFilters(new InputFilter[]{new InputFilterMinMax(0, 9999)});
    }

    private ZXingScannerView mScannerView;

    public ZXingScannerView initQR(ViewGroup contentFrame, MainFragment mainFragment) {
        mScannerView = new ZXingScannerView(baseActivity) {
            @Override
            protected IViewFinder createViewFinderView(Context context) {
                ViewFinderView finderView = new ViewFinderView(context);
                finderView.setSquareViewFinder(true);
                return finderView;
            }
        };
        contentFrame.addView(mScannerView);
        return mScannerView;
    }

    @ViewById(R.id.info)
    ExpandableLayout info;

    @ViewById(R.id.scan)
    FrameLayout contentFrame;

    @Touch(R.id.count)
    public boolean clickToExtra() {
        stopCamera();
        baseActivity.showSoftKeyboard(count);
        if (info.isExpanded()) {
            info.setSelected(false);
            info.collapse();
        }
        return false;
    }

    @Touch({R.id.name, R.id.order, R.id.location, R.id.product, R.id.extra})
    public boolean clickToOther() {
        if (!info.isExpanded()) {
            resetCamera();
            info.setSelected(true);
            info.expand();
        }
        baseActivity.hideKeyboard(baseActivity);
        return false;
    }


    @Override
    public void handleResult(Result result) {
        if (result.toString().toCharArray().length > 20) {
            Toast.makeText(baseActivity, "Max length of 20 characters", Toast.LENGTH_SHORT).show();
            resetCamera();
            return;
        }
        ((EditText) Objects.requireNonNull(baseActivity.getCurrentFocus())).setText(result.getText());
        switch (baseActivity.getCurrentFocus().getId()) {
            case R.id.name:
                nextFocus(order);
                break;
            case R.id.order:
                nextFocus(location);
                break;
            case R.id.location:
                nextFocus(product);
                break;
            case R.id.product:
                nextFocus(extra);
                break;
            case R.id.extra:
                clickToExtra();
                count.requestFocus();
                return;
        }
        resetCamera();
    }

    private void resetCamera() {
        mScannerView.setResultHandler(ScanBean.this);
        mScannerView.startCamera();
    }

    private void stopCamera() {
        mScannerView.stopCamera();
    }

    public void nextFocus(EditText view) {
        view.requestFocus();
        view.setShowSoftInputOnFocus(false);
    }
}
