package com.ways.os.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

/**
 * Created by wwzy on 16/3/14.
 */
public class ClearEditText extends EditText implements View.OnFocusChangeListener{
    private final static long DURATION_MILLIS=1000;
    private Drawable right;
    public ClearEditText(Context context){
        super(context);
        init();
    }
    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        right=getResources().getDrawable(android.R.drawable.ic_delete);
        right.setBounds(0, 0, right.getIntrinsicWidth(), right.getIntrinsicHeight());

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clearButton(s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        setOnFocusChangeListener(this);
    }

    private void clearButton(boolean isClear){
        Drawable rightDrawable=isClear?right:null;
        setCompoundDrawables(null, null, rightDrawable, null);
    }

    // n秒/counts次
    public void shakeAnimation(int counts){
        startAnimation(shakeAnimationBuilder(counts));
    }

    static Animation shakeAnimationBuilder(int counts){
       Animation animation=new TranslateAnimation(0,10,0,0);
         animation.setInterpolator(new CycleInterpolator(counts));
         animation.setDuration(DURATION_MILLIS);
        return animation;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

       if(event.getAction()==MotionEvent.ACTION_UP){
           boolean touchable=event.getX()>(getWidth()-getTotalPaddingRight())&&(event.getY()<(getWidth()-getPaddingRight()));
            if(touchable){
                setText("");
            }

       }

        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
          if(hasFocus){

            clearButton(getText().length()>0);

          }else {
              clearButton((false));
          }
    }
}
