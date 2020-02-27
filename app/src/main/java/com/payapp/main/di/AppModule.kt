package com.payapp.main.di

import android.content.Context
import com.android.pay.core.utils.AppCoroutineDispatchers
import com.google.gson.Gson
import com.payapp.main.BuildConfig
import com.payapp.main.PayApp
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [AppModuleBinds::class])
class AppModule {

    @Provides
    fun provideContext(application: PayApp): Context = application.applicationContext


    @Singleton
    @Provides
    fun provideCoroutineDispatchers() = AppCoroutineDispatchers(
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
        main = Dispatchers.Main
    )

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()


    @Provides
    @Named("api-key")
    fun provideApiKey(): String = BuildConfig.API_KEY


    @Provides
    @Singleton
    @Named("cache")
    fun provideCacheDir(application: PayApp): File = application.cacheDir

}