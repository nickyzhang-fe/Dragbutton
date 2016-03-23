package com.example.cral_gates.floatingbutton;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        Button button = (Button) findViewById(R.id.btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DisplayMetrics dm = getResources().getDisplayMetrics();
        final int screenWidth = dm.widthPixels;
        final int screenHeight = dm.heightPixels - 50;

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        button.setOnTouchListener(new View.OnTouchListener() {
            int lastX, lastY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                int ea = event.getAction();
                Log.i("TAG", "Touch:" + ea);
                switch (ea) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = (int) event.getRawX();// 获取触摸事件触摸位置的原始X坐标
                        lastY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int dx = (int) event.getRawX() - lastX;
                        int dy = (int) event.getRawY() - lastY;
                        int l = v.getLeft() + dx;
                        int b = v.getBottom() + dy;
                        int r = v.getRight() + dx;
                        int t = v.getTop() + dy;
                        // 下面判断移动是否超出屏幕
                        if (l < 0) {
                            l = 0;
                            r = l + v.getWidth();
                        }
                        if (t < 0) {
                            t = 0;
                            b = t + v.getHeight();
                        }
                        if (r > screenWidth) {
                            r = screenWidth;
                            l = r - v.getWidth();
                        }
                        if (b > screenHeight) {
                            b = screenHeight;
                            t = b - v.getHeight();
                        }
                        v.layout(l, t, r, b);
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        Log.i("zhang", l + "," + t + "," + r + "," + b);
                        v.postInvalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                        Toast.makeText(MainActivity.this, "我点击了", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }
}