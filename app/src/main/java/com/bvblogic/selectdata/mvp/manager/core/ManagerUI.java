package com.bvblogic.selectdata.mvp.manager.core;

import android.support.annotation.StringRes;

import com.bvblogic.selectdata.fragment.core.BaseFragment;
import com.bvblogic.selectdata.mvp.core.BaseFragmentData;
import com.bvblogic.selectdata.mvp.core.ToolBarById;


public interface ManagerUI {
    int ADD_BACK_STACK = 1;
    int ADD = 2;
    int REPLACE = 3;
    int REMOVE_LAST_FRAGMENT_ADD_TO_BACK_STACK = 4;
    int ADD_TO_BACK_STACK = 5;


    void changeFragmentTo(BaseFragmentData baseFragmentData);

    void initToolbar(BaseFragment baseFragment, ToolBarById toolBarById, @StringRes int... text);

    void changeNavigationMenuFragmentTo(BaseFragmentData baseFragmentData);
}
