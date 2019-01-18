package com.example.audiopush.params;

public class VideoParam {

	private int cameraId;
	private int width;
	private int hight;
	
	private int bitrate = 480000;
	// 帧频默认25帧/s
	private int fps = 25;
	
	public int getBitrate() {
		return bitrate;
	}
	public void setBitrate(int bitrate) {
		this.bitrate = bitrate;
	}
	public int getFps() {
		return fps;
	}
	public void setFps(int fps) {
		this.fps = fps;
	}
	public VideoParam() {
		
	}
	public VideoParam(int width, int hight,int cameraId) {
		super();
		this.cameraId = cameraId;
		this.width = width;
		this.hight = hight;
		
	}
	
	
	public int getCameraId() {
		return cameraId;
	}
	public void setCameraId(int cameraId) {
		this.cameraId = cameraId;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHight() {
		return hight;
	}
	public void setHight(int hight) {
		this.hight = hight;
	}
}
