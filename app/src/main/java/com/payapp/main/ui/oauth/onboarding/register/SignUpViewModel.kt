package com.payapp.main.ui.oauth.onboarding.register

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.pay.core.data.entities.Loading
import com.android.pay.core.data.entities.State
import com.payapp.data.entities.SignupResponse
import com.payapp.interactors.UserSignup
import com.payapp.main.R
import com.payapp.main.ui.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private val userLogin: UserSignup) : BaseViewModel() {

    private val _signupForm = MutableLiveData<SignupFormState>()
    val signupFormState: LiveData<SignupFormState> = _signupForm

    private val _signupResult = MutableLiveData<State<SignupResponse>>()
    val loginState: LiveData<State<SignupResponse>> = _signupResult

    fun register(username: String, password: String, email : String) {
        _signupResult.value = Loading()
        viewModelScope.launch {
            val result = userLogin(UserSignup.Params(username, password, email))
            _signupResult.value = result
        }
    }

    fun signUpDataChanged(
        phNumber: String,
        password: String,
        email: String,
        checked: Boolean
    ) {
        if (!isPhoneNumberValid(phNumber)) {
            _signupForm.value = SignupFormState(usernameError = R.string.invalid_number)
        } else if (!isPasswordValid(password)) {
            _signupForm.value = SignupFormState(passwordError = R.string.invalid_password)
        } else if (!isEmailValid(email)) {
            _signupForm.value = SignupFormState(passwordError = R.string.invalid_email)
        }else if(!checked){
            _signupForm.value = SignupFormState(tncError = R.string.accept_tnc)
        } else {
            _signupForm.value = SignupFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isPhoneNumberValid(username: String): Boolean {
        return username.isNotBlank() && username.length >= 10 //TODO  Should use phone libs for Phone number validation
    }


    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }



    private fun isEmailValid(email: String): Boolean {
        return if (email.isNotBlank() && email.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            return true
        }
    }

}
