package com.payapp.main.di

import android.content.Context
import com.android.pay.core.utils.AppCoroutineDispatchers
import com.android.pay.core.utils.AppRxSchedulers
import com.payapp.main.BuildConfig
import com.payapp.main.PayApp
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.rx2.asCoroutineDispatcher
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [AppModuleBinds::class])
class AppModule {

    @Provides
    fun provideContext(application: PayApp): Context = application.applicationContext



    @Singleton
    @Provides
    fun provideRxSchedulers(): AppRxSchedulers = AppRxSchedulers(
        io = Schedulers.io(),
        computation = Schedulers.computation(),
        main = AndroidSchedulers.mainThread()
    )

    @Singleton
    @Provides
    fun provideCoroutineDispatchers(schedulers: AppRxSchedulers) = AppCoroutineDispatchers(
        io = schedulers.io.asCoroutineDispatcher(),
        computation = schedulers.computation.asCoroutineDispatcher(),
        main = Dispatchers.Main
    )



    @Provides
    @Named("api-key")
    fun provideApiKey(): String = BuildConfig.API_KEY


    @Provides
    @Singleton
    @Named("cache")
    fun provideCacheDir(application: PayApp): File = application.cacheDir

}