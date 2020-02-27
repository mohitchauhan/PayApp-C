package com.payapp.main.ui.oauth.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.pay.core.data.entities.Loading
import com.android.pay.core.data.entities.State
import com.payapp.data.entities.LoginResponse
import com.payapp.interactors.VerifyOtp
import com.payapp.main.ui.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class OtpViewModel @Inject constructor(private val verifyOtp: VerifyOtp) : BaseViewModel() {
    private val _otpState = MutableLiveData<OtpViewState>()
    val otpState : LiveData<OtpViewState> = _otpState


    private val _validateOtpResult = MutableLiveData<State<LoginResponse>>()
    val validateOtpState: LiveData<State<LoginResponse>> = _validateOtpResult


    fun onOtpInputComplete(){
        _otpState.value = OtpViewState(true)
    }

    fun validateOtp(otp : String) {
        _validateOtpResult.value = Loading()
        viewModelScope.launch {
            val result = verifyOtp.invoke(VerifyOtp.Params(otp))
            _validateOtpResult.value = result
        }
    }
}