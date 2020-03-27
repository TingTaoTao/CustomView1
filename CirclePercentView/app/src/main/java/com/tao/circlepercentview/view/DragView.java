package com.ruicheng.teacher.Myview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 *随意拖动的view
 */

@SuppressLint("AppCompatCustomView")
public class DragView extends ImageView {

    private int width;
    private int height;
    private int screenWidth;
    private int screenHeight;
    private Context context;
    private ClickListener clickListener;

    //是否拖动
    private boolean isDrag=false;

    public boolean isDrag() {
        return isDrag;
    }
    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=getMeasuredWidth();
        height=getMeasuredHeight();
        ViewGroup mViewGroup = (ViewGroup) getParent();
        if(null != mViewGroup){
            // 在固定区域内滑动,要得到父控件的宽高位置后再做处理
            screenWidth = mViewGroup.getWidth();
            screenHeight = mViewGroup.getHeight();
        }
        // 在整个屏幕内滑动，要得到屏幕大小
//        screenWidth= ScreenUtil.getScreenWidth(context);
//        screenHeight=ScreenUtil.getScreenHeight(context)-getStatusBarHeight();

    }
    public int getStatusBarHeight(){
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        return getResources().getDimensionPixelSize(resourceId);
    }


    private float downX;
    private float downY;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (this.isEnabled()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isDrag=false;
                    downX = event.getX();
                    downY = event.getY();
                    // 防止拖动后，页面重新渲染，view自动回到起始位置
                    bringToFront();
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.e("kid","ACTION_MOVE");
                    final float xDistance = event.getX() - downX;
                    final float yDistance = event.getY() - downY;
                    int l,r,t,b;
                    //当水平或者垂直滑动距离大于10,才算拖动事件
                    if (Math.abs(xDistance) >10 ||Math.abs(yDistance)>10) {
                        Log.e("kid","Drag");
                        isDrag=true;
                        l = (int) (getLeft() + xDistance);
                        r = l+width;
                        t = (int) (getTop() + yDistance);
                        b = t+height;
                        //不划出边界判断,此处应按照项目实际情况,因为本项目需求移动的位置是手机全屏,
                        // 所以才能这么写,如果是固定区域,要得到父控件的宽高位置后再做处理
                        if(l<0){
                            l=0;
                            r=l+width;
                        }else if(r>screenWidth){
                            r=screenWidth;
                            l=r-width;
                        }
                        if(t<0){
                            t=0;
                            b=t+height;
                        }else if(b>screenHeight){
                            b=screenHeight;
                            t=b-height;
                        }
//                        this.layout(l, t, r, b);
                        // 防止拖动后，页面重新渲染，view自动回到起始位置
                        setRelativeViewLocation(l, t, r, b);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    setPressed(false);
                    if (!isDrag){
                        if (clickListener != null){
                            clickListener.onClick();
                        }
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                    setPressed(false);
                    break;
            }
            return true;
        }
        return false;
    }

    public void setListener(ClickListener listener){
        this.clickListener = listener;
    }

    public interface ClickListener {
        void onClick();
    }

    private void setRelativeViewLocation(int left, int top, int right, int bottom) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(right - left, bottom - top);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        params.setMargins(left, top, 0, 0);
        setLayoutParams(params);
    }

}
