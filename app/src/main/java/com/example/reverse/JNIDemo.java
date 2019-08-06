package com.example.reverse;

public class JNIDemo {
    static {
        System.loadLibrary("JNIDemo");
    }
    public static native String sayHello();
}
