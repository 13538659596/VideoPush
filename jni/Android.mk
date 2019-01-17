LOCAL_PATH := $(call my-dir)


#faac
include $(CLEAR_VARS)
LOCAL_MODULE    := faac
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/faac/include
LOCAL_SRC_FILES := faac/libfaac.a
include $(PREBUILT_STATIC_LIBRARY)



#x264
include $(CLEAR_VARS)
LOCAL_MODULE    := x264
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/x264/include
LOCAL_SRC_FILES := x264/libx264.a
include $(PREBUILT_STATIC_LIBRARY)

#rtmpdump
include $(CLEAR_VARS)
LOCAL_MODULE    := rtmpdump
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/rtmpdump/include
LOCAL_SRC_FILES := rtmpdump/librtmp.a

include $(PREBUILT_STATIC_LIBRARY)



include $(CLEAR_VARS)

LOCAL_MODULE    := audioPush
LOCAL_SRC_FILES := audioPush.cpp

include $(BUILD_SHARED_LIBRARY)
