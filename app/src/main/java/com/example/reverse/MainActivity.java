package com.example.reverse;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.haha.perflib.Main;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public EditText editText;
    public TextView textView;
    public ProgressBar progressBar;
    public Button button;
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
        FrameLayout layout = new FrameLayout(this);

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

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        editText = new EditText(this);
        editText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        button = new Button(this);
        button.setText("翻译");
        button.setTextColor(Color.WHITE);
        button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setBackground(getBackground(1));

        progressBar = new ProgressBar(this);
        progressBar.setVisibility(View.GONE);

        textView= new TextView(this);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        linearLayout.addView(editText);
        linearLayout.addView(button);
        linearLayout.addView(progressBar);
        linearLayout.addView(textView);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setPadding(200,0,200,0);
        layout.addView(linearLayout);
        setContentView(layout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setBackground(getBackground(0));
                button.setClickable(false);
                request();
            }
        });


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

    public void request(){
        String text = editText.getText().toString();
        textView.setText("");
        if (TextUtils.isEmpty(text)){
            setButtonColor();
            Toast.makeText(this,"请确保输入文字",Toast.LENGTH_SHORT).show();
            return;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        Call<Translation> call = request.getCall(text);

        progressBar.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                response.body().show();
                setButtonColor();
                textView.setTextColor(Color.WHITE);
                textView.setText(response.body().getText());
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                System.out.println("连接失败");
                setButtonColor();
                Toast.makeText(MainActivity.this,"翻译失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setButtonColor(){
        progressBar.setVisibility(View.GONE);
        button.setBackground(getBackground(1));
        button.setClickable(true);
    }

    /**
     * @param state 1 is click, other is unclick
     * @return background
     */
    public GradientDrawable getBackground(int state){
        GradientDrawable drawable;
        drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(20f);

        if (state == 1) {
            drawable.setColor(Color.argb(255, 0, 150, 136));
            button.setTextColor(Color.WHITE);
        } else {
            drawable.setColor(Color.argb(255, 200, 221, 238));
            button.setTextColor(Color.BLACK);
        }
        return drawable;
    }
}
