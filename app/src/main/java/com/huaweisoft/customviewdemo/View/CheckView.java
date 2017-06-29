package com.huaweisoft.customviewdemo.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.huaweisoft.customviewdemo.R;

/**
 * Created by baiaj on 2017/6/29.
 */

public class CheckView extends View {

    private static final int ANIM_NULL = 0;         //动画状态-没有
    private static final int ANIM_CHECK = 1;        //动画状态-开启
    private static final int ANIM_UNCHECK = 2;      //动画状态-结束

    private int animDuation = 500;   // 动画时长

    // CheckView绘制最大次数
    private static final int MAX = 13;

    private Paint mPaint;
    private float mWidth;
    private float mHeight;
    private float circleX;
    private float circleY;
    private float radius;
    private Bitmap mBitmap;
    private Context mContext;
    private int cuurentIndex = -1;
    private float offset = 50;

    private int animState = ANIM_NULL;

    private Handler mHandler;

    public CheckView(Context context) {
        super(context);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        Log.e("CheckView", "Construct");
    }

    private void init(Context context) {
        Log.e("CheckView", "Init");

        mContext = context;

        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#FF3030"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);

        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.src_check);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (cuurentIndex < MAX && cuurentIndex >= 0) {
                    invalidate();
                    if (animState == ANIM_NULL) {
                        return;
                    } else if (animState == ANIM_CHECK) {
                        cuurentIndex++;
                    } else if (animState == ANIM_UNCHECK) {
                        cuurentIndex--;
                    }
                    sendEmptyMessageDelayed(0, animDuation / MAX);
                } else {
                    invalidate();
                    animState = ANIM_NULL;
                }
            }
        };
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("CheckView", "On Size Change");
        mWidth = w;
        mHeight = h;
        circleX = 0;
        circleY = 0;
        radius = mWidth / 4;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("CheckView", "OnDraw");
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        radius = (mBitmap.getWidth() + offset * 2) / 2;
        // 绘制圆
        canvas.drawCircle(circleX, circleY, radius, mPaint);
        // 绘制
        int bitmapHeight = mBitmap.getHeight();
        int bitmapWidth = mBitmap.getWidth();
        // 指定图片绘制区域
        Rect src = new Rect(0, 0, bitmapWidth / MAX * cuurentIndex, bitmapHeight);
        // 指定图片显示区域
        RectF dst = new RectF(-radius + offset, -radius + offset, -radius + offset + bitmapWidth / MAX * cuurentIndex, radius - offset);
        canvas.drawBitmap(mBitmap, src, dst, null);
    }

    public void check() {
        animState = ANIM_CHECK;
        cuurentIndex = 0;
        mHandler.sendEmptyMessageDelayed(0, animDuation / MAX);
    }

    public void unCheck() {
        animState = ANIM_UNCHECK;
        cuurentIndex = MAX - 1;
        mHandler.sendEmptyMessageDelayed(0, animDuation / MAX);
    }

    /**
     * 设置动画时长
     * @param time
     */
    public void setAnimDuration(int time) {
        if (time <= 0) {
            return;
        } else {
            animDuation = time;
        }
    }

    /**
     * 设置背景圆颜色
     *
     * @param color
     */
    public void setBackGroundColor(int color) {
        mPaint.setColor(color);
    }
}
