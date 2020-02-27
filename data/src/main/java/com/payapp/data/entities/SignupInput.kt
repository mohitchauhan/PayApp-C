package com.payapp.data.entities

import com.google.gson.annotations.SerializedName

data class SignupInput(@SerializedName("userName") val userName : String, @SerializedName("password") val password : String, @SerializedName("email")  val email : String)