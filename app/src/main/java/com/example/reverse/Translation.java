package com.example.reverse;

import android.text.TextUtils;

public class Translation {
    private int status;

    private content content;

    private static class content{
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
        private String[] word_mean;
    }

    public void show(){
        System.out.println(status);
        System.out.println(content.from);
        System.out.println(content.to);
        System.out.println(content.vendor);
        System.out.println(content.out);
        System.out.println(content.errNo);
    }
    public String getText(){
        if (TextUtils.isEmpty(content.vendor)){
            StringBuilder stringBuffer = new StringBuilder();
            for (int i = 0; i < content.word_mean.length; i++){
                stringBuffer.append(content.word_mean[i]);
                stringBuffer.append('\n');
            }
            return stringBuffer.toString();
        }
        return "翻译来源：" + content.vendor + "\n" + content.out;
    }
}
