package com.payapp.main.ui.login

import com.payapp.main.di.ViewModelBuilder
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class LoginDiModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun contributeLoginActivity(): LoginActivity


}