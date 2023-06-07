//================================================================================================================================
//
// Copyright (c) 2015-2022 VisionStar Information Technology (Shanghai) Co., Ltd. All Rights Reserved.
// EasyAR is the registered trademark or trademark of VisionStar Information Technology (Shanghai) Co., Ltd in China
// and other countries for the augmented reality technology developed by VisionStar Information Technology (Shanghai) Co., Ltd.
//
//================================================================================================================================

package cn.easyar.samples.helloarmotiontracking;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

import cn.easyar.CalibrationDownloadStatus;
import cn.easyar.CalibrationDownloader;
import cn.easyar.CameraDevice;
import cn.easyar.Engine;
import cn.easyar.FunctorOfVoidFromCalibrationDownloadStatusAndOptionalOfString;
import cn.easyar.ImmediateCallbackScheduler;
import cn.easyar.MotionTrackerCameraDevice;

public class ARActivity extends Activity
{
    /*
    * Steps to create the key for this sample:
    *  1. login www.easyar.com
    *  2. create app with
    *      Name: HelloARMotionTracking
    *      Package Name: cn.easyar.samples.helloarmotiontracking
    *  3. find the created item in the list and show key
    *  4. set key string bellow
    */
    private static String key = "XuQs+1r3NOdCkf1jvAV4klFI+E+dp5TtWIEEgG7WGtBaxhzNbssLgCGHTpErk0aXL5VOl1vUDox4yhKAN4cSw2jRGtBQwAbrf4dFkzeHE8t4wBHRftZdmEDeXcBuyxvOfuwb0TmfJP83hwnDacwezG/WXZhAhxzNdsgKzHLRBoBGiV3Sd8QLxHTXEtE5nySAbMwRxnTSDIA3hxLDeIcijjnIEMZuyRrROZ8kgGjAEdF+izbPesIa9mnEHMlyyxiAN4cMx3XWGoxYyRDXf/cawXTCEctvzBDMOYld0X7LDMc19xrBdNcby3XCXY451hrMaMBR7XnPGsFv8Q3DeM4WzHyHU4BowBHRfoss12nDHsF+8Q3DeM4WzHyHU4BowBHRfoss0nrXDMdI1R7WcsQT73rVXY451hrMaMBR73TRFs118Q3DeM4WzHyHU4BowBHRfos7x3XWGvFrxAvLeskyw2uHU4BowBHRfos841/xDcN4zhbMfIcijjnAB9Jy1xr2csga8W/EEtI5nxHXd8lTgHLWM814xBOAIcMezmjAAo5ghx3XdcETx1LBDIAh/l3BdYsaw2jcHtA11h7Pa8ka0TXNGs53yh7QdsoLy3TLC9B6xhTLdcJd/zeHCcNpzB7Mb9ZdmECHHM12yArMctEGgEaJXdJ3xAvEdNcS0TmfJIB6yxvQdMwbgEaJXc90wQrOftZdmECHDMd11hqMUsgexX7xDcN4zhbMfIdTgGjAEdF+izzOdNAb8H7GEMV1zAvLdMtdjjnWGsxowFHwfsYQ0H/MEcU5iV3RfssMxzXqHch+xgv2acQcyXLLGIA3hwzHddYajEjQDcR6xhr2acQcyXLLGIA3hwzHddYajEjVHtBowCzSetEWw3foHtI5iV3RfssMxzXoENZyyhH2acQcyXLLGIA3hwzHddYajF/AEdF+9g/Db8wezlbED4A3hwzHddYajFjkO/ZpxBzJcssYgEaJXcdj1RbQfvEWz372C8N21V2YddATzjeHFtFXyhzDd4dFxHrJDMdmiQSAedARxnfANsZoh0X5OYcijjnTHtByxBHWaIdF+TnGEM920BHLb9xd/zeHD8560RnNacgMgCH+Xct01l3/N4cSzX/QE8doh0X5OdYazGjAUet2xBjHT9cewXDMEcU5iV3RfssMxzXmE81uwS3HeMoYzHLRFs11h1OAaMAR0X6LLcd4yg3GcssYgDeHDMd11hqMVMcVx3jRK9B6xhTLdcJdjjnWGsxowFHxbtcZw3jAK9B6xhTLdcJdjjnWGsxowFHxa8QN0X72D8NvzB7OVsQPgDeHDMd11hqMVsoLy3TLK9B6xhTLdcJdjjnWGsxowFHmfssMx0jVHtZyxBPvetVdjjnWGsxowFHhWuEr0HrGFMt1wl3/N4ca2mvMDcdPzBLHSNEez2uHRcxuyROOOcwM7nTGHs45nxnDd9Ya30bYAtQkGkvVscp0rjc7qywDnjPchz6JfnimZ7oo4KNtqU0jCwU5CZVxwbvzMA3RTbeE4qxnxPEesY7yUfkY77rkx0r9pv7GxciDfK7pQcNH7dzHn4Q66wn+xH8yT+87Nk5oAMP2w3OX2b6Tj+Tiu8ItzZCJ/MUc/p9cFZW65wFtHTSgjtQG6cO5zxdlXBEh3rJ3UxylZWXHKzXFgQ2Mq3v78T9i5KUQUiLPRCsaMXomHFKDobc+NQadHBHYniV75eNmmtJvirrFLAf4/mhWteOlgeGEA+Jqa8qP4WBUl3eqtDcThzSYLBdrbJxNDM0PAB/dG4ApxA5toD7+arVjG6V/og==";
    private GLView glView;
    private CalibrationDownloader downloader;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (!Engine.initialize(this, key)) {
            Log.e("HelloAR", "Initialization Failed.");
            Toast.makeText(ARActivity.this, Engine.errorMessage(), Toast.LENGTH_LONG).show();
            return;
        }

