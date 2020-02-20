package com.payapp.main.di

import com.payapp.main.PayApp
import com.payapp.main.ui.login.LoginDiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    LoginDiModule::class,
    AppModule::class,
    NetworkModule::class
])
interface AppComponent : AndroidInjector<PayApp> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: PayApp): AppComponent
    }
}