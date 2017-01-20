package exp.rusan.musicplayer.Util;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description: PermissionUtils can be used to manager permissions in device with Android 6.0+
 * (API >= 23).
 * <!--
 * Author: Rusan
 * Date: 2017/1/18
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class PermissionUtils {

    private static final String TAG = PermissionUtils.class.getSimpleName();

    public static final int CODE_RECORD_AUDIO = 0;
    public static final int CODE_GET_ACCOUNTS = 1;
    public static final int CODE_READ_PHONE_STATE = 2;
    public static final int CODE_CALL_PHONE = 3;
    public static final int CODE_CAMERA = 4;
    public static final int CODE_ACCESS_FINE_LOCATION = 5;
    public static final int CODE_ACCESS_COARSE_LOCATION = 6;
    public static final int CODE_READ_EXTERNAL_STORAGE = 7;
    public static final int CODE_WRITE_EXTERNAL_STORAGE = 8;
    public static final int CODE_MULTI_PERMISSION = 100;

    public static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    public static final String PERMISSION_GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;
    public static final String PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String PERMISSION_CALL_PHONE = Manifest.permission.CALL_PHONE;
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    public static final String PERMISSION_ACCESS_FINE_LOCATION = Manifest.permission
            .ACCESS_FINE_LOCATION;
    public static final String PERMISSION_ACCESS_COARSE_LOCATION = Manifest.permission
            .ACCESS_COARSE_LOCATION;
    public static final String PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission
            .READ_EXTERNAL_STORAGE;
    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission
            .WRITE_EXTERNAL_STORAGE;

    private static final String[] requestPermissions = {
            PERMISSION_RECORD_AUDIO,
            PERMISSION_GET_ACCOUNTS,
            PERMISSION_READ_PHONE_STATE,
            PERMISSION_CALL_PHONE,
            PERMISSION_CAMERA,
            PERMISSION_ACCESS_FINE_LOCATION,
            PERMISSION_ACCESS_COARSE_LOCATION,
            PERMISSION_READ_EXTERNAL_STORAGE,
            PERMISSION_WRITE_EXTERNAL_STORAGE
    };


    public interface PermissionGrant {
        void onPermissionGranted(int requestCode);
    }


    /**
     * Requests Permission.
     *
     * @param pActivity The target activity
     * @param pRequestCode    Request code is PermissionUtils.class e.g. PermissionUtils.CODE_CAMERA
     * @param pPermissionGrant The parameter to callback and return the permission request code
     *                        which is granted
     *
     */
    public static void requestPermission(Activity pActivity, int pRequestCode, PermissionGrant
            pPermissionGrant) {

        if (pActivity == null) {
            Log.i(TAG, "requestPermission: activity == null");
            return;
        }

        if (pRequestCode < 0 || pRequestCode >= requestPermissions.length) {
            Log.i(TAG, "requestPermission: request code illegal " + pRequestCode);
            return;
        }

        if (pPermissionGrant == null) {
            return;
        }

        String requestPermission = requestPermissions[pRequestCode];

        int checkSelfPermission;

        checkSelfPermission = ActivityCompat.checkSelfPermission(pActivity, requestPermission);

        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "requestPermission: " + requestPermission + " : is not granted.");

                ActivityCompat.requestPermissions(pActivity, new String[]{requestPermission},
                        pRequestCode);

        } else {
            Log.i(TAG, "requestPermission: CheckSelfPermission is granted.");
            pPermissionGrant.onPermissionGranted(pRequestCode);
        }
    }


    /**
     * Requests Mutilple Permissions.
     *
     * @param pActivity The target activity
     * @param pRequestCodes    An array containing permission request codes e.g. PermissionUtils
     *                         .class e.g. PermissionUtils.CODE_CAMERA
     * @param pPermissionGrant The parameter to callback and return the permission request code
     *                        which is granted
     *
     */
    public static void requestMultiPermissions(Activity pActivity, int[] pRequestCodes,
                                               PermissionGrant pPermissionGrant) {

        List<String> permissionsNoGranted = new ArrayList<>();

        String requestPermission;

        if (pActivity == null) {
            return;
        }

        if (pRequestCodes.length == 0 || pRequestCodes == null) {
            return;
        }

        if (pPermissionGrant == null) {
            return;
        }

        for (int i = 0; i < pRequestCodes.length; i++) {

            requestPermission = requestPermissions[pRequestCodes[i]];

            int checkSelfPermission = ActivityCompat.checkSelfPermission(pActivity,
                    requestPermission);

            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
                permissionsNoGranted.add(requestPermission);
            } else {
                Log.i(TAG, "requestMultiPermissions: " + requestPermission + " has granted!");
                pPermissionGrant.onPermissionGranted(pRequestCodes[i]);
            }
        }

        if (!permissionsNoGranted.isEmpty()) {
            ActivityCompat.requestPermissions(pActivity, permissionsNoGranted.toArray(new
                    String[permissionsNoGranted.size()]), CODE_MULTI_PERMISSION);
        }

    }

    private static void showRationale(final Activity pActivity, final String pPremission, final int pRequestCode) {

        showMessageDialog(pActivity, pPremission, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions(pActivity, new String[]{pPremission}, pRequestCode);
                Log.i(TAG, "onClick: show rationale and request the permission again : " + pPremission);
            }
        });
    }


    /**
     * Callback for requesting permissions. This method should be invoked in
     * onRequestPermissionsResult(int, String[], int[])}.
     *
     * @param pActivity The targer activity
     * @param pRequestCode Request code is PermissionUtils.class e.g. PermissionUtils
     *                     .CODE_CAMERA. If request multiple permissions, the pRequestCode should
     *                     be PermissionUtils.CODE_MULTI_PERMISSION.
     * @param pPermissions Permission requesting
     * @param pGrantResult The grant results for the corresponding permissions
     *     which is either {@link android.content.pm.PackageManager#PERMISSION_GRANTED}
     *     or {@link android.content.pm.PackageManager#PERMISSION_DENIED}. Never null.
     * @param pPermissionGrant The parameter to callback and return the permission request code
     *                        which is granted
     */
    public static void requestPermissionsResult(Activity pActivity, int pRequestCode, String[]
            pPermissions, int[] pGrantResult, PermissionGrant pPermissionGrant) {

        if (pActivity == null) {
            return;
        }

        if (pRequestCode < 0) {
            return;
        }

        if (pRequestCode == CODE_MULTI_PERMISSION) {
            Log.i(TAG, "requestPermissionsResult: Jump to requestMultiPermissionsResult");
            requestMultiPermissionsResult(pActivity, pRequestCode, pPermissions, pGrantResult,
                    pPermissionGrant);
            return;
        }


        if (pGrantResult.length == 1 && pGrantResult[0] == PackageManager.PERMISSION_GRANTED) {
            pPermissionGrant.onPermissionGranted(pRequestCode);
        } else {
            openSettingActivity(pActivity, pRequestCode);
        }

    }

    private static void requestMultiPermissionsResult(Activity pActivity, int pRequestCode,
                                                      String[] pPermissions, int[] pGrantResult,
                                                      PermissionGrant pPermissionGrant) {

        List<String> permissions = Arrays.asList(requestPermissions);

        List<Integer> permissionsNoGranted = new ArrayList<>();

        int count = 0;

        boolean isShowSettingActivity = false;

        if (pRequestCode != CODE_MULTI_PERMISSION) {
            Log.i(TAG, "requestMultiPermissionsResult: The request code is not " +
                    "CODE_MULTI_PERMISSION, it is " + pRequestCode);
        } else {

            for (int i = 0; i < pGrantResult.length; i++) {

                if (pGrantResult[i] == PackageManager.PERMISSION_GRANTED) {
                    pPermissionGrant.onPermissionGranted(permissions.indexOf(pPermissions[i]));
                } else {
                    permissionsNoGranted.add(permissions.indexOf(pPermissions[i]));
                }

            }

        }

        if (permissionsNoGranted.size() == 0) {
            return;
        } else if (permissionsNoGranted.size() == 1) {
            openSettingActivity(pActivity, permissionsNoGranted.get(0));
        } else {
            openSettingActivity(pActivity, pRequestCode);
        }

    }


    private static void openSettingActivity(final Activity pActivity, int pRequestCode) {

        String msg = new String();

        if (pRequestCode == CODE_MULTI_PERMISSION) {
            msg = "multiple permissions";
        } else {
            msg = requestPermissions[pRequestCode];
        }

        showMessageDialog(pActivity, msg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", pActivity.getPackageName(), null);
                intent.setData(uri);
                pActivity.startActivity(intent);
            }
        });
    }


    private static void showMessageDialog(Activity pActivity, String Msg, DialogInterface
            .OnClickListener pOkListener) {
        new AlertDialog.Builder(pActivity).setMessage("Please grant the permission(s) (" + Msg +
                ") for this app, " + "or may bring on some errors.")
                .setPositiveButton
                ("OK",
                pOkListener)
                .setCancelable(false)
                .create()
                .show();
    }

}
