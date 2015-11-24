package edu.cqut.cn.touchfeedbackbutton.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;

/**
 * Created by dun on 2015/10/30.
 */
public class TouchButton extends Button{

    private int radius = 0;
    private int FINAL_RADIUS = 400;
    private Paint mPaint;
    private int PaintColor = 0xbcbcbc;
    private int alpha = 90;

    public TouchButton(Context context) {
        this(context, null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(PaintColor);
        mPaint.setAlpha(alpha);
    }

    public TouchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(PaintColor);
        mPaint.setAlpha(alpha);
    }

    public TouchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(PaintColor);
        mPaint.setAlpha(alpha);
    }




    public void setRadius(int radius) {
        this.radius = radius;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(currentX,currentY, radius, mPaint);
    }

    private float currentX = -1;
    private float currentY = -1;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            currentX = event.getX();
            currentY = event.getY();

            FINAL_RADIUS = Math.max(getWidth(),getHeight());


            ObjectAnimator oa = ObjectAnimator.ofInt(this,"radius",0,FINAL_RADIUS);
            oa.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    setRadius(0);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            oa.setDuration(300);
            oa.setInterpolator(new AccelerateDecelerateInterpolator());
            oa.start();

        }



        return super.dispatchTouchEvent(event);
    }

}
