package com.example.audiopush.pusher;

import com.example.audiopush.jni.PushNative;
import com.example.audiopush.params.AudioParam;
import com.example.audiopush.params.VideoParam;

import android.hardware.Camera.CameraInfo;
import android.view.SurfaceHolder;


public class LivePusher {
	
	private SurfaceHolder surfaceHolder;
	private VideoPusher videoPusher;
	private AudioPusher audioPusher;
	private PushNative pushNative;

	public LivePusher(SurfaceHolder surfaceHolder) {
		this.surfaceHolder = surfaceHolder;
		pushNative = new PushNative();
		prepare();
	}
	
	
	private void prepare() {
		AudioParam audioParam = new AudioParam();
		audioPusher = new AudioPusher(audioParam, pushNative);
		
		//VideoParam videoParam = new VideoParam();
		VideoParam videoParam = new VideoParam(1920, 1080, CameraInfo.CAMERA_FACING_BACK);
		//videoParam.setCameraId(CameraInfo.CAMERA_FACING_BACK);
		videoPusher = new VideoPusher(surfaceHolder, videoParam, pushNative);
	}

	public void startPush(String url) {
		// TODO Auto-generated method stub
		videoPusher.startPush();
		//audioPusher.startPush();
		pushNative.startPush(url);
	}


	public void stopPush() {
		// TODO Auto-generated method stub
		videoPusher.stopPush();
		audioPusher.stopPush();
		pushNative.stopPush();
	}


	public void release() {
		// TODO Auto-generated method stub
		pushNative.release();
	}

	public void switchCamera() {
		// TODO Auto-generated method stub
		videoPusher.switchCamera();
	}
}
