package com.example.progressbarandseekbar05102020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.progressbarandseekbar05102020.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding mBinding;
    CountDownTimer mCountTimer;
    Random mRandom;
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
        randomSeekbar();
    }

    private void init() {
        mRandom = new Random();
    }

    private void randomSeekbar() {
        mCountTimer = new CountDownTimer(1100 , 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished / 1000 > 0){
                    mBinding.seekbarOne.setProgress(mBinding.seekbarOne.getProgress() + mRandom.nextInt(10));
                    mBinding.seekbarTwo.setProgress(mBinding.seekbarTwo.getProgress() + mRandom.nextInt(10));
                    mBinding.seekbarThree.setProgress(mBinding.seekbarThree.getProgress() + mRandom.nextInt(10));
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