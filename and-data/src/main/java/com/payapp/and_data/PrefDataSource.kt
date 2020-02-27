package com.payapp.and_data

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

const val APP_PREF = "pay_app_pref"
class PrefDataSource @Inject constructor(private val ctx : Context) {
    val appPref : SharedPreferences  by lazy {
        ctx.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE);
    }

}