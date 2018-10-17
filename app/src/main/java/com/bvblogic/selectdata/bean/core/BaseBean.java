package com.bvblogic.selectdata.bean.core;

import com.bvblogic.selectdata.activity.core.BaseActivity;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class BaseBean {

    @RootContext
    public BaseActivity baseActivity;
}
