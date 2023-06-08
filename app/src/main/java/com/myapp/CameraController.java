package com.myapp;

import com.example.usbcamera.HCUSBCameraSDK;
import com.example.usbcamera.HCUSBCameraSDKByJNA;
import com.sun.jna.Memory;
import com.sun.jna.Pointer;

public class CameraController {

    private HCUSBCameraSDKByJNA cameraSdk = HCUSBCameraSDK.getInstance();
    private int deviceHandle = -1;

    public CameraController() {
        boolean initSuccess = cameraSdk.HC_USBCamera_Init();
        if (!initSuccess) {
            throw new RuntimeException("Failed to initialize the camera SDK");
        }
    }

    public boolean openDeviceByVIDAndPID(int vid, int pid) {
        // Assuming that each device has a unique combination of vendor ID and product ID
        Pointer pDevInfoList = new Memory(256); // Assuming that the total size of all devices' info is less than 256 bytes
        int deviceCount = cameraSdk.HC_USBCamera_EnumDevice(pDevInfoList, 256, null);

        for (int i = 0; i < deviceCount; i++) {
            // Assuming that each device's info is stored in a consecutive block of memory and is 16 bytes in size
            Pointer deviceInfoPointer = pDevInfoList.share(i * 16);

            // Assuming that the first 4 bytes is the vendor ID and the next 4 bytes is the product ID
            int deviceVid = deviceInfoPointer.getInt(0);
            int devicePid = deviceInfoPointer.getInt(5);

            if (deviceVid == vid && devicePid == pid) {
                this.deviceHandle = cameraSdk.HC_USBCamera_Open(i);
                return true;
            }
        }
        return false;
    }


    public float getMaxTemperatureInRegion() {
        final int TEMPERATURE_COMMAND = HCUSBCameraSDKByJNA.USBCAMERA_GET_THERMAL_TEMPERATURE_CORRECT;
        final int FLOAT_SIZE = 4; // size of float in bytes

        Memory temperatureMemory = new Memory(FLOAT_SIZE);

        boolean success = getConfig(TEMPERATURE_COMMAND, temperatureMemory, FLOAT_SIZE);
        if (!success) {
            System.err.println("Failed to get temperature. Error: " + getLastError());
            return -1; // or throw an exception
        }

        float temperature = temperatureMemory.getFloat(0);

        return temperature;
    }


    public boolean initialize() {
        return cameraSdk.HC_USBCamera_Init();
    }

    public int getSdkVersion() {
        return cameraSdk.HC_USBCamera_GetSDKVersion();
    }

    // This is a rough implementation, you'll need to handle the Pointers correctly in your actual code.
    public int enumerateDevices(Pointer pDevInfoList, int size, Pointer pParam) {
        return cameraSdk.HC_USBCamera_EnumDevice(pDevInfoList, size, pParam);
    }

    public boolean setLogToFile(int dwLogLevel, String pLogDir, int bAutoDel) {
        return cameraSdk.HC_USBCamera_SetLogToFile(dwLogLevel, pLogDir, bAutoDel);
    }

    public int openDevice(int iDevIndex) {
        this.deviceHandle = cameraSdk.HC_USBCamera_Open(iDevIndex);
        return deviceHandle;
    }

    public boolean closeDevice() {
        return cameraSdk.HC_USBCamera_Close(this.deviceHandle);
    }

    public boolean getConfig(int dwCommand, Pointer lpConfig, int dwConfigSize) {
        return cameraSdk.HC_USBCamera_GetConfig(this.deviceHandle, dwCommand, lpConfig, dwConfigSize);
    }

    public boolean setConfig(int dwCommand, Pointer lpConfig, int dwConfigSize) {
        return cameraSdk.HC_USBCamera_SetConfig(this.deviceHandle, dwCommand, lpConfig, dwConfigSize);
    }

    public int startPreview(Pointer pStreamCBParam) {
        return cameraSdk.HC_USBCamera_StartPreview(this.deviceHandle, pStreamCBParam);
    }

    public boolean stopPreview(int nChannel) {
        return cameraSdk.HC_USBCamera_StopPreview(this.deviceHandle, nChannel);
    }

    public boolean captureImage(Pointer pCaptuerParam) {
        return cameraSdk.HC_USBCamera_Capture(this.deviceHandle, pCaptuerParam);
    }

    public int getLastError() {
        return cameraSdk.HC_USBCamera_GetLastError();
    }

    public int getIndex(){
        int index = deviceHandle;
        return index;
    }


    // Add more methods here to perform operations on the camera
}
