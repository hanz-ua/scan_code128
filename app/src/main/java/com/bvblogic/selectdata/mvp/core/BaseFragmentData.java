package com.bvblogic.selectdata.mvp.core;

public abstract class BaseFragmentData {

    protected Object[] bundle;

    public BaseFragmentData setBundle(Object... bundle) {
        this.bundle = bundle;
        return this;
    }

    public Object[] getBundle() {
        return bundle;
    }

    public abstract FragmentById getFragmentById();

}
