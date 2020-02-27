package com.payapp.data

import com.payapp.data.entities.LoginResponse
import com.payapp.data.entities.SignupResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class SignupRepository @Inject constructor(val dataSource: SignupDataSource){

    fun logout() {
        dataSource.logout()
    }


    suspend fun validateOtp(otp: String): Response<LoginResponse> {
        return dataSource.validateOtp(otp)
    }

    suspend fun register(username: String, password: String, email : String): Response<SignupResponse> {
        return dataSource.register(username, password, email)
    }

}
