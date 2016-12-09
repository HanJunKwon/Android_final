package com.example.a403.myapplication;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    final int ADUALT_PAY = 15000;
    final int STUDENT_TPAY = 12000;
    final int CHILDREN_PAY = 8000;

    int personCount; //총 명수 저장 변수
    int sum=0; //결제금액
    int discount =0; //할인금액

    int adult=0; // 어른 명수
    int student =0; // 청소년 수
    int children =0; // 어린이 수


    FrameLayout frame;
    LinearLayout linearLayout1,linearLayout2;
    Switch swt;
    Chronometer chrono;
    EditText et1,et2,et3;
    Button bt1,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 프레임 선언
        frame = (FrameLayout)findViewById(R.id.frameLayout);
        linearLayout1 = (LinearLayout)findViewById(R.id.inLayout1);
        linearLayout2 = (LinearLayout)findViewById(R.id.inLayout2);

        // 컴포넌트 선언
        setChronometer();
        setSwitch();
        setEditText();
        setButton();
    }
    // 크로노미터 설정
    void setChronometer(){
        chrono = (Chronometer)findViewById(R.id.chronometer);
    }

    // 스위치 설정
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
                    chrono.setTextColor(Color.GRAY);
                }
            }
        });
    }

    // 에디트텍스트 설정
    void setEditText(){
        et1 = (EditText)findViewById(R.id.editText);
        et2 = (EditText)findViewById(R.id.editText2);
        et3 = (EditText)findViewById(R.id.editText3);
    }

    // 버튼 설정
    void setButton(){
        bt1 = (Button)findViewById(R.id.button);
        bt2 = (Button)findViewById(R.id.button2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout1.setVisibility(View.INVISIBLE);
                linearLayout2.setVisibility(View.VISIBLE);
            }
        });
    }

}
