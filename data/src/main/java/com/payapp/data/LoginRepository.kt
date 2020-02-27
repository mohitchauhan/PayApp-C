package com.payapp.data

import com.payapp.data.entities.LoginResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository @Inject constructor(val dataSource: LoginDataSource){



    fun logout() {
        dataSource.logout()
    }

    suspend fun login(username: String, password: String): Response<LoginResponse> {
        return dataSource.login(username, password)
    }

}
