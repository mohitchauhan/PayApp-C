
package com.android.pay.core.utils

import io.reactivex.Scheduler

data class AppRxSchedulers(
    val io: Scheduler,
    val computation: Scheduler,
    val main: Scheduler
)