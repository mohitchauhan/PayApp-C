
package com.android.pay.core.data.entities

sealed class State<T> {
    open fun get(): T? = null
}

class Loading<T> : State<T>()
data class Success<T>(val data: T, val responseModified: Boolean = true) : State<T>() {
    override fun get(): T = data
}

data class ErrorResult<T>(val exception: Exception, val message: String? = null) : State<T>()