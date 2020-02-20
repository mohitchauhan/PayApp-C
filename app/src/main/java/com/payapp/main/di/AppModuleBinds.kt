package com.payapp.main.di

import android.app.Application
import com.payapp.main.initializers.RxAndroidInitializer
import com.payapp.main.initializers.AppInitializer
import com.payapp.main.PayApp
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class AppModuleBinds {

    @Binds
    abstract fun provideApplication(bind: PayApp): Application


    @Binds
    @IntoSet
    abstract fun provideRxAndroidInitializer(bind: RxAndroidInitializer): AppInitializer


}