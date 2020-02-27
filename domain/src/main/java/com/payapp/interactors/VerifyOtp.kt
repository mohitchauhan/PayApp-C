package com.payapp.interactors

import com.android.pay.core.PayAPIException
import com.android.pay.core.data.entities.ErrorResult
import com.android.pay.core.data.entities.State
import com.android.pay.core.data.entities.Success
import com.android.pay.core.utils.AppCoroutineDispatchers
import com.google.gson.Gson
import com.payapp.and_data.PrefDataSource
import com.payapp.data.SignupRepository
import com.payapp.data.entities.LoginResponse
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class VerifyOtp @Inject constructor(private val signupRepository : SignupRepository, private val prefDataSource : PrefDataSource, private val gson: Gson,
                                    dispatchers: AppCoroutineDispatchers) : ResultInteractor<VerifyOtp.Params, State<LoginResponse>>(){
    override val dispatcher: CoroutineDispatcher = dispatchers.io

    override suspend fun doWork(params: Params): State<LoginResponse> {
        try {
            val otpResult = signupRepository.validateOtp(params.otp)
            if (otpResult.isSuccessful){
                val loginResponse = otpResult.body()
                if(loginResponse!!.status == 200){
                    prefDataSource.appPref.edit().putString(KEY_LOGGED_IN_USER, gson.toJson(loginResponse.loggedInUser!!).toString()).apply()
                    return Success<LoginResponse>(loginResponse);
                }else{
                    return ErrorResult(PayAPIException(otpResult.message()), "Error occurred")
                }
            }else{
                return ErrorResult(PayAPIException("Something went wrong!!"), "Error occurred")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return ErrorResult(PayAPIException("", e), "Error occurred")
        }
    }

    
    data class Params(val otp: String)

}