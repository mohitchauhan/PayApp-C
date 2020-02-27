package com.payapp.interactors

import com.android.pay.core.Logger
import com.android.pay.core.PayAPIException
import com.android.pay.core.data.entities.ErrorResult
import com.android.pay.core.data.entities.State
import com.android.pay.core.data.entities.Success
import com.android.pay.core.utils.AppCoroutineDispatchers
import com.payapp.data.SignupRepository
import com.payapp.data.entities.SignupResponse
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UserSignup @Inject constructor(private val signupRepository : SignupRepository, dispatchers: AppCoroutineDispatchers, private val logger: Logger) : ResultInteractor<UserSignup.Params, State<SignupResponse>>(){
    override val dispatcher: CoroutineDispatcher = dispatchers.io

    override suspend fun doWork(params: Params): State<SignupResponse> {
        try {
            val loginResult = signupRepository.register(params.userName, params.password, params.email)
            if (loginResult.isSuccessful){
                val loginResponse = loginResult.body()
                if(loginResponse!!.status == 200){
                    return Success(loginResponse)
                }else{
                    return ErrorResult(PayAPIException(loginResult.message()), "Error occurred")
                }
            }else{
                return ErrorResult(PayAPIException("Something went wrong!!"), "Error occurred")
            }
        } catch (e: Exception) {
            logger.e(e)
            return ErrorResult( e, "Error occurred")
        }
    }

    
    data class Params(val userName: String, val password : String, val email : String)

}