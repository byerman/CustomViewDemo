package com.huaweisoft.customviewdemo.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by baiaj on 2017/6/26.
 */

public class ConversionView extends View {

    // 当前View的宽高
    private float mWidth;
    private float mHeight;
    // 画笔
    private Paint mPaint = new Paint();
    // 位移坐标
    private float transX, transY;
    // 旋转角度
    private float rotateDigree = -1;
    // 缩放比例
    private float scaleX, scaleY;
    // 缩放中心偏移量
    private float xScaleOffset, yScaleOffset;
    // 错切x偏移角度的tan值，错切y偏移角度的tan值
    private float skewX, skewY;
    // demo flag
    private boolean isScaleDemo;
    private boolean isRotateDemo;
    private boolean isSkew;

    public ConversionView(Context context) {
        super(context);
    }

    public ConversionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    private void initPaint() {
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (transX != 0) {
            // 按照位移量画两个圆
            // 第一个圆
            mPaint.setColor(Color.BLACK);
            canvas.translate(transX, transY);
            canvas.drawCircle(0, 0, transX / 2, mPaint);
            // 第二个圆
            mPaint.setColor(Color.BLUE);
            canvas.translate(transX, transY);
            canvas.drawCircle(0, 0, transX / 2, mPaint);
            // 初始化
            transX = 0;
            transY = 0;
            mPaint.setColor(Color.WHITE);
        }
        if (scaleX != 0) {
            // 将坐标系原点移到画布中心
            canvas.translate(mWidth / 2, mHeight / 2);
            // 绘制矩形
            RectF rectF = new RectF(0, -400, 400, 0);
            mPaint.setColor(Color.BLACK);
            canvas.drawRect(rectF, mPaint);
            // 缩放
            canvas.scale(scaleX, scaleY, xScaleOffset, yScaleOffset);
            mPaint.setColor(Color.BLUE);
            canvas.drawRect(rectF, mPaint);
            scaleX = 0;
            scaleY = 0;
            xScaleOffset = 0;
            yScaleOffset = 0;
            mPaint.setColor(Color.WHITE);
        }
        if (isScaleDemo) {
            canvas.translate(mWidth / 2, mHeight / 2);
            RectF rectF = new RectF(-400, -400, 400, 400);
            mPaint.setColor(Color.BLACK);
            for (int i = 0; i < 20; i++) {
                canvas.scale(0.9f, 0.9f);
                canvas.drawRect(rectF, mPaint);
            }
            mPaint.setColor(Color.WHITE);
            isScaleDemo = false;
        }
        if (rotateDigree != -1) {
            canvas.translate(mWidth / 2, mHeight / 2);
            RectF rectF = new RectF(0, -400, 400, 0);
            mPaint.setColor(Color.BLACK);
            canvas.drawRect(rectF, mPaint);
            // 旋转
            mPaint.setColor(Color.BLUE);
            canvas.rotate(rotateDigree, xScaleOffset, yScaleOffset);
            canvas.drawRect(rectF, mPaint);
            // 初始化
            rotateDigree = -1;
            xScaleOffset = 0;
            yScaleOffset = 0;
            mPaint.setColor(Color.WHITE);
        }
        if (isRotateDemo) {
            canvas.translate(mWidth / 2, mHeight / 2);
            // 绘制两个圆形
            mPaint.setColor(Color.BLACK);
            canvas.drawCircle(0, 0, 380, mPaint);
            canvas.drawCircle(0, 0, 400, mPaint);
            // 绘制直线
            mPaint.setColor(Color.BLUE);
            for (int i = 0; i <= 360; i = i + 10) {
                canvas.drawLine(0, 380, 0, 400, mPaint);
                canvas.rotate(10);
            }
            isRotateDemo = false;
            mPaint.setColor(Color.WHITE);
        }
        if (isSkew){
            canvas.translate(mWidth/2,mHeight/2);
            //绘制矩形
            mPaint.setColor(Color.BLACK);
            RectF rectF = new RectF(0,0,200,200);
            canvas.drawRect(rectF,mPaint);
            // 错切
            canvas.skew(skewX,skewY);
            mPaint.setColor(Color.BLUE);
            canvas.drawRect(rectF,mPaint);
            mPaint.setColor(Color.WHITE);
            isSkew = false;
        }
    }

    public void translate(float dx, float dy) {
        transX = dx;
        transY = dy;
        invalidate();
    }

    public void scale(float sx, float sy, float xOffset, float yOffset) {
        scaleX = sx;
        scaleY = sy;
        xScaleOffset = xOffset;
        yScaleOffset = yOffset;
        invalidate();
    }

    public void scaleDemo() {
        isScaleDemo = true;
        invalidate();
    }

    public void rotate(float digree, float xOffset, float yOffset) {
        rotateDigree = digree;
        xScaleOffset = xOffset;
        yScaleOffset = yOffset;
        invalidate();
    }

    public void rotateDemo() {
        isRotateDemo = true;
        invalidate();
    }

    public void skem(float sx, float sy) {
        skewX = sx;
        skewY = sy;
        isSkew = true;
        invalidate();
    }
}
