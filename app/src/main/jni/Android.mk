
LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := JNIDemo
LOCAL_SRC_FILES := com_example_reverse_JNIDemo.c

include $(BUILD_SHARED_LIBRARY)