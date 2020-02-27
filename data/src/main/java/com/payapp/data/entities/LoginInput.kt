package com.payapp.data.entities

import com.google.gson.annotations.SerializedName

data class LoginInput(@SerializedName("userName") val userName : String, @SerializedName("password")  val password : String)