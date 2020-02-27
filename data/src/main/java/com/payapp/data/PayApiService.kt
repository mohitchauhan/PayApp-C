package com.payapp.data

import com.payapp.data.entities.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface PayApiService{

    @POST(" /v2/{mocked_key}")
    fun register(@Path("mocked_key") mockedKey : String, @Body inputData : SignupInput): Call<SignupResponse>


    @POST(" /v2/{mocked_key}")
    fun login(@Path("mocked_key") mockedKey : String, @Body inputData : LoginInput): Call<LoginResponse>


    @POST(" /v2/{mocked_key}")
    fun validateOtp(@Path("mocked_key") mockedKey : String, @Body inputData : OtpInput): Call<LoginResponse>

}

