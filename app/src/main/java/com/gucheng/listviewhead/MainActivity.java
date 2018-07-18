package com.gucheng.listviewhead;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private ListView mListView;
    private View mHeadView;
    private int mHeadHeight;
    float lastMoveY = 0,moveY = 0,deltaY = 0;
    private int currentPadding = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView)findViewById(R.id.list_view);
        ArrayList<String> datas = new ArrayList<String>();
        for (int i=0;i<10;i++) {
            datas.add("item" + (i+1));
        }
        ListAdapter listAdapter = new ListAdapter(MainActivity.this, datas);
        mListView.setAdapter(listAdapter);
        mListView.setOnTouchListener(this);
        initHeadView();
    }

    private void initHeadView() {
        mHeadView = LayoutInflater.from(this).inflate(R.layout.header_view, null);
        mHeadView.measure(0,0);
        mHeadHeight = mHeadView.getMeasuredHeight();
        mHeadView.setPadding(0, -mHeadHeight,0,0);
        currentPadding = -mHeadHeight;
        mListView.addHeaderView(mHeadView);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastMoveY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = event.getY();
                deltaY = moveY - lastMoveY;
                currentPadding += deltaY;
                Log.d("liuwei", "currentPadding is " + currentPadding);
                if (deltaY > 0 ) {
                    mHeadView.setPadding(0, currentPadding, 0, 0);
                } else {
                    mHeadView.setPadding(0, currentPadding, 0, 0);
                }
                lastMoveY = moveY;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
