package com.example.audiopush.params;

public class AudioParam {

	//音频采样率
	private int sampleRateInHz = 44100;
	
	//音频采样通道个数
	private int channelNumber = 1;

	
	public AudioParam() {
		
	}
	
	public AudioParam(int sampleRateInHz, int channelNumber) {
		super();
		this.sampleRateInHz = sampleRateInHz;
		this.channelNumber = channelNumber;
	}

	public int getSampleRateInHz() {
		return sampleRateInHz;
	}

	public void setSampleRateInHz(int sampleRateInHz) {
		this.sampleRateInHz = sampleRateInHz;
	}

	public int getChannelNumber() {
		return channelNumber;
	}

	public void setChannelNumber(int channelNumber) {
		this.channelNumber = channelNumber;
	}
	
	
}
