package com.example.foodapp.data.Repository

import android.content.Context
import androidx.core.content.edit

class OnboardingRepository(private val context: Context) {

    fun setOnboardingFinished() {
        val sharedPref = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        sharedPref.edit() { putBoolean("finished", true) }
    }
}
