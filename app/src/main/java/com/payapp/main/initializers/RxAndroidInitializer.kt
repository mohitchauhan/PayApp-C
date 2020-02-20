
package com.payapp.main.initializers

import android.app.Application
import android.os.Looper
import com.payapp.main.initializers.AppInitializer
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class RxAndroidInitializer @Inject constructor() : AppInitializer {
    override fun init(application: Application) {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            AndroidSchedulers.from(Looper.getMainLooper(), true)
        }
    }
}