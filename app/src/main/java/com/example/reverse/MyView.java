package com.example.reverse;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
    private Paint paint = new Paint();
    public MyView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Region region = new Region(new Rect(50, 50, 200, 100));
        drawRegion(canvas, region, paint);
    }

    private void drawRegion(Canvas canvas, Region region, Paint paint) {
        RegionIterator iterator = new RegionIterator(region);

        Rect r = new Rect();
        while (iterator.next(r)){
            canvas.drawRect(r, paint);
        }
    }
}
