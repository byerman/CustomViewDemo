package com.huaweisoft.customviewdemo.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.huaweisoft.customviewdemo.utils.UIUtil;

/**
 * Created by baiaj on 2017/6/30.
 */

public class LoaddingView extends View {

    // 淡白色
    private static final String WHITE_COLOR = "#fde399";
    // 橙色
    private static final String ORANGE_COLOR = "#ffa800";
    // 黄色
    private static final String YELLOW_COLOR = "#fdcb49";

    // 进度条状态
    private static final int PROGRESS_LESS_MIN = 0;
    private static final int PROGRESS_MIN = 1;
    private static final int PROGRESS_LESS_MIDDLE = 2;
    private static final int PROGRESS_MIDDLE = 3;
    private static final int PROGRESS_MAX = 4;
    private static final int PROGRESS_NULL = 5;

    private Context mContext;
    private float mWidth;
    private float mHeight;
    // 进度条Paint和空白处Paint
    private Paint progressPaint;
    private Paint blankPaint;
    // 背景圆角矩形
    private RectF backgroundRectF;

    // View内部左右边距
    private float leftPadding;
    private float rightPadding;
    // View外部左右边距
    private float leftMargin;
    private float rightMargin;
    // arc半径
    private float arcRadius;
    // 矩形长度
    private float rectWidth;
    // 进度条arc半径
    private float proArcRadius;
    //    private float proArcChangeRadius;
    // 进度条矩形长度
    private float proRectWidth;
    // 进度条背景左边arc位置
    private float leftArcTopX, leftArcTopY;
    private float leftArcBottomX, leftArcBottomY;
    // 进度条背景右边Arc位置
    private float rightArcTopX, rightArcTopY;
    private float rightArcBottomX, rightArcBottomY;
    // 进度条背景矩形位置
    private float rectTopX, rectTopY, rectBottomX, rectBottomY;
    // 左边arc所属RectF
    private RectF leftRectF;
    // 中间RectF
    private RectF middleRectF;
    // 右边arc所属RectF
    private RectF rightRectF;
    // 进度条左边arc位置
    private float leftProArcTopX, leftProArcTopY;
    private float leftProArcBottomX, leftProArcBottomY;
    // 进度条右边arc位置
    private float rightProArcTopX, rightProArcTopY;
    private float rightProArcBottomX, rightProArcBottomY;
    // 进度条矩形位置
    private float proRectTopX, proRectTopY, proRectBottomX, proRectBottomY;
    // 进度条左边arc所属RectF
    private RectF leftProRectF;
    // 进度条中间RectF
    private RectF middleProRectF;
    // 进度条右边RectF1,RectF2
    private RectF rightProRectF;
    private RectF rightProRectF2;
    // 进度条右边矩形Path,圆形Path
    private Path rectPath = new Path();
    private Path circlepath = new Path();
    // 进度条宽度
    private float progressWidth;
    private float progressAverWidth;
    private float progressCurWidth;
    // 当前进度条状态
    private int progressState;

    public LoaddingView(Context context) {
        super(context, null);
    }

