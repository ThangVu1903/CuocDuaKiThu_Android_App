package com.example.cuocduakithu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtDiem;
    SeekBar seekBarOne,seekBarTwo,seekBarThree;
    CheckBox cbOne,cbTwo,cbThree;
    ImageButton imgBtnPlay;
    int soDiem = 100 ; // khai báo biến toàn cụ chứa số điểm
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mapping();
        seekBarOne.setEnabled(false);
        seekBarTwo.setEnabled(false);
        seekBarThree.setEnabled(false);
        txtDiem.setText(soDiem + ""); // vừa vào app sẽ có 100 điểm

        CountDownTimer countDownTimer = new CountDownTimer(60000,300) { //truyền 2 giá trị : thời gian đếm ngược và mỗi lần lặp lại bao lâu
            @Override
            public void onTick(long l) {    // Mỗi lần 1/10s chạy
                int number = 5;
                Random random = new Random();
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three = random.nextInt(number);

                // kiểm tra win :
                if(seekBarOne.getProgress() >= seekBarOne.getMax()){
                    this.cancel();
                    imgBtnPlay.setVisibility(View.VISIBLE); // sau khi hết lượt chơi thì hiện button để chơi lượt tiếp
                    Toast.makeText(MainActivity.this,"dog win",Toast.LENGTH_SHORT).show();

                    if(cbOne.isChecked()){ // nếu được checkbox thì tăng 10 điểm
                        soDiem += 10 ;
                        cbEnable();
                        Toast.makeText(MainActivity.this,"bạn đoán chính xác",Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this,"+10",Toast.LENGTH_SHORT).show();
                    }else {  // nếu không được check hay thua thì trừ 10 điểm
                        soDiem -= 10 ;
                        Toast.makeText(MainActivity.this,"bạn đoán sai",Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this,"-10",Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem +"");
                }
                if(seekBarTwo.getProgress() >= seekBarTwo.getMax()){
                    this.cancel();
                    imgBtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"cat win",Toast.LENGTH_SHORT).show();
                    if(cbTwo.isChecked()){
                        soDiem += 10;
                        cbEnable();
                        Toast.makeText(MainActivity.this,"bạn đoán chính xác",Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this,"+10",Toast.LENGTH_SHORT).show();
                    }else{
                        soDiem -=10 ;
                        Toast.makeText(MainActivity.this,"bạn đoán sai",Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this,"-10",Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem +"");
                }
                if(seekBarThree.getProgress() >= seekBarThree.getMax()){
                    this.cancel();
                    imgBtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"bird win",Toast.LENGTH_SHORT).show();
                    if(cbThree.isChecked()){
                        soDiem += 10;
                        cbEnable();
                        Toast.makeText(MainActivity.this,"bạn đoán chính xác",Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this,"+10",Toast.LENGTH_SHORT).show();
                    }else {
                        soDiem -= 10;
                        Toast.makeText(MainActivity.this,"bạn đoán sai",Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this,"-10",Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem +"");
                }
                seekBarOne.setProgress(seekBarOne.getProgress() + one);
                seekBarTwo.setProgress(seekBarTwo.getProgress() + two);
                seekBarThree.setProgress(seekBarThree.getProgress() + three);
            }

            @Override
            public void onFinish() {    //

            }
        };
        imgBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked()){ // kiểm tra xem người chơi đã đặt cược chưa

                    seekBarOne.setProgress(0);
                    seekBarTwo.setProgress(0);
                    seekBarThree.setProgress(0); // trước khi chạy hoặc sau khi chạy xong thì reset để bắt đầu lượt chơi mới
                    imgBtnPlay.setVisibility(View.INVISIBLE); // ẩn button sau khi ấn
                    countDownTimer.start();
                    cbDisable();
                }else {
                    Toast.makeText(MainActivity.this,"vui lòng chọn đặt cược",Toast.LENGTH_SHORT).show();
                }

            }
        });

        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    // nếu chọn 1 thì bỏ 2 3
                    cbTwo.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });
        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cbOne.setChecked(false);
                cbThree.setChecked(false);

            }
        });

        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cbOne.setChecked(false);
                cbTwo.setChecked(false);
            }
        });
    }

    private void Mapping() {
        txtDiem         = findViewById(R.id.textViewDiemSo);
        seekBarOne      = findViewById(R.id.seekbarOne);
        seekBarTwo      = findViewById(R.id.seekbarTwo);
        seekBarThree    = findViewById(R.id.seekbarThree);
        cbOne           = findViewById(R.id.checkBox_1);
        cbTwo           = findViewById(R.id.checkBox_2);
        cbThree         = findViewById(R.id.checkBox_3);
        imgBtnPlay      =  findViewById(R.id.imgPlayBtn);
    }

    private  void cbEnable(){
        cbOne.setEnabled(true);
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
    }
    private  void cbDisable(){
        cbOne.setEnabled(false);
        cbTwo.setEnabled(false);
        cbThree.setEnabled(false);
    }

}