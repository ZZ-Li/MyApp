package com.example.administrator.myapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    private int mLastX = 0;
    private int mLastY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
        button = findViewById(R.id.button1);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mLastX = (int)motionEvent.getRawX();
                        mLastY = (int)motionEvent.getRawY();

                        return true;
                    case MotionEvent.ACTION_MOVE:
                        int deltaX = (int)(mLastX - motionEvent.getRawX());
                        int deltaY = (int)(mLastY - motionEvent.getRawY());

                        int nextX = (int)(button.getX() - deltaX);
                        int nextY = (int)(button.getY() - deltaY);

                        ObjectAnimator animatorX = ObjectAnimator.ofFloat(button, "x", button.getX(), nextX);
                        ObjectAnimator animatorY = ObjectAnimator.ofFloat(button, "y", button.getY(), nextY);
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.playTogether(animatorX,animatorY);
                        animatorSet.setDuration(0);
                        animatorSet.start();
                        textView.setText("x:" + mLastX + ",y:" + mLastY);

                        mLastX = (int)motionEvent.getRawX();
                        mLastY = (int)motionEvent.getRawY();
                    default:
                        break;
                }
                return false;
            }
        });
    }
}
