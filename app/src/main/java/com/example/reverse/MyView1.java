package com.example.reverse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyView1 extends View {

    //内圆边框的宽度
    private float innerCircleWidth = 10;

    //外围圆的边框的宽度
    private float outerCircleWidth = 15;

    //画笔对象
    private Paint mPaint;

    //圆心的X坐标
    private float centerX;

    //圆心的Y坐标
    private float centerY;

    //内圆半径的颜色
    private int outerCircleColor = 0xFFD8D9D9;

    //外围半径的颜色 、点击的颜色
    private int innerCircleColor = 0xFFF1F1F1;

    //37°正弦值
    private float mSin45 = (float) Math.sin(45 * Math.PI / 180);

    //37°的余弦值

    private float mCos45 = (float) Math.cos(45 * Math.PI / 180);


    private float outerCircleRadius, innerCircleRadius;


    private AREA mArea;

    public MyView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView1(Context context) {
        this(context,null);
    }

    //长度
    int mWidth;
    //高度
    int mHeight;

    public void setWidthAndHeight(int w, int h) {
        this.mWidth = w;
        this.mHeight = h;

    }

    public MyView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int w = mWidth;
        int h = mHeight;

        centerX = w / 2;

        centerY = h / 2;

        outerCircleRadius = centerX - outerCircleWidth;

        innerCircleRadius = centerX / 3;
//        int defaultWidth = 600;
//        int defaultHeight = 600;
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int width = 0;
//        int height = 0;
//        switch (widthMode) {
//            case MeasureSpec.AT_MOST:
//                width = Math.min(defaultWidth, getMeasuredWidth());
//                break;
//            case MeasureSpec.EXACTLY:
//                width = Math.min(defaultWidth, getMeasuredWidth());
//                break;
//            case MeasureSpec.UNSPECIFIED:
//                width = defaultWidth;
//                break;
//            default:
//                break;
//        }
//        switch (heightMode) {
//            case MeasureSpec.AT_MOST:
//                height = Math.min(defaultHeight, getMeasuredHeight());
//                break;
//            case MeasureSpec.EXACTLY:
//                height = Math.min(defaultHeight, getMeasuredHeight());
//                break;
//            case MeasureSpec.UNSPECIFIED:
//                height = defaultHeight;
//                break;
//            default:
//                break;
//        }
//        setMeasuredDimension(width, height);
    }

    private void init() {
        mPaint = new Paint();
    }

    //定义接口
    public interface ChangeStateListener {
        void onChangeState(AREA area);
    }

    ChangeStateListener mChangeStateListener = null;

    public void setChangeStateListener(ChangeStateListener changeStateListener) {
        mChangeStateListener = changeStateListener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setAntiAlias(true);
        //画圆
        drawCircle(canvas);
        //花直线
        drawLine(canvas);
        //画背景选择器
        drawOnclikColor(canvas, mArea);
        //画文字
        drawText(canvas);
        //画图
       // drawImageView1(canvas);
       // drawImageView2(canvas);
        //画灯泡
        //drawImageViewCenter(canvas);
    }


    //画图
    private void drawImageView1(Canvas canvas) {
        Paint imgPaint = new Paint();
        imgPaint.setAntiAlias(true);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Matrix matrix = new Matrix();
        matrix.postScale(0.3f, 0.3f);
        Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);

        RectF mRectF = new RectF((float) (centerX - (mSin45 * outerCircleRadius) / 3 - 20), (float) (centerY + (0.55f * outerCircleRadius) - 10),
                (float) ((centerX - (mSin45 * outerCircleRadius) / 3) + 20), (float) (centerY + (0.55f * outerCircleRadius) + 30));
        canvas.drawBitmap(dstbmp, null, mRectF, imgPaint);
    }

    //画图
    private void drawImageView2(Canvas canvas) {
        Paint imgPaint = new Paint();
        imgPaint.setAntiAlias(true);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Matrix matrix = new Matrix();
        matrix.postScale(0.3f, 0.3f);
        Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);

        RectF mRectF = new RectF((float) (centerX + (mSin45 * outerCircleRadius) / 3 - 20), (float) (centerY + (0.55f * outerCircleRadius) - 10),
                (float) ((centerX + (mSin45 * outerCircleRadius) / 3) + 20), (float) (centerY + (0.55f * outerCircleRadius) + 30));
        canvas.drawBitmap(dstbmp, null, mRectF, imgPaint);
    }

    //画图
    private void drawImageViewCenter(Canvas canvas) {
        Paint imgPaint = new Paint();
        imgPaint.setAntiAlias(true);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Matrix matrix = new Matrix();
        matrix.postScale(0.4f, 0.4f);
        Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);

        RectF mRectF = new RectF((float) (centerX - 30), (float) (centerY - 30),
                (float) (centerX + 30), (float) (centerY + 30));
        canvas.drawBitmap(dstbmp, null, mRectF, imgPaint);
    }

    //写文字
    private void drawText(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(100);
        paint.setColor(innerCircleColor);
        paint.setTextAlign(Paint.Align.CENTER);


        //写1
        canvas.drawText("1", centerX - mSin45 * (outerCircleRadius / 2) - (outerCircleRadius - innerCircleRadius) * 2 / 5,
                centerY + mSin45 * (outerCircleRadius / 2) - (outerCircleRadius - innerCircleRadius) / 20, paint);

        //写2
        canvas.drawText("2", centerX - mSin45 * (outerCircleRadius / 2) - (outerCircleRadius - innerCircleRadius) * 2 / 5,
                centerY - mCos45 * (outerCircleRadius / 4), paint);
        //写3
        canvas.drawText("3", centerX - (mSin45 * outerCircleRadius) / 3, centerY - (0.55f * outerCircleRadius), paint);

        //写4
        canvas.drawText("4", centerX + mSin45 * (outerCircleRadius / 3),
                centerY - (0.55f * outerCircleRadius), paint);
        //写5
        canvas.drawText("5", centerX + mSin45 * (outerCircleRadius / 2) + (outerCircleRadius - innerCircleRadius) * 2 / 5,
                centerY - mCos45 * (outerCircleRadius / 4), paint);
        //写6
        canvas.drawText("6", centerX + mSin45 * (outerCircleRadius / 2) + (outerCircleRadius - innerCircleRadius) * 2 / 5,
                centerY + mSin45 * (outerCircleRadius / 2) - (outerCircleRadius - innerCircleRadius) / 20, paint);

    }

    //画圆
    private void drawCircle(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, outerCircleRadius, mPaint);

        mPaint.setColor(outerCircleColor);
        mPaint.setStrokeWidth(outerCircleWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centerX, centerY, outerCircleRadius, mPaint);

        mPaint.setColor(outerCircleColor);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centerX, centerY, innerCircleRadius, mPaint);

        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, innerCircleRadius, mPaint);


    }

    //画直线
    private void drawLine(Canvas canvas) {
        mPaint.setColor(outerCircleColor);
        mPaint.setStrokeWidth(5);
        //第一条
        canvas.drawLine(centerX, centerY - innerCircleRadius, centerX, centerY - outerCircleRadius, mPaint);
        //第二条
        canvas.drawLine(centerX - (mSin45 * outerCircleRadius), centerY - (mSin45 * outerCircleRadius),
                centerX - (mSin45 * innerCircleRadius), centerY - (mSin45 * innerCircleRadius), mPaint);
        //第三条
        canvas.drawLine(centerX - innerCircleRadius, centerY, centerX - outerCircleRadius, centerY, mPaint);
        //第四条
        canvas.drawLine(centerX - (mSin45 * outerCircleRadius), centerY + (mSin45 * outerCircleRadius),
                centerX - (mSin45 * innerCircleRadius), centerY + (mSin45 * innerCircleRadius), mPaint);
        //第五条
        canvas.drawLine(centerX - (mSin45 * outerCircleRadius), centerY + (mSin45 * outerCircleRadius),
                centerX - (mSin45 * innerCircleRadius), centerY + (mSin45 * innerCircleRadius), mPaint);
        //第六条
        canvas.drawLine(centerX, centerY + innerCircleRadius, centerX, centerY + outerCircleRadius, mPaint);
        //第七条
        canvas.drawLine(centerX + (mSin45 * outerCircleRadius), centerY + (mSin45 * outerCircleRadius),
                centerX + (mSin45 * innerCircleRadius), centerY + (mSin45 * innerCircleRadius), mPaint);
        //第八条
        canvas.drawLine(centerX + innerCircleRadius, centerY, centerX + outerCircleRadius, centerY, mPaint);
        //第九条
        canvas.drawLine(centerX + (mSin45 * outerCircleRadius), centerY - (mSin45 * outerCircleRadius),
                centerX + (mSin45 * innerCircleRadius), centerY - (mSin45 * innerCircleRadius), mPaint);
    }


    /**
     * 清空画布
     *
     * @param canvas
     */
    private void clearCanvas(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
    }

    //按下时的X，Y坐标
    float mDownX, mDownY;
    //松开时的X,Y坐标
    float mUpX, mUpY;

    //触摸事件 即点击事件的处理


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                Log.i("custom", "mDown==" + mDownX);
                Log.i("custom", "mDown==" + judgeArea(mDownX, mDownY));
                mArea = judgeArea(mDownX, mDownY);
                invalidate();
                //mChangeStateListener.onChangeState(mArea);
                if (null != mChangeStateListener && null != mArea) {
                    mChangeStateListener.onChangeState(mArea);
                }
                break;
            case MotionEvent.ACTION_UP:
