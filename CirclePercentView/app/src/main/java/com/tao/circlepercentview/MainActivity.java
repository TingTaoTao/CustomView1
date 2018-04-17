package com.tao.circlepercentview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tao.circlepercentview.view.DlsCustomDialog;

public class MainActivity extends AppCompatActivity {

    private CirclePercentView mCirclePercentView;
    private DlsCustomDialog dialog;

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
    }
}
