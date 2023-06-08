package com.example.usbcamera;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Structure;
import com.sun.jna.Pointer;

public interface HCUSBCameraSDKByJNA extends Library {
	
	public static final int BYTE_ARRAY_LEN = 1024;
	public static final int MAX_ROI_REGIONS = 10;
	public static final int THERMAL_MAX_REGIONS = 10;
	
	public static final int USBCAMERA_STREAM_MJPEG = 1;
	public static final int USBCAMERA_STREAM_YUV = 2;
	public static final int USBCAMERA_STREAM_H264 = 3;
	
	
	public static final int USBCAMERA_GET_VIDEO_CAP = 1;
	public static final int USBCAMERA_GET_AUDIO_CAP = 2;

	public static final int USBCAMERA_GET_VIDEO_FORMAT = 3;
	public static final int USBCAMERA_SET_VIDEO_FORMAT = 4;

	public static final int USBCAMERA_GET_AUDIO_FORMAT = 5;
	public static final int USBCAMERA_SET_AUDIO_FORMAT = 6;

	public static final int USBCAMERA_GET_RESOLUTION = 7;
	public static final int USBCAMERA_SET_RESOLUTION = 8;

	public static final int USBCAMERA_GET_FRAMERATE = 9;
	public static final int USBCAMERA_SET_FRAMERATE = 10;
	
	public static final int USBCAMERA_GET_SYSTEM_DEVICE_INFO = 11;
	public static final int USBCAMERA_SET_SYSTEM_REBOOT = 12;
	public static final int USBCAMERA_SET_SYSTEM_RESET = 13;
	public static final int USBCAMERA_GET_SYSTEM_HARDWARE_SERVER = 14;
	public static final int USBCAMERA_SET_SYSTEM_HARDWARE_SERVER = 15;
	public static final int USBCAMERA_GET_SYSTEM_LOCALTIME = 16;
	public static final int USBCAMERA_SET_SYSTEM_LOCALTIME = 17;
	public static final int USBCAMERA_GET_IMAGE_BRIGHTNESS = 18;
	public static final int USBCAMERA_SET_IMAGE_BRIGHTNESS = 19;
	public static final int USBCAMERA_GET_IMAGE_CONTRAST = 20;
	public static final int USBCAMERA_SET_IMAGE_CONTRAST = 21;
	public static final int USBCAMERA_SET_SYSTEM_UPDATE_FIRMWARE = 22; //����
	public static final int USBCAMERA_SET_IMAGE_BACKGROUND_CORRECT = 23;
	//public static final int USBCAMERA_GET_IMAGE_MANUAL_CORRECT = 24;
	public static final int USBCAMERA_SET_IMAGE_MANUAL_CORRECT = 25;
	public static final int USBCAMERA_GET_IMAGE_ENHANCEMENT = 26;
	public static final int USBCAMERA_SET_IMAGE_ENHANCEMENT = 27;
	public static final int USBCAMERA_GET_IMAGE_VIDEO_ADJUST = 28;
	public static final int USBCAMERA_SET_IMAGE_VIDEO_ADJUST = 29;
	public static final int USBCAMERA_GET_THERMAL_THERMOMETRY_BASIC_PARAM = 30;
	public static final int USBCAMERA_SET_THERMAL_THERMOMETRY_BASIC_PARAM = 31;
	public static final int USBCAMERA_GET_THERMAL_THERMOMETRY_MODE = 32;
	public static final int USBCAMERA_SET_THERMAL_THERMOMETRY_MODE = 33;
	public static final int USBCAMERA_GET_THERMAL_THERMOMETRY_REGIONS = 34;
	public static final int USBCAMERA_SET_THERMAL_THERMOMETRY_REGIONS = 35;
	public static final int USBCAMERA_GET_THERMAL_ALG_VERSION = 36;
	//public static final int USBCAMERA_SET_THERMAL_ALG_VERSION = 37;
	public static final int USBCAMERA_GET_THERMAL_STREAM_PARAM = 38;
	public static final int USBCAMERA_SET_THERMAL_STREAM_PARAM = 39;
	public static final int USBCAMERA_GET_THERMAL_TEMPERATURE_CORRECT = 40;
	public static final int USBCAMERA_SET_THERMAL_TEMPERATURE_CORRECT = 41;
	public static final int USBCAMERA_GET_THERMAL_BLACK_BODY = 42;
	public static final int USBCAMERA_SET_THERMAL_BLACK_BODY = 43;
	public static final int USBCAMERA_GET_THERMAL_BODYTEMP_COMPENSATION = 44;
	public static final int USBCAMERA_SET_THERMAL_BODYTEMP_COMPENSATION = 45;
	public static final int USBCAMERA_GET_THERMAL_JPEGPIC_WITH_APPENDDATA = 46;
	public static final int USBCAMERA_SET_THERMAL_ROI_MAX_TEMPERATURE_SEARCH = 47;
	public static final int USBCAMERA_GET_THERMAL_P2P_PARAM = 48;
	public static final int USBCAMERA_SET_THERMAL_P2P_PARAM = 49;
	//public static final int USBCAMERA_GET_THERMAL_DOUBLE_LIGHTS_CORRECT = 50;
	public static final int USBCAMERA_SET_THERMAL_DOUBLE_LIGHTS_CORRECT = 51;
	public static final int USBCAMERA_GET_THERMAL_DOUBLE_LIGHTS_CORRECT_POINTS_CTRL = 52;
	public static final int USBCAMERA_SET_THERMAL_DOUBLE_LIGHTS_CORRECT_POINTS_CTRL = 53;

	
	public interface FStreamCallBack extends Callback {
		public void invoke(int handle, Pointer pFrameInfo, Pointer pUser);
	}
	
