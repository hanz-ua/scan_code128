package com.bvblogic.selectdata.bean;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;


import com.bvblogic.selectdata.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by t-horielova on 27.04.17.
 */

public class PermissionsManager {
    private final int CAMERA = 2;
    private final int WRITE_EXTERNAL_STORAGE = 1;

    private final Activity activity;
    public boolean permissionAccept = false;

    public List<String> permissionsNeeded = new ArrayList<String>();
    public List<String> permissionsList = new ArrayList<String>();
    private List<String> listPermissionsNeeded = new ArrayList<>();

    public PermissionsManager(Activity activity) {
        this.activity = activity;
    }

    public void getLocationPermission() {
        permissionsList.clear();
        permissionsNeeded.clear();

        if (!addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionsNeeded.add("файл");
        }

        if (permissionList((ArrayList) permissionsList,
                WRITE_EXTERNAL_STORAGE)) {
            permissionAccept = true;
        }
    }


    public void getCameraPermision() {
        if (!addPermission(permissionsList, Manifest.permission.CAMERA))
            permissionsNeeded.add("камере");
        if (permissionList((ArrayList) permissionsList,
                CAMERA)) {
            permissionAccept = true;
        }
    }

    public void getPermission() {
        int permissionCAMERA = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA);


        int permissionWrite = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//        int permissionRead = ContextCompat.checkSelfPermission(activity,
//                Manifest.permission.READ_EXTERNAL_STORAGE);

//        if (permissionWrite != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        }
//
//        if (permissionRead != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
//        }


        if (permissionCAMERA != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }

        if (permissionWrite != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    1);
        }
    }

    private boolean addPermission(List<String> permissionsList, String permission) {
        if (ContextCompat.checkSelfPermission(activity, permission)
                != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    permission))
                return false;
        }
        return true;
    }

    private void showMessageOKCancel(String message,
                                     DialogInterface.OnClickListener okListener,
                                     DialogInterface.OnClickListener cancelListener) {
        new android.app.AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", cancelListener)
                .create()
                .show();
    }

    private boolean showPermission(final List<String> permissionsList,
                                   final int code) {
        ActivityCompat.requestPermissions(activity,
                permissionsList.toArray(
                        new String[permissionsList.size()]),
                code);
        return false;
    }

    public boolean permissionList(final ArrayList<String> permissionsList,
                                  final int code) {
        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                String message = activity.getResources().getText(R.string.permission_need_grant_access) +
                        permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);
                showMessageOKCancel(message,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(activity,
                                        permissionsList.toArray(
                                                new String[permissionsList.size()]),
                                        code);
                            }
                        },
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(activity.getApplicationContext(),
                                        activity.getResources()
                                                .getText(R.string.permission_allow_some_permission),
                                        Toast.LENGTH_LONG).show();

                            }
                        });
                return false;
            }

            ActivityCompat.requestPermissions(activity,
                    permissionsList.toArray(new String[permissionsList.size()]),
                    code);
            return false;
        } else return true;
    }
}
