package com.huaweisoft.customviewdemo.activities;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.huaweisoft.customviewdemo.R;
import com.huaweisoft.customviewdemo.View.MyView;

public class BaseViewAct extends AppCompatActivity implements View.OnClickListener{

    private MyView myView;
    private Button btnSetColor, btnClear, btnPoint,btnLine,btnRect,btnRoundRect,btnOval,btnCicle,btnArc,btnText;
    private int[] colors = new int[]{Color.BLUE, Color.GRAY, Color.GREEN, Color.RED, Color.YELLOW, Color.WHITE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseview);
        initView();
        initEvent();
    }

    private void initView() {
        myView = (MyView) findViewById(R.id.myview);
        btnSetColor = (Button) findViewById(R.id.btn_setcolor);
        btnClear = (Button) findViewById(R.id.btn_clear);
        btnPoint = (Button) findViewById(R.id.btn_point);
        btnLine = (Button) findViewById(R.id.btn_line);
        btnRect = (Button) findViewById(R.id.btn_rect);
        btnRoundRect = (Button) findViewById(R.id.btn_roundrect);
        btnOval = (Button) findViewById(R.id.btn_oval);
        btnCicle = (Button) findViewById(R.id.btn_cicle);
        btnArc = (Button) findViewById(R.id.btn_arc);
        btnText = (Button) findViewById(R.id.btn_text);
    }

    private void initEvent() {
        btnSetColor.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnLine.setOnClickListener(this);
        btnRect.setOnClickListener(this);
        btnRoundRect.setOnClickListener(this);
        btnOval.setOnClickListener(this);
        btnCicle.setOnClickListener(this);
        btnArc.setOnClickListener(this);
        btnText.setOnClickListener(this);
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
                inputPointNumber();
                break;
            case R.id.btn_line:
                inputLineNumber();
                break;
            case R.id.btn_rect:
                inputRectNumber();
                break;
            case R.id.btn_roundrect:
                inputRoundRectNumber();
                break;
            case R.id.btn_oval:
                inputOvalNumber();
                break;
            case R.id.btn_cicle:
                inputCicleNumber();
                break;
            case R.id.btn_arc:
                inputArcNumber();
                break;
            case R.id.btn_text:
                inputText();
                break;
            default:
                break;
        }
    }

    private void inputPointNumber(){
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

    private void inputLineNumber(){
        final EditText inputEdit = new EditText(this);
        inputEdit.setFocusable(true);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(getString(R.string.input_line_pts))
                .setView(inputEdit)
                .setNegativeButton(getString(R.string.input_cancel),null)
                .setPositiveButton(getString(R.string.input_concert), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String inputStr = inputEdit.getText().toString().replace("，",",");
                        String[] lines = inputStr.split(",");
                        float[] linePts = new float[lines.length];
                        for (int i = 0; i < lines.length; i++) {
                            float pointNumber = Float.parseFloat(lines[i]);
                            linePts[i] = pointNumber;
                        }
                        myView.drawLines(linePts);
                    }
                });
        mBuilder.show();
    }

    private void inputRectNumber(){
        final EditText inputEdit = new EditText(this);
        inputEdit.setFocusable(true);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(getString(R.string.input_rect_pts))
                .setView(inputEdit)
                .setNegativeButton(getString(R.string.input_cancel),null)
                .setPositiveButton(getString(R.string.input_concert), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String inputStr = inputEdit.getText().toString().replace("，",",");
                        String[] rects = inputStr.split(",");
                        float[] rectPts = new float[rects.length];
                        for (int i = 0; i < rects.length; i++) {
                            float pointNumber = Float.parseFloat(rects[i]);
                            rectPts[i] = pointNumber;
                        }
                        myView.drawRect(rectPts[0],rectPts[1],rectPts[2],rectPts[3]);
                    }
                });
        mBuilder.show();
    }

    private void inputRoundRectNumber(){
        final EditText inputEdit = new EditText(this);
        inputEdit.setFocusable(true);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(getString(R.string.input_roundrect_pts))
                .setView(inputEdit)
                .setNegativeButton(getString(R.string.input_cancel),null)
                .setPositiveButton(getString(R.string.input_concert), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String inputStr = inputEdit.getText().toString().replace("，",",");
                        String[] rects = inputStr.split(",");
                        float[] rectPts = new float[rects.length];
                        for (int i = 0; i < rects.length; i++) {
                            float pointNumber = Float.parseFloat(rects[i]);
                            rectPts[i] = pointNumber;
                        }
                        myView.drawRoundRect(rectPts[0],rectPts[1],rectPts[2],rectPts[3],rectPts[4],rectPts[5]);
                    }
                });
        mBuilder.show();
    }

    private void inputOvalNumber(){
        final EditText inputEdit = new EditText(this);
        inputEdit.setFocusable(true);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(getString(R.string.input_oval_pts))
                .setView(inputEdit)
                .setNegativeButton(getString(R.string.input_cancel),null)
                .setPositiveButton(getString(R.string.input_concert), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String inputStr = inputEdit.getText().toString().replace("，",",");
                        String[] rects = inputStr.split(",");
                        float[] rectPts = new float[rects.length];
                        for (int i = 0; i < rects.length; i++) {
                            float pointNumber = Float.parseFloat(rects[i]);
                            rectPts[i] = pointNumber;
                        }
                        myView.drawOval(rectPts[0],rectPts[1],rectPts[2],rectPts[3]);
                    }
                });
        mBuilder.show();
    }

    private void inputCicleNumber(){
        final EditText inputEdit = new EditText(this);
        inputEdit.setFocusable(true);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(getString(R.string.input_cicle_pts))
                .setView(inputEdit)
                .setNegativeButton(getString(R.string.input_cancel),null)
                .setPositiveButton(getString(R.string.input_concert), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String inputStr = inputEdit.getText().toString().replace("，",",");
                        String[] rects = inputStr.split(",");
                        float[] rectPts = new float[rects.length];
                        for (int i = 0; i < rects.length; i++) {
                            float pointNumber = Float.parseFloat(rects[i]);
                            rectPts[i] = pointNumber;
                        }
                        myView.drawCicle(rectPts[0],rectPts[1],rectPts[2]);
                    }
                });
        mBuilder.show();
    }

    private void inputArcNumber(){
        final EditText inputEdit = new EditText(this);
        inputEdit.setFocusable(true);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(getString(R.string.input_arc_pts))
                .setView(inputEdit)
                .setNegativeButton(getString(R.string.input_cancel),null)
                .setPositiveButton(getString(R.string.input_concert), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String inputStr = inputEdit.getText().toString().replace("，",",");
                        String[] rects = inputStr.split(",");
                        float[] rectPts = new float[rects.length];
                        for (int i = 0; i < rects.length; i++) {
                            float pointNumber = Float.parseFloat(rects[i]);
                            rectPts[i] = pointNumber;
                        }
                        myView.drawArc(rectPts[0],rectPts[1]);
                    }
                });
        mBuilder.show();
    }

    private void inputText(){
        final EditText inputEdit = new EditText(this);
        inputEdit.setFocusable(true);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(getString(R.string.input_text_str))
                .setView(inputEdit)
                .setNegativeButton(getString(R.string.input_cancel),null)
                .setPositiveButton(getString(R.string.input_concert), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String textStr = inputEdit.getText().toString();
                        myView.drawText(textStr);
                    }
                });
        mBuilder.show();
    }
}
