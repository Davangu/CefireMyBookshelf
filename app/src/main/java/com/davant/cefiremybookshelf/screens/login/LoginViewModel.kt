package com.davant.cefiremybookshelf.screens.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _password = MutableLiveData<String>()
    val password:LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable


    fun onLoginChange(userName:String, password:String) {
        _userName.value = userName
        _password.value = password
        _isLoginEnable.value = enableLogin(userName, password)
    }

    private fun enableLogin(userName: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(userName).matches()
            && password.length > 7
}