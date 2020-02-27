package com.payapp.interactors

import com.android.pay.core.utils.AppCoroutineDispatchers
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.payapp.and_data.PrefDataSource
import com.payapp.data.entities.LoggedInUser
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

const val KEY_LOGGED_IN_USER = "l_user";
class AuthInteractor @Inject constructor(private val dataSource : PrefDataSource, private val gson: Gson, dispatchers: AppCoroutineDispatchers) : ResultInteractor<AuthInteractor.Params, LoggedInUser?>() {

    override val dispatcher: CoroutineDispatcher = dispatchers.io
    override suspend fun doWork(params: Params): LoggedInUser? {
        val userObj = dataSource.appPref.getString(KEY_LOGGED_IN_USER, null);
        if (userObj != null){
            return gson.fromJson(userObj, object : TypeToken<LoggedInUser>(){}.type)
        }else return null;
    }

    class Params

}