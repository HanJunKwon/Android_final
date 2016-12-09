package com.example.a403.myapplication;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final int ADUALT_PAY = 15000;
    final int STUDENT_TPAY = 12000;
    final int CHILDREN_PAY = 8000;

    int discountRatio=5;
    int personCount; //총 명수 저장 변수
    int sum=0; //결제금액
    int discount =0; //할인금액

    int adult=0; // 어른 명수
    int student =0; // 청소년 수
    int children =0; // 어린이 수

    String day="";
    String time="";


    FrameLayout frame;
    LinearLayout linearLayout1,linearLayout2;
    Switch swt;
    Chronometer chrono;
    EditText et1,et2,et3;
    Button bt1,bt2,bt3,bt4;
    RadioGroup rg1,rg2;
    ImageView iv;
    CalendarView cv;
    TimePicker tp;



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
        setImageView();
        setRadioGroup();
        setTimePicker();
        setCalendarView();
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

    // 이미지 뷰 설정
    void setImageView(){
        iv =(ImageView)findViewById(R.id.imageView);
        iv.setImageResource(R.drawable.basic);
    }

    // 라디오 버튼 설정
    void setRadioGroup(){
        rg1= (RadioGroup)findViewById(R.id.radioGroup);
        rg2 =(RadioGroup)findViewById(R.id.radioGroup2);

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radioButton){
                    iv.setImageResource(R.drawable.basic);
                    discountRatio=5;
                }
                else if(checkedId==R.id.radioButton2){
                    iv.setImageResource(R.drawable.cash);
                    discountRatio=10;
                }
                else{
                    iv.setImageResource(R.drawable.membership);
                    discountRatio=20;
                }
            }
        });

        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radioButton4){
                    cv.setVisibility(View.VISIBLE);
                    tp.setVisibility(View.INVISIBLE);
                }
                else{
                    cv.setVisibility(View.INVISIBLE);
                    tp.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    // 버튼 설정
    void setButton(){
        bt1 = (Button)findViewById(R.id.button);
        bt2 = (Button)findViewById(R.id.button2);
        bt3 = (Button)findViewById(R.id.button3);
        bt4 = (Button)findViewById(R.id.button4);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et1.getText().toString().equals("") || et2.getText().toString().equals("") || et3.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),"인원을 입력하세요",Toast.LENGTH_SHORT).show();
                    return;
                }

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

    void setCalendarView(){
        cv = (CalendarView) findViewById(R.id.calendarView);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                day = year + "년" + (month + 1) + "월" + dayOfMonth + "일";
            }
        });
    }

    void setTimePicker(){
        tp = (TimePicker) findViewById(R.id.timePicker);
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                time = hourOfDay + "시" + minute + "분";
            }
        });
    }
}
