package com.example.foodapp.viewmodel.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.Repository.OnboardingRepository

class OnboardingViewModel(private val repository: OnboardingRepository) : ViewModel() {

    private val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean> get() = _navigateToLogin

    fun onSkipClicked() {
        repository.setOnboardingFinished()
        _navigateToLogin.value = true
    }

    fun onNextClicked(currentIndex: Int): Int {
        return currentIndex + 1
    }

    fun onBackClicked(currentIndex: Int): Int {
        return currentIndex - 1
    }

    fun doneNavigating() {
        _navigateToLogin.value = false
    }
}
