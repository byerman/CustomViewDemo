package com.huaweisoft.customviewdemo;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.huaweisoft.customviewdemo.View.MyView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MyView myView;
    private Button btnSetColor, btnClear, btnPoint;
    private int[] colors = new int[]{Color.BLUE, Color.GRAY, Color.GREEN, Color.RED, Color.YELLOW, Color.WHITE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initView() {
        myView = (MyView) findViewById(R.id.myview);
        btnSetColor = (Button) findViewById(R.id.btn_setcolor);
        btnClear = (Button) findViewById(R.id.btn_clear);
        btnPoint = (Button) findViewById(R.id.btn_point);
    }

    private void initEvent() {
        btnSetColor.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_setcolor:
                int index = (int) (Math.random() * 6);
                myView.setColor(colors[index]);
                break;
            case R.id.btn_clear:
                myView.clear();
                break;
            case R.id.btn_point:
                selectPointNumber();
                break;
            default:
                break;
        }
    }

    private void selectPointNumber(){
        final EditText inputEdit = new EditText(this);
        inputEdit.setFocusable(true);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(getString(R.string.input_point_pts))
                .setView(inputEdit)
                .setNegativeButton(getString(R.string.input_cancel),null)
                .setPositiveButton(getString(R.string.input_concert), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String inputStr = inputEdit.getText().toString().replace("，",",");
                        String[] points = inputStr.split(",");
                        Log.e("baiaj","points:"+points.toString());
                        float[] pointPts = new float[points.length];
                        for (int i = 0; i < points.length; i++) {
                            float pointNumber = Float.parseFloat(points[i]);
                            pointPts[i] = pointNumber;
                        }
                        myView.drawPoints(pointPts);
                    }
                });
        mBuilder.show();
    }
}
