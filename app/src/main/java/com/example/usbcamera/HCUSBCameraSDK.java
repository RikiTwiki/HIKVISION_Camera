package com.example.usbcamera;

import com.sun.jna.Native;

public enum HCUSBCameraSDK 
{	
	CLASS;
	private static HCUSBCameraSDKByJNA usbSdk = null;
	/**
	 * get the instance of USBCameraSDK
	 * @return the instance of USBCameraSDK
	 */
	public static HCUSBCameraSDKByJNA getInstance()
	{
		if (null == usbSdk)
		{
			synchronized (HCUSBCameraSDKByJNA.class)
			{
				usbSdk = (HCUSBCameraSDKByJNA) Native.loadLibrary("HCUSBCamera",
						HCUSBCameraSDKByJNA.class);
			}			
		}
		return usbSdk;
	}	
}
