package com.huaweisoft.customviewdemo.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.huaweisoft.customviewdemo.R;
import com.huaweisoft.customviewdemo.factory.LeafFactory;
import com.huaweisoft.customviewdemo.model.Leaf;
import com.huaweisoft.customviewdemo.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    // 纯白色
    private static final String PURE_WHITE_COLOR = "#ffffff";

    // 中等振幅大小
    private static final int MIDDLE_AMPLITUDE = 13;
    // 不同类型之间的振幅差距
    private static final int AMPLITUDE_DISPARITY = 5;

    // 进度条状态
    private static final int PROGRESS_LESS_MIN = 0;
    private static final int PROGRESS_MIN = 1;
    private static final int PROGRESS_LESS_MIDDLE = 2;
    private static final int PROGRESS_MIDDLE = 3;
    private static final int PROGRESS_MAX = 4;
    private static final int PROGRESS_NULL = 5;
    private static final int PROGRESS_PAUSE = 6;

    // handleMsg what
    private static final int FAN_ROTATE = 0;
    private static final int LEAF_SWING = 1;

    // 叶子飘动一个周期所花的时间
    private static final long LEAF_FLOAT_TIME = 3000;
    // 叶子旋转一周需要的时间
    private static final long LEAF_ROTATE_TIME = 2000;

    private Context mContext;
    private float mWidth;
    private float mHeight;
    // 进度条Paint和空白处Paint
    private Paint progressPaint;
    private Paint blankPaint;
    private Paint whitePaint;
    private Paint yellowPaint;
    private Paint mBitmapPaint;
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
    private int progressState = PROGRESS_NULL;
    // 风扇Bitmap
    private Bitmap fanBmp;
    private Bitmap leafBmp;
    // Handler
    private Handler mHandler;
    // 重绘次数
    private int index = 0;
    // 是否应该发消息让风扇旋转
    private boolean isSend;

    // 叶子列表
    private LeafFactory mLeafFactory = new LeafFactory();
    private List<Leaf> leafs = new ArrayList<>();
    // 叶子飘完一个周期的时间
    private long mLeafFloatTime;
    // 叶子旋转一周需要的时间
    private long mLeafRotateTime;
    // 叶子振幅
    private long mAmplitude;
    // 叶子宽度,叶子高度
    private float leafWidth, leafHeight;
    // isFanRotate
    private boolean isFanRotate = true;

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
        mLeafFloatTime = LEAF_FLOAT_TIME;
        mLeafRotateTime = LEAF_ROTATE_TIME;
        mAmplitude = MIDDLE_AMPLITUDE;
        leafs = mLeafFactory.generateLeafs();
        initBitmap();
        initSize();
        initHandler();
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

        whitePaint = new Paint();
        whitePaint.setColor(Color.parseColor(PURE_WHITE_COLOR));
        whitePaint.setStyle(Paint.Style.FILL);
        whitePaint.setStrokeWidth(5);

        yellowPaint = new Paint();
        yellowPaint.setColor(Color.parseColor(YELLOW_COLOR));
        yellowPaint.setStyle(Paint.Style.FILL);
        yellowPaint.setStrokeWidth(5);

        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setDither(true);
        mBitmapPaint.setFilterBitmap(true);
    }

    private void initSize() {
        arcRadius = UIUtil.dipToPx(mContext, 30);
        leftPadding = UIUtil.dipToPx(mContext, 8);
        rightPadding = UIUtil.dipToPx(mContext, 4);
        leftMargin = UIUtil.dipToPx(mContext, 50);
        rightMargin = UIUtil.dipToPx(mContext, 50);
        rectWidth = mWidth - leftMargin - rightMargin - arcRadius * 2;
        proArcRadius = arcRadius - leftPadding;
        proRectWidth = rectWidth - arcRadius;

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

        leafWidth = leafBmp.getWidth();
        leafHeight = leafBmp.getHeight();
    }

    private void initBitmap() {
        fanBmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.src_fan);
        leafBmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.src_leaf);
    }

    private void initHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case FAN_ROTATE:
                        if (progressState == PROGRESS_NULL || progressState == PROGRESS_MAX || progressState == PROGRESS_PAUSE) {
                            return;
                        }
                        isFanRotate = true;
                        postInvalidate();
                        sendEmptyMessageDelayed(FAN_ROTATE, 150);
                        break;
                    case LEAF_SWING:
                        break;
                    default:
                        break;
                }
            }
        };

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制背景和背景进度框
        canvas.drawColor(Color.parseColor(YELLOW_COLOR));
        canvas.drawArc(leftRectF, 90, 180, false, blankPaint);
        canvas.drawRect(middleRectF, blankPaint);
        canvas.drawArc(rightRectF, 270, 180, false, blankPaint);
        // 绘制右边风扇(右边白色圆，中间橙色圆，中间风扇)
        canvas.drawCircle(rightArcTopX + arcRadius, rightArcTopY + arcRadius, arcRadius, whitePaint);
        canvas.drawCircle(rightArcTopX + arcRadius, rightArcTopY + arcRadius, arcRadius - rightPadding, yellowPaint);
        // 风扇绘制区域
        if (isFanRotate) {
            index++;
        }
        drawRotateFan(canvas, 30 * index, rightArcTopX + arcRadius, rightArcTopY + arcRadius);
        // 绘制叶子
        if (progressState != PROGRESS_MAX && progressState != PROGRESS_NULL) {
            drawLeafs(canvas);
        }
        // 绘制进度条
        Log.e("baiaj", "进度状态:" + progressState);
        switch (progressState) {
            case PROGRESS_NULL:
                break;
            case PROGRESS_MAX:
                index = 0;
                canvas.drawArc(leftProRectF, 90, 180, true, progressPaint);
                canvas.drawRect(middleProRectF, progressPaint);
                rectPath.addRect(rightProRectF2, Path.Direction.CW);
                circlepath.addArc(rightRectF, 90, 180);
                rectPath.op(circlepath, Path.Op.DIFFERENCE);
                canvas.drawPath(rectPath, progressPaint);
                rectPath.close();
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
            postInvalidate();
            return;
        }
        float curWidth = progressAverWidth * progress;
        if (curWidth != progressCurWidth) {
            if (progressState == PROGRESS_NULL || progressState == PROGRESS_PAUSE || progressState == PROGRESS_MAX) {
                isSend = true;
            } else {
                isSend = false;
            }
            progressCurWidth = curWidth;
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
            }
            if (progress == 100) {
                progressState = PROGRESS_MAX;
            }
            isFanRotate = false;
            postInvalidate();
            if (isSend) {
                sendHandleMsg(FAN_ROTATE);
            }
        } else {
            // loadding暂停了
            progressState = PROGRESS_PAUSE;
        }
    }

    /**
     * 绘制自旋转的风扇
     *
     * @param canvas   画布
     * @param rotation 旋转角度
     * @param posX     bmp中心X坐标
     * @param posY     bmp中心Y坐标
     */
    private void drawRotateFan(Canvas canvas, float rotation, float posX, float posY) {
        Matrix matrix = new Matrix();
        RectF src = new RectF(0, 0, fanBmp.getWidth(), fanBmp.getHeight());
        RectF dst = new RectF(rightArcTopX + rightPadding * 3 / 2, rightArcTopY + rightPadding * 3 / 2,
                rightArcBottomX - rightPadding * 3 / 2, rightArcBottomY - rightPadding * 3 / 2);
        matrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);
        matrix.postRotate(rotation, posX, posY);
        canvas.drawBitmap(fanBmp, matrix, null);
    }

    // 绘制叶子
    private void drawLeafs(Canvas canvas) {
        mLeafRotateTime = mLeafRotateTime <= 0 ? LEAF_ROTATE_TIME : mLeafRotateTime;
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < leafs.size(); i++) {
            Leaf leaf = leafs.get(i);
            if (currentTime > leaf.getStartTime() && leaf.getStartTime() != 0) {
                // 绘制叶子 -- 根据叶子类型和当前时间得出叶子的xy
                getLeafLocation(leaf, currentTime);
                canvas.save();
                // 通过Matrix控制叶子旋转
                Matrix matrix = new Matrix();
                float transX = leftMargin + leaf.getX();
                float transY = leftMargin + leaf.getY();
                Log.e("baiaj", "leafx:" + transX + ",leafy:" + transY);
                matrix.postTranslate(transX, transY);
                // 通过时间关联旋转角度，则可以直接通过修改LEAF_ROTATE_TIME调节叶子旋转快慢
                float rotateFraction = ((currentTime - leaf.getStartTime()) % mLeafRotateTime)
                        / (float) mLeafRotateTime;
                int angle = (int) (rotateFraction * 360);
                // 根据叶子旋转方向确定叶子旋转角度
                int rotate = leaf.getRotateDirection() == 0 ? angle + leaf.getRotateAngle() : -angle
                        + leaf.getRotateAngle();
                matrix.postRotate(rotate, transX
                        + leafWidth / 2, transY + leafHeight / 2);
                canvas.drawBitmap(leafBmp, matrix, mBitmapPaint);
                canvas.restore();
            } else {
                continue;
            }
        }
    }

    private void getLeafLocation(Leaf leaf, long currentTime) {
        long intervalTime = currentTime - leaf.getStartTime();
        Log.e("baiaj", "intervalTime:" + intervalTime);
        mLeafFloatTime = mLeafFloatTime <= 0 ? LEAF_FLOAT_TIME : mLeafFloatTime;
        // 间隔小于0,代表叶子已经飘完
        if (intervalTime < 0) {
            return;
        } else if (intervalTime > mLeafFloatTime) {
            leaf.setStartTime(System.currentTimeMillis() + new Random().nextInt((int) mLeafFloatTime));
            return;
        }
        float fraction = (float) intervalTime / mLeafFloatTime;
        leaf.setX((int) (progressWidth - progressWidth * fraction + leftPadding));
        leaf.setY(getLocationY(leaf));
    }

    private int getLocationY(Leaf leaf) {
//        叶子曲线函数
//        y = A(wx+@)+h;
        float w = (float) (2f * Math.PI / progressWidth);
        float a = 40;
        return (int) (a * Math.sin((w * leaf.getX())) + arcRadius);
    }

    private void sendHandleMsg(int what) {
        mHandler.sendEmptyMessageDelayed(what, 150);
    }
}
