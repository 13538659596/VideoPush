package com.example.audiopush.pusher;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

import com.example.audiopush.jni.PushNative;
import com.example.audiopush.params.AudioParam;


public class AudioPusher extends Pusher{

	private AudioParam audeoParam;
	private AudioRecord audioRecord;
	private boolean isPushing;
	private int minBufferSize;
	private PushNative pushNative;
	private int channelConfig;
	public AudioPusher(AudioParam audeoParam, PushNative pushNative) {
		this.audeoParam = audeoParam;
		this.pushNative = pushNative;
		channelConfig = audeoParam.getChannelNumber() == 1? AudioFormat.CHANNEL_CONFIGURATION_MONO:AudioFormat.CHANNEL_CONFIGURATION_STEREO;
		int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
		minBufferSize = AudioRecord.getMinBufferSize(audeoParam.getSampleRateInHz(), channelConfig, audioFormat);
		audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, 
				audeoParam.getSampleRateInHz(), 
				channelConfig, audioFormat, minBufferSize);
	
	}
	
	@Override
	public void startPush() {
		// TODO Auto-generated method stub
		pushNative.setAudioOptions(audeoParam.getSampleRateInHz(), audeoParam.getChannelNumber());
		isPushing = true;
		new Thread(new AudioRecordTask()).start();
	}

	@Override
	public void stopPush() {
		// TODO Auto-generated method stub
		isPushing = false;
		audioRecord.stop();
		
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		if(audioRecord != null){
			audioRecord.release();
			audioRecord = null;
		}
	}
	
	class AudioRecordTask implements Runnable{
		@Override
		public void run() {
			//开始录音
			audioRecord.startRecording();
			
			while(isPushing){
				//通过AudioRecord不断读取音频数据
				byte[] buffer = new byte[minBufferSize];
				int len = audioRecord.read(buffer, 0, buffer.length);
				if(len > 0){
					//Log.e("------->", "视频录音");
					//传给Native代码，进行音频编码
					pushNative.fireAudio(buffer, len);
				}
			}
		}
		
	}

}
