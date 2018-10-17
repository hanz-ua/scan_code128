package com.bvblogic.selectdata.mvp.manager;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import com.bvblogic.selectdata.R;
import com.bvblogic.selectdata.fragment.MainFragment_;
import com.bvblogic.selectdata.fragment.core.BaseFragment;
import com.bvblogic.selectdata.mvp.core.BaseFragmentData;
import com.bvblogic.selectdata.mvp.core.ToolBarById;
import com.bvblogic.selectdata.mvp.manager.core.BaseMainActivityManagerUI;
import com.bvblogic.selectdata.mvp.manager.core.ManagerUI;


public class MainActivityManagerUI extends BaseMainActivityManagerUI {


    public MainActivityManagerUI(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    protected int getIdFragmentsContainer() {
        return R.id.fragment_container ;
    }

    @Override
    public void changeFragmentTo(BaseFragmentData baseFragmentData) {
        switch (baseFragmentData.getFragmentById()) {
            case MAIN_FRAGMENT: {
               addFragmentToContainer(MainFragment_.builder().build(), ManagerUI.REPLACE);
                break;
            }

        }
    }


    @Override
    public void changeNavigationMenuFragmentTo(BaseFragmentData baseFragmentData) {

    }
}