	public static class USB_CAMERA_FRAME_INFO extends Structure{
		public int		nStamp;
		public int 		dwStreamType;
		public int 		dwWidth;
		public int		dwHeight;
		public int		dwFrameRate;
		public int		nFrameNum;
		public Pointer  pBuf;
		public int		dwBufSize;
		public byte[]	byRes = new byte[32];
		
		public USB_CAMERA_FRAME_INFO(Pointer p)
		{
			super(p);
		}
		
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("nStamp", "dwStreamType", "dwWidth", "dwHeight","dwFrameRate"
					, "nFrameNum", "pBuf", "dwBufSize", "byRes");
		}
	}
	
	public static class USB_CAMERA_ENUM_DEV_PARAM extends Structure{
		public byte    byType;
		public byte[]	byRes = new byte[127];
		
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byType", "byRes");
		}
	}
	
	public static class USB_CAMERA_VIDEO_FORMAT extends Structure{
		public int		dwStreamType;
		public int 		dwWidth;
		public int 		dwHeight;
		public int		dwFrameRate;
		public byte[]	byRes = new byte[16];
		
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("dwStreamType", "dwWidth", "dwHeight", "dwFrameRate","byRes");
		}
	}
	
	public static class USB_CAMERA_STREAM_CALLBACK_PARAM extends Structure{
		public int		dwSize;
		public int 		dwStreamType;
		public int 		nChannel;
		public FStreamCallBack		fnStreamCallBack;
		public Pointer	pUser;
		public byte[]	byRes = new byte[20];
		
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("dwSize", "dwStreamType", "nChannel", "fnStreamCallBack","pUser"
					, "byRes");
		}
	}
	
	public static class USB_CAMERA_CAPTURE_PARAM extends Structure{
		public int		dwSize;
		public int 		dwStreamType;
		public Pointer  pBuf;
		public int 		dwBufSize;
		public int 		dwDataLen;
		public byte[]	szFilePath = new byte[256];
		public byte[]	byRes = new byte[32];
		
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("dwSize", "dwStreamType", "pBuf", "dwBufSize", "dwDataLen", "szFilePath"
					, "byRes");
		}
	}
	
	public static class USB_CAMERA_VIDEO_CAP extends Structure{
		
		public int		dwVideoFormatCount;
		
		public USB_CAMERA_VIDEO_FORMAT[] struVideoFormat = (USB_CAMERA_VIDEO_FORMAT[])new USB_CAMERA_VIDEO_FORMAT().toArray(128);
		public byte[]	byRes = new byte[20];
		
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("dwVideoFormatCount", "struVideoFormat","byRes");
		}
	}
	
	public static class USB_CAMERA_CONFIG extends Structure{
		
		public Pointer		pCondBuf;
		public int			dwCondSize;
		public Pointer		pInBuf;
		public int 			dwInSize;
		public Pointer		pOutBuf;
		public int 			dwOutSize;
		public byte[]	byRes = new byte[40];
		
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("pCondBuf", "dwCondSize", "pInBuf", "dwInSize"
					, "pOutBuf", "dwOutSize", "byRes");
		}
	}
	
	
	public static class BYTE_ARRAY extends Structure {
		public byte[] byValue;

		public BYTE_ARRAY(int iLen) {
			byValue = new byte[iLen];
		}

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("byValue");
		}
	}
	
	//Уʱ
	public static class USB_CAMERA_SYSTEM_LOCALTIME extends Structure{
		public short wMillisecond;	//����
		public byte bySecond;	 //��
		public byte byMinute;	 //����
		public byte byHour;	  //Сʱ
		public byte byDay;   //��
		public byte byMonth;   //��
		public byte[] byRes1 = new byte[1];
		public short wYear;   //��
		public byte byExternalTimeSourceEnabled;  //�ⲿУʱԴʹ��, 0-�رգ�1-����
		public byte[] byRes = new byte[6];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("wMillisecond", "bySecond", "byMinute", "byHour"
					, "byDay", "byMonth", "byRes1","wYear","byExternalTimeSourceEnabled","byRes");
		}
	}
	
	//�豸ϵͳ��Ϣ	
	public static class USB_CAMERA_SYSTEM_DEVICE_INFO extends Structure{
		public byte[] byFirmwareVersion = new byte[64];	//���س���汾
		public byte[] byEncoderVersion = new byte[64];	//����汾
		public byte[] byHardwareVersion = new byte[64];	//��о�汾
		public byte[] byDeviceType = new byte[64];	//�豸�ͺ�
		public byte[] byProtocolVersion = new byte[4];	//Э��汾��Ϣ��1.0
		public byte[] byRes = new byte[252];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byFirmwareVersion", "byEncoderVersion", "byHardwareVersion", "byDeviceType"
					, "byProtocolVersion", "byRes");
		}
	}
	
	//Ӳ�������������
	public static class USB_CAMERA_SYSTEM_HARDWARE_SERVER extends Structure{
		public byte byUsbMode;	//USBģʽ�л�	1-USB��UVCģʽ,	2-USB��NCMģʽ	
		public byte[] byRes = new byte[31];	
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byUsbMode", "byRes");
		}
	}
	
	//ͼ�����ȵ���
	public static class USB_CAMERA_IMAGE_BRIGHTNESS extends Structure{
		public byte byChannelID;	//ͨ����
		public int dwBrightness;	//ͼ������0-100
		public byte[] byRes = new byte[27];	
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byChannelID", "dwBrightness", "byRes");
		}
	}
	
	//ͼ��Աȶȵ���
	public static class USB_CAMERA_IMAGE_CONTRAST extends Structure{
		public byte byChannelID;	//ͨ����
		public int dwContrast;	//ͼ��Աȶ�0-100
		public byte[] byRes = new byte[27];	
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byChannelID", "dwContrast", "byRes");
		}
	}
	
	//һ������У��
	public static class USB_CAMERA_IMAGE_BACKGROUND_CORRECT extends Structure{
		public byte byChannelID;  //ͨ����
		public byte[] byRes = new byte[31];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byChannelID", "byRes");
		}
	}
	
	//һ���ֶ�У��
	public static class USB_CAMERA_IMAGE_MANUAL_CORRECT extends Structure{
		public byte byChannelID;  //ͨ����
		public byte[] byRes = new byte[31];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byChannelID", "byRes");
		}
	}
	
	//ͼ����ǿ
	public static class USB_CAMERA_IMAGE_ENHANCEMENT extends Structure{
		public byte byChannelID;	//ͨ����
		public byte byNoiseReduceMode;	//���ֽ���ģʽ��0-�ر�	2	-	ר��ģʽ
		public byte[] byRes1 = new byte[2];	
		public int dwGeneralLevel;	//��ͨģʽ���뼶��	0-100
		public int dwFrameNoiseReduceLevel;	//ר��ģʽ�����뼶��	0-100
		public int dwInterFrameNoiseReduceLevel;	//ר��ģʽʱ���뼶��	0-100
		public byte byPaletteMode;	//α��ɫ��ɫģʽ��1-����	2-����	3-ɫ��2	4-����	5-��	6-����	7-����	8-����
		public byte byLSEDetailEnabled;	//ͼ��ϸ����ǿʹ��:	0-�ر�	1-����
		public byte[] byRes2 = new byte[2];	
		public int dwLSEDetailLevel;	//ͼ��ϸ����ǿ�ȼ�:	0-100
		public byte[] byRes = new byte[40];	
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byChannelID", "byNoiseReduceMode","byRes1","dwGeneralLevel","dwFrameNoiseReduceLevel"
					,"dwInterFrameNoiseReduceLevel","byPaletteMode","byLSEDetailEnabled","byRes2","dwLSEDetailLevel","byRes");
		}
	}
	
	//��Ƶ����
	public static class USB_CAMERA_IMAGE_VIDEO_ADJUST extends Structure{
		public byte byChannelID;	//ͨ����
		public byte byImageFlipStyle;	//����ģʽ:	0-�ر�	1-����	2-����	3-����
		public byte byPowerLineFrequencyMode;	//��Ƶ��ʽ��1-PAL(50HZ)
		public byte byCorridor;	//��ͷ����ģʽ(��ת):	0-�ر�	1-����
		public byte[] byRes = new byte[28];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byChannelID", "byImageFlipStyle","byPowerLineFrequencyMode","byCorridor","byRes");
		}
	}
	
	//˫��У׼�������
	public static class USB_CAMERA_THERMAL_DOUBLE_LIGHTS_CORRECT_POINTS_CTRL extends Structure{
		public byte byChannelID;	//ͨ����
		public byte byDoubleLightsCorrectPointsEnabled;	//˫��У׼����ʹ�ܿ���	0-�ر�	1-����
		public byte[] byRes = new byte[30];	
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byChannelID", "byDoubleLightsCorrectPointsEnabled","byRes");
		}
	}
	
	//ȫ�����²�������
	public static class USB_CAMERA_THERMAL_P2P_PARAM extends Structure{
		public byte byChannelID;	//ͨ����
		public byte byJpegPicEnabled;	//�豸�Ƿ񷵻�jpegͼƬ	0-�ر�	1-����
		public byte[] byRes = new byte[30];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byChannelID", "byJpegPicEnabled","byRes");
		}
	}
	
	//���²�����������
	public static class USB_CAMERA_THERMAL_BODYTEMP_COMPENSATION extends Structure{
		public byte byChannelID;	//ͨ����
		public byte byEnabled;	//ʹ��	0-�ر�	1-����
		public byte byType;	//������ʽ:1-�ֶ�����	2-�Զ�����
		public byte byRes1;	
		public int iCompensationValue;	//�����¶�	[-10.0	10.0]���϶�,	����ʱʵ��ֵ*10���������
		public int dwSmartCorrection;	//�ֶ�У׼	-99.0~990.��,	����ʱ(ʵ��ֵ+100)*10�����������
		public int dwEnvironmentalTemperature;	//�����¶�	-99.0~99.0��,	����ʱ(ʵ��ֵ+100)*10�����������
		public byte byEnvironmentalTemperatureMode;	//�����¶�ģʽ	1-�Զ�ģʽ	2-�ֶ�ģʽ
		public byte[] byRes = new byte[47];	
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byChannelID", "byEnabled", "byType","byRes1", "iCompensationValue"
					,"dwSmartCorrection", "dwEnvironmentalTemperature", "byEnvironmentalTemperatureMode" ,"byRes");
		}
	}
	
	//�����������
	public static class USB_CAMERA_THERMAL_BLACK_BODY extends Structure{
		public byte byChannelID;	//ͨ����
		public byte byEnabled;	//ʹ��	0-�ر�	1-����
		public byte[] byRes1 = new byte[2];	
		public int dwEmissivity;	//���巢����:0.01	-	1.00,	����ʱʵ��ֵ	*	100���������
		public int dwDistance;	//����	0.3-2m,	Э�鴫��ʱ������Ϊ��λ
		public int dwTemperature;	//�����¶�	30.0~50.0��,	����ʱʵ��ֵ*10���������
		public int dwCentrePointX;	//�������ĵ�X���꣬��һ��ֵ����Χ0-1000
		public int dwCentrePointY;	//�������ĵ�Y���꣬��һ��ֵ����Χ0-1000
		public byte[] byRes = new byte[40];	
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byChannelID", "byEnabled","byRes1", "dwEmissivity","dwDistance", "dwTemperature"
					,"dwCentrePointX", "dwCentrePointY", "byRes");
		}
	}
	
	//��������
	public static class USB_CAMERA_THERMAL_TEMPERATURE_CORRECT extends Structure{
		public byte byChannelID;	//ͨ����
		public byte byEnabled;	//ʹ��	0-�ر�	1-����
		public byte byStreamOverlay;	//�����¶���������ʹ��	0-�ر�	1-����
		public byte byCorrectEnabled;	//��������ʹ��	0-�ر�	1-����
		public int dwEmissivity;	//���巢����:0.01-1.00,	����ʱʵ��ֵ*100���������
		public int dwDistance;	//����	0.3-2m,	Э�鴫��ʱ������Ϊ��λ
		public int dwTemperature;	//�����¶�	30.0~50.0��,	����ʱʵ��ֵ*10���������
		public int dwCentrePointX;	//�������ĵ�X���꣬��һ��ֵ����Χ0-1000
		public int dwCentrePointY;	//�������ĵ�Y���꣬��һ��ֵ����Χ0-1000
		public int dwCorrectTemperature;	//�����¶�	-99.0-99.0��	����ʱ(ʵ��ֵ	+	100)	*	10�����������
		public byte[] byRes = new byte[36];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byChannelID","byEnabled", "byStreamOverlay", "byCorrectEnabled", "dwEmissivity"
					,"dwDistance", "dwTemperature", "dwCentrePointX", "dwCentrePointY", "dwCorrectTemperature", "byRes");
		}
	}
	
	//�ȳ�������㷨�汾
	public static class USB_CAMERA_THERMAL_ALG_VERSION extends Structure{
		public byte[] byThermometryAlgName = new byte[64];	//�����㷨��汾��Ϣ
		public byte[] byRes = new byte[64];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byThermometryAlgName", "byRes");
		}
	}
	
	//���¹�������
	public static class THERMAL_REGION extends Structure{
		public byte byRegionID;	//����ID����1��ʼ����
		public byte byRegionEnabled;	//����ʹ��	0-�ر�	1-����
		public byte[] byRes1 = new byte[2];	
		public int dwRegionX;	//�������϶���X���꣬��һ��ֵ����Χ0-1000
		public int dwRegionY;	//�������϶���Y���꣬��һ��ֵ����Χ0-1000
		public int dwRegionWidth;	//�����ȣ���һ��ֵ����Χ0-1000
		public int dwRegionHeight;	//����߶ȣ���һ��ֵ����Χ0-1000
		public byte[] byRes = new byte[12];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byRegionID", "byRegionEnabled","byRes1", "dwRegionX","dwRegionY", "dwRegionWidth"
					,"dwRegionHeight", "byRes");
		}
	}
	
	//���¹�������	
	public static class USB_CAMERA_THERMAL_THERMOMETRY_REGIONS extends Structure{
		public byte byChannelID;	//ͨ����
		public byte bySID;	//����ID
		public byte byRegionNum;	//���������ܸ���
		public byte[] byRes1 = new byte[5];	
		public THERMAL_REGION[] struRegion = new THERMAL_REGION[THERMAL_MAX_REGIONS];	
		public byte[] byRes = new byte[184];	
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byChannelID", "bySID","byRegionNum", "byRes1","struRegion", "byRes");
		}
	}
	
	//���»�����������
	public static class USB_CAMERA_THERMAL_THERMOMETRY_BASIC_PARAM extends Structure{
		public byte byChannelID;   //ͨ����
		public byte byEnabled;   //�������¹���ʹ��
		public byte byDisplayMaxTemperatureEnabled;   //��ʾ�����: 0-�ر�; 1-����
		public byte byDisplayMinTemperatureEnabled;   //��ʾ�����: 0-�ر�; 1-����
		public byte byDisplayAverageTemperatureEnabled;   //��ʾƽ����: 0-�ر�; 1-����
		public byte byTemperatureUnit;   //�¶ȵ�λ: 1 - �����¶�;2 - �����¶�;3 - �������¶�(Э�鴫����Լ���������¶���Ϊ��λ����)
		public byte byTemperatureRange;   //���·�Χ: 1-30~45
		public byte byCalibrationCoefficientEnabled;   //���ñ궨ϵ��:0 - �ر�;1 - ����
		public int dwCalibrationCoefficient;   //�궨ϵ��: 0.00~30.00 ,����ʱʵ��ֵ * 100���������
		public int dwExternalOpticsWindowCorrection;   //�ⲿ��ѧ�¶�: -40.0~80.0�� ,����ʱ(ʵ��ֵ + 100) * 10�����������
		public int dwEmissivity;   //������: 0.01~1(��ȷ��С�������λ), ����ʱʵ��ֵ * 100���������
		public byte byDistanceUnit;   //���뵥λ: 1 - ��; 2 - ����; 3 - Ӣ��
		public byte[] byRes1 = new byte[3];
		public int dwDistance;   //����: 0.3-2m��Э�鴫����Լ����cm��Ϊ��λ����, ��ȷ��С�����1λ��
		public byte byReflectiveEnable;   //�����¶�ʹ��: 0 - �ر�; 1 - ����
		public byte[] byRes2 = new byte[3];
		public int dwReflectiveTemperature;   //�����¶�: -100.0~1000.0�棨��ȷ��С�����1λ��,����ʱ(ʵ��ֵ + 100) * 10�����������
		public byte[] byRes = new byte[220];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byChannelID", "byEnabled","byDisplayMaxTemperatureEnabled", "byDisplayMinTemperatureEnabled","byDisplayAverageTemperatureEnabled"
					, "byTemperatureUnit", "byTemperatureRange", "byCalibrationCoefficientEnabled", "dwCalibrationCoefficient", "dwExternalOpticsWindowCorrection"
					, "dwEmissivity", "byDistanceUnit", "byRes1", "dwDistance", "byReflectiveEnable", "byRes2", "dwReflectiveTemperature", "byRes");
		}
	}
	
	//����ģʽ����
	public static class USB_CAMERA_THERMAL_THERMOMETRY_MODE extends Structure{
		public byte byChannelID;   //ͨ����
		public byte byThermometryMode;   //����ģʽ: 1-��ͨ; 2-ר��
		public byte byThermometryROIEnabled;   //����ROIʹ��: 0 - �ر�; 1 - ����
		public byte[] byRes = new byte[61];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byChannelID", "byThermometryMode","byThermometryROIEnabled", "byRes");
		}
	}
	
	//ץ��ͼ
	public static class USB_CAMERA_THERMAL_JPEGPIC_WITH_APPENDDATA extends Structure{
		public byte byChannelID;   //ͨ����
		public byte[] byRes1 = new byte[3];
		public int dwJpegPicLen;   //JpegͼƬ����
		public int dwJpegPicWidth;   //ͼ����
		public int dwJpegPicHeight;   //ͼ��߶�
		public int dwP2pDataLen;   //ȫ���������ݳ���
		public byte byIsFreezedata;   //�Ƿ����ݶ���: 0-��; 1-��
		public byte byTemperatureDataLength;   //�������ݳ��ȣ�2��4��
		public byte[] byRes2 = new byte[2];
		public int dwScale;   //ƫ�������������ݳ���Ϊ2ʱ���أ�
		public int dwOffset;   //�����¶�: -10.0~10.0��, ����ʱʵ��ֵ*10���������
		public Pointer pJpegPic;   //�ȳ���ͼƬ����
		public Pointer pP2pData;   //ȫ����������
		public byte[] byRes = new byte[24];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byChannelID", "byRes1", "dwJpegPicLen", "dwJpegPicWidth"
					, "dwJpegPicHeight", "dwP2pDataLen", "byIsFreezedata","byTemperatureDataLength","byRes2"
					,"dwScale","dwOffset","pJpegPic","pP2pData","byRes");
		}
	}
	
	//�ȳ���������������
	public static class USB_CAMERA_THERMAL_STREAM_PARAM extends Structure{
		public byte byChannelID;   //ͨ����
		public byte byVideoCodingType;   //�������ݱ��������: 1-�ȳ���������; 2-ȫ����������; 3-ʵʱ������; 4-��ͼ����; 5-�ȳ���ʵʱ��
		public byte[] byRes = new byte[14];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byChannelID", "byVideoCodingType", "byRes");
		}
	}
	
	//ROI����
	public static class THERMAL_ROI_REGION extends Structure{
		public byte byROIRegionID;   //ROI����ID����1��ʼ������+1����
		public byte byROIRegionEnabled;   //ROI����ʹ�ܣ�0 - �رգ�1 - ����
		public byte[] byRes1 = new byte[2];
		public int dwROIRegionX;   //�������϶���X���꣬��һ��ֵ����Χ0-1000
		public int dwROIRegionY;   //�������϶���Y���꣬��һ��ֵ����Χ0-1000
		public int dwROIRegionWidth;   //�����ȣ���һ��ֵ����Χ0-1000
		public int dwROIRegionHeight;   //����߶ȣ���һ��ֵ����Χ0-1000
		public int dwDistance;   //���룺0.3-2m��Э�鴫����Լ����cm��Ϊ��λ����, ��ȷ��С�����1λ��
		public byte[] byRes = new byte[8];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byROIRegionID", "byROIRegionEnabled", "byRes1", "dwROIRegionX", 
					"dwROIRegionY", "dwROIRegionWidth", "dwROIRegionHeight", "dwDistance", "byRes");
		}
	}
	
	//ROI�������Ϣ��ѯ
	public static class USB_CAMERA_THERMAL_ROI_MAX_TEMPERATURE_SEARCH extends Structure{
		public byte byChannelID;   //ͨ����
		public short wMillisecond;   //����
		public byte bySecond;   //��
		public byte byMinute;   //����
		public byte byHour;   //Сʱ
		public byte byDay;   //��
		public byte byMonth;   //��
		public short wYear;   //��
		public byte byJpegPicEnabled;   //�豸�Ƿ񷵻�jpegͼƬ
		public byte byMaxTemperatureOverlay;   //�Ƿ���������
		public byte byRegionsOverlay;   //�Ƿ���ӹ����
		public byte byROIRegionNum;    //ROI�����ܸ���
		public byte[] byRes1 = new byte[2];
		public THERMAL_ROI_REGION[] struThermalROIRegion = new THERMAL_ROI_REGION[MAX_ROI_REGIONS];//ROI����
		public byte[] byRes = new byte[176];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byChannelID", "wMillisecond", "bySecond", 
					"byMinute", "byHour", "byDay", "byMonth", "wYear", "byJpegPicEnabled", 
					"byMaxTemperatureOverlay", "byRegionsOverlay", "byROIRegionNum", "byRes1", "struThermalROIRegion", "byRes");
		}
	}
	
	//ROI������Ϣ
	public static class THERMAL_ROI_REGION_INFO extends Structure{
		public byte byROIRegionID;   //ROI����ID����1��ʼ������+1����
		public byte[] byRes1 = new byte[3];
		public int dwMaxROIRegionTemperature;   //�����: 30.0~50.0��, ����ʱʵ��ֵ * 10���������
		public int dwVisibleROIRegionMaxTemperaturePointX;   //ROI����ɼ��������X���꣬��һ��ֵ����Χ0-1000
		public int dwVisibleROIRegionMaxTemperaturePointY;   //ROI����ɼ��������Y���꣬��һ��ֵ����Χ0-1000
		public int dwThermalROIRegionMaxTemperaturePointX;   //ROI�����ȳ��������X���꣬��һ��ֵ����Χ0-1000
		public int dwThermalROIRegionMaxTemperaturePointY;   //ROI�����ȳ��������Y���꣬��һ��ֵ����Χ0-1000
		public byte[] byRes = new byte[8];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byROIRegionID", "byRes1", "dwMaxROIRegionTemperature", "dwVisibleROIRegionMaxTemperaturePointX", 
					"dwVisibleROIRegionMaxTemperaturePointY", "dwThermalROIRegionMaxTemperaturePointX", "dwThermalROIRegionMaxTemperaturePointY", "byRes");
		}
	}
	
	//ROI�������Ϣ��ѯ��Ӧ
	public static class USB_CAMERA_THERMAL_ROI_MAX_TEMPERATURE_SEARCH_RESULT extends Structure{
		public byte byChannelID;   //ͨ����
		public byte[] byRes1 = new byte[3];
		public int dwMaxP2PTemperature;   //ȫ�������: 30.0~50.0��, ����ʱʵ��ֵ * 10���������
		public int dwVisibleP2PMaxTemperaturePointX;   //ȫ���ɼ��������X���꣬��һ��ֵ����Χ0-1000
		public int dwVisibleP2PMaxTemperaturePointY;   //ȫ���ɼ��������Y���꣬��һ��ֵ����Χ0-1000
		public int dwThermalP2PMaxTemperaturePointX;   //ȫ���ȳ��������X���꣬��һ��ֵ����Χ0-1000
		public int dwThermalP2PMaxTemperaturePointY;   //ȫ���ȳ��������Y���꣬��һ��ֵ����Χ0-1000
		public byte byROIRegionNum;   //ROI�����ܸ���
		public byte[] byRes2 = new byte[3]; 
		public int dwJpegPicLen;   //JpegͼƬ����
		public THERMAL_ROI_REGION_INFO[] struThermalROIRegionInfo = new THERMAL_ROI_REGION_INFO[MAX_ROI_REGIONS];   //ROI������Ϣ
		public Pointer pJpegPic;   //ͼƬ���ݣ�����������Ϣ֮��ĩβֻ����1�Σ�
		public byte[] byRes = new byte[156];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byChannelID", "byRes1", "dwMaxP2PTemperature", "dwVisibleP2PMaxTemperaturePointX", 
					"dwVisibleP2PMaxTemperaturePointY", "dwThermalP2PMaxTemperaturePointX", "dwThermalP2PMaxTemperaturePointY", "byROIRegionNum", 
					"byRes2", "dwJpegPicLen", "struThermalROIRegionInfo", "pJpegPic", "byRes");
		}
	}
	
	//˫��У׼
	public static class USB_CAMERA_THERMAL_DOUBLE_LIGHTS_CORRECT extends Structure{
		public byte byChannelID;   //ͨ����
		public byte byVisiblePicHorizontalScale;   //��׼�ü��ɼ���ͼ��ˮƽ����ϵ������λ��һ��128��[12,128]
		public byte byVisiblePicVerticalScale;   //��׼�ü��ɼ���ͼ��ֱ����ϵ������λ��һ��128��[12,128]
		public byte[] byRes1 = new byte[1];
		public short wHorizontalCalibrationOffset;   //ˮƽ�궨ƫ�����ֵΪ�궨ʱ�����ã���λ��һ��1000��[0,1000]
		public short wVerticalCalibrationOffset;   //��ֱ�궨ƫ�����ֵΪ�궨ʱ�����ã���λ��һ��1000��[0,1000]
		public int dwVisibleFocusDistance;   //�ɼ��⾵ͷ���࣬��λ����
		public int dwVisiblePixelInterval;   //�ɼ���̽������Ԫ�ߴ磬��λ΢��
		public int dwHorizontalCenterDistance;   //�ɼ�����ȳ������ĵ�ˮƽ���룬��λ����
		public int dwVerticalCenterDistance;   //�ɼ�����ȳ������ĵ㴹ֱ���룬��λ����
		public int dwCalibrationDistance;   //�궨����[0.3,2]m,Э�鴫��ʱͳһʹ��cmΪ��λ
		public int dwVisiblePicLen;   //�ɼ���ͼƬ����
		public Pointer pVisiblePic;   //�ɼ���ͼƬ����
		public byte[] byRes = new byte[92];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byChannelID", "byVisiblePicHorizontalScale", "byVisiblePicVerticalScale", "byRes1", 
					"wHorizontalCalibrationOffset", "wVerticalCalibrationOffset", "dwVisibleFocusDistance", "dwVisiblePixelInterval", 
					"dwHorizontalCenterDistance", "dwVerticalCenterDistance", "dwCalibrationDistance", "dwVisiblePicLen", "pVisiblePic", "byRes");
		}
	}
	
	//˫��У׼��Ӧ
	public static class USB_CAMERA_THERMAL_DOUBLE_LIGHTS_CORRECT_RESULT extends Structure{
		public int dwJpegPicLen;   //�ںϵ���ͼƬ����
		public Pointer pJpegPic;   //�ںϵ���ͼƬ
		public byte[] byRes = new byte[120];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("dwJpegPicLen", "pJpegPic", "byRes");
		}
	}
	
	//�豸����
	public static class USB_CAMERA_SYSTEM_UPDATE_FIRMWARE extends Structure{		
		public int  dwUpdateFileLength;   //����������
		public Pointer pUpdateFile;   //�������ļ�
		public byte[] byRes = new byte[56];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("dwUpdateFileLength", "pUpdateFile", "byRes");
		}
	}
	
	//�������
		public static class USB_CAMERA_SYSTEM_UPDATE_FIRMWARE_PERMIT extends Structure{		
			public byte byUpdatePermission;    //������ɣ�0-����������, 1-��������
			public byte[] byRes = new byte[31];
			@Override
			protected List<String> getFieldOrder() {

				return Arrays.asList("byUpdatePermission", "byRes");
			}
		}
	
	//���������Ϣ
	public static class USB_CAMERA_SYSTEM_UPDATE_FIRMWARE_RESULT extends Structure{
		public byte byUpdateStatus;     //����״̬��0-����, 1-������, 2-�����ɹ�, 3-����ʧ��
		public byte byPercent;      //�������ȣ���Χ0-100
		public byte byErrMsg;       //����ʧ��ԭ��0-����, 1-�������Ͳ�ƥ��, 2-�����汾��ƥ��, 3-�������Բ�ƥ��, 0xFF-δ֪����
		public byte[] byRes = new byte[61];
		@Override
		protected List<String> getFieldOrder() {

			return Arrays.asList("byUpdateStatus", "byPercent", "byErrMsg", "byRes");
		}
	}
	
	////////////////////////////
	//ȫ������--����
		public static class USB_CAMERA_THERMAL_STREAM_DATA_TIME extends Structure{
			public short wYear;   
			public short wMonth;   //0����1��  11����12��
			public short wDayOfWeek;   //0����������  6����������
			public short wDay;   
			public short wHour;   
			public short wMinute;   
			public short wSecond;   
			public short wMillSecond;    
			
			@Override
			protected List<String> getFieldOrder() {

				return Arrays.asList("wYear", "wMonth", "wDayOfWeek", 
						"wDay", "wHour", "wMinute", "wSecond", "wMillSecond");
			}
		}
		
		//ȫ������--������Ϣ
		public static class USB_CAMERA_THERMAL_STREAM_RT_DATA_INFO_S extends Structure{
			public int dwRTDataType;    //��������
			public int dwFrmNum; 
			public int dwStdStamp; //DSP���ʱ���
			public USB_CAMERA_THERMAL_STREAM_DATA_TIME struTime;  //����ʱ���
			public int dwWidth;
			public int dwHeight;
			public int dwLen;
			public int dwFps;
			public int dwChan;
			
			@Override
			protected List<String> getFieldOrder() {

				return Arrays.asList("dwRTDataType", "dwFrmNum", "dwStdStamp", 
						"struTime", "dwWidth", "dwHeight","dwLen", "dwFps", "dwChan");
			}
		}
		
		//ȫ������--������Ϣ
		public static class USB_CAMERA_THERMAL_STREAM_FS_SUPPLE_INFO_TEMP extends Structure{
			public int dwTmDataMode;    //0 Ϊ���ֽ� 1Ϊ2�ֽ�
			public int dwTmScale;	//�������ű���
			public int dwTmOffset;	//����ƫ��������ǰ�̶�Ϊ0
			public int dwIsFreezedata;//�Ƿ񶳽�����  0������  1���Ƕ���
			
			@Override
			protected List<String> getFieldOrder() {

				return Arrays.asList("dwTmDataMode", "dwTmScale", "dwTmOffset", "dwIsFreezedata");
			}
		}	

		//��ͼʵʱ--JPG��Ϣ
		public static class USB_CAMERA_THERMAL_STREAM_JPEG_DATA_INFO extends Structure{
			public int dwFrmNum;
			public int dwWidth;
			public int dwHeight;
			public int dwLen;
			public int dwStdStamp;//DSP���ʱ���
			public USB_CAMERA_THERMAL_STREAM_DATA_TIME struTime;  //����ʱ���
			
			@Override
			protected List<String> getFieldOrder() {

				return Arrays.asList("dwFrmNum", "dwWidth", "dwHeight", "dwLen","dwStdStamp","struTime");
			}
		}	

		//��ͼʵʱ--point��Ϣ
		public static class USB_CAMERA_THERMAL_STREAM_IFR_POINT extends Structure{
			public int dwX;
			public int dwY;
			
			@Override
			protected List<String> getFieldOrder() {

				return Arrays.asList("dwX", "dwY");
			}
		}	

		//��ͼʵʱ--���½����Ϣ
		public static class USB_CAMERA_THERMAL_IFR_ROI_OUTCOME_INFO extends Structure{
			public byte byEnable;//�Ƿ����� 0 �� 1 ��
			public byte byRrgionId;//����ID
			public byte[] byRes = new byte[34];//����
			public byte[] byName = new byte[32];//��������
			public float fEmissionRate;//������ 0.00-100
			public float fDistance;//Ԥ��Ĳ��¾���
			public float fMinTemp;//����¶ȡ�-40��1000����λ���϶�
			public float fMaxTemp;//����¶ȡ�-40��1000����λ���϶�
			public float fAvrTemp;//ƽ���¶ȡ�-40��1000����λ���϶�
			public USB_CAMERA_THERMAL_STREAM_IFR_POINT[] struIFRPoints = new USB_CAMERA_THERMAL_STREAM_IFR_POINT[2];//������Խ���е�����º���������꣬��һ��0-1000�������±꣺0 �����  1 �����
			
			@Override
			protected List<String> getFieldOrder() {

				return Arrays.asList("byEnable", "byRrgionId", "byRes", "byName", "fEmissionRate", "fDistance", "fMinTemp"
						, "fMaxTemp", "fAvrTemp", "struIFRPoints");
			}
		}	
		
		//��ͼʵʱ--ROI���¹�����Ϣ
		public static class USB_CAMERA_THERMAL_STREAM_IFR_ROI_LIST extends Structure{
			public int dwRoiRegionNum;//��Ч���������������
			public USB_CAMERA_THERMAL_IFR_ROI_OUTCOME_INFO struIFRRoiOutComeInfo;//���½��
			
			@Override
			protected List<String> getFieldOrder() {

				return Arrays.asList("dwRoiRegionNum", "struIFRRoiOutComeInfo");
			}
		}
		
		//��ͼʵʱ--ʵʱ������Ϣ
		public static class USB_CAMERA_THERMAL_IFR_REALTIME_TM_UPLOAD_INFO extends Structure{
			public int dwTempUnit;//�¶ȵ�λ Ĭ�����϶�
			public byte byRefTempKey;//�����¶ȿ���
			public byte[] byRes = new byte[3];//����
			public float fDistance;//��������
			public float fRefTemp;//�����¶ȣ����ݷ����¶ȿ���
			public float fEmissionRate;//�����ʣ�0.00-1.00
			public float fEnvTemp;//�����¶�
			public float fMinTemp;//ȫ�������
			public float fMaxTemp;//ȫ�������
			public float fAvrTemp;//ȫ��ƽ����
			public USB_CAMERA_THERMAL_STREAM_IFR_POINT[] struIFRPoints = new USB_CAMERA_THERMAL_STREAM_IFR_POINT[3];//������Խ���е�����º���������꣬��һ��0-1000�������±꣺0 �����  1 �����
			public int[] dwRes = new int[6];
			public USB_CAMERA_THERMAL_STREAM_IFR_ROI_LIST struStreamIFRRoiList;//ROI���������Ϣ
			
			@Override
			protected List<String> getFieldOrder() {

				return Arrays.asList("dwTempUnit", "byRefTempKey", "byRes", "fDistance","fRefTemp","fEmissionRate"
						,"fEnvTemp","fMinTemp","fMaxTemp","fAvrTemp","struIFRPoints","dwRes","struStreamIFRRoiList");
			}
		}	
		
		
		//ȫ������ʵʱ�ϴ�����
		public static class USB_CAMERA_THERMAL_STREAM_FRAME_INFO_TEMP extends Structure{
			public int dwMagicNo;    //0x70827773 "FRMI"��ASCII��
			public int dwHeaderSize; //�ṹ�峤��
			public int dwStreamType; //��������
			public int dwStreamLen;  //���ݳ���
			public USB_CAMERA_THERMAL_STREAM_RT_DATA_INFO_S struStreamRtDataInfo;
			public USB_CAMERA_THERMAL_STREAM_FS_SUPPLE_INFO_TEMP struStreamFsSuppleInfoTemp;
			public byte[] byRes = new byte[12];
			public int dwCrcVal;//�ṹ��У����
		
			public USB_CAMERA_THERMAL_STREAM_FRAME_INFO_TEMP(Pointer p)
			{
				super(p);
			}
			
			@Override
			protected List<String> getFieldOrder() {

				return Arrays.asList("dwMagicNo", "dwHeaderSize", "dwStreamType", 
						"dwStreamLen", "struStreamRtDataInfo", "struStreamFsSuppleInfoTemp", "byRes", "dwCrcVal");
			}
		}

		//��ͼʵʱ�ϴ�����
		public static class USB_CAMERA_THERMAL_STREAM_FRAME_INFO_TEMP_HOT extends Structure{
			public int dwMagicNo;    //0x70827773 "FRMI"��ASCII��
			public int dwHeaderSize; //�ṹ�峤��
			public int dwStreamType; //��������
			public int dwStreamLen;  //���ݳ���
			public int dwIFRJpg;//�Ƿ�Я��JPGͼƬ
			public USB_CAMERA_THERMAL_STREAM_RT_DATA_INFO_S struStreamRtDataInfo;
			public USB_CAMERA_THERMAL_STREAM_FS_SUPPLE_INFO_TEMP struStreamFsSuppleInfoTemp;
			public USB_CAMERA_THERMAL_STREAM_JPEG_DATA_INFO struStreamJpegDataInfo;
			public USB_CAMERA_THERMAL_IFR_REALTIME_TM_UPLOAD_INFO struIFRRealtimeTmUploadInfo;
			public byte[] byRes = new byte[12];
			public int dwCrcVal;//�ṹ��У����
		
			public USB_CAMERA_THERMAL_STREAM_FRAME_INFO_TEMP_HOT(Pointer p)
			{
				super(p);
			}
			@Override
			protected List<String> getFieldOrder() {

				return Arrays.asList("dwMagicNo", "dwHeaderSize", "dwStreamType", 
						"dwStreamLen", "dwIFRJpg","struStreamRtDataInfo", "struStreamFsSuppleInfoTemp", "struStreamJpegDataInfo", "struIFRRealtimeTmUploadInfo", "byRes", "dwCrcVal");
			}
		}

	
	
	
			
	Boolean HC_USBCamera_Init();
	Boolean HC_USBCamera_Fini();
	int HC_USBCamera_GetSDKVersion();
	int HC_USBCamera_EnumDevice(Pointer pDevInfoList, int size, Pointer pParam);
//	int HC_USBCamera_EnumDevice(Pointer pDevInfoList, int size);
	Boolean HC_USBCamera_SetLogToFile(int dwLogLevel, String pLogDir, int bAutoDel);
	int HC_USBCamera_Open(int iDevIndex);
	Boolean HC_USBCamera_Close(int iDevHandle);
	Boolean HC_USBCamera_GetConfig(int iDevHandle, int dwCommand, Pointer lpConfig, int dwConfigSize);
	Boolean HC_USBCamera_SetConfig(int iDevHandle, int dwCommand, Pointer lpConfig, int dwConfigSize);
	int HC_USBCamera_StartPreview(int iDevHandle, Pointer pStreamCBParam);
	Boolean HC_USBCamera_StopPreview(int iDevHandle, int nChannel);
	Boolean HC_USBCamera_Capture(int iDevHandle, Pointer pCaptuerParam);
	int HC_USBCamera_GetLastError();
}