package com.example.foodapp.viewmodel

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

    private val _navigateToHome = MutableLiveData<Boolean>(false)
    val navigateToHome: LiveData<Boolean> get() = _navigateToHome

    fun login(email: String, password: String) {
        repository.signInWithEmail(email, password) { success, error ->
            _loginSuccess.postValue(success)
            _errorMessage.postValue(error)
            if (success) _navigateToHome.postValue(true)
        }
    }

    fun signUp(email: String, password: String) {
        repository.signUpWithEmail(email, password) { success, error ->
            _loginSuccess.postValue(success)
            _errorMessage.postValue(error)
            if (success) _navigateToHome.postValue(true)
        }
    }

    fun loginWithGoogle(account: GoogleSignInAccount) {
        repository.signInWithGoogle(account) { success, error ->
            _loginSuccess.postValue(success)
            _errorMessage.postValue(error)
            if (success) _navigateToHome.postValue(true)
        }
    }

    fun isUserLoggedIn(): Boolean = repository.isUserLoggedIn()

    fun doneNavigating() {
        _navigateToHome.postValue(false)
    }
}
