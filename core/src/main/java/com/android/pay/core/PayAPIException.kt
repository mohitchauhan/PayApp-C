package com.android.pay.core

class PayAPIException(message: String?, throwable: Throwable?) : Exception(message, throwable) {
    constructor(message: String?) : this(message, null)
}