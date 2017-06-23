package com.huaweisoft.customviewdemo.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
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
    // 底色
    private int mColor = -1;
    // 点坐标
    private float[] ponitPts = new float[]{};
    // 线坐标
    private float[] linePts = new float[]{};
    // 矩形坐标
    private float rectX1, rectX2, rectY1, rectY2;
    // 圆角矩形坐标
    private float roundX1, roundY1, roundX2, roundY2, roundRx, roundRy;
    // 椭圆坐标
    private float ovalX1, ovalY1, ovalX2, ovalY2;
    // 圆坐标
    private float cicleX1, cicleY1, radiu;
    // 圆弧角度
    private float arcStartAngle = -1;
    private float arcSweepAngle = -1;
    // paint的颜色
    private int paintColor = Color.BLACK;

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
        if (mColor != -1) {
            canvas.drawColor(mColor);
        }
        if (ponitPts != null && ponitPts.length >= 2) {
            canvas.drawPoints(ponitPts, mPaint);
        }
        if (linePts != null && linePts.length >= 2) {
            canvas.drawLines(linePts, mPaint);
        }
        if (rectX1 != 0) {
            RectF rect = new RectF(rectX1, rectY1, rectX2, rectY2);
            canvas.drawRect(rect, mPaint);
        }
        if (roundX1 != 0) {
            RectF rect = new RectF(roundX1, roundY1, roundX2, roundY2);
            canvas.drawRoundRect(rect, roundRx, roundRy, mPaint);
        }
        if (ovalX1 != 0) {
            RectF rectF = new RectF(ovalX1, ovalY1, ovalX2, ovalY2);
            canvas.drawOval(rectF, mPaint);
        }
        if (cicleX1 != 0) {
            canvas.drawCircle(cicleX1, cicleY1, radiu, mPaint);
        }
        if (arcStartAngle != -1 && rectX1 != 0){
            RectF rect = new RectF(rectX1, rectY1, rectX2, rectY2);
            mPaint.setColor(Color.BLUE);
            canvas.drawArc(rect,arcStartAngle,arcSweepAngle,true,mPaint);
            mPaint.setColor(Color.BLACK);
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
        mColor = -1;
        ponitPts = null;
        linePts = null;
        rectX1 = 0;
        roundX1 = 0;
        ovalX1 = 0;
        cicleX1 = 0;
        arcStartAngle = -1;
        invalidate();
    }

    public void drawPoints(float[] pts) {
        ponitPts = pts;
    }

    public void drawLines(float[] pts) {
        linePts = pts;
    }

    public void drawRect(float x1, float y1, float x2, float y2) {
        rectX1 = x1;
        rectX2 = x2;
        rectY1 = y1;
        rectY2 = y2;
    }

    public void drawRoundRect(float x1, float y1, float x2, float y2, float rx, float ry) {
        roundX1 = x1;
        roundX2 = x2;
        roundY1 = y1;
        roundY2 = y2;
        roundRx = rx;
        roundRy = ry;
    }

    public void drawOval(float x1, float y1, float x2, float y2) {
        ovalX1 = x1;
        ovalY1 = y1;
        ovalX2 = x2;
        ovalY2 = y2;
    }

    public void drawCicle(float x1, float y1, float radius) {
        cicleX1 = x1;
        cicleY1 = y1;
        radiu = radius;
    }

    public void drawArc(float startAngle, float sweepAngle){
        arcStartAngle = startAngle;
        arcSweepAngle = sweepAngle;
    }

}