//                clearCanvas(mCanvas);
                mArea = null;
                invalidate();
                break;
            default:
                break;
        }

        return true;
    }


    //判断区域
    public AREA judgeArea(float x, float y) {
        //判断是是否在大圆内
        if ((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY) <= outerCircleRadius * outerCircleRadius) {
            //判断是否在小圆内
            if ((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY) > innerCircleRadius * innerCircleRadius) {
                x = x - centerX;
                y = y - centerY;
                float tan = y / x;
                if (tan > Math.tan(45 * Math.PI / 180) && tan < Integer.MAX_VALUE && x < 0 && y < 0) {
                    return AREA.NUMBER_ONE;
                } else if (tan > 0 && tan < Math.tan(45 * Math.PI / 180) && x < 0 && y < 0) {
                    return AREA.NUMBER_TWO;
                } else if (tan < 0 && tan > -Math.tan(45 * Math.PI / 180) && x < 0 && y > 0) {
                    return AREA.NUMBER_THREE;
                } else if (tan < -Math.tan(45 * Math.PI / 180) && tan > Integer.MIN_VALUE && x < 0 && y > 0) {
                    return AREA.NUMBER_FOUR;
                } else if (tan > Math.tan(45 * Math.PI / 180) && tan < Integer.MAX_VALUE && x > 0 && y > 0) {
                    return AREA.NUMBER_FIVE;
                } else if (tan > 0 && tan < Math.tan(45 * Math.PI / 180) && x > 0 && y > 0) {
                    return AREA.NUMBER_SIX;
                } else if (tan > -Math.tan(45 * Math.PI / 180) && tan < 0 && x > 0 && y < 0) {
                    return AREA.NUMBER_SEVEN;
                } else if (tan > Integer.MIN_VALUE && tan < -Math.tan(45 * Math.PI / 180) && x > 0 && y < 0) {
                    return AREA.NUMBER_EIGHT;
                }
            } else {
                return AREA.CENTER;
            }
        } else {
            return null;
        }
        return null;
    }


    /**
     * 点击的时候绘制深色的扇形
     *
     * @param canvas
     * @param area
     */
    private void drawOnclikColor(Canvas canvas, AREA area) {
        //先诀条件
        if (area == null) {
            return;
        }
        //设置点击之后的颜色
        mPaint.setColor(outerCircleColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(outerCircleRadius - innerCircleRadius);

        switch (area) {
            case NUMBER_ONE:
                canvas.drawArc(new RectF(centerX - (outerCircleRadius - innerCircleRadius) - innerCircleWidth, centerY - (outerCircleRadius - innerCircleRadius) - innerCircleWidth,
                        centerX + (outerCircleRadius - innerCircleRadius) + innerCircleWidth, centerY
                        + (outerCircleRadius - innerCircleRadius) + innerCircleWidth), 225, 45, false, mPaint);
                break;
            case NUMBER_TWO:
                canvas.drawArc(new RectF(centerX - (outerCircleRadius - innerCircleRadius) - innerCircleWidth, centerY - (outerCircleRadius - innerCircleRadius) - innerCircleWidth,
                        centerX + (outerCircleRadius - innerCircleRadius) + innerCircleWidth, centerY
                        + (outerCircleRadius - innerCircleRadius) + innerCircleWidth), 180, 45, false, mPaint);
                break;
            case NUMBER_THREE:
                canvas.drawArc(new RectF(centerX - (outerCircleRadius - innerCircleRadius) - innerCircleWidth, centerY - (outerCircleRadius - innerCircleRadius) - innerCircleWidth,
                        centerX + (outerCircleRadius - innerCircleRadius) + innerCircleWidth, centerY
                        + (outerCircleRadius - innerCircleRadius) + innerCircleWidth), 135, 45, false, mPaint);
                break;
            case NUMBER_FOUR:
                canvas.drawArc(new RectF(centerX - (outerCircleRadius - innerCircleRadius) - innerCircleWidth, centerY - (outerCircleRadius - innerCircleRadius) - innerCircleWidth,
                        centerX + (outerCircleRadius - innerCircleRadius) + innerCircleWidth, centerY
                        + (outerCircleRadius - innerCircleRadius) + innerCircleWidth), 90, 45, false, mPaint);
                break;
            case NUMBER_FIVE:
                canvas.drawArc(new RectF(centerX - (outerCircleRadius - innerCircleRadius) - innerCircleWidth, centerY - (outerCircleRadius - innerCircleRadius) - innerCircleWidth,
                        centerX + (outerCircleRadius - innerCircleRadius) + innerCircleWidth, centerY
                        + (outerCircleRadius - innerCircleRadius) + innerCircleWidth), 45, 45, false, mPaint);
                break;
            case NUMBER_SIX:
                canvas.drawArc(new RectF(centerX - (outerCircleRadius - innerCircleRadius) - innerCircleWidth, centerY - (outerCircleRadius - innerCircleRadius) - innerCircleWidth,
                        centerX + (outerCircleRadius - innerCircleRadius) + innerCircleWidth, centerY
                        + (outerCircleRadius - innerCircleRadius) + innerCircleWidth), 0, 45, false, mPaint);
                break;
            case NUMBER_SEVEN:
                canvas.drawArc(new RectF(centerX - (outerCircleRadius - innerCircleRadius) - innerCircleWidth, centerY - (outerCircleRadius - innerCircleRadius) - innerCircleWidth,
                        centerX + (outerCircleRadius - innerCircleRadius) + innerCircleWidth, centerY
                        + (outerCircleRadius - innerCircleRadius) + innerCircleWidth), -45, 45, false, mPaint);
                break;
            case NUMBER_EIGHT:
                canvas.drawArc(new RectF(centerX - (outerCircleRadius - innerCircleRadius) - innerCircleWidth, centerY - (outerCircleRadius - innerCircleRadius) - innerCircleWidth,
                        centerX + (outerCircleRadius - innerCircleRadius) + innerCircleWidth, centerY
                        + (outerCircleRadius - innerCircleRadius) + innerCircleWidth), -90, 45, false, mPaint);
                break;
            case CENTER:
                mPaint.setStrokeWidth(0);
                mPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(centerX, centerY, innerCircleRadius, mPaint);
                break;
            default:
                break;
        }

    }

    /**
     * 关于不同区域的枚举，逆时针方向
     */
    public enum AREA {
        //第一区域
        NUMBER_ONE,
        //第二区域
        NUMBER_TWO,
        //第三区域
        NUMBER_THREE,
        //第四区域
        NUMBER_FOUR,
        //第五区域
        NUMBER_FIVE,
        //第六区域
        NUMBER_SIX,
        //第七区域
        NUMBER_SEVEN,
        //第八区域
        NUMBER_EIGHT,
        //中心区域
        CENTER,
    }
}