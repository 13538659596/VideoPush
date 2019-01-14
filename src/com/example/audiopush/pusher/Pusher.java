package com.example.audiopush.pusher;

public abstract class Pusher {
	/**
	 * 开始推流
	 */
	public abstract void startPush();
	
	/**
	 * 停止推流
	 */
	public abstract void stopPush();
	
	/**
	 * 释放资源
	 */
	public abstract void release();
}
