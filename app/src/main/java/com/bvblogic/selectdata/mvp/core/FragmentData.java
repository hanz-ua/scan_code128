package com.bvblogic.selectdata.mvp.core;


public class FragmentData extends BaseFragmentData {

    private FragmentById fragmentByID;


    public FragmentData(FragmentById fragmentById) {
        this.fragmentByID = fragmentById;
    }


    public FragmentData(FragmentById fragmentById, Object... bundle) {
        this.fragmentByID = fragmentById;
        this.bundle = bundle;
    }

    @Override
    public FragmentById getFragmentById() {
        return fragmentByID;
    }
}
