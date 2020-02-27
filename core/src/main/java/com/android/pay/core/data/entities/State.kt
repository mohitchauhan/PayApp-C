
package com.android.pay.core.data.entities

sealed class IResult<T> {
    open fun get(): T? = null
}

data class Success<T>(val data: T, val responseModified: Boolean = true) : IResult<T>() {
    override fun get(): T = data
}

data class ErrorResult<T>(val exception: Exception, val message: String? = null) : IResult<T>()