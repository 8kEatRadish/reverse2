package com.example.reverse;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            } else {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.flags |= flagTranslucentStatus | flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
        LinearLayout layout = new LinearLayout(this);
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setImageResource(R.drawable.i001);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        scaleAnimation.setDuration(6000);
        layout.addView(imageView);
        imageView.startAnimation(scaleAnimation);
        setContentView(layout);
        MyApplication.getRefWatcher().watch(this);
        Observer<String> receiver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("123456","接收开始了");
            }

            @Override
            public void onNext(String value) {
                Log.d("123456","接收的参数为：" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("123456","接收出错：" + e);
            }

            @Override
            public void onComplete() {
                Log.d("123456","接收完成");
            }
        };
        Observable justObservable = Observable.just("just1","just2");
        //justObservable.subscribe(receiver);
        List<String> list = new ArrayList<>();
        list.add("from1");
        list.add("from2");
        list.add("from3");


        String[]items = {"just1","just2","just3","just4","just5","just6"};

        Observable fromeObservable = Observable.fromArray(items);
        fromeObservable.subscribe(receiver);


    }
}
