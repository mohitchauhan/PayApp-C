package com.payapp.data


import com.payapp.data.entities.*
import retrofit2.Response
import javax.inject.Inject

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class SignupDataSource @Inject constructor(private val api : PayAppAPI) {

    suspend fun register(username: String, password: String, email : String): Response<SignupResponse> {
        return api.userSignup(SignupInput(username, password, email));
    }

    fun validateOtp(otp : String): Response<LoginResponse> {
        return api.validateOtp(OtpInput(otp))
    }


    fun logout() {
        // TODO: revoke authentication
    }
}

