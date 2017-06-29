package com.huaweisoft.customviewdemo.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.huaweisoft.customviewdemo.R;
import com.huaweisoft.customviewdemo.View.CheckView;

/**
 * Created by baiaj on 2017/6/29.
 */

public class CheckAct extends AppCompatActivity implements View.OnClickListener {

    private Button btnCheck,btnUnCheck;
    private CheckView checkView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        initView();
        initEvent();
    }

    private void initEvent() {
        btnCheck.setOnClickListener(this);
        btnUnCheck.setOnClickListener(this);
    }

    private void initView() {
        btnCheck = (Button) findViewById(R.id.btn_check);
        btnUnCheck = (Button) findViewById(R.id.btn_uncheck);
        checkView = (CheckView) findViewById(R.id.checkview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_check:
                checkView.check();
                break;
            case R.id.btn_uncheck:
                checkView.unCheck();
                break;
            default:
                break;
        }
    }
}
