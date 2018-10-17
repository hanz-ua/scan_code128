package com.bvblogic.selectdata.mvp.manager.core;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.bvblogic.selectdata.fragment.core.BaseFragment;

public abstract class BaseManagerUI implements ManagerUI {

    private AppCompatActivity activity;


    public BaseManagerUI(AppCompatActivity activity) {
        this.activity = activity;
    }

    protected AppCompatActivity getActivity() {
        return this.activity;
    }

    protected abstract int getIdFragmentsContainer();


    protected void addFragmentToContainer(BaseFragment baseFragment, int toBackStack) {
        FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (toBackStack) {
            case ManagerUI.ADD_BACK_STACK:
                transaction.addToBackStack(baseFragment.getClass().getSimpleName()).add(this.getIdFragmentsContainer(), baseFragment, baseFragment.getClass().getSimpleName());
                break;
            case ManagerUI.ADD:
                transaction.add(this.getIdFragmentsContainer(), baseFragment, baseFragment.getClass().getSimpleName());
                break;
            case ManagerUI.REPLACE:
                transaction.replace(this.getIdFragmentsContainer(), baseFragment,
                        baseFragment.getClass().getSimpleName());
                break;
            case ManagerUI.REMOVE_LAST_FRAGMENT_ADD_TO_BACK_STACK:
                fragmentManager.popBackStack();
                transaction.addToBackStack(baseFragment.getClass().getSimpleName()).add(this.getIdFragmentsContainer(), baseFragment, baseFragment.getClass().getSimpleName());
                break;
            case ManagerUI.ADD_TO_BACK_STACK:
                if (fragmentManager.getBackStackEntryCount() != 0 && fragmentManager.getBackStackEntryCount() >0) {
                    if (!fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName().equals(baseFragment.getClass().getSimpleName())) {
                        transaction.addToBackStack(baseFragment.getClass().getSimpleName())
                                .add(this.getIdFragmentsContainer(), baseFragment, baseFragment.getClass().getSimpleName());
                    }
                }else if(fragmentManager.findFragmentByTag(baseFragment.getClass().getSimpleName()).getId()==baseFragment.getId()){
                    transaction.addToBackStack(baseFragment.getClass().getSimpleName())
                            .add(this.getIdFragmentsContainer(), baseFragment, baseFragment.getClass().getSimpleName());
                }
                break;
        }

        transaction.commit();
    }

}
