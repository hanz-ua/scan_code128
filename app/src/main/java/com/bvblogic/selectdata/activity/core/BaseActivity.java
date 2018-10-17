package com.bvblogic.selectdata.activity.core;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.bvblogic.selectdata.R;
import com.bvblogic.selectdata.fragment.core.BaseFragment;
import com.bvblogic.selectdata.mvp.core.BaseFragmentData;
import com.bvblogic.selectdata.mvp.core.FragmentFeedback;
import com.bvblogic.selectdata.mvp.core.ToolBarById;
import com.bvblogic.selectdata.mvp.manager.core.ManagerUI;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Objects;


@EActivity
public abstract class BaseActivity extends AppCompatActivity implements FragmentFeedback {

    private ManagerUI managerUI;

    @ViewById(R.id.progress_bar)
    public View progressBar;

    @AfterInject
    protected void create() {
        managerUI = this.getManagerUIToInit();
    }

    @Override
    public void initToolBar(BaseFragment baseFragment, ToolBarById toolBarById, int... label) {
        this.managerUI.initToolbar(baseFragment, toolBarById, label);
    }

    protected abstract ManagerUI getManagerUIToInit();

    @Override
    public void changeFragmentTo(BaseFragmentData baseFragmentData) {
        this.managerUI.changeFragmentTo(baseFragmentData);
    }

    public void showProgressBar() {
        if (progressBar.getVisibility() != View.VISIBLE) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        Objects.requireNonNull(imm).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            Objects.requireNonNull(imm).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public void hideProgressBar() {
        if (progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.GONE);
        }
    }

}