        if (!CameraDevice.isAvailable()) {
            Toast.makeText(ARActivity.this, "CameraDevice not available.", Toast.LENGTH_LONG).show();
            return;
        }

        glView = new GLView(ARActivity.this);

        downloader = new CalibrationDownloader();
        downloader.download(10000, ImmediateCallbackScheduler.getDefault(), new FunctorOfVoidFromCalibrationDownloadStatusAndOptionalOfString() {
            @Override
            public void invoke(int status, String error) {
                //following code runs on a non-GUI thread
                if (status == CalibrationDownloadStatus.Successful) {
                    Log.i("HelloAR", "Calibration file download successful.");
                } else if (status == CalibrationDownloadStatus.NotModified) {
                    Log.i("HelloAR", "Calibration file is latest.");
                } else if (status == CalibrationDownloadStatus.ConnectionError) {
                    Log.i("HelloAR", "Calibration file download connection error: " + error);
                } else if (status == CalibrationDownloadStatus.UnexpectedError) {
                    Log.i("HelloAR", "Calibration file download unexpected error: " + error);
                } else {
                    Log.i("HelloAR", "Calibration file download failed.");
                }
                ARActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (downloader != null) {
                            downloader.dispose();
                            downloader = null;
                        }

                        if (isFinishing()) {
                            return;
                        }

                        if (!MotionTrackerCameraDevice.isAvailable()) {
                            Toast.makeText(ARActivity.this, "MotionTrackerCameraDevice not available.", Toast.LENGTH_LONG).show();
                            return;
                        }

                        requestCameraPermission(new PermissionCallback() {
                            @Override
                            public void onSuccess() {
                                ViewGroup preview = ((ViewGroup) findViewById(R.id.preview));
                                preview.addView(glView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                            }

                            @Override
                            public void onFailure() {
                            }
                        });
                    }
                });
            }
        });
    }

    private interface PermissionCallback
    {
        void onSuccess();
        void onFailure();
    }
    private HashMap<Integer, PermissionCallback> permissionCallbacks = new HashMap<Integer, PermissionCallback>();
    private int permissionRequestCodeSerial = 0;
    @TargetApi(23)
    private void requestCameraPermission(PermissionCallback callback)
    {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                int requestCode = permissionRequestCodeSerial;
                permissionRequestCodeSerial += 1;
                permissionCallbacks.put(requestCode, callback);
                requestPermissions(new String[]{Manifest.permission.CAMERA}, requestCode);
            } else {
                callback.onSuccess();
            }
        } else {
            callback.onSuccess();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if (permissionCallbacks.containsKey(requestCode)) {
            PermissionCallback callback = permissionCallbacks.get(requestCode);
            permissionCallbacks.remove(requestCode);
            boolean executed = false;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    executed = true;
                    callback.onFailure();
                }
            }
            if (!executed) {
                callback.onSuccess();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (glView != null) { glView.onResume(); }
    }

    @Override
    protected void onPause()
    {
        if (glView != null) { glView.onPause(); }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (downloader != null) {
            downloader.dispose();
            downloader = null;
        }
    }
}
