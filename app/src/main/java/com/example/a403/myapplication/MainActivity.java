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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    final int ADUALT_PAY = 15000;
    final int STUDENT_PAY = 12000;
    final int CHILDREN_PAY = 8000;

    boolean peopleCheck=false;

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
    TextView tv7,tv8,tv9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("놀이동산 예약 시스템");
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
        setTextView();
    }

    // 텍스트 뷰 설정
    void setTextView(){
        tv7 = (TextView)findViewById(R.id.textView7);
        tv8 = (TextView)findViewById(R.id.textView8);
        tv9 = (TextView)findViewById(R.id.textView9);
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
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.INVISIBLE);

                    et1.setText(null);
                    et2.setText(null);
                    et3.setText(null);

                    iv.setImageResource(R.drawable.basic);

                    tv7.setText("총 명수:");
                    tv7.setText("할인금액:");
                    tv9.setText("결제금액:");
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
                    peopleCheck =false;
                    Toast.makeText(getApplicationContext(),"인원을 입력하세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    peopleCheck = true; // 인원 예약 완료 됬다고 체크
                    adult = Integer.parseInt(et1.getText().toString());
                    student = Integer.parseInt(et2.getText().toString());
                    children = Integer.parseInt(et2.getText().toString());
                    sum = (adult*ADUALT_PAY)+(student*STUDENT_PAY)+(children*CHILDREN_PAY);
                    discount = sum/100*discountRatio;
                    sum = sum-discount;
                    int peoples= adult+student+children;
                    tv7.setText("총 명수:"+peoples);
                    tv8.setText("할인금액:"+discount);
                    tv9.setText("결제금액:"+sum);
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
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!peopleCheck){
                    Toast.makeText(getApplicationContext(),"인원예약을 먼저 하세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(),day+" "+time+"예약이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.INVISIBLE);
            }
        });
    }

    // 캘린더 뷰 설정
    void setCalendarView(){
        cv = (CalendarView) findViewById(R.id.calendarView);
        Date curDate = new Date(cv.getDate());

        day = Integer.toString(1900+curDate.getYear())+"-"+Integer.toString(1+curDate.getMonth())+"-"+Integer.toString(curDate.getDay());
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                day = year + "-" + (month + 1) + "-" + dayOfMonth;
            }
        });
    }

    // 타임피커 설정
    void setTimePicker(){
        tp = (TimePicker) findViewById(R.id.timePicker);
        time = Integer.toString(tp.getCurrentHour())+Integer.toString(tp.getCurrentMinute());
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                time = hourOfDay + ":" + minute;
            }
        });
    }
}
