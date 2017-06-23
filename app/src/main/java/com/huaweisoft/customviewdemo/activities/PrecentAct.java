package com.huaweisoft.customviewdemo.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.huaweisoft.customviewdemo.R;
import com.huaweisoft.customviewdemo.View.PrecentView;
import com.huaweisoft.customviewdemo.model.PrecentData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baiaj on 2017/6/23.
 */

public class PrecentAct extends AppCompatActivity implements View.OnClickListener {

    private List<PrecentData> precentDatas = new ArrayList<>();
    private EditText etClothes;
    private EditText etEat;
    private EditText etLive;
    private EditText etWalk;
    private PrecentView precentView;
    private Button btnPost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precent);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        etClothes = (EditText) findViewById(R.id.et_clothes);
        etEat = (EditText) findViewById(R.id.et_eat);
        etLive = (EditText) findViewById(R.id.et_live);
        etWalk = (EditText) findViewById(R.id.et_walk);
        precentView = (PrecentView) findViewById(R.id.precentview);
        btnPost = (Button) findViewById(R.id.btn_post);
    }

    private void initEvent() {
        btnPost.setOnClickListener(this);
    }

    private void initData() {
        PrecentData clothesData = new PrecentData();
        clothesData.setName("衣");
        PrecentData eatData = new PrecentData();
        eatData.setName("食");
        PrecentData liveData = new PrecentData();
        liveData.setName("住");
        PrecentData walkData = new PrecentData();
        walkData.setName("行");
        precentDatas.add(clothesData);
        precentDatas.add(eatData);
        precentDatas.add(liveData);
        precentDatas.add(walkData);
    }

    @Override
    public void onClick(View v) {
        float firstValue = etClothes.getText().toString().equals("") ? 0:Float.parseFloat(etClothes.getText().toString());
        float secondValue = etEat.getText().toString().equals("") ? 0 : Float.parseFloat(etEat.getText().toString());
        float thirdValue = etLive.getText().toString().equals("") ? 0 : Float.parseFloat(etLive.getText().toString());
        float forthValue = etWalk.getText().toString().equals("") ? 0 : Float.parseFloat(etWalk.getText().toString());
        precentView.setPrecet(firstValue, secondValue, thirdValue, forthValue);
        precentView.setName(precentDatas.get(0).getName(), precentDatas.get(1).getName()
                , precentDatas.get(2).getName(), precentDatas.get(3).getName());
    }
}
