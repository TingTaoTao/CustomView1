package com.tao.circlepercentview.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.tao.circlepercentview.utils.DensityUtil;

/**
 * Created by jt on 2018/4/16.
 * 不同布局的dialog
 */

public class DlsCustomDialog extends Dialog {
    private Context context;
    private int height, width;
    private boolean cancelTouchout;
    private View view;
    private int gravityPosition;//出现的位置
    private int animations;//出现动画

    private DlsCustomDialog(Builder builder) {
        super(builder.context);
        context = builder.context;
        height = builder.height;
        width = builder.width;
        cancelTouchout = builder.cancelTouchout;
        view = builder.view;
    }


    private DlsCustomDialog(Builder builder, int resStyle) {
        super(builder.context, resStyle);
        context = builder.context;
        height = builder.height;
        width = builder.width;
        cancelTouchout = builder.cancelTouchout;
        view = builder.view;
        gravityPosition = builder.gravityPosition;
        animations = builder.animations;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(view);

        setCanceledOnTouchOutside(cancelTouchout);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = gravityPosition;//Gravity.CENTER
        lp.height = height;
        lp.width = width;
        win.setAttributes(lp);
        win.setWindowAnimations(animations);
    }

    public static final class Builder {

        private Context context;
        private int height, width;
        private boolean cancelTouchout;
        private View view;
        private int resStyle = -1;
        private int gravityPosition;//出现的位置
        private int animations;//出现动画

        public Builder(Context context) {
            this.context = context;
        }

        public Builder view(int resView) {
            view = LayoutInflater.from(context).inflate(resView, null);
            return this;
        }

        public Builder setGravity(int gravity) {
            gravityPosition = gravity;
            return this;
        }

        public Builder setAnimations(int anim){
            animations = anim;
            return this;
        }

        public Builder heightpx(int val) {
            height = val;
            return this;
        }

        public Builder widthpx(int val) {
            width = val;
            return this;
        }

        public Builder heightdp(int val) {
            height = DensityUtil.dip2px(context, val);
            return this;
        }

        public Builder widthdp(int val) {
            width = DensityUtil.dip2px(context, val);
            return this;
        }

        public Builder heightDimenRes(int dimenRes) {
            height = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder widthDimenRes(int dimenRes) {
            width = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        //根据布局宽度设置
        public Builder widthLayout(){
            width = context.getResources().getDisplayMetrics().widthPixels;
            return this;
        }
        //根据布局高度设置
        public Builder heightLayout(){
            height = context.getResources().getDisplayMetrics().heightPixels;
            return this;
        }

        public Builder style(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        public Builder cancelTouchout(boolean val) {
            cancelTouchout = val;
            return this;
        }

        public Builder addViewOnclick(int viewRes,View.OnClickListener listener){
            view.findViewById(viewRes).setOnClickListener(listener);
            return this;
        }


        public DlsCustomDialog build() {
            if (resStyle != -1) {
                return new DlsCustomDialog(this, resStyle);
            } else {
                return new DlsCustomDialog(this);
            }
        }
    }
}
