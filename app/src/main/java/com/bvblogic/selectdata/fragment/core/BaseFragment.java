package com.bvblogic.selectdata.fragment.core;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.bvblogic.selectdata.R;
import com.bvblogic.selectdata.activity.core.BaseActivity;
import com.bvblogic.selectdata.mvp.core.FragmentFeedback;
import com.bvblogic.selectdata.mvp.core.ToolBarById;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;



@EFragment
public abstract class BaseFragment extends Fragment {
    private Bundle bundleData;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBundle("bundleData", this.bundleData);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.bundleData = savedInstanceState.getBundle("bundleData");
        }
        resumeBundle();
    }

    protected FragmentFeedback getFragmentFeedback() {
        return (FragmentFeedback) this.getActivity();
    }

    protected void initToolBar(ToolBarById toolBarById, @StringRes int... text) {
        if (this.getFragmentFeedback() != null) {
            this.getFragmentFeedback().initToolBar(this, toolBarById, text);
        }
    }

    protected void setFullScreen() {
        if (getActivity() != null)
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    protected void cleanFullScreen() {
        if (getActivity() != null)
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    protected void resumeBundle() {

    }
    @ViewById(R.id.toolbar)
    public Toolbar toolbar;

    @ViewById(R.id.toolbar_container)
    protected RelativeLayout toolbarContainer;

    public Toolbar getToolbar() {
        return toolbar;
    }

    public RelativeLayout getToolbarContainer() {
        return toolbarContainer;
    }

    public void clickToToolbar(int id) {

    }
    protected BaseActivity getBaseActivity(){
        return (BaseActivity) getActivity();
    }
}
