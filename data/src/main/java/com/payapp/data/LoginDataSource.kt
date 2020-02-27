package com.payapp.data


import com.payapp.data.entities.LoggedInUser
import com.payapp.data.entities.LoginInput
import com.payapp.data.entities.LoginResponse
import com.payapp.data.entities.OtpInput
import retrofit2.Response
import javax.inject.Inject

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource @Inject constructor(private val api : PayAppAPI) {

    suspend fun login(username: String, password: String): Response<LoginResponse> {
        return api.login(LoginInput(username, password));
    }



    fun logout() {
        // TODO: revoke authentication
    }
}

