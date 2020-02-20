package com.payapp.main

import com.payapp.main.di.DaggerAppComponent
import com.payapp.main.initializers.AppInitializers
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class PayApp : DaggerApplication() {

    @Inject
    lateinit var initializers: AppInitializers


    override fun onCreate() {
        super.onCreate()
        initializers.init(this)
//        System.setProperty(IO_PARALLELISM_PROPERTY_NAME, "2")
//        System.setProperty("kotlinx.coroutines.scheduler.core.pool.size", "2")
    }


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}