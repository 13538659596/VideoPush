package com.example.audiopush.pusher;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.example.audiopush.jni.PushNative;
import com.example.audiopush.params.VideoParam;

import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

public class VideoPusher extends Pusher implements Callback, PreviewCallback{

	private SurfaceHolder surfaceHolder;
	private Camera mCamera;
	private VideoParam praram;
	private byte[] buffer;
	private PushNative pushNative;
	
	private boolean isPushing = false;
	
	public VideoPusher(SurfaceHolder surfaceHolder, VideoParam param, PushNative pushNative) {
		this.surfaceHolder = surfaceHolder;
		this.praram = param;
		this.pushNative = pushNative;
		this.surfaceHolder.addCallback(this);
	}
	
	@Override
	public void startPush() {
		// TODO Auto-generated method stub
		//设置视频参数
		pushNative.setVideoOptions(praram.getWidth(), 
				praram.getHight(), praram.getBitrate(), praram.getFps());
		isPushing = true;
	}

	@Override
	public void stopPush() {
		// TODO Auto-generated method stub
		isPushing = false;
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		startPreview();
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		//stopPreiew();
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		stopPreiew();
	}

	public void switchCamera() {
		// TODO Auto-generated method stub
		if(praram.getCameraId() == CameraInfo.CAMERA_FACING_BACK) {
			praram.setCameraId(CameraInfo.CAMERA_FACING_FRONT);
		}else {
			praram.setCameraId(CameraInfo.CAMERA_FACING_BACK);
		}
		stopPreiew();
		startPreview();
	}

	private void startPreview() {
		// TODO Auto-generated method stub
		try {
			mCamera = Camera.open(praram.getCameraId());
		
			mCamera.setPreviewDisplay(surfaceHolder);
			mCamera.startPreview();
		
			Camera.Parameters parameters = mCamera.getParameters();
			
			//获取预览分辨率
			/*Size size = parameters.getPreviewSize();
			Log.e("------>", "相机预览分辨率    "+ "width: " + size.width + "   height: " + size.height);*/
			parameters.setPreviewSize(praram.getWidth(), praram.getHight());
			//设置预览格式
			//parameters.setPictureFormat(ImageFormat.YV12);
			parameters.setPreviewFormat(ImageFormat.NV21);
			//设置相机帧率
			parameters.setPreviewFpsRange(20, 25);
			mCamera.setParameters(parameters);
			
			/*List<Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes(); 
			List<Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
			
			for(Size s:supportedPictureSizes) {
				Log.e("------>", "width: " + s.width + "   height: " + s.height);
			}*/
			buffer = new byte[praram.getHight()* praram.getHight() * 4];
			mCamera.addCallbackBuffer(buffer);
			mCamera.setPreviewCallbackWithBuffer(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void stopPreiew() {
		// TODO Auto-generated method stub
		if(mCamera != null) {
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
		}
		isPushing = false;
	}

	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {
		// TODO Auto-generated method stub
		//Log.e("------>", " onPreviewFrame " );
		mCamera.addCallbackBuffer(buffer);
		
		
		if(isPushing){
			//回调函数中获取图像数据，然后给Native代码编码
			pushNative.fireVideo(data);
		}
	}

}
