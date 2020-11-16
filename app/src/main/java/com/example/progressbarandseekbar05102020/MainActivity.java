package com.example.progressbarandseekbar05102020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.progressbarandseekbar05102020.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{


    ActivityMainBinding mBinding;
    CountDownTimer mCountTimer;
    Random mRandom;
    Boolean mIsFinish = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        // Task 1 : Xử lý việc chạy random cho seekbar
        // Task 2 : Kiểm tra con nào về nhất , các con khác sẽ dừng lại
        // Task 3 : Chỉ được chọn 1 trong 3 con đặt cược
        // Task 4 : Cộng điểm khi chiến thắng vào báo khi thua cuộc

        init();
        event();
    }

    private void event() {
        validCheckbox();
        mBinding.imageviewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBinding.checkboxOne.isChecked() || mBinding.checkboxTwo.isChecked() || mBinding.checkboxThree.isChecked()){
                    randomSeekbar();
                }else{
                    Toast.makeText(MainActivity.this, "Bạn chưa đặt cược", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void validCheckbox() {
        mBinding.checkboxOne.setOnCheckedChangeListener(this);
        mBinding.checkboxTwo.setOnCheckedChangeListener(this);
        mBinding.checkboxThree.setOnCheckedChangeListener(this);
    }

    private void init() {
        mRandom = new Random();
    }

    private void randomSeekbar() {
        if (mIsFinish){
            mIsFinish = false;
            disableView(mBinding.seekbarOne);
            disableView(mBinding.seekbarTwo);
            disableView(mBinding.seekbarThree);
            disableView(mBinding.checkboxOne);
            disableView(mBinding.checkboxTwo);
            disableView(mBinding.checkboxThree);
            mCountTimer = new CountDownTimer(1100 , 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if (millisUntilFinished / 1000 > 0){
                        if (mBinding.seekbarOne.getProgress() >= 100){
                            this.cancel();
                            enableView(mBinding.seekbarOne);
                            enableView(mBinding.seekbarTwo);
                            enableView(mBinding.seekbarThree);
                            mIsFinish = true;
                        } else if (mBinding.seekbarTwo.getProgress() >= 100){
                            this.cancel();
                            enableView(mBinding.seekbarOne);
                            enableView(mBinding.seekbarTwo);
                            enableView(mBinding.seekbarThree);
                            mIsFinish = true;
                        } else if (mBinding.seekbarThree.getProgress() >= 100){
                            this.cancel();
                            enableView(mBinding.seekbarOne);
                            enableView(mBinding.seekbarTwo);
                            enableView(mBinding.seekbarThree);
                            mIsFinish = true;
                        }else{
                            mBinding.seekbarOne.setProgress(mBinding.seekbarOne.getProgress() + mRandom.nextInt(10));
                            mBinding.seekbarTwo.setProgress(mBinding.seekbarTwo.getProgress() + mRandom.nextInt(10));
                            mBinding.seekbarThree.setProgress(mBinding.seekbarThree.getProgress() + mRandom.nextInt(10));
                        }
                    }
                }

                @Override
                public void onFinish() {
                    mCountTimer.start();
                }
            };
            mCountTimer.start();
        }

    }

    @Override
    protected void onStop() {
        mCountTimer.cancel();
        super.onStop();
    }
    private void enableView(View view){
        view.setEnabled(true);
    }
    private void disableView(View view){
        view.setEnabled(false);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.checkboxOne :
                if (isChecked){
                    mBinding.checkboxTwo.setChecked(false);
                    mBinding.checkboxThree.setChecked(false);
                }
                break;
            case R.id.checkboxTwo :
                if (isChecked){
                    mBinding.checkboxOne.setChecked(false);
                    mBinding.checkboxThree.setChecked(false);
                }
                break;
            case R.id.checkboxThree :
                if (isChecked){
                    mBinding.checkboxOne.setChecked(false);
                    mBinding.checkboxTwo.setChecked(false);
                }
                break;
        }
    }
}