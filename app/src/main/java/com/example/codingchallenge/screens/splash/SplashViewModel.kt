package com.example.codingchallenge.screens.splash

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {
    private var countdownTimer: CountDownTimer? = null
    val remainingTime = MutableLiveData<Long>()

    private fun startCountdown() {
        countdownTimer = object : CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTime.value = millisUntilFinished / 1000
            }

            override fun onFinish() {
                remainingTime.value = 0
            }
        }
        countdownTimer?.start()
    }

    init {
        startCountdown()
    }

    override fun onCleared() {
        countdownTimer?.cancel()
        super.onCleared()
    }
}