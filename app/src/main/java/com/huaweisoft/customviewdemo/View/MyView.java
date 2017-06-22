package com.huaweisoft.customviewdemo.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by baiaj on 2017/6/22.
 */

public class MyView extends View {

//    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private int mColor = -1;
    private float[] ponitPts = new float[]{};

    public MyView(Context context) {
        super(context);
        Log.e("BAIAJ", "myview");
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("BAIAJ", "onMeasure");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("BAIAJ", "onSizeChanged");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("BAIAJ", "onDraw");
        if (mColor != -1){
            canvas.drawColor(mColor);
        }
        if (ponitPts != null){
            canvas.drawPoints(ponitPts,mPaint);
        }
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
    }

    public void setColor(int color) {
        mColor = color;
        invalidate();
    }

    public void clear() {
        invalidate();
    }

    public void drawPoints(float[] pts){
        ponitPts = pts;
    }

    public void drawLine() {

    }

}
