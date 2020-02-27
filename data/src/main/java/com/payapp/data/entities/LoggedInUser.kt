package com.payapp.data.entities

import com.google.gson.annotations.SerializedName

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    @SerializedName("userId") val userId: String,
    @SerializedName("displayName")   val displayName: String,
    @SerializedName("picUrl")  val picUrl: String
)
