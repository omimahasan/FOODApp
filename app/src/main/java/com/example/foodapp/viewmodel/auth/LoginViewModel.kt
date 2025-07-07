package com.example.foodapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.Repository.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class LoginViewModel(private val repository: AuthRepository): ViewModel() {

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun login(email: String, password: String) {
        repository.signInWithEmail(email, password) { success, error ->
            _loginSuccess.postValue(success)
            _errorMessage.postValue(error)
        }
    }

    fun signUp(email: String, password: String) {
        repository.signUpWithEmail(email, password) { success, error ->
            _loginSuccess.postValue(success)
            _errorMessage.postValue(error)
        }
    }

    fun loginWithGoogle(account: GoogleSignInAccount) {
        repository.signInWithGoogle(account) { success, error ->
            _loginSuccess.postValue(success)
            _errorMessage.postValue(error)
        }
    }

    fun isUserLoggedIn(): Boolean = repository.isUserLoggedIn()
}
