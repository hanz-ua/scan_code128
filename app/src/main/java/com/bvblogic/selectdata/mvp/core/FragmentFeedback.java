package com.bvblogic.selectdata.mvp.core;


import android.support.annotation.StringRes;

import com.bvblogic.selectdata.fragment.core.BaseFragment;


public interface FragmentFeedback {

    void changeFragmentTo(BaseFragmentData baseFragmentData);

    void initToolBar(BaseFragment baseFragment, ToolBarById toolBarById, @StringRes int... label);

}
