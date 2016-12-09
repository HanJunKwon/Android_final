package com.example.a403.myapplication;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    FrameLayout frame;
    Switch swt;
    Chronometer chrono;
    EditText et1,et2,et3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frame = (FrameLayout)findViewById(R.id.frameLayout);
        setChronometer();
        setSwitch();
        setEditText();

    }
    void setChronometer(){
        chrono = (Chronometer)findViewById(R.id.chronometer);
    }
    void setSwitch(){
        swt = (Switch)findViewById(R.id.switch1);
        swt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chrono.setBase(SystemClock.elapsedRealtime());
                    chrono.start();
                    frame.setVisibility(View.VISIBLE);
                    chrono.setTextColor(Color.BLUE);
                }
                else{
                    chrono.setBase(SystemClock.elapsedRealtime());
                    chrono.stop();
                    frame.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    void setEditText(){
        et1 = (EditText)findViewById(R.id.editText);
        et2 = (EditText)findViewBu
    }

}
