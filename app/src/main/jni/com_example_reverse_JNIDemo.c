//
// Created by 61454 on 19/07/10.
//
#include <jni.h>
#include <com_example_reverse_JNIDemo.h>

JNIEXPORT jstring JNICALL Java_com_example_reverse_JNIDemo_sayHello
  (JNIEnv *env, jclass jobj){
   return (*env)->NewStringUTF(env,"JNI hahahahahahahaha");
  }