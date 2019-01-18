#include <jni.h>
#include "com_example_audiopush_jni_PushNative.h"

#include <stdlib.h>


extern "C" {
	#include "x264/include/x264.h"
	#include "x264/include/x264_config.h"
};


//输入图像
x264_picture_t pic_in;
/*
 * Class:     com_example_audiopush_jni_PushNative
 * Method:    startPush
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_example_audiopush_jni_PushNative_startPush
  (JNIEnv *env, jobject jobj, jstring jstr) {

}

/*
 * Class:     com_example_audiopush_jni_PushNative
 * Method:    stopPush
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_example_audiopush_jni_PushNative_stopPush
  (JNIEnv *env, jobject jobj) {

}

/*
 * Class:     com_example_audiopush_jni_PushNative
 * Method:    release
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_example_audiopush_jni_PushNative_release
  (JNIEnv *env, jobject jobj) {

}

/*
 * Class:     com_example_audiopush_jni_PushNative
 * Method:    setVideoOptions
 * Signature: (IIII)V
 * 视频参数设置
 */
JNIEXPORT void JNICALL Java_com_example_audiopush_jni_PushNative_setVideoOptions
  (JNIEnv *env, jobject jobj, jint width, jint height, jint bitrate, jint fps) {

	x264_param_t param;
	 /* Get default params for preset/tuning */
	x264_param_default_preset( &param, "ultrafast", "zerolatency");
	/* Configure non-default params */
	param.i_csp = X264_CSP_I420;
	param.i_width  = width;
	param.i_height = height;

	//参数i_rc_method表示码率控制，CQP(恒定质量)，CRF(恒定码率)，ABR(平均码率)
	//恒定码率，会尽量控制在固定码率
	param.rc.i_rc_method = X264_RC_CRF;
	param.rc.i_bitrate = bitrate / 1000; //* 码率(比特率,单位Kbps)
	param.rc.i_vbv_max_bitrate = bitrate / 1000 * 1.2; //瞬时最大码率

	//码率控制不通过timebase和timestamp，而是fps
	param.b_vfr_input = 0;
	param.i_fps_num = fps;  //* 帧率分子
	param.i_fps_den = 1;    //* 帧率分母

	param.i_timebase_den = param.i_fps_num;
	param.i_timebase_num = param.i_fps_den;

	param.i_threads = 1;//并行编码线程数量，0默认为多线程

	//是否把SPS和PPS放入每一个关键帧
	//SPS Sequence Parameter Set 序列参数集，PPS Picture Parameter Set 图像参数集
	//为了提高图像的纠错能力
	param.b_repeat_headers = 1;
	//设置Level级别
	param.i_level_idc = 51;
	//设置Profile档次
	//baseline级别，没有B帧
	x264_param_apply_profile(&param,"baseline");

	//x264_picture_t（输入图像）初始化
	x264_picture_alloc(&pic_in, param.i_csp, param.i_width, param.i_height);

	//打开编码器
	x264_t *x264_encoder = x264_encoder_open(&param);
	if(x264_encoder){
		LOGI("打开编码器成功...");
	}
}
/*
 * Class:     com_example_audiopush_jni_PushNative
 * Method:    setAudioOptions
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_com_example_audiopush_jni_PushNative_setAudioOptions
  (JNIEnv * env, jobject jobj, jint sampleRateInHz, jint channel) {

}

/*
 * Class:     com_example_audiopush_jni_PushNative
 * Method:    fireVideo
 * Signature: ([B)V
 */
JNIEXPORT void JNICALL Java_com_example_audiopush_jni_PushNative_fireVideo
  (JNIEnv *env, jobject jobj, jbyteArray data) {

	//NV21->YUV420
	jbyte *byte = env->GetByteArrayElements(data, NULL);
}

/*
 * Class:     com_example_audiopush_jni_PushNative
 * Method:    fireAudio
 * Signature: ([BI)V
 */
JNIEXPORT void JNICALL Java_com_example_audiopush_jni_PushNative_fireAudio
  (JNIEnv *env, jobject jobj, jbyteArray data, jint len) {

}
