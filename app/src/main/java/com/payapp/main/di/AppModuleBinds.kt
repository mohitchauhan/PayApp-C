package com.payapp.main.di

import android.app.Application
import com.android.pay.core.Logger
import com.android.pay.core.utils.PayLogger
import com.payapp.main.initializers.AppInitializer
import com.payapp.main.PayApp
import com.payapp.main.initializers.TimberInitializer
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
abstract class AppModuleBinds {

    @Binds
    abstract fun provideApplication(bind: PayApp): Application


    @Singleton
    @Binds
    abstract fun provideLogger(bind: PayLogger): Logger

    @Binds
    @IntoSet
    abstract fun provideTimberInitializer(bind: TimberInitializer): AppInitializer


}