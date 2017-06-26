package com.huaweisoft.customviewdemo.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.huaweisoft.customviewdemo.R;
import com.huaweisoft.customviewdemo.View.ConversionView;

/**
 * Created by baiaj on 2017/6/26.
 */

public class ConversionAct extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private Button btnTranslate;
    private Button btnScale;
    private Button btnRotate;
    private Button btnSkem;

    private TextView tvDeclare;

    private ConversionView conversionView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
        initView();
        initEvent();
    }

    private void initView() {
        btnTranslate = (Button) findViewById(R.id.btn_translate);
        btnScale = (Button) findViewById(R.id.btn_scale);
        btnRotate = (Button) findViewById(R.id.btn_rotate);
        btnSkem = (Button) findViewById(R.id.btn_skew);
        tvDeclare = (TextView) findViewById(R.id.tv_declare);
        conversionView = (ConversionView) findViewById(R.id.conversionview);
    }

    private void initEvent() {
        btnTranslate.setOnClickListener(this);
        btnScale.setOnClickListener(this);
        btnRotate.setOnClickListener(this);
        btnSkem.setOnClickListener(this);
        btnScale.setOnLongClickListener(this);
        btnRotate.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_translate:
                tvDeclare.setText("基于坐标原点位移200,200两次，并在坐标原点绘制圆形");
                conversionView.translate(200f,200f);
                break;
            case  R.id.btn_scale:
                tvDeclare.setText("把坐标原点移动到中心,绘制矩形，并以200,0为中心缩放");
                conversionView.scale(-0.5f,-0.5f,200,0);
                break;
            case R.id.btn_rotate:
                tvDeclare.setText("把坐标原点移动到中心，绘制矩形，并以200,0为中心，旋转180度");
                conversionView.rotate(180f,200,0);
                break;
            case R.id.btn_skew:
                tvDeclare.setText("把坐标原点移动到中心，绘制矩形，并在x方向偏移45度，y方向偏移0度,再次绘制矩形");
                conversionView.skem(1,0);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == R.id.btn_scale){
            conversionView.scaleDemo();
        }else if (v.getId() == R.id.btn_rotate){
            conversionView.rotateDemo();
        }
        return true;
    }
}