    public LoaddingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        initSize();
    }

    private void initPaint(Context context) {
        mContext = context;

        progressPaint = new Paint();
        progressPaint.setColor(Color.parseColor(ORANGE_COLOR));
        progressPaint.setStyle(Paint.Style.FILL);
        progressPaint.setStrokeWidth(5);

        blankPaint = new Paint();
        blankPaint.setColor(Color.parseColor(WHITE_COLOR));
        blankPaint.setStyle(Paint.Style.FILL);
        blankPaint.setStrokeWidth(5);
    }

    private void initSize() {
        arcRadius = UIUtil.dipToPx(mContext, 30);
        leftPadding = UIUtil.dipToPx(mContext, 8);
        rightPadding = UIUtil.dipToPx(mContext, 8);
        leftMargin = UIUtil.dipToPx(mContext, 50);
        rightMargin = UIUtil.dipToPx(mContext, 50);
        rectWidth = mWidth - leftMargin - rightMargin - arcRadius * 2;
        proArcRadius = arcRadius - leftPadding;
        proRectWidth = rectWidth - proArcRadius;

        progressWidth = proArcRadius * 2 + proRectWidth;
        progressAverWidth = progressWidth / 100;

        leftArcTopX = leftMargin;
        leftArcTopY = (mHeight - 2 * arcRadius) / 2;
        leftArcBottomX = leftArcTopX + 2 * arcRadius;
        leftArcBottomY = leftArcTopY + 2 * arcRadius;

        rectTopX = leftArcTopX + arcRadius;
        rectTopY = leftArcTopY;
        rectBottomX = rectTopX + rectWidth;
        rectBottomY = leftArcBottomY;

        rightArcTopX = rectBottomX - arcRadius;
        rightArcTopY = leftArcTopY;
        rightArcBottomX = rightArcTopX + 2 * arcRadius;
        rightArcBottomY = leftArcBottomY;

        leftProArcTopX = leftArcTopX + leftPadding;
        leftProArcTopY = leftArcTopY + leftPadding;
        leftProArcBottomX = leftProArcTopX + 2 * proArcRadius;
        leftProArcBottomY = leftProArcTopY + 2 * proArcRadius;

        proRectTopX = leftProArcTopX + proArcRadius;
        proRectTopY = leftProArcTopY;
        proRectBottomX = proRectTopX + proRectWidth;
        proRectBottomY = leftProArcBottomY;

        rightProArcTopX = proRectBottomX;
        rightProArcTopY = leftProArcTopY;
        rightProArcBottomX = rightProArcTopX + 2 * proArcRadius;
        rightProArcBottomY = leftProArcBottomY;

        leftRectF = new RectF(leftArcTopX, leftArcTopY, leftArcBottomX, leftArcBottomY);
        middleRectF = new RectF(rectTopX, rectTopY, rectBottomX, rectBottomY);
        rightRectF = new RectF(rightArcTopX, rightArcTopY, rightArcBottomX, rightArcBottomY);

        leftProRectF = new RectF(leftProArcTopX, leftProArcTopY, leftProArcBottomX, leftProArcBottomY);
        middleProRectF = new RectF(proRectTopX, proRectTopY, proRectBottomX, proRectBottomY);
        rightProRectF = new RectF(rightProArcTopX, rightProArcTopY, rightProArcBottomX, rightProArcBottomY);
        rightProRectF2 = new RectF(rightProArcTopX, rightProArcTopY, rightProArcBottomX - proArcRadius, rightProArcBottomY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制背景和背景进度框
        canvas.drawColor(Color.parseColor(YELLOW_COLOR));
        canvas.drawArc(leftRectF, 90, 180, false, blankPaint);
        canvas.drawRect(middleRectF, blankPaint);
        canvas.drawArc(rightRectF, 270, 180, false, blankPaint);
        // 绘制进度条
        Log.e("baiaj","进度状态:"+progressState);
        switch (progressState) {
            case PROGRESS_NULL:
                break;
            case PROGRESS_MAX:
                canvas.drawArc(leftProRectF, 90, 180, true, progressPaint);
                canvas.drawRect(middleProRectF, progressPaint);
                rectPath.addRect(rightProRectF2, Path.Direction.CW);
                circlepath.addArc(rightProRectF, 90, 180);
                rectPath.op(circlepath, Path.Op.DIFFERENCE);
                canvas.drawPath(rectPath, progressPaint);
                break;
            case PROGRESS_LESS_MIDDLE:
                canvas.drawArc(leftProRectF, 90, 180, true, progressPaint);
                float middleBottomX = proRectTopX + (progressCurWidth - proArcRadius);
                RectF middleRectF = new RectF(proRectTopX, proRectTopY, middleBottomX, proRectBottomY);
                canvas.drawRect(middleRectF, progressPaint);
                break;
            case PROGRESS_MIDDLE:
                canvas.drawArc(leftProRectF, 90, 180, true, progressPaint);
                canvas.drawRect(middleProRectF, progressPaint);
                break;
            case PROGRESS_LESS_MIN:
                float widthScale = proArcRadius / progressCurWidth;
                float curHeight = proArcRadius * 2 / widthScale;
                float curYDistance = (proArcRadius * 2 - curHeight) / 2;
                float topX = leftProArcTopX;
                float topY = leftProArcTopY + curYDistance;
                float bottomX = leftProArcTopX + 2 * progressCurWidth;
                float bottomY = topY + curHeight;
                RectF rectF = new RectF(topX, topY, bottomX, bottomY);
                canvas.drawArc(rectF, 90, 180, true, progressPaint);
                break;
            case PROGRESS_MIN:
                canvas.drawArc(leftProRectF, 90, 180, true, progressPaint);
                break;
            default:
                break;
        }

    }

    public void setProgress(int progress) {
        // 计算当前进度宽度
        if (progress == 0) {
            progressState = PROGRESS_NULL;
            return;
        }
        progressCurWidth = progressAverWidth * progress;
        Log.e("baiaj","半径:"+proArcRadius);
        Log.e("baiaj","总宽度:"+progressWidth);
        Log.e("baiaj","当前进度条宽度:"+progressCurWidth);
        if (progressCurWidth < proArcRadius) {
            progressState = PROGRESS_LESS_MIN;
        } else if (progressCurWidth == proArcRadius) {
            progressState = PROGRESS_MIN;
        } else if (progressCurWidth > proArcRadius && progressCurWidth < progressWidth - proArcRadius) {
            progressState = PROGRESS_LESS_MIDDLE;
        } else if (progressCurWidth == (proArcRadius + proRectWidth)) {
            progressState = PROGRESS_MIDDLE;
        } else if (progressCurWidth > (proArcRadius + proRectWidth) && progress < 100) {
            progressState = PROGRESS_MIDDLE;
        } else if (progress == 100) {
            progressState = PROGRESS_MAX;
        }
        invalidate();
    }
}
