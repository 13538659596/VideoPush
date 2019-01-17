#include <jni.h>
#include "com_example_audiopush_jni_PushNative.h"
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
 */
JNIEXPORT void JNICALL Java_com_example_audiopush_jni_PushNative_setVideoOptions
  (JNIEnv *env, jobject jobj, jint width, jint height, jint bitrate, jint fps) {

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

}

/*
 * Class:     com_example_audiopush_jni_PushNative
 * Method:    fireAudio
 * Signature: ([BI)V
 */
JNIEXPORT void JNICALL Java_com_example_audiopush_jni_PushNative_fireAudio
  (JNIEnv *env, jobject jobj, jbyteArray data, jint len) {

}
