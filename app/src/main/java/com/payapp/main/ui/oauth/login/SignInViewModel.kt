package com.payapp.main.ui.oauth.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.pay.core.data.entities.Loading
import com.android.pay.core.data.entities.State
import com.payapp.data.entities.LoginResponse
import com.payapp.interactors.UserLogin
import com.payapp.main.R
import com.payapp.main.ui.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val userLogin: UserLogin) : BaseViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<State<LoginResponse>>()
    val loginState: LiveData<State<LoginResponse>> = _loginResult

    fun login(username: String, password: String) {
        _loginResult.value = Loading()
        viewModelScope.launch {
            val result = userLogin(UserLogin.Params(username, password))
            _loginResult.value = result
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value =
                LoginFormState(usernameError = R.string.invalid_number)
        } else if (!isPasswordValid(password)) {
            _loginForm.value =
                LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value =
                LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

}
