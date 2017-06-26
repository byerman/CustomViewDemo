package com.huaweisoft.customviewdemo.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by baiaj on 2017/6/23.
 */

public class PrecentView extends View {

    // 当前View的宽高
    private float mWidth;
    private float mHeight;
    private float radius;
    // Paint
    private Paint mPaint = new Paint();
    // 百分比对应的名字和比例
    private float firstPrecent, secondPrecent, thirdPrecent, forthPrecent;
    private float firstAngel, secondAngel, thirdAngel, forthAngel;
    private String firstName, secondName, thirdName, forthName;
    // 对应的颜色
    private int firstColor = Color.parseColor("#90EE90");
    private int secondColor = Color.parseColor("#B2DFEE");
    private int thirdColor = Color.parseColor("#CD8500");
    private int forthColor = Color.parseColor("#CD2626");
    // 字体对应坐标
    private float textX;
    private float textY;
    // 字体y偏移量
    private float yOffset = 10;

    public PrecentView(Context context) {
        super(context);
    }

    public PrecentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        Log.e("baiaj", "precentW:" + mWidth + ",precentH:" + mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = Math.min(mWidth, mHeight);
        radius = mWidth / 8 * 3;
        canvas.drawCircle(mWidth / 2, mWidth / 2, radius, mPaint);
        RectF rectF = new RectF(mWidth / 8, mWidth / 8, mWidth / 8 * 7, mWidth / 8 * 7);
        Log.e("baiaj", "angle:" + firstAngel + "," + secondAngel + "," + thirdAngel + "," + forthAngel);
        if (firstPrecent != 0) {
            mPaint.setColor(firstColor);
            canvas.drawArc(rectF, 0, firstAngel, true, mPaint);
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(50);
            setTextPts(firstAngel == 360f ? 360 : firstAngel / 2);
            Log.e("baiaj", "text1:" + textX + "," + textY);
            canvas.drawText(firstName, textX, textY, mPaint);
        }
        if (secondPrecent != 0) {
            mPaint.setColor(secondColor);
            canvas.drawArc(rectF, firstAngel, secondAngel, true, mPaint);
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(50);
            if (secondAngel - firstAngel == 360f){
                setTextPts(360f);
            }else {
                setTextPts(firstAngel + secondAngel / 2);
            }
            Log.e("baiaj", "text2:" + textX + "," + textY);
            canvas.drawText(secondName, textX, textY, mPaint);
        }
        if (thirdPrecent != 0) {
            mPaint.setColor(thirdColor);
            canvas.drawArc(rectF, firstAngel + secondAngel, thirdAngel, true, mPaint);
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(50);
            if (thirdAngel - firstAngel - secondAngel == 360f){
                setTextPts(360);
            }else {
                setTextPts(firstAngel + secondAngel + thirdAngel / 2);
            }
            Log.e("baiaj", "text3:" + textX + "," + textY);
            canvas.drawText(thirdName, textX, textY, mPaint);
        }
        if (forthPrecent != 0) {
            mPaint.setColor(forthColor);
            canvas.drawArc(rectF, firstAngel + secondAngel + thirdAngel, forthAngel, true, mPaint);
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(50);
            if (forthAngel - firstAngel - secondAngel - thirdAngel == 360f){
                setTextPts(360f);
            }else {
                setTextPts(firstAngel + secondAngel + thirdAngel + forthAngel / 2);
            }
            Log.e("baiaj", "text4:" + textX + "," + textY);
            canvas.drawText(forthName, textX, textY, mPaint);
        }
        mPaint.setColor(Color.WHITE);
    }

    private void initPaint() {
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);
    }

    private void setTextPts(float angle) {
        // 弧度，sin,cos传进去的是弧度，不是角度
        double rad = angle * Math.PI / 180;
        ;
        if (angle == 360f) {
            textX = mWidth / 2;
            textY = mWidth / 2;
        } else {
            if (angle < 90f) {
                textX = (float) (mWidth / 2 + radius / 2 * Math.cos(rad));
                textY = (float) (mWidth / 2 + radius / 2 * Math.sin(rad)) + yOffset;
            } else if (angle == 90f) {
                textX = mWidth / 2;
                textY = mWidth / 2 + radius / 2;
            } else if (angle > 90f && angle < 180f) {
                rad = (180 - angle) * Math.PI / 180;
                textX = (float) (mWidth / 2 - radius / 2 * Math.cos(rad));
                textY = (float) (mWidth / 2 + radius / 2 * Math.sin(rad)) + yOffset;
            } else if (angle == 180f) {
                textX = mWidth / 2 - radius / 2;
                textY = mWidth / 2;
            } else if (angle > 180f && angle < 270f) {
                rad = (angle - 180) * Math.PI / 180;
                textX = (float) (mWidth / 2 - radius / 2 * Math.cos(rad));
                textY = (float) (mWidth / 2 - radius / 2 * Math.sin(rad)) + yOffset;
            } else if (angle == 270f) {
                textX = mWidth / 2;
                textY = mWidth / 2 - radius / 2;
            } else if (angle > 270f && angle < 360f) {
                rad = (360 - angle) * Math.PI / 180;
                textX = (float) (mWidth / 2 + radius / 2 * Math.cos(rad));
                textY = (float) (mWidth / 2 - radius / 2 * Math.sin(rad)) + yOffset;
            }
        }
    }

    public void setColor(int firstColor, int sencondColor, int thirdColor, int forthColor) {
        this.firstColor = firstColor;
        this.secondColor = sencondColor;
        this.thirdColor = thirdColor;
        this.forthColor = forthColor;
    }

    public void setPrecet(float firstPrecent, float secondPrecent, float thirdPrecent, float forthPrecent) {
        if (firstPrecent + secondPrecent + thirdPrecent + forthPrecent > 100f) {
            return;
        }
        this.firstPrecent = firstPrecent;
        this.secondPrecent = secondPrecent;
        this.thirdPrecent = thirdPrecent;
        this.forthPrecent = forthPrecent;
        this.firstAngel = 360 * firstPrecent / 100f;
        this.secondAngel = 360 * secondPrecent / 100f;
        this.thirdAngel = 360 * thirdPrecent / 100f;
        this.forthAngel = 360 * forthPrecent / 100f;
        invalidate();
    }

    public void setName(String firstName, String secondName, String thirdName, String forthName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.forthName = forthName;
    }

}
