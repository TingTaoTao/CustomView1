package com.tao.circlepercentview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;

import com.tao.circlepercentview.view.DlsCustomDialog;

public class MainActivity extends AppCompatActivity {

    private CirclePercentView mCirclePercentView;
    private DlsCustomDialog dialog;
    private String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCirclePercentView = (CirclePercentView) findViewById(R.id.circlePercentView);
        mCirclePercentView.setOnCircleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float percent = (float) (Math.random() * 99 + 1);
                mCirclePercentView.setCurPercent(percent);
            }
        });

        findViewById(R.id.bt_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DlsCustomDialog.Builder builder = new DlsCustomDialog.Builder(MainActivity.this);
                dialog = builder.cancelTouchout(false)
                                .view(R.layout.dialog_custom_layout)
                                .heightDimenRes(R.dimen.dialog_height)
                                .widthDimenRes(R.dimen.dialog_width)
                                .setGravity(Gravity.CENTER)
//                        .setAnimations(R.style.myBottomDialog_Animation)
                                .style(R.style.Dialog)
                                .cancelTouchout(true)
                                .addViewOnclick(R.id.btn_cancel,new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                })
                                .build();
                dialog.show();
            }
        });

        findViewById(R.id.bt_bottom_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DlsCustomDialog.Builder builder = new DlsCustomDialog.Builder(MainActivity.this);
                dialog = builder.cancelTouchout(false)
                        .view(R.layout.dialog_buttom_layout)
                        .widthLayout()
                        .heightLayout()//根据布局高度设置不生效
                        .heightDimenRes(R.dimen.activity_vertical_margin)
                        .setGravity(Gravity.BOTTOM)
                        .setAnimations(R.style.myBottomDialog_Animation)
                        .style(R.style.myBottomDialog)
                        .cancelTouchout(true)
//                        .addViewOnclick(R.id.btn_cancel,new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                dialog.dismiss();
//                            }
//                        })
                        .build();
                dialog.show();
            }
        });

        findViewById(R.id.bt_edit_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DlsCustomDialog.Builder builder = new DlsCustomDialog.Builder(MainActivity.this);
                dialog = builder.cancelTouchout(false)
                        .view(R.layout.dialog_edit_layout)
                        .widthLayout()
                        .heightLayout()//根据布局高度设置不生效
                        .heightDimenRes(R.dimen.activity_horizontal_margin)
                        .setGravity(Gravity.BOTTOM)
                        .setAnimations(R.style.myBottomDialog_Animation)
                        .style(R.style.inputDialog)
                        .cancelTouchout(true)
                        .addViewOnclick(R.id.but,new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.i("aaaaa", "code：" + code);
//                                dialog.dismiss();
                            }
                        })
                        .addViewTextChanged(R.id.et_edit_text, new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                Log.i("aaaaa", "before：" + i +"," + i1+","+i2+ ">" + charSequence);
                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                Log.i("aaaaa", "onTextChanged：" + i +"," + i1+","+i2+ ">" + charSequence);
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                                Log.i("aaaaa", "after：" + editable);
                                code = editable.toString();
                            }
                        })
                        .build();
                dialog.show();
            }
        });

    }
}
