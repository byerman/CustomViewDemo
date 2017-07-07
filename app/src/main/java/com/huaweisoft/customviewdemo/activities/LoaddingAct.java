package com.huaweisoft.customviewdemo.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

import com.huaweisoft.customviewdemo.R;
import com.huaweisoft.customviewdemo.View.LoaddingView;

import java.util.Random;

/**
 * Created by baiaj on 2017/6/30.
 */

public class LoaddingAct extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private static final int REFRESH_PROGRESS = 0x10;

    private LoaddingView loaddingView;
    private SeekBar durationBar;
    private SeekBar swingBar;
    private SeekBar progressBar;
    private TextView tvAnimDuration;
    private TextView tvLeafSwing;
    private TextView tvProgress;
    private int mProgress;

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_PROGRESS:
                    if (mProgress == 100){
                        return;
                    }
                    if (mProgress < 40) {
                        mProgress += 1;
                        // 随机800ms以内刷新一次
                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                                new Random().nextInt(600));
                        loaddingView.setProgress(mProgress);
                    } else {
                        mProgress += 1;
                        // 随机1200ms以内刷新一次
                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                                new Random().nextInt(400));
                        loaddingView.setProgress(mProgress);

                    }
                    break;

                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaddingview);
        initView();
        initEvent();
        updateTxt();
        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,2000);
    }

    private void initView() {
        loaddingView = (LoaddingView) findViewById(R.id.loaddingview);
        durationBar = (SeekBar) findViewById(R.id.sk_set_duration);
        swingBar = (SeekBar) findViewById(R.id.sk_set_leaf);
        progressBar = (SeekBar) findViewById(R.id.sk_set_progress);
        tvAnimDuration = (TextView) findViewById(R.id.tv_anim_duration);
        tvLeafSwing = (TextView) findViewById(R.id.tv_leaf_swing);
        tvProgress = (TextView) findViewById(R.id.tv_progress);
    }

    private void initEvent() {
        durationBar.setOnSeekBarChangeListener(this);
        swingBar.setOnSeekBarChangeListener(this);
        progressBar.setOnSeekBarChangeListener(this);
    }

    private void updateTxt(){
        tvAnimDuration.setText(getString(R.string.anim_duration_txt) + durationBar.getProgress());
        tvLeafSwing.setText(getString(R.string.leaf_swing) + swingBar.getProgress());
        tvProgress.setText(getString(R.string.progress) + progressBar.getProgress());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()){
            case R.id.sk_set_duration:
                break;
            case R.id.sk_set_leaf:
                break;
            case R.id.sk_set_progress:
                tvProgress.setText(getString(R.string.progress) + progressBar.getProgress());
                loaddingView.setProgress(progressBar.getProgress());
                break;
            default:
                break;
        }
    }
}
