package com.example.audiopush.pusher;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.example.audiopush.params.VideoParam;

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

	public VideoPusher(SurfaceHolder surfaceHolder, VideoParam param) {
		this.surfaceHolder = surfaceHolder;
		this.praram = param;
		this.surfaceHolder.addCallback(this);
	}
	
	@Override
	public void startPush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopPush() {
		// TODO Auto-generated method stub
		
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
			parameters.setPreviewSize(praram.getWidth(), praram.getHight());
			//mCamera.setParameters(parameters);
			List<Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes(); 
			List<Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
			
			for(Size s:supportedPictureSizes) {
				Log.e("------>", "width: " + s.width + "   height: " + s.height);
			}
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
	}

	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {
		// TODO Auto-generated method stub
		Log.e("------>", " onPreviewFrame " );
		mCamera.addCallbackBuffer(buffer);
	}

}
