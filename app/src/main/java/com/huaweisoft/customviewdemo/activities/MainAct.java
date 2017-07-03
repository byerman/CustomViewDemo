package com.huaweisoft.customviewdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.huaweisoft.customviewdemo.R;

/**
 * Created by baiaj on 2017/6/23.
 */

public class MainAct extends AppCompatActivity implements View.OnClickListener {

    private Button btnGotoBaseView;
    private Button btnGotoPrecentView;
    private Button btnGotoConversion;
    private Button btnGotoCheckView;
    private Button btnGotoLoaddingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initView() {
        btnGotoBaseView = (Button) findViewById(R.id.btn_gotobase);
        btnGotoPrecentView = (Button) findViewById(R.id.btn_gotoprecent);
        btnGotoConversion = (Button) findViewById(R.id.btn_gotoconversion);
        btnGotoCheckView = (Button) findViewById(R.id.btn_gotocheck);
        btnGotoLoaddingView = (Button) findViewById(R.id.btn_gotoloadding);
    }

    private void initEvent() {
        btnGotoBaseView.setOnClickListener(this);
        btnGotoPrecentView.setOnClickListener(this);
        btnGotoConversion.setOnClickListener(this);
        btnGotoCheckView.setOnClickListener(this);
        btnGotoLoaddingView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_gotobase:
                gotoAct(BaseViewAct.class);
                break;
            case R.id.btn_gotoprecent:
                gotoAct(PrecentAct.class);
                break;
            case R.id.btn_gotoconversion:
                gotoAct(ConversionAct.class);
                break;
            case R.id.btn_gotocheck:
                gotoAct(CheckAct.class);
                break;
            case R.id.btn_gotoloadding:
                gotoAct(LoaddingAct.class);
                break;
            default:
                break;
        }
    }

    private void gotoAct(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
