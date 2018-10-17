package com.bvblogic.selectdata.mvp.manager.core;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bvblogic.selectdata.R;
import com.bvblogic.selectdata.fragment.core.BaseFragment;
import com.bvblogic.selectdata.mvp.core.ToolBarById;


public abstract class BaseMainActivityManagerUI extends BaseManagerUI {

    public BaseMainActivityManagerUI(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void initToolbar(BaseFragment baseFragment, ToolBarById toolBarById, @StringRes int... text) {
        View inflate = null;
        switch (toolBarById) {
            case SIMPLE: {
                inflate = getActivity().getLayoutInflater().inflate(R.layout.simple_toolbar, null);
                break;
            }
        }
        updateToolbar(baseFragment, inflate);
    }

    private void updateToolbar(BaseFragment baseFragment, View inflate) {
        baseFragment.getToolbarContainer().removeAllViews();
        baseFragment.getToolbarContainer().addView(inflate);
    }
}
