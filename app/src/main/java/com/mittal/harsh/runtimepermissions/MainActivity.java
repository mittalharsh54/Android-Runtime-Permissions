package com.mittal.harsh.runtimepermissions;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// activity where we will check and request the multiple Permissions in Android N
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 145;
    private static final String TAG = "MainActivity";

    public static boolean checkAndRequestPermissions(int flag, Activity context) {
        //permission for reading phone state
        int permissionPhoneState = ContextCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE");
        //permission for reading EXTERNAL STORAGE
        int permissionStorageR = ContextCompat.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE");
        //permission for writing EXTERNAL STORAGE
        int permissionStorageW = ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE");
        //permission for acccessing CAMERA
        int permissionCamera = ContextCompat.checkSelfPermission(context, "android.permission.CAMERA");
        //permission for recording AUDIO
        int permissionRecordAudio = ContextCompat.checkSelfPermission(context, "android.permission.RECORD_AUDIO");
        //permission for accessing DEVICE LOCATION
        int permissionLocation = ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION");

        //wrapping all the permissions in the list
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionPhoneState != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add("android.permission.READ_PHONE_STATE");
        }

        if (permissionStorageR != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add("android.permission.READ_EXTERNAL_STORAGE");
        }
        if (permissionStorageW != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add("android.permission.CAMERA");
        }
        if (permissionRecordAudio != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add("android.permission.RECORD_AUDIO");
        }
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add("android.permission.ACCESS_FINE_LOCATION");
        }

        if (flag == 1) {
            listPermissionsNeeded.add("android.permission.READ_PHONE_STATE");
            listPermissionsNeeded.add("android.permission.CAMERA");
            listPermissionsNeeded.add("android.permission.READ_EXTERNAL_STORAGE");
            listPermissionsNeeded.add("android.permission.WRITE_EXTERNAL_STORAGE");
            listPermissionsNeeded.add("android.permission.RECORD_AUDIO");
            listPermissionsNeeded.add("android.permission.ACCESS_FINE_LOCATION");
        }


        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(context, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //check and request the PERMISSIONS
        checkAndRequestPermissions(0, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        Log.e(TAG, "----Permission callback called-----");

        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS:

                Map<String, Integer> perms = new HashMap<>();
                // Initialize the map with both permissions
                perms.put("android.permission.READ_PHONE_STATE", PackageManager.PERMISSION_GRANTED);
                perms.put("android.permission.CAMERA", PackageManager.PERMISSION_GRANTED);
                perms.put("android.permission.READ_EXTERNAL_STORAGE", PackageManager.PERMISSION_GRANTED);
                perms.put("android.permission.WRITE_EXTERNAL_STORAGE", PackageManager.PERMISSION_GRANTED);
                perms.put("android.permission.ACCESS_FINE_LOCATION", PackageManager.PERMISSION_GRANTED);
                perms.put("android.permission.RECORD_AUDIO", PackageManager.PERMISSION_GRANTED);
                // Fill with actual results from user
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++)
                        perms.put(permissions[i], grantResults[i]);
                    // Check for all permissions
                    if (perms.get("android.permission.READ_PHONE_STATE") == PackageManager.PERMISSION_GRANTED
                            && perms.get("android.permission.CAMERA") == PackageManager.PERMISSION_GRANTED
                            && perms.get("android.permission.READ_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED
                            && perms.get("android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED
                            && perms.get("android.permission.ACCESS_FINE_LOCATION") == PackageManager.PERMISSION_GRANTED
                            && perms.get("android.permission.RECORD_AUDIO") == PackageManager.PERMISSION_GRANTED
                            ) {
                        Log.e("TAG", "All s ervices permission granted");
                        // process the normal flow
                        //else any one or both the permissions are not granted
                    } else {
                        Log.e("TAG", "Some permissions are not granted ask again ");
                        // permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
//                      // shouldShowRequestPermissionRationale will return true
                        //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_PHONE_STATE")
                                || ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA")
                                || ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_EXTERNAL_STORAGE")
                                || ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")
                                || ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.ACCESS_FINE_LOCATION")
                                || ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.ACCESS_RECORD_AUDIO")
                                ) {


                            String pString = "";

                            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_PHONE_STATE")) {
                                pString = pString + " READ_PHONE_STATE, ";
                            }
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA")) {
                                pString = pString + " CAMERA, ";
                            }
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_EXTERNAL_STORAGE") || ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                                pString = pString + " Storage, ";
                            }
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.ACCESS_FINE_LOCATION")) {
                                pString = pString + " Location, ";
                            }
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.RECORD_AUDIO")) {
                                pString = pString + " Audio, ";
                            }

                            if (pString.toString().length() > 0) {
                                pString = pString.substring(0, pString.length() - 2);
                                pString = pString + "";
                            }


                            showDialogOK(pString + " Permission required for this app to work..",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    checkAndRequestPermissions(0, MainActivity.this);
                                                    break;
                                                case DialogInterface.BUTTON_NEGATIVE:
                                                    // proceed with logic by disabling the related features or quit the app.
                                                    finish();
                                                    break;
                                            }
                                        }
                                    });
                        }
                        //permission is denied (and never ask again is  checked)
                        //shouldShowRequestPermissionRationale will return false
                        else {

                            Toast.makeText(this, "Go to settings and enable permissions", Toast.LENGTH_SHORT).show();
                            //proceed with logic by disabling the related features or quit the app.
                        }
                    }
                }
        }
    }

    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("Allow", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }

}
