package com.example.reverse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        LinearLayout layout = new LinearLayout(this);
        MyView myView = new MyView(this);
        MyView1 myView1 = new MyView1(this);
        myView1.setWidthAndHeight(1000, 1000);
        layout.addView(myView1);
        setContentView(layout);
        Log.d("123456", "onCreate: " + JNIDemo.sayHello());
    }
}
