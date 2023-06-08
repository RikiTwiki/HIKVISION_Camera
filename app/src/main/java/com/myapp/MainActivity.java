package com.myapp;

import com.example.usbcamera.HCUSBCameraSDK;
import com.example.usbcamera.HCUSBCameraSDKByJNA;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TextView temperatureTextView;
    private CameraController cameraController;

    private static final String ACTION_USB_PERMISSION = "com.myapp.USB_PERMISSION";

    private Context ContextofMainAcitivity;
    public IntentFilter USBconnectdisconnect_intentFilter;
    public Intent GetEXTRA_PERMISSION_GRANTEDIntent;
    public PendingIntent current_usbpermissionIntent;
    public UsbDevice current_usbdevice;
    public UsbManager current_usbManager;
    private static final String TAG = "usbtestapp";
    static final int intentFlags = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) ? PendingIntent.FLAG_IMMUTABLE : 0;
    private UsbManager usbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContextofMainAcitivity = this;

        usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
        USBconnectdisconnect_intentFilter = new IntentFilter();
        USBconnectdisconnect_intentFilter.addAction(ACTION_USB_PERMISSION);
        USBconnectdisconnect_intentFilter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        USBconnectdisconnect_intentFilter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        registerReceiver(usbReceiver, USBconnectdisconnect_intentFilter);
        GetEXTRA_PERMISSION_GRANTEDIntent = new Intent(ACTION_USB_PERMISSION);
        GetEXTRA_PERMISSION_GRANTEDIntent.putExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false);
        Log.v(TAG, "Waiting for USB devices...");
        Toast.makeText(this, "Waiting for USB devices...", Toast.LENGTH_LONG).show();

        HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
        PendingIntent permissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), intentFlags);

        for(UsbDevice usbDevice: deviceList.values()){
            if (usbDevice.getVendorId() == 11231 && usbDevice.getProductId() == 257) {
                Toast.makeText(this, "success device temperature", Toast.LENGTH_LONG).show();
                usbManager.requestPermission(usbDevice, permissionIntent);
            } else if (usbDevice.getVendorId() == 11231 && usbDevice.getProductId() == 5) {
//                Toast.makeText(this, "success usb web cam", Toast.LENGTH_LONG).show();
            } else {
//                Toast.makeText(this, "don't see", Toast.LENGTH_LONG).show();
            }

        }


        temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
    }



    private final BroadcastReceiver usbReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(context, "done", Toast.LENGTH_LONG).show();
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false) == false) {
                        if(device != null){
                            boolean openSuccess = cameraController.openDeviceByVIDAndPID(11231, 257);
                            int indexDevice = cameraController.getIndex();
                            int indexDev = cameraController.openDevice(indexDevice);
                            if (indexDev == 0 || indexDev == 1 || indexDev == 2 || indexDev == 3) {
                                Toast.makeText(context, "Success openSuccess!", Toast.LENGTH_LONG).show();
                                displayMaxTemperatureInRegion();

                            } else {
                                Toast.makeText(context, "Failed to open the device", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            float temp = cameraController.getMaxTemperatureInRegion();
                            Toast.makeText(context, "permission done" + temp, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "Permission denied for the device", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    };







    public void displayMaxTemperatureInRegion() {
        float maxTemperature = cameraController.getMaxTemperatureInRegion();

        if (maxTemperature != -1) {
            temperatureTextView.setText("The maximum temperature in the region is: " + maxTemperature + " degrees.");
        } else {
            temperatureTextView.setText("Failed to get the temperature from the device.");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(usbReceiver);
    }
}
