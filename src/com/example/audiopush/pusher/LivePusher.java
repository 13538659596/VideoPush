package com.example.audiopush.pusher;

import com.example.audiopush.params.AudioParam;
import com.example.audiopush.params.VideoParam;

import android.hardware.Camera.CameraInfo;
import android.view.SurfaceHolder;


public class LivePusher {
	
	private SurfaceHolder surfaceHolder;
	private VideoPusher videoPusher;
	private AudioPusher audioPusher;

	public LivePusher(SurfaceHolder surfaceHolder) {
		this.surfaceHolder = surfaceHolder;
		prepare();
	}
	
	
	private void prepare() {
		AudioParam audioParam = new AudioParam();
		audioPusher = new AudioPusher(audioParam);
		
		//VideoParam videoParam = new VideoParam();
		VideoParam videoParam = new VideoParam(1920, 1080, CameraInfo.CAMERA_FACING_BACK);
		//videoParam.setCameraId(CameraInfo.CAMERA_FACING_BACK);
		videoPusher = new VideoPusher(surfaceHolder, videoParam);
	}

	public void startPush(String url) {
		// TODO Auto-generated method stub
		videoPusher.startPush();
		audioPusher.startPush();
	}


	public void stopPush() {
		// TODO Auto-generated method stub
		videoPusher.stopPush();
		audioPusher.stopPush();
	}


	public void release() {
		// TODO Auto-generated method stub
		
	}

	public void switchCamera() {
		// TODO Auto-generated method stub
		videoPusher.switchCamera();
	}
}
