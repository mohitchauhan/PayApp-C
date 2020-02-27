package com.payapp.main.ui.oauth.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.payapp.data.entities.LoggedInUser
import com.payapp.interactors.AuthInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject


class AuthViewModel @Inject constructor(private val authInteractor: AuthInteractor) : ViewModel() {
    private val _authStateLiveData = MutableLiveData<AuthState>()
    val authStateLiveData: LiveData<AuthState> = _authStateLiveData

    init {
        viewModelScope.launch {
             val state = authInteractor(AuthInteractor.Params())
            if (state == null){
                _authStateLiveData.value = ShowLogin()
            }else{
                _authStateLiveData.value = ShowHome(state)
            }
        }
    }

    fun showRegister(){
        _authStateLiveData.value = ShowRegister()
    }

    fun showLogin(){
        _authStateLiveData.value = ShowLogin()
    }

    fun showOtpVerification() {
        _authStateLiveData.value = ShowOtpVerification()
    }

    fun showHome(loggedInUser: LoggedInUser) {
        _authStateLiveData.value = ShowHome(loggedInUser)
    }


}
