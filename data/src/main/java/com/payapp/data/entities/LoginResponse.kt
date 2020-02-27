package com.payapp.data.entities

import com.google.gson.annotations.SerializedName

data class LoginResponse(@SerializedName("status") val status : Int, @SerializedName("message") val message : String, @SerializedName("loggedInUser") val loggedInUser: LoggedInUser?)